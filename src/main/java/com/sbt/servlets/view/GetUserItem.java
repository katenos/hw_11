package com.sbt.servlets.view;

import com.sbt.DAO.DAO;
import com.sbt.DAO.Database;
import com.sbt.model.Item;
import com.sbt.model.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kate_ on 30.01.2017.
 */
@WebServlet(name = "GetUserItem", urlPatterns = {"/GetUserItem"})
public class GetUserItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new Database();
            User user = dao.getUser(username);
            List<HashMap> userItemList = new ArrayList<>();
            for (Item item : user.getItemList()) {
                if (!item.isSales()) {
                    userItemList = addMap(userItemList, item);
                }
            }
            dao.closeConnection();
            out.println(new JSONObject().put("documents", userItemList).toString());
        } catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }

    private List<HashMap> addMap(List<HashMap> list, Item item) {
        HashMap result = new HashMap();
        result.put("id", item.getId());
        result.put("name", item.getName());
        result.put("description", item.getDescription());
        list.add(result);
        return list;
    }
}
