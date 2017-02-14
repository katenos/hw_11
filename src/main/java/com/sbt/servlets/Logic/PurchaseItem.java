package com.sbt.servlets.Logic;

import com.sbt.DAO.DAO;
import com.sbt.DAO.Database;
import com.sbt.model.Bid;
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
 * Created by kate_ on 07.02.2017.
 */
@WebServlet(name = "PurchaseItem", urlPatterns = {"/PurchaseItem"})
public class PurchaseItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        String fullName = session.getAttribute("fullName").toString();
        String address = session.getAttribute("address").toString();
        int idItem = Integer.parseInt(request.getParameter("id").toString());
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new Database();
            List<Item> allItemList = (List<Item>) dao.findAll('i');
            List<HashMap> list = new ArrayList<>();
            for (Item item : allItemList) {
                if (item.getId().equals(idItem)) {
                    User user = dao.getUser(username);
                    Bid bid=dao.createBid(user, item);
                    dao.createDelivery(bid,fullName,address,false);
                }
            }
            dao.closeConnection();
            out.println(new JSONObject().put("success", true).toString());
        } catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }
}
