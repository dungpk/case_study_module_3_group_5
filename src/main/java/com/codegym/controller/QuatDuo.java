package com.codegym.controller;

import com.codegym.dao.AccountDao;
import com.codegym.dao.GameDAO;
import com.codegym.dao.PlayerDAO;
import com.codegym.dao.UserDAO;
import com.codegym.model.Account;
import com.codegym.model.Game;
import com.codegym.model.Player;
import com.codegym.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "QuatDuo", urlPatterns = "/quat")
public class QuatDuo extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "login":
                    System.out.println(request.getParameter(request.getLocalName()));
                    confirmLogin(request, response);
                    break;
                case "search_player":
                    searchPlayer(request, response);
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "login":
                    showFormLogin(request, response);
                    break;
                case "search_player_by_game":
                    searchPlayerByGame(request, response);
                    break;
                    default:

                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }




    private void showFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        AccountDao accountDao = new AccountDao();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDao.confirmLogin(name,password);

        if(account==null){
            response.sendRedirect("jsp/login.jsp");
        }else{
            PlayerDAO playerDAO = new PlayerDAO();
            GameDAO gameDAO = new GameDAO();
            List<Game> gameList = gameDAO.getAllGame();
            List<Player> vipList = playerDAO.vipPlayer();
            List<Player> hotList = playerDAO.hotPlayer();
            List<Player> playerList = playerDAO.listPlayer();
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Home.jsp");
            request.setAttribute("hotList", hotList);
            request.setAttribute("playerList", playerList);
            request.setAttribute("vipList", vipList);
            request.setAttribute("gameList", gameList);
            request.setAttribute("account", account);
            dispatcher.forward(request, response);
        }
    }

    private void searchPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerDAO playerDao = new PlayerDAO();
        String name = request.getParameter("search");
        List<Player> playerList = playerDao.searchPlayer(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SearchPages.html");
        request.setAttribute("playerList", playerList);
        dispatcher.forward(request, response);
    }

    private void    searchPlayerByGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PlayerDAO playerDao = new PlayerDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        List<Player> playerList = playerDao.searchPlayerByGame(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SearchPages.html");
        request.setAttribute("playerList", playerList);
        dispatcher.forward(request, response);
    }
}
