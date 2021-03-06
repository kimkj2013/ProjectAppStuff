/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.*;
import java.util.*;

import com.secure.userInfo.*;
//<<<<<<< HEAD
import com.App;
//=======
//import com.others.App;
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f

/**
 *
 * @author Kwangju
 */
public class DBHandling {

    private static final String DBUrl = "jdbc:mysql://localhost:3306/appstore";
    private static final String DBID = "root";
//<<<<<<< HEAD
    private static final String DBPW = "Levi*7537";
    
    private static Statement stmt;
    
//=======


//    private static Statement stmt;

//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
    private static Statement initializer() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DBUrl, DBID, DBPW);
        return con.createStatement();
    }
//<<<<<<< HEAD
    
//    public static boolean Login(User u) throws Exception {
//        stmt = initializer();
//	String statement;
//=======

    public static boolean Login(User u) throws Exception {
        stmt = initializer();
        String statement;
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
        if (u.isAdmin()) {
            statement = String.format("SELECT USERNAME, PASSWORD FROM ADMIN WHERE USERNAME = '%s' AND PASSWORD = '%s'", u.getName(), u.getPassword());
        } else if (u.isModerator()) {
            statement = String.format("SELECT USERNAME, PASSWORD FROM MODERATOR WHERE USERNAME = '%s' AND PASSWORD = '%s'", u.getName(), u.getPassword());
        } else {
            statement = String.format("SELECT USERNAME, PASSWORD FROM USER WHERE USERNAME = '%s' AND PASSWORD = '%s'", u.getName(), u.getPassword());
        }
        ResultSet rs = stmt.executeQuery(statement);
        int count = 0;
        while (rs.next()) {
            ++count;
        }
//<<<<<<< HEAD
        
//=======

//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
        if (count > 1) {
            throw new IllegalStateException();
        }
        if (count == 1) {
            rs.first();
            String foundID = rs.getString("USERNAME");
            String foundPW = rs.getString("PASSWORD");
            if (u.getName().equals(foundID)) {
                if (u.getPassword().equals(foundPW)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean SignUp(User u) throws Exception {
        stmt = initializer();
//<<<<<<< HEAD
//	String statement;
//       if (u.isAdmin()) {
//            statement = String.format("INSERT INTO ADMIN " + "VALUES ('%s', '%s');", u.getName(), u.getPassword());
// /       } else if (u.isModerator()) {
//            statement = String.format("INSERT INTO MODERATOR " + "VALUES ('%s', '%s');", u.getName(), u.getPassword());
//        } else {
//            statement = String.format("INSERT INTO USER " + "VALUES ('%s', '%s');", u.getName(), u.getPassword());
//        }
//        
//=======
        String statement = String.format("INSERT INTO USER VALUES ('%s', '%s', ", u.getName(), u.getPassword());
        String secondStatement = "";
        if (u.isAdmin()) {
            statement += "'Administrator');";
            secondStatement = String.format("INSERT INTO ADMIN VALUES ('%s', '%s');", u.getName(), u.getPassword());
        } else if (u.isModerator()) {
            statement += "'Moderator');";
            secondStatement = String.format("INSERT INTO MODERATOR VALUES ('%s', '%s');", u.getName(), u.getPassword());
        } else {
            statement += "'Normal');";
        }

//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
        try {
            stmt.executeUpdate(statement);
            if (!secondStatement.equals("")) {
                stmt.executeUpdate(secondStatement);
            }
        } catch (SQLException e) {
            return false; // SIGNUP FAILED
        }
        return true; //SIGNUP SUCCESSFUL
    }

    public static boolean DeleteAccount(User u) throws Exception {
        try {
//<<<<<<< HEAD
//            if (! Login(u)) {
//=======
            if (!Login(u)) {
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
                throw new IllegalArgumentException("No such account found.");
            }
            stmt = initializer();
            String statement;
            if (u instanceof Administrator) {
                statement = String.format("DELETE FROM ADMIN WHERE USERNAME = '%s';", u.getName());
//<<<<<<< HEAD
//            }
//            else if (u instanceof Moderator) {
//=======
            } else if (u instanceof Moderator) {
//>>>>>>> 71677c879cd52361e0863c882ec6896c4ff1001f
                statement = String.format("DELETE FROM MODERATOR WHERE USERNAME = '%s';", u.getName());
            } else {
                statement = String.format("DELETE FROM USER WHERE USERNAME = '%s';", u.getName());
            }

            stmt.executeUpdate(statement);
        } catch (SQLException e) {
            return false; // DELETE FAILED
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Fatal error. Contact the programmer. " + Arrays.toString(e.getStackTrace()));
            return false;
        }
        return true; // DELETE SUCCESSFUL
    }

    public static ArrayList<App> search(String keyword, boolean filter, boolean sort, int col) throws Exception {
        stmt = initializer();
        String statement;

        if (keyword.equals("puppy_") || keyword.equals("Wolfgang")) {
            statement = "SELECT * FROM APPS;";
        } else {

            statement = "SELECT * FROM APPS WHERE NAME LIKE '" + "%" + String.format("%s", keyword).toLowerCase() + "%" + "'";

            /*public App(String name, String developer, String description, String[] platforms, String link) {*/
            if (filter) {
                String cmd = " AND ";
                switch (col) {
                    case 1:
                        cmd += "";
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        throw new IllegalStateException("Something went wrong.");
                }
                statement += cmd;
            }

            if (sort) {
                statement += String.format(" ORDER BY %s", col);
            }

            statement += ";";
        }
        ResultSet rs = stmt.executeQuery(statement);

        ArrayList<App> ret = new ArrayList<App>();

        while (rs.next()) {
            String name = rs.getString("NAME");
            String developer = rs.getString("DEVELOPER");
            String description = rs.getString("DESCRIPTION");
            String[] platforms = new String[]{rs.getString("PLATFORMS")};
            String link = rs.getString("LINK");
            ret.add(new App(name, developer, description, platforms, link));
        }

        return ret;
    }

    public static boolean insertApp(App a) throws Exception {
        stmt = initializer();
        String statement = "INSERT INTO APPS VALUES ('";
        statement += a.getName() + "', '";
        statement += a.getDeveloper() + "', '";
        statement += a.getDescription() + "', '";
        for (int i = 0; i < a.getPlatforms().length; i++) {
            statement += a.getPlatforms()[i];
            statement += i == a.getPlatforms().length - 1 ? "" : ", ";
        }
        statement += "', null, '" + a.getLink() + "', null, " + a.getRating() + ", 'Y')";

        int executeUpdate = stmt.executeUpdate(statement);

        return executeUpdate != 0;
    }
    
    public static boolean deleteApp(App a) throws Exception {
        stmt = initializer();
        String statement = "DELETE FROM apps WHERE ";
        statement += "NAME = '" + a.getName() + "';";
        try {
            stmt.executeUpdate(statement);
        } catch (SQLException e1) {
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean updateRating(App a, double rate) throws Exception {
        stmt = initializer();
        String appName = a.getName();
        String statement = String.format("SELECT rating FROM apps WHERE NAME = '%s';", appName);
        ResultSet rs = stmt.executeQuery(statement);
        
        while (rs.next()) {
            
        }
        
        statement = String.format("UPDATE apps SET rating = %f WHERE NAME = '%s';", rate, appName);
        
        try {
            stmt.executeUpdate(statement);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /*
    public static ArrayList<App> search(String keyword, boolean filter, boolean sort, int col) throws Exception {
        stmt = initializer();
        String statement;
        
        if (keyword.equals("puppy_") || keyword.equals("Wolfgang")) {
            statement = "SELECT * FROM APPS;";
        } else {

            statement = "SELECT * FROM APPS WHERE IS_ACCEPTED = 'Y' AND NAME LIKE '" + "%" + String.format("%s", keyword).toLowerCase() + "%" + "'";

            public App(String name, String developer, String description, String[] platforms, String link) {


            if (filter) {
                String cmd =  " AND ";
                switch (col) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default: throw new IllegalStateException("Something went wrong.");
                }
                statement += cmd;
            }

            if (sort) {
                statement += String.format(" ORDER BY %s", col);
            }

            statement += ";";
        }
        ResultSet rs = stmt.executeQuery(statement);
        
        ArrayList<App> ret = new ArrayList<App>();
        
        while (rs.next()) {
            String name = rs.getString("NAME");
            String developer = rs.getString("DEVELOPER");
            String description = rs.getString("DESCRIPTION"); 
            String[] platforms = new String[] {rs.getString("PLATFORMS")}; 
            String link = rs.getString("LINK");
            ret.add(new App(name, developer, description, platforms, link));
        }
        
        return ret;
    }
    
    public static boolean insertApp(App a) throws Exception {
        stmt = initializer();
        String statement = "INSERT INTO APPS VALUES ('";
        statement += a.getName() + "', '";
        statement += a.getDeveloper() + "', '";
        statement += a.getDescription() + "', '";
        for (int i = 0; i < a.getPlatforms().length; i++) {
            statement += a.getPlatforms()[i];
            statement += i == a.getPlatforms().length - 1 ? "" :  ", "; 
        }
        statement += "', null, '" + a.getLink() + "', null, " + a.getRating() + ", 'Y')";
        
        int executeUpdate = stmt.executeUpdate(statement);
        
        return executeUpdate != 0;
    }
    */
}
