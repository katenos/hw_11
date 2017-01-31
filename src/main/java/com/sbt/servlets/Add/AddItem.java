package com.sbt.servlets.Add;

import com.sbt.DAO.DAO;
import com.sbt.DAO.Database;
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

/**
 * Created by kate_ on 31.01.2017.
 */
@WebServlet(name = "AddItem", urlPatterns = {"/AddItem"})
public class AddItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = session.getAttribute("user").toString();
        String name = request.getParameter("name").toString();
        String description = request.getParameter("description").toString();
        int startPrice = Integer.parseInt(request.getParameter("startPrice").toString());
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new Database();
            User user = dao.getUser(username);
            dao.createItem(name, user, description, startPrice, false);
            dao.closeConnection();
        } catch (Exception e) {
            System.out.println("Exception is ;" + e);
        }
    }
}
