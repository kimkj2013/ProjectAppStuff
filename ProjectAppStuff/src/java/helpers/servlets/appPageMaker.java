/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.servlets;

import com.App;
import com.Comment;
import com.secure.userInfo.*;
import helpers.SearchesInfo;
import helpers.UserInfoDump;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Damon Wolfgang Duckett
 */
public class appPageMaker extends MamaServlet {

    UserInfoDump userInfo = UserInfoDump.getInstance();
    SearchesInfo appInfo = SearchesInfo.getInstance();

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
            User user = userInfo.getUser();
            String userPath = request.getServletPath();

            makePageTop(out, user, userPath);
            App app = appInfo.getDesiredApp();

            if (userPath.compareTo("/viewAppPage") == 0) {
                printAppPage(out);

            } else if (userPath.compareTo("/deleteComment") == 0) {
                //TODO: implement logic
                printAppPage(out);

            } else if (userPath.compareTo("/commentOnForum") == 0) {
                ArrayList<Comment> forum = app.getForum();
                forum.add(new Comment(user.getName(), request.getParameter("quote")));
                printAppPage(out);

            } else if (userPath.compareTo("/rateApp") == 0) {
                //TODO: implement logic
                int score = Integer.parseInt(request.getParameter("searchType"));
                /*Make it so ratings work (because others who were supposed to
                 * do it didn't do it) */
                printAppPage(out);

            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    private void printAppPage(PrintWriter out) {
        App app = appInfo.getDesiredApp();
        out.println("<h1 style=\"text-align: center\">" + app.getName() + "</h1>");

        /* will need to alter this a bit */
        out.println("<h2>This app's current rating: " + app.getRating() + "</h2>");
        if (!(userInfo.getUser() instanceof Guest)) {
            out.println("<form action=\"rateApp\"> \n"
                    + "                             <fieldset> \n"
                    + "                                 <legend></legend> \n"
                    + "                                 <p> Your rating: \n"
                    + "                                     <select id = \"searchType\" name=\"searchType\"> \n"
                    + "                                         <option value = \"1\">1</option> \n"
                    + "                                         <option value = \"2\">2</option>\n"
                    + "                                         <option value = \"3\">3</option> \n"
                    + "                                         <option value = \"4\">4</option>\n"
                    + "                                         <option value = \"5\">5</option>\n"
                    + "                                     </select> \n"
                    + "                                 </p> \n"
                    + "                             </fieldset> \n"
                    + "                             <input type=\"submit\" value=\"Rate App\" name=\"to the testing stuff\" /> \n"
                    + "                         </form>");
        } else {
            out.println("<h3>Login to rate apps</h3>");
        }
        /* The rating needs to be determined by the method that calculates it
         (once the method is created) instead of what there is now. Need to add
         in the ability for users to rate the app (so add another rating to the
         list of Ratings) or edit their existing rating (so this needs to check
         to see if the user has already rated the app and if the user has, it
         will allow them to change their Rating (may need to add another action
         request type))*/

        out.println("<p>" + app.getDescription() + "</p>");

        printForum(out, app);
        /*an error occurs when I try to add comment at this line of code. The
         comment is added but when it goes to repeat this part, an error is thrown*/

        //TEST
        //out.println("<h1>Testing now</h1>");
        //TEST

        User user = userInfo.getUser();
        if (user instanceof Guest) {
            out.println("<h3>To participate in conversations or comment on the App, please log in\n"
                    + "            or create an account</h3>\n"
                    + "        <form name=\"whatever\" action=\"login\" method=\"POST\">\n"
                    + "            <input type=\"submit\" value=\"Login\" name=\"button1\" />\n"
                    + "        </form>\n"
                    + "        <form name=\"whatever2\" action=\"getCreateAccountInfo\" method=\"POST\">\n"
                    + "            <input type=\"submit\" value=\"Create an account\" name=\"button2\" />\n"
                    + "        </form>");
        } else {
            out.println("<h3>Post a comment or join a conversation</h3>\n"
                    + "        <form name=\"whatever\" action=\"commentOnForum\" method=\"POST\">\n"
                    + "            <textarea name=\"quote\" rows=\"4\" cols=\"20\">\n"
                    + "            </textarea>\n"
                    + "            <input type=\"submit\" value=\"Post\" name=\"button1\" />\n"
                    + "        </form>");
        }

    }

    private void printForum(PrintWriter out, App app) {
        /*TODO: add in the logic to display all of the things on the Forum
         * *option buttons will appear next to comments if the user was the one
         *who posted the comment, allowing the user to change or delete the comment
         * *an option to delete a comment will appear next to each comment for
         * Moderators and the additional ability to edit the comment will appear
         * when the Moderator id the one who made the comment*/
        // Forum forum = app.getForum();
        ArrayList<Comment> forum = app.getForum();
        int initSize = forum.size();
        for (int i = initSize; i > 0; i--) {
            printComment(out, forum.get(i));
        }
    }

    private void printComment(PrintWriter out, Comment comment) {
        out.println("<tbody>\n <tr>");
        out.println("<td>" + comment.getCommenter() + "</td>");

        User user = userInfo.getUser();
        if (user instanceof Moderator) {
            out.println("<td><form name=\"whatevs\" action=\"deleteComment\" method=\"POST\">\n"
                    + "            <input type=\"submit\" value=\"Delete Post\" name=\"button1\" />\n"
                    + "        </form></td></tr>");
        } else {
            out.println("<td></td></tr>");
        }
        out.println("<tr><td>" + comment.getQuote() + "</td><td></td></tr>");
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
