package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;

public class ItemListMapper {


    static void addItemList(ItemEntry itemEntry, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO itemlist (order_id, product_variant_id) values (?,?);";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, itemEntry.getOrderId());
                ps.setInt(2, itemEntry.getProductVariantId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new DatabaseException("ItemEntry could not be inserted into the database");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }


    }

    static void addItemList(int orderId, int productVariantId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO itemlist (order_id, product_variant_id) values (?,?);";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, productVariantId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new DatabaseException("ItemEntry could not be inserted into the database");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }

    }

    static CompleteProduct getCompletProduct(Orders order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT il.id, order_id, product_variant_id, product_id, heigth, width, length, product.name," +
                " description, price_pr_unit, unit, type, count(*) as amount FROM carport.itemlist as il " +
                "INNER JOIN carport.product_variant as pvar on il.product_variant_id = pvar.id " +
                "NNER JOIN carport.product as product on pvar.product_id = product.id where order_id = ? group by product_variant_id";
        CompleteProduct completeProduct = null;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order.getId());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int order_id = rs.getInt("order_id");
                    int product_variant_id = rs.getInt("product_variant_id");
                    int product_id = rs.getInt("product_id");
                    float height = rs.getFloat("height");
                    float width = rs.getFloat("width");
                    float length = rs.getFloat("length");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price_pr_unit = rs.getFloat("price_pr_unit");
                    Unit unit = rs.getObject("unit", Unit.class);
                    ProductType type = rs.getObject("type", ProductType.class);
                    int amount = rs.getInt("amount");

                    completeProduct = new CompleteProduct(name, length, amount, unit, description);

                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return completeProduct;
    }


    static void removeItemListOrderId(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM carport.itemlist WHERE order_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }

}
