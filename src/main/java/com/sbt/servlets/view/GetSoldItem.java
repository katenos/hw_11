package com.sbt.servlets.view;

import com.sbt.DAO.DAO;
import com.sbt.DAO.Database;
import com.sbt.model.Bid;
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
@WebServlet(name = "GetSoldItem", urlPatterns = {"/GetSoldItem"})
public class GetSoldItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new Database();
            User user = dao.getUser(username);
            List<HashMap> resultList = new ArrayList<>();
            for (Bid bid : user.getBidList()) {
                HashMap result = new HashMap();
                result.put("id", bid.getItem().getId());
                result.put("name", bid.getItem().getName());
                result.put("description", bid.getItem().getDescription());
                result.put("user", bid.getUser().getName());
                resultList.add(result);
            }
            dao.closeConnection();
            out.println(new JSONObject().put("documents", resultList).toString());
        } catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }
}
