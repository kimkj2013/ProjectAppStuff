/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.servlets;

import com.secure.userInfo.*;
import helpers.AccountCreator;
import helpers.UserInfoDump;
import java.io.IOException;
import java.io.PrintWriter;
//<<<<<<< HEAD
//=======
import java.util.Arrays;
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Damon Wolfgang Duckett
 */
public class LoginHandling extends MamaServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            UserInfoDump userInfo = UserInfoDump.getInstance();
            String userPath = request.getServletPath();
            User user = userInfo.getUser();
            makePageTop(out, user, userPath);

            if (userPath.compareTo("/accountLogin") == 0) {

                userInfo.setPass(request.getParameter("pass"));
                userInfo.setUsername(request.getParameter("username"));
                try {

                    if (userInfo.login()) {
                        out.println("<h1>Hello there " + userInfo.getUsername() + "</h1>");
                    } else {
                        out.println("<h1>Username or Password is wrong.</h1>");
                        out.println("<h2>Please try to login again.</h2>");
                        out.println("<h2>Don't have an account? Go to Sign Up Page!</h2>");
                        makeLoginPage(out);
                    }
                } catch (Exception ex) {
                    out.println("<h1>There is a critical error. Contact the programmer.</h1>");
//<<<<<<< HEAD
//                    out.println("<p>" + ex.getLocalizedMessage() + "</p>");
//=======
                    out.println("<p>" + ex.getLocalizedMessage() + " "
                            + Arrays.toString(ex.getStackTrace()) + "</p>");
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
                    makeLoginPage(out);
                }
            }

            if (userPath.compareTo("/login") == 0) {
                makeLoginPage(out);
            }

            if (userPath.compareTo("/logout") == 0) {
                userInfo.logout();
                out.println("<h1>You have logged out. Have a nice day :)</h1><br/>");
                makeLoginPage(out);
            }

            if (userPath.compareTo("/getCreateAccountInfo") == 0) {
                makeGetAccountCreationInfoPage(out);

            }

            if (userPath.compareTo("/accountCreation") == 0) {

                AccountCreator creation = AccountCreator.getInstance();
                creation.setPass(request.getParameter("pass"));
                creation.setPassConfirm(request.getParameter("passConfirm"));
                // creation.setPassPhrase(request.getParameter("passPhrase"));
                creation.setUsername(request.getParameter("username"));

                try {
                    if (creation.canMake()) {

/*<<<<<<< HEAD
                        if (creation.getPassPhrase().compareTo(creation.MODERATOR_PHRASE) == 0) {
                            out.println("<h2>So you want to be a moderator named "
                                    + creation.getUsername() + "</h2>");
                        } else if (creation.getPassPhrase().compareTo(creation.ADMINISTRATOR_STRING) == 0) {
                            out.println("<h2>So you want to be an administrator named "
                                    + creation.getUsername() + "</h2>");
                        } else {
                            out.println("<h2>So you would like to be called "
                                    + creation.getUsername() + "</h2>");
                        }
=======*/
                        out.println("<h2>So you would like to be called "
                                + creation.getUsername() + "</h2>");
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f

                        out.println("<h3>You can login below</h3>");
                        makeLoginPage(out);
                    } else {
//<<<<<<< HEAD
//                        out.println("<h3>That username is already taken. Please try a different username</h3>");
//=======
                        out.println("<h3>That username is already taken, or you typed the mismatched password."
                                + " Please try a different username</h3>");
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
                        makeGetAccountCreationInfoPage(out);
                    }
                } catch (Exception ex) {
                    out.println("<h1>There is a critical eror. Contact the programmer.</h1>");
//<<<<<<< HEAD
//                    out.println("<p>" + ex.getLocalizedMessage() + " " + java.util.Arrays.toString(ex.getStackTrace()) + "</p>");
//=======
                    out.println("<p>" + ex.getLocalizedMessage() + " " + Arrays.toString(ex.getStackTrace()) + "</p>");
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f

                    makeGetAccountCreationInfoPage(out);
                }

            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    private void makeLoginPage(PrintWriter out) {
        out.println("<form name=\"Login to existing account\" method=\"post\"action=\"accountLogin\">\n"
                + "            Username: <input type=\"text\" name=\"username\" /> <br/>\n"
                + "            Password: <input type=\"password\" name=\"pass\" />\n"
                + "            <input type=\"submit\" value=\"Login\" name=\"onward with the login\" />\n"
                + "        </form>"
                + "<form name=\"Login to existing account\" action=\"getCreateAccountInfo\" method=\"POST\">\n"
                + "            <input type=\"submit\" value=\"or click here create an account\" name=\"name goes here\" />\n"
                + "        </form>");
    }

    private void makeGetAccountCreationInfoPage(PrintWriter out) {
        out.println("<h1>Account Creation</h1>\n"
                + "        <h3>Please fill out the following information to create an account</h3>\n"
                + "        <form action = \"accountCreation\" method = \"post\"> \n"
                + "            Username: <input name = \"username\" /><br/>\n"
                + "            Password: <input type = \"password\" name = \"pass\"/><br/>\n"
                + "            Confirm Password: <input type =\"password\" name =\"passConfirm\"/><br/>\n"
                + "            <input type= \"submit\" value = \"OK\"/>\n"
                + "        </form><br/><br/>\n"
                + "<form name=\"Login to existing account\" action=\"login\" method=\"POST\">\n"
                + "            <input type=\"submit\" value=\"or click here to login to an existing account\" name=\"name goes here\" />\n"
                + "        </form>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
