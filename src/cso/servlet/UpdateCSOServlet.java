package cso.servlet;

import cso.database.ImportOpenToDB;
import cso.database.UpdateCSO;
import cso.database.UpdateOpenFromClosedCSO;
import utils.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by myl on 14-11-10.
 */
public class UpdateCSOServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = " ";
        String password = " ";
        String id = " ";

        username = request.getParameter("username");
        password = request.getParameter("password");
        id = request.getParameter("id");

        System.out.println("User: " + username + ", Pwd: " + password);
        UpdateCSO updateCSO = new UpdateCSO(
                GlobalVariables.oracleUrl, GlobalVariables.oracleUserName, GlobalVariables.oraclePassword);
        if (username.equals(GlobalVariables.defaultUserName) && password.equals(GlobalVariables.defaultPassword)) {
            if ("fromOpenCSO".equals(id)) {
                updateCSO.updateFromOpenCSO();
            }
            if ("fromClosedCSO".equals(id)) {
                updateCSO.updateFromCloseCSO();
            }
            response.getWriter().print("Success!");
        } else {
            response.getWriter().print("Failed!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}