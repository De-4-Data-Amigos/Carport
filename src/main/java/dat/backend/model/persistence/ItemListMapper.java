package dat.backend.model.persistence;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import java.sql.*;

public class ItemListMapper {


    static void ItemList(ItemEntry itemEntry, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO itemlist (order_id, product_variant_id) values (?,?);";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,itemEntry.getOrderId());
                ps.setInt(2, itemEntry.getProductVariantId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0)
                {
                    throw new DatabaseException("ItemEntry could not be inserted into the database");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }

    }

}
