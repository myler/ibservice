package cso.servlet;

import cso.database.TotalCSOtoDB;
import oracle.jdbc.OracleConnection;
import scr.database.TotalSCRtoDB;
import utils.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by myl on 14-11-10.
 */
public class DeleteCSOServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = " ";
        String password = " ";

        username = request.getParameter("username");
        password = request.getParameter("password");

        System.out.println("User: " + username + ", Pwd: " + password);
        TotalCSOtoDB totalCSOtoDB = new TotalCSOtoDB(
                GlobalVariables.oracleUrl, GlobalVariables.oracleUserName, GlobalVariables.oraclePassword);
        if (username.equals(GlobalVariables.defaultUserName) && password.equals(GlobalVariables.defaultPassword)) {
            totalCSOtoDB.createTotalCSO();
            response.getWriter().print("Delete cso success.");
        } else {
            response.getWriter().print("Username/password is empty or error.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
