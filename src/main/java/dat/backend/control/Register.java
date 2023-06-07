package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {

    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      //fejl fra main - mangler!
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        //Laver httpsession objektet
        HttpSession session = request.getSession();
        //Sætter attributen 'user' til null - gør plads til at en ny kan laves
        session.setAttribute("user", null);

        //Får fat i parametrene som brugeren har skrevet ind
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");

        //Parser de tegn brugeren skriver, selvom det er tal, om til reelle tal som man kan beregne
        int zipcode = Integer.parseInt(request.getParameter("zipcode"));
        int phonenumber = Integer.parseInt(request.getParameter("phonenumber"));

        //Flere parametre
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");


        if (!password.equals(confirmpassword)) {
            request.setAttribute("message", "Passwords did not match.");

            request.getRequestDispatcher("register.jsp").forward(request, response);
        }


        try {


            User user = UserFacade.createUser(email, password, firstname, lastname, phonenumber, connectionPool);

            //Fra Mapper, bruger oprettet ->

            //Får fat på en session (denne er overflødig, vi gør det samme i starten af metoden)
            session = request.getSession();

            //tilføjer bruger til session
            session.setAttribute("user", user);


            request.getRequestDispatcher("WEB-INF/carportbuilder.jsp").forward(request, response);

            //Hvis noget fejler, db-exception og errormsg -> error.jsp og prøv igen :)
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

