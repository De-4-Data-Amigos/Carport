package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.ItemEntry;
import dat.backend.model.entities.Orders;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.CarportBuilderHelper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Carportbuilder", value = "/carportbuilder")
public class CarportBuilder extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("checkout").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String widthString = request.getParameter("width");
        String lengthString = request.getParameter("length");

        int width = Integer.parseInt(widthString);
        int length = Integer.parseInt(lengthString);
        User user = (User) request.getSession().getAttribute("user");
        Orders order = null;
        try {
            order = new Orders(width, length, user, connectionPool);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        List<ItemEntry> itemEntryList = CarportBuilderHelper.generateItemList(width, length, order);
        request.setAttribute("itemList", itemEntryList);
        request.getRequestDispatcher("checkout").forward(request,response);



    }
}
