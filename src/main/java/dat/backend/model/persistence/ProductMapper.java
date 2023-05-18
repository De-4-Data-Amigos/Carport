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
        String sql = "DELETE FROM carport.product WHERE id = ?;";

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
        String sql = "DELETE FROM carport.product_variant WHERE product_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }
    public static void editAdminProduct(int id, ConnectionPool connectionPool) {
        String sql = ""

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int Id = Integer.parseInt(request.getParameter("id"));
                    int productId = Integer.parseInt(request.getParameter("product_id"));
                    int pricePerUnit = Integer.parseInt(request.getParameter("price_per_unit"));
                    int width = Integer.parseInt(request.getParameter("width"));
                    int length = Integer.parseInt(request.getParameter("length"));

                    String newDescription = request.getHeader("description");
                    String newName = request.getParameter("namename");
                    String newUnit = request.getParameter("unit");
    }
}

}
