package dat.backend.model.persistence;

import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;

public class ItemListFacade {

    public static CompleteProduct getCompletProduct(Orders order, ConnectionPool connectionPool) throws DatabaseException {

        return ItemListMapper.getCompletProduct(order, connectionPool);
    }

    public static void addItemList(ItemEntry itemEntry, ConnectionPool connectionPool) throws DatabaseException {
        addItemList(itemEntry, connectionPool);
    }

}
