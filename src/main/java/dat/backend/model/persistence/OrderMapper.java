package dat.backend.model.persistence;

import dat.backend.model.entities.Orders;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {


    static int createOrder(Orders order, ConnectionPool connectionPool) throws DatabaseException {
       String sql = "INSERT INTO orders (user_id, price, length, width) values (?,?,?,?);";
        int orderId = 0;
        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getUser_id());
                ps.setFloat(2, order.getPrice());
                ps.setInt(3, order.getLength());
                ps.setInt(3, order.getWidth());

                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert item into database");
        }
        return orderId;
    }


    static List<Orders> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from orders;";

        List<Orders> orderList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int user_id = rs.getInt("user_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    float price = rs.getFloat("price");
                    int length = rs.getInt("length");
                   int width = rs.getInt("width");


                    Orders order = new Orders(id, user_id, timestamp, price, length, width);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return orderList;
    }



    static void deleteOrders(int Id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orders WHERE id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, Id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }

}
