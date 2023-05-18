package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditAdminProduct", value = "editadminproduct")
public class EditAdminProduct extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int Id = Integer.parseInt(request.getParameter("id"));
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int pricePerUnit = Integer.parseInt(request.getParameter("price_per_unit"));
        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        String newDescription = request.getHeader("description");
        String newName = request.getParameter("name");
        String newUnit = request.getParameter("unit");

        try {
            List<ItemList> adminItems = ItemListFacade.editAdminProduct(connectionPool);

            request.setAttribute("adminItems", adminItems); //gemmer adminItems i request-scope
            it

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

                request.sendRedirect("editadminproduct.jsp").forward(request, response);


    }
}
