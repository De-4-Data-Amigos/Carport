package dat.backend.model.persistence;

import dat.backend.model.entities.Product;
import dat.backend.model.entities.ProductType;
import dat.backend.model.entities.Unit;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    static List<Product> getAllProducts(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM carport.product as p INNER JOIN carport.product_variant as pv on pv.product_id = p.id;";

        List<Product> productList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String unitString = rs.getString("unit");
                    Unit unit = Unit.valueOf(unitString);
                    float pricePrUnit = rs.getFloat("price_pr_unit");
                    String typeString = rs.getString("type");
                    ProductType type = ProductType.valueOf(typeString);
                    int id = rs.getInt("id");
                    int productId = rs.getInt("product_id");
                    float length = rs.getFloat("length");
                    float width = rs.getFloat("width");

                    Product product = new Product(name, description, unit, pricePrUnit, type, id, productId, length, width);
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return productList;
    }
}
