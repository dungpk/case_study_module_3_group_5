package com.codegym.controller;

import com.codegym.dao.AccountDao;
import com.codegym.dao.GameDAO;
import com.codegym.dao.PlayerDAO;
import com.codegym.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private AccountDao accountDao;
    private PlayerDAO playerDAO;
    private GameDAO gameDAO;
    public void init(){
        userDAO = new UserDAO();
        accountDao = new AccountDao();
        playerDAO = new PlayerDAO();
        gameDAO = new GameDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
            switch (action) {
                case "create":
                    break;
                case "edit":
                    editProfile(request, response);
                    break;
                case "delete":
                    break;
                default:
                    ListAccount(request, response);
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
    }
    private void editProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminEdit");
        dispatcher.forward(request, response);
    }
    private void ListAccount(HttpServletRequest request,HttpServletResponse response){

    }

}
