package partfinder.servlet;

import complaints.database.ImportDBfromAllSheets;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import partfinder.database.PartFinderCatalogueFromDB;
import utils.GlobalVariables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by myl
 * on 2015/2/6.
 */
public class PartFinderSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKeys = request.getParameter("searchKeys");
        String searchTable = request.getParameter("tableName");

        Map<String, String> searchMap = new HashMap<String, String>();
        JSONObject searchObj = JSONObject.fromObject(searchKeys);
        for (Object o : searchObj.entrySet()) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) o;
            if (!entry.getValue().toString().isEmpty()) {
                searchMap.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        List<String> result;
        PartFinderCatalogueFromDB partFinderCatalogueFromDB = new PartFinderCatalogueFromDB(
                GlobalVariables.oracleUrl, GlobalVariables.oracleUserName, GlobalVariables.oraclePassword);
        if ((result = partFinderCatalogueFromDB.partFinderSearch(searchMap, searchTable)) != null) {
            JSONArray jsonArray = JSONArray.fromObject(result);
            response.getWriter().write(jsonArray.toString());
        } else {
            response.getWriter().write("fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
