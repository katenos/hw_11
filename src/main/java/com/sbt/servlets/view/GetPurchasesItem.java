package com.sbt.servlets.view;

import com.sbt.DAO.DAO;
import com.sbt.DAO.Database;
import com.sbt.model.Delivery;
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
@WebServlet(name = "GetPurchasesItem", urlPatterns = {"/GetPurchasesItem"})
public class GetPurchasesItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new Database();
            List<Delivery> deliveryList = (List<Delivery>) dao.findAll('d');
            List<HashMap> list = new ArrayList<>();
            for (Delivery delivery : deliveryList) {
                if (delivery.getBid().getUser().getName().equals(username)) {
                    HashMap result = new HashMap();
                    result.put("del_id", delivery.getId());
                    result.put("name", delivery.getBid().getItem().getName());
                    result.put("description", delivery.getBid().getItem().getDescription());
                    result.put("user_pur_id", delivery.getBid().getItem().getUser().getId());
                    result.put("user_pur_name", delivery.getBid().getItem().getUser().getName());
                    result.put("price", delivery.getBid().getPrice());
                    result.put("user_fio", delivery.getUserFullName());
                    result.put("user_address", delivery.getAddress());
                    result.put("status", delivery.isDeliveried());
                    list.add(result);
                }

            }
            out.println(new JSONObject().put("documents", list).toString());
        } catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }
}
