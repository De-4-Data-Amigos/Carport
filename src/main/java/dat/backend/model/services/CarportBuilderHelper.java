package dat.backend.model.services;


import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.ProductVariant;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.ItemListMapper;

import java.util.ArrayList;
import java.util.List;

public class CarportBuilderHelper {
    private static float price = 0;


    public static List<CompleteProduct> generateItemList(int width, int length, Orders order) throws DatabaseException {
        price = 0;
        ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
        List<ItemEntry> itemEntryList = new ArrayList<>();
        List<ItemEntry> posts = getPostAmount(width, length, order.getId());
        List<ItemEntry> straps = getStrapAmount(length, order.getId());
        itemEntryList.addAll(getSternFrontAndBackAmount(width,order.getId()));
        itemEntryList.addAll(getSideSternAmount(length,order.getId()));
        List<ItemEntry> rafters = getRaftersAmount(width, length, order.getId());
        itemEntryList.addAll(getRoofAmount(width, length, order.getId()));
        itemEntryList.addAll(getScrewsAmount(order.getId()));
        itemEntryList.addAll(getBracketAmount(rafters.size(),order.getId()));
        itemEntryList.addAll(getBracketScrewsAmount(rafters.size(), order.getId()));
        itemEntryList.addAll(getHollowtiesAmount(width, length, order.getId()));
        itemEntryList.addAll(getPostBoltAmount(posts.size(), straps.size(),order.getId()));
        itemEntryList.addAll(posts);
        itemEntryList.addAll(straps);
        itemEntryList.addAll(rafters);
        order.setDbPrice(price, connectionPool);

        for (ItemEntry items : itemEntryList) {
            ItemListFacade.addItemList(items,connectionPool);
        }
        List<CompleteProduct> completeProducts = ItemListFacade.getCompletProduct(order, connectionPool);

        return completeProducts;
    }

    //Stolpe: Alt over 5 x 4,80 m skal der bruges 6 stolper, alt under er det skal der bruges 4 stk.
    private static List<ItemEntry> getPostAmount(int width, int length, int orderId) {
        float pricePrUnit = 0.58f;
        int amount = 4;
        int widthTmp = width;
        int lengthTmp = length;

        while (widthTmp > 500 && lengthTmp > 480) {
            amount += 2;
            widthTmp -= 500;
            lengthTmp -= 480;
        }
        // This is the post in the database that we want to use, h:9.7 cm, w:9.7 cm
        int postProductVariantId = 7;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, postProductVariantId));
            price += pricePrUnit * 300;
        }
        return itemEntryList;
    }

    //Rem: Alt over 6 m længde skal der bruges 4 rem, alt under er det skal der bruges 2 stk.
    private static List<ItemEntry> getStrapAmount(int length, int orderId) {
        float pricePrUnit = 0.37f;

        int amount = 2;
        int lengthTmp = length;

        while (lengthTmp > 600) {
            amount += 2;
            lengthTmp -= 600;
        }

        int strapProductVariantId = 9;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, strapProductVariantId));
            price += pricePrUnit * 600;
        }

        return itemEntryList;
    }

    //Stern; foran og bagved. Alt under 360 cm skal der bruges 4 (2 stk foran 2 stk bagved), alt over er det skal der plusses med 1.

    private static List<ItemEntry> getSternFrontAndBackAmount(int width, int orderId) {
        float pricePrUnit = 0.37f;
        int amount = 4;
        int widthTmp = width;

        while (widthTmp > 360) {
            amount += 1;
            widthTmp -= 360;
        }

        int sternProductVariantId = 1;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, sternProductVariantId));
            price += pricePrUnit * 360;
        }

        return itemEntryList;
    }


    //Siderne sættes sammen (540cm), og antallet bregnes udefra dette. Vi ganger det med 2 og divider med max længde stern.

    private static List<ItemEntry> getSideSternAmount(int length, int orderId) {
        float pricePrUnit = 0.37f;
        int amount = 2;
        int lengthTmp = length * 2;

        while (lengthTmp > 540) {
            amount += 1;
            lengthTmp -= 540;
        }

        int sternProductVariantId = 3;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, sternProductVariantId));
            price += pricePrUnit * 540;
        }

        return itemEntryList;
    }

    //Spær: 0,59 x 2 – længde af carporten og dividere med 0,59 så får vi det antal stk vi skal bruge.
