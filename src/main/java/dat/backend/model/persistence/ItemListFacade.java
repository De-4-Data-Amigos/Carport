package dat.backend.model.persistence;

import dat.backend.model.entities.CompleteProduct;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class ItemListFacade {

    public static List<CompleteProduct> getCompletProduct(Orders order, ConnectionPool connectionPool) throws DatabaseException {

        return ItemListMapper.getCompletProduct(order, connectionPool);
    }

    public static void addItemList(ItemEntry itemEntry, ConnectionPool connectionPool) throws DatabaseException {
        addItemList(itemEntry, connectionPool);
    }

}
