package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    static List<ProductAndProductVariant> getAllProducts(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM product as p INNER JOIN product_variant as pv on pv.product_id = p.id;";

        List<ProductAndProductVariant> productList = new ArrayList<>();

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
                    float height = rs.getFloat("height");

                    ProductAndProductVariant product = new ProductAndProductVariant(name, description, unit, pricePrUnit, type, id, productId, length, width, height);
                    productList.add(product);

                    //Vi bruger getString()-metoden til at konvertere enumværdien til en streng, ved linje 28-29 og 34-35.
                    // da Resultset ikke virker direkte med en konvertering af enumværdier til Java-enum typer.
                    // getObject()-metoden bruges normalt til at hente værdier af primitiv datatype,
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return productList;
    }

    static ProductAndProductVariant getProduct(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM product as p INNER JOIN product_variant as pv on pv.product_id = p.id WHERE p.id = ?;";

        ProductAndProductVariant product = null;

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
                    int productId = rs.getInt("product_id");
                    float length = rs.getFloat("length");
                    float width = rs.getFloat("width");
                    float height = rs.getFloat("height");

                    product = new ProductAndProductVariant(name, description, unit, pricePrUnit, type, id, productId, length, width, height);


                    //Vi bruger getString()-metoden til at konvertere enumværdien til en streng, ved linje 28-29 og 34-35.
                    // da Resultset ikke virker direkte med en konvertering af enumværdier til Java-enum typer.
                    // getObject()-metoden bruges normalt til at hente værdier af primitiv datatype,
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return product;
    }

    static void removeProductList(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM product WHERE id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }

    static void removeProductVariant(int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM product_variant WHERE product_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }

    public static void editProduct(String name, int id, String description, Unit unit, float pricePerUnit, ProductType type, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE carport.product SET name = ?, description = ?, unit = ?, price_pr_unit = ?, type = ? WHERE id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setString(3, String.valueOf(unit));
                ps.setFloat(4, pricePerUnit);
                ps.setString(5, String.valueOf(type));
                ps.setInt(6, id);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }

    public static void editProductVariant(float height, float width, float length, int productId, int id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE carport.product_variant set height = ?, width = ?, length = ? WHERE product_id = ? AND id = ?;";

        try(Connection connection = connectionPool.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setFloat(1, height);
                ps.setFloat(2, width);
                ps.setFloat(3, length);
                ps.setInt(4, productId);
                ps.setInt(5, id);

                ps.executeUpdate();
            }
        }catch (SQLException e){
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }
}
