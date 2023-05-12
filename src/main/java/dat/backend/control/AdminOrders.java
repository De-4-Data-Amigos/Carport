package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.AdminFacade;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminOrders", value = "/admin-orders")
public class AdminOrders extends HttpServlet {

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
                List<User> users = null;
                try {
                    users = AdminFacade.getAllUsers(connectionPool);

                } catch (DatabaseException e) {
                    request.setAttribute("errormessage", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("userList", users);
                request.getRequestDispatcher("WEB-INF/admin-orders.jsp").forward(request, response);

            }
        }

        request.setAttribute("besked", "du er ikke en admin");
        request.getRequestDispatcher("index").forward(request, response);

    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (!user.getRole().equalsIgnoreCase("admin")) {

            request.setAttribute("besked", "du er ikke en admin");
            request.getRequestDispatcher("index").forward(request, response);

        }

        // Hvad skal admin kunne gøre, og hvordan vil man have det til at se ud?
        // Vi skal have tilføjet metoder til hvad man skal her - for inspiration kig cupcake


        response.sendRedirect("admin-users");
    }

}

