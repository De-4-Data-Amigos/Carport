package dat.backend.model.persistence;

import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.util.List;

public class OrderFacade {



    public static int createOrder(Orders orders, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.createOrder(orders, connectionPool);
    }

    public static List<Orders> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {

        return OrderMapper.getAllOrders(connectionPool);
    }
    public static void deleteOrders(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.deleteOrders(orderId, connectionPool);
    }

    public static void setPrice(int id, float price, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.setPrice(id, price, connectionPool);


    }
}
