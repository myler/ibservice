package scr.servlet;

import scr.database.UpdateSCR;
import utils.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by myl on 2014/12/15.
 */
public class UpdateSCRServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = " ";
        String password = " ";

        username = request.getParameter("username");
        password = request.getParameter("password");

        System.out.println("User: " + username + ", Pwd: " + password);
        UpdateSCR updateSCR = new UpdateSCR(
                GlobalVariables.oracleUrl, GlobalVariables.oracleUserName, GlobalVariables.oraclePassword);
        if (username.equals(GlobalVariables.defaultUserName) && password.equals(GlobalVariables.defaultPassword)) {
            updateSCR.updateSCRfromExcel();
            response.getWriter().print("Success!");
        } else {
            response.getWriter().print("Failed!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
