package com.codegym.controller;

import com.codegym.dao.*;
import com.codegym.model.Account;
import com.codegym.model.Player;
import com.codegym.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
                    showCreateForm(request, response);
                    break;
                case "edit":
                    ShowEditProfile(request, response);
                    break;
                case "delete_player":
                    showDeleteForm(request, response);
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
        switch (action) {
            case "create_player":
                createPlayer(request, response);
                break;
            case "edit":
                break;
            case "delete":
                DeleteAccount(request, response);
                break;
        }
    }
    private void ShowEditProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        request.setAttribute("role", role);
        if(role.equals("player")){
            Player player = accountDao.getPlayerByAccountId(id);
            request.setAttribute("account", player);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminEdit.jsp");
            dispatcher.forward(request, response);
        }else{
            User user = accountDao.selectUserById(id);
            request.setAttribute("account", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminEdit.jsp");
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminEdit.jsp");
        dispatcher.forward(request, response);
    }
    private void ListAccount(HttpServletRequest request,HttpServletResponse response){
        List<Account> accountList = accountDao.listAccount();
        try{
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Admin.jsp");
            request.setAttribute("list", accountList);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        request.setAttribute("role", role);
        AdminDAO adminDAO = new AdminDAO();
        Account account = adminDAO.getAccountByID(id);
        request.setAttribute("account", account);
        if(role.equals("player")){
            Player player = accountDao.getPlayerByAccountId(id);
            request.setAttribute("player", player);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminDelete.jsp");
            dispatcher.forward(request, response);
        }else{
            User user = accountDao.selectUserById(id);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminDelete.jsp");
            dispatcher.forward(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminDelete.jsp");
        dispatcher.forward(request, response);
    }
    private void createPlayer(HttpServletRequest request, HttpServletResponse response){
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            int coin = Integer.parseInt(request.getParameter("coin"));
            int rate = Integer.parseInt(request.getParameter("rate"));
            int price = Integer.parseInt(request.getParameter("price"));
            AdminDAO adminDAO = new AdminDAO();
            int id = adminDAO.CreatePlayer(username, password, name, coin, rate, price);
            response.sendRedirect("jsp/AdminCreatePlayer.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AdminCreatePlayer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void DeleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.DeleteAccount(id);
        response.sendRedirect("jsp/AdminDelete.jsp");
    }
}
