package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ProductFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditAdminProduct", value = "/editadminproduct")
public class EditAdminProduct extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                ProductAndProductVariant products = null;
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    products = ProductFacade.getProduct(id, connectionPool);

                } catch (DatabaseException e) {
                    request.setAttribute("errormessage", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("productList", products);
                request.getRequestDispatcher("WEB-INF/admin-editproduct.jsp").forward(request, response);

            }
        }

        request.setAttribute("errormessage", "Du er ikke en admin");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int productId = Integer.parseInt(request.getParameter("product_id"));
            int pricePerUnit = Integer.parseInt(request.getParameter("price_per_unit"));
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            int length = Integer.parseInt(request.getParameter("length"));
            String description = request.getHeader("description");
            String name = request.getParameter("name");
            Unit unit = Unit.valueOf(request.getParameter("unit"));
            ProductType type = ProductType.valueOf(request.getParameter("type"));

            String edit = request.getParameter("edit");

            ProductFacade.editProduct(name,id,description,unit,pricePerUnit,type,connectionPool);
            ProductFacade.editProductVariant(height,width,length,productId,id,connectionPool);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        response.sendRedirect("admin-editproduct");


    }
}
