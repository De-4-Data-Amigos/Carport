package dat.backend.model.persistence;

import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    static List<Product> getAllProducts(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM product as p INNER JOIN product_variant as pv on pv.product_id = p.id;";

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


    public static int addProduct(String name, String description, Unit unit, float pricePrUnit, ProductType type, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO product (name, description, unit, price_pr_unit, type) VALUES (?, ?, ?, ?, ?);";
        int productId = 0;
        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setString(3, String.valueOf(unit));
                ps.setFloat(4, pricePrUnit);
                ps.setString(5, String.valueOf(type));

                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    productId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return productId;
    }


    public static int addProductVariant(int productId, float length, float width, float height, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO product_variant(product_id, length, width, height) VALUES (?, ?, ?, ?);";
        int productVariantId = 0;
        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, productId);
                ps.setFloat(2, length);
                ps.setFloat(3, width);
                ps.setFloat(4, height);


                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    productVariantId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return productVariantId;
    }





}