// Bredde <= 300 kan vi få to ud af det.
    private static List<ItemEntry> getRaftersAmount(int width, int length, int orderId) {
        float pricePrUnit = 0.28f;
        int amount = 0;
        int lengthTmp = length - 2 * 60;
        int raftersLength = 0;

        int amountTmp = (int) (600f / width);

        while (raftersLength < lengthTmp) {
            raftersLength += amountTmp * 60;
            amount++;
        }

        int raftersProductVariantId = 10;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, raftersProductVariantId));
            price += pricePrUnit * 600;
        }

        return itemEntryList;
    }

    private static float getRafterDistance(int length, int amount) {
        return (float) Math.round((double) ((float) length / (amount + 2) * 10) / 10);
    }
//tag
    private static List<ItemEntry> getRoofAmount(int width, int length, int orderId) {
        float pricePrUnit = 0.57f;
        int pricePrUnitScrew = 429;

        int amount = 0;
        int widthAmount = (int) Math.ceil(width / 109f);

        int smallVarAmount = (int) Math.ceil(length / 240f);
        int largeVarAmount = (int) Math.ceil(length / 600f);

        int smallVarWaste = smallVarAmount * 240 - length;
        int largeVarWaste = largeVarAmount * 600 - length;
        int roofScrewAmount = 0;

        List<ItemEntry> itemEntryList = new ArrayList<>();
        if (smallVarWaste < largeVarWaste) {
            int roofProductVariantId = 15;
            for (int i = 0; i < smallVarAmount * widthAmount; i++) {
                itemEntryList.add(new ItemEntry(orderId, roofProductVariantId));
                price += pricePrUnit * 240;
                roofScrewAmount++;
            }

        } else {
            int roofProductVariantId = 16;
            if (largeVarWaste < 0) {
                itemEntryList.add(new ItemEntry(orderId, roofProductVariantId));
                roofScrewAmount += 2;
                int extraOfSmallVar = (int) Math.max(Math.ceil((length - 600f) / 240f), 0);
                price += pricePrUnit * 600;
                for (int i = 0; i < extraOfSmallVar; i++) {
                    itemEntryList.add(new ItemEntry(orderId, 15));
                    price += pricePrUnit * 240;
                    roofScrewAmount++;
                }
            } else {

                for (int i = 0; i < largeVarAmount * widthAmount; i++) {
                    itemEntryList.add(new ItemEntry(orderId, roofProductVariantId));
                    price += pricePrUnit * 600;
                    roofScrewAmount += 2;

                }
            }
        }
        for (int i = 0; i < roofScrewAmount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 17));
            price += pricePrUnitScrew;

        }
        return itemEntryList;
    }

    //bræddebolt og firkantskiver
    private static List<ItemEntry> getPostBoltAmount(int postAmount, int strapAmount, int orderId) {
        int pricePrUnitBolt = 15;
        float pricePrUnitSquareWashers = 8.8f;


        int amount = postAmount * strapAmount;

        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 14));
            itemEntryList.add(new ItemEntry(orderId, 19));
            price += pricePrUnitBolt + pricePrUnitSquareWashers;
        }
        return itemEntryList;
    }

    //beslag til højre og venstre
    private static List<ItemEntry> getBracketAmount(int raftersAmount, int orderId) {
        int pricePrUnit =  49;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < raftersAmount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 11));
            itemEntryList.add(new ItemEntry(orderId, 18));
            price += pricePrUnit * 2;
        }
        return itemEntryList;
    }

    //skruer
    private static List<ItemEntry> getScrewsAmount(int orderId) {
        float pricePrUnit = 259;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        itemEntryList.add(new ItemEntry(orderId, 13));
        price += pricePrUnit;
        return itemEntryList;
    }


    //hulbånd
    private static List<ItemEntry> getHollowtiesAmount(int width, int length, int orderId) {
        int pricePrUnit = 349;
        int amount = 1;
        int triangleWidth = (width - 30);
        int triangleLength = length;
        float hypotenuse = (float) Math.sqrt((triangleWidth ^ 2 + triangleLength ^ 2));
        amount = (int) Math.ceil(hypotenuse * 2 / 1000);

        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 20));
            price += pricePrUnit;
        }
        return itemEntryList;
    }


    //beslagskruer
    private static List<ItemEntry> getBracketScrewsAmount(int raftersAmount, int orderId) {
        int pricePrUnit = 349;
        int amount = raftersAmount / 5;

        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            itemEntryList.add(new ItemEntry(orderId, 21));
            price += pricePrUnit;
        }
        return itemEntryList;
    }


    public static float startPrice(int width, int length) {
        /*  - Vi skal beregne en pris ud fra længde x bredde, som bliver en "start-pris"
            - .... før kunden rammer salgsteamet for en endelig pris */
        int CustomerChosenArea = width * length;
        float price = (float) (CustomerChosenArea * 0.08);

        return price;
    }

}