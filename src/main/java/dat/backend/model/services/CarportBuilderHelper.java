package dat.backend.model.services;


import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.ProductVariant;

import java.util.ArrayList;
import java.util.List;

public class CarportBuilderHelper {

//TODO her skal vi generate vores stykliste.

    public static List<CompleteProduct> generateItemList(int width, int length, Orders order) {


        return null;
    }

//Stolpe: Alt over 5 x 4,80 m skal der bruges 6 stolper, alt under er det skal der bruges 4 stk.
    private static List<ItemEntry> getPostAmount(int width, int length, int orderId, List<ProductVariant> productVariants) {
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
        for (int i = 0; i < amount; i++){
            itemEntryList.add(new ItemEntry(orderId,postProductVariantId ));
        }
        return  itemEntryList;
    }

    //Rem: Alt over 6 m længde skal der bruges 4 rem, alt under er det skal der bruges 2 stk.
    private static List<ItemEntry> getStrapAmount(int length, int orderId, List<ProductVariant> productVariants) {
        int amount = 2;
        int lengthTmp = length;

        while (lengthTmp > 600) {
            amount += 2;
            lengthTmp -= 600;
        }

        int strapProductVariantId = 9;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            itemEntryList.add(new ItemEntry(orderId,strapProductVariantId));
        }

          return  itemEntryList;
    }

    //Stern; foran og bagved. Alt under 360 cm skal der bruges 4 (2 stk foran 2 stk bagved), alt over er det skal der plusses med 1.

    private static List<ItemEntry> getSternFrontAndBackAmount(int width, int orderId, List<ProductVariant> productVariants) {
        int amount = 4;
        int widthTmp= width;

        while (widthTmp > 360) {
            amount += 1;
            widthTmp -= 360;
        }

        int sternProductVariantId = 1;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            itemEntryList.add(new ItemEntry(orderId,sternProductVariantId));
        }

        return  itemEntryList;
    }


    //Siderne sættes sammen (540cm), og antallet bregnes udefra dette. Vi ganger det med 2 og divider med max længde stern.

    private static List<ItemEntry> getSideSternAmount(int length, int orderId, List<ProductVariant> productVariants) {
        int amount = 2;
        int lengthTmp = length * 2;

        while (lengthTmp > 540) {
            amount += 1;
            lengthTmp -= 540;
        }

        int sternProductVariantId = 3;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            itemEntryList.add(new ItemEntry(orderId,sternProductVariantId));
        }

        return  itemEntryList;
    }

//Spær: 0,59 x 2 – længde af carporten og dividere med 0,59 så får vi det antal stk vi skal bruge.
// Bredde <= 300 kan vi få to ud af det.
    private static List<ItemEntry> getRaftersAmount(int width, int length, int orderId, List<ProductVariant> productVariants) {
        int amount = 0;
        int lengthTmp = length - 2 * 55;
        int raftersLength = 0;
        int amountTmp = (int)(600f/width);
        while (raftersLength < lengthTmp) {
          raftersLength += amountTmp * 55;
          amount++;
        }

        int raftersProductVariantId = 10;
        List<ItemEntry> itemEntryList = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            itemEntryList.add(new ItemEntry(orderId,raftersProductVariantId));
        }

        return  itemEntryList;
    }


    public static float startPrice(int width, int length){
        /*  - Vi skal beregne en pris ud fra længde x bredde, som bliver en "start-pris"
            - .... før kunden rammer salgsteamet for en endelig pris */

        int CustomerChosenArea = width * length;
        float price = (float) (CustomerChosenArea * 0.08);

        return price;
    }
}
