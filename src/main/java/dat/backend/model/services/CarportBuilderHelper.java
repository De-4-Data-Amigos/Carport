package dat.backend.model.services;


import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;

import java.util.List;

public class CarportBuilderHelper {

//TODO her skal vi generate vores stykliste.

    public static List<CompleteProduct> generateItemList(int width, int length, Orders order) {

        return null;
    }

    public static float startPrice(int width, int length){
        /*  - Vi skal beregne en pris ud fra længde x bredde, som bliver en "start-pris"
            - .... før kunden rammer salgsteamet for en endelig pris */

        int CustomerChosenArea = width * length;
        float price = (float) (CustomerChosenArea * 0.08);

        return price;
    }
}
