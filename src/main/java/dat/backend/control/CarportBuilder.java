package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CompleteProduct;
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
        String[] split = request.getParameter("dimensions").split(":");
        String widthString = split[0];
        String lengthString = split[1];

        int width = Integer.parseInt(widthString);
        int length = Integer.parseInt(lengthString);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Orders order = (Orders) session.getAttribute("order");

        List<CompleteProduct> itemList = null;
        try {
            itemList = CarportBuilderHelper.generateItemList(width, length, order);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("WEB-INF/orderConfirmation.jsp").forward(request,response);



    }
}
