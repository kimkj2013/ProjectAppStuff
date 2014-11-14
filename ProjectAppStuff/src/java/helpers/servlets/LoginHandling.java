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
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Damon Wolfgang Duckett
 */
public class LoginHandling extends MamaServlet {

    AccountCreator creation = new AccountCreator();
    UserInfoDump userInfo = new UserInfoDump();

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

            User user = userInfo.getUser();
            makePageTop(out, user);

            String userPath = request.getServletPath();

            if (userPath.compareTo("/getCreateAccountInfo") == 0) {
                makeGetAccountCreationInfoPage(out);

            }

            if (userPath.compareTo("/accountCreation") == 0) {
                /*TAG: database stuff
                 * you need to put in here a call to a method that is in AccountCreator
                 * that will check to see if the account can be created and then
                 * returns a boolean so I can determine what needs to happen next
                 * in the HTML stuff*/

                if (creation.canMake()) {

                    if (creation.getPassPhrase().compareTo(creation.MODERATOR_PHRASE) == 0) {
                        out.println("<h2>So you want to be a moderator named "
                                + creation.getUsername() + "</h2>");
                    } else if (creation.getPassPhrase().compareTo(creation.ADMINISTRATOR_STRING) == 0) {
                        out.println("<h2>So you want to be an administrator named "
                                + creation.getUsername() + "</h2>");
                    } else {
                        out.println("<h2>So you would like to be called " + user.getName()
                                + "</h2>");
                    }
                }

                out.println("need to put in something that will move user on to next page here");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    private void makeGetAccountCreationInfoPage(PrintWriter out) {
        out.println("<h1>Account Creation</h1>\n"
                + "        <h3>Please fill out the following information to create an account</h3>\n"
                + "        <form action = \"CreationSteppingStone.jsp\" method = \"get\"> \n"
                + "            Username: <input name = \"username\" /><br/>\n"
                + "            Password: <input type = \"password\" name = \"pass\"/><br/>\n"
                + "            Confirm Password: <input type =\"password\" name =\"passConfirm\"/><br/>\n"
                + "            <!-- put in something that will ask if the person is going to be\n"
                + "            an administrator or not and then have them confirm it with their\n"
                + "            super secret password or something -->\n"
                + "            code: <input name =\"passPhrase\"/>\n"
                + "            just leave this blank if you were not given a special code\n"
                + "            <input type= \"submit\" value = \"OK\"/>\n"
                + "        </form>\n"
                + "    </body>\n"
                + "</html>");
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
