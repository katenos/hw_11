package com.sbt.servlets.view;

import com.sbt.DAO.DAO;
import com.sbt.DAO.Database;
import com.sbt.model.Item;
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
@WebServlet(name = "GetAllItem", urlPatterns = {"/GetAllItem"})
public class GetAllItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new Database();
            List<Item> allItemList = (List<Item>) dao.findAll('i');
            List<HashMap> list=new ArrayList<>();
            for (Item item : allItemList) {
                if (item.isSales() == false) {
                    HashMap result = new HashMap();
                    result.put("id", item.getId());
                    result.put("name", item.getName());
                    result.put("description", item.getDescription());
                    result.put("user", item.getUser().getName());
                    list.add(result);
                }
            }
            dao.closeConnection();
            out.println(new JSONObject().put("documents", list).toString());
        } catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }
}
