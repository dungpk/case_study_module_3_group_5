package com.codegym.controller;

import com.codegym.dao.*;
import com.codegym.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuatDuo", urlPatterns = "/quat")
public class QuatDuo extends HttpServlet {

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
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    searchPlayer(request, response);
                    break;
                case "createUser":
                    createUser(request, response);
                    break;
                case "createPlayer":
                    createPlayer(request, response);
                    break;
                case "createGame":
                    createGame(request, response);
                    break;
                case "accept_request":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    acceptRequest(request, response);
                    break;
                case "refuse_request":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    refuseRequest(request, response);
                    break;
                case "user_edit_confirm":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    confirmUpdateUser(request, response);
                    break;
                case "rent_request":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    confirmRentRequest(request, response);
                    break;

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
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    searchPlayerByGame(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "goHomePage":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    goHomePage(request, response);
                    break;
                case "display_player":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    displayPlayer(request, response);
                case "profile":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    displayProfile(request, response);
                    break;
                case "playerRegister":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    playerRegister(request, response);
                    break;
                case "accept_request":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showFormAccept(request, response);
                    break;
                case "refuse_request":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showFormRefuse(request, response);
                    break;
                case "deposit":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showDepositForm(request, response);
                    break;
                case "coin_deposit":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    deposit(request, response);
                    break;
                case "user_edit":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showUserEditForm(request, response);
                    break;
                case "rent":
                    request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    request.setAttribute("player_id", Integer.parseInt(request.getParameter("player_id")));
                    showFormRent(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void createPlayer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        AccountDao accountDao = new AccountDao();
        PlayerDAO playerDAO = new PlayerDAO();
        ProfileDao profileDao = new ProfileDao();
        GameDAO gameDAO = new GameDAO();

        boolean checkAccount = accountDao.checkAccountExist(userName);

        if (checkAccount || !password.equals(confirm)) {
            response.sendRedirect("jsp/playerRegister.jsp");
        } else {
            accountDao.createAccount(userName, password, "player");
            int idForeign = accountDao.getIdByUserName(userName);
            playerDAO.createPlayer(name, 0, 0, 0, idForeign, "", 0);
            int idPlayer = playerDAO.getIdByIdForegin(idForeign);
            profileDao.createProfile(age, address, email, idForeign);
//
            request.setAttribute("idPlayer", idPlayer);

            //query db de get list game
            List<Game> gameList = gameDAO.getAllGame();
            request.setAttribute("gameList", gameList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Game.jsp?idPlayer=" + idPlayer);
            dispatcher.forward(request, response);
        }

    }

    private void showFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        AccountDao accountDao = new AccountDao();
        PlayerDAO playerDAO = new PlayerDAO();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDao.confirmLogin(name, password);
        if (name.equals("admin") && password.equals("admin")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Admin.jsp");

//            request.setAttribute("userCoin", userDAO.listCoin());


            request.setAttribute("playerCoin", playerDAO.listCoin());
//            request.setAttribute("listPlayer", accountDao.listAccountPlayer());
//            request.setAttribute("listUser", accountDao.listAccountUser());

            request.setAttribute("list", accountDao.listAccount());

            dispatcher.forward(request, response);
        } else if (account == null) {
            response.sendRedirect("jsp/login.jsp");
        } else {
            request.setAttribute("id", account.getId());
            String role = account.getRole();

            if (role.equals("user")) {
                User user = accountDao.getUserByAccountId(account.getId());
                request.setAttribute("coin", user.getCoin());
            } else if (role.equals("player")) {
                Player player = accountDao.getPlayerByAccountId(account.getId());
                request.setAttribute("coin", player.getCoin());
            } else {

            }

            goHomePage(request, response);
        }
    }

    private void goHomePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        PlayerDAO playerDAO = new PlayerDAO();
        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        List<Player> vipList = playerDAO.vipPlayer();
        List<Player> hotList = playerDAO.hotPlayer();
        List<Player> playerList = playerDAO.listPlayer();
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Home.jsp");
        System.out.println(request.getAttribute("id"));
        request.setAttribute("hotList", hotList);
        request.setAttribute("playerList", playerList);
        request.setAttribute("vipList", vipList);
        request.setAttribute("gameList", gameList);
        dispatcher.forward(request, response);
    }

    private void searchPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerDAO playerDao = new PlayerDAO();
        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        String name = request.getParameter("search");
        List<Player> playerList = playerDao.searchPlayer(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SearchPages.jsp");
        request.setAttribute("playerList", playerList);
        dispatcher.forward(request, response);
    }

    private void searchPlayerByGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerDAO playerDao = new PlayerDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        List<Player> playerList = playerDao.searchPlayerByGame(id);
        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SearchPages.jsp");
        request.setAttribute("playerList", playerList);
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Register.jsp");
        dispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        AccountDao accountDao = new AccountDao();
        UserDAO userDao = new UserDAO();
        ProfileDao profileDao = new ProfileDao();

        boolean checkAccount = accountDao.checkAccountExist(userName);
        if (checkAccount || !password.equals(confirm)) {
            response.sendRedirect("jsp/Register.jsp");
        } else {
            accountDao.createAccount(userName, password, "user");
            int idForeign = accountDao.getIdByUserName(userName);
            userDao.createUser(name, 0, idForeign);
            profileDao.createProfile(age, address, email, idForeign);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/successfulRegistration.jspl");
            dispatcher.forward(request, response);
        }
    }

    private void playerRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerRegister.jsp");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/welcome.jsp");
    }

    private void displayPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        GameDAO gameDAO = new GameDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        request.setAttribute("player", playerDAO.searchPlayerById(playerId));
        List<Game> games = playerDAO.searchGameByIdPlayer(playerId);
        request.setAttribute("listGameOfPlayer", games);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SelectPlayer.jsp");
        dispatcher.forward(request, response);
    }

    private void displayProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlayerDAO playerDAO = new PlayerDAO();
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfileByAccountId(Integer.parseInt(request.getParameter("account_id")));
        request.setAttribute("profile", profile);


        AccountDao accountDao = new AccountDao();
        String role = accountDao.getRoleByAccountId(Integer.parseInt(request.getParameter("account_id")));

        if (role.equals("user")) {
            int accountId = Integer.parseInt(request.getParameter("account_id"));
            User user = accountDao.getUserByAccountId(accountId);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/userProfile.jsp");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            dispatcher.forward(request, response);
        } else {
            Player player = accountDao.getPlayerByAccountId(Integer.parseInt(request.getParameter("account_id")));
            request.setAttribute("player", player);

            List<Game> games = playerDAO.searchGameByIdPlayer(player.getPlayer_id());
            request.setAttribute("listGameOfPlayer", games);

            RequestDAO requestDAO = new RequestDAO();
            List<Request> requests = requestDAO.getRequestByIdPlayer(player.getPlayer_id());
            request.setAttribute("requests", requests);

            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerProfile.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void createGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        int idInt = Integer.parseInt(request.getParameter("idPlayer"));

        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        List<String> options = new ArrayList<>();
        List<Integer> intOptions = new ArrayList<>();
        for (Game g : gameList) {
            String game = request.getParameter("" + g.getId());
            if (game != null) {
                options.add(game);
            }

        }
        for (String option : options) {
            int intOption = Integer.parseInt(option);
            intOptions.add(intOption);
        }
        for (int option : intOptions) {
            // Thực hiện các xử lý tương ứng với giá trị của option ở đây
            gameDAO.createGamePlayer(option, idInt, 100);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/successfulRegistration.jsp");
        dispatcher.forward(request, response);
    }

    private void showDepositForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Deposit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int requestId = Integer.parseInt(request.getParameter("request_id"));
        String userName = request.getParameter("name_user");
        int hours = Integer.parseInt(request.getParameter("hours"));
        String des = request.getParameter("des");

        Request requestRent = new Request(requestId, hours, des, userName);
        request.setAttribute("request", requestRent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/acceptRequest.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormRefuse(HttpServletRequest request, HttpServletResponse response) {

        int requestId = Integer.parseInt(request.getParameter("request_id"));
        String userName = request.getParameter("name_user");
        int hours = Integer.parseInt(request.getParameter("hours"));
        String des = request.getParameter("des");

        Request requestRent = new Request(requestId, hours, des, userName);
        request.setAttribute("request", requestRent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/refuseRequest.jsp");
        try {

            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("account_id"));
        int coin = Integer.parseInt(request.getParameter("d_coin"));
        User user = userDAO.getUserByAccountId(id);
        if (user.getName() != null) {
            int currentCoin = user.getCoin();
            int afterDeposit = currentCoin + coin;
            userDAO.deposit(afterDeposit, id);
            request.setAttribute("id", id);
            request.setAttribute("coin", afterDeposit);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/DepositSuccess.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void refuseRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int requestId = Integer.parseInt(request.getParameter("request_id"));
        RequestDAO requestDAO = new RequestDAO();
        requestDAO.deleteRecordByRequestId(requestId);

        HttpSession session = request.getSession();
        session.setAttribute("message", "Đã hủy đơn đặt hàng !");
        displayProfile(request, response);
    }

    private void acceptRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int requestId = Integer.parseInt(request.getParameter("request_id"));
        int hours = Integer.parseInt(request.getParameter("hours"));

        RequestDAO requestDAO = new RequestDAO();
        int idPlayer = requestDAO.getIdPlayerByIdRequest(requestId);
        int idUser = requestDAO.getIdUserByIdRequest(requestId);


        PlayerDAO playerDAO = new PlayerDAO();
        Player player = playerDAO.searchPlayerById(idPlayer);
        int pricePlayer = player.getPrice();

        UserDAO userDao = new UserDAO();
        int coinUser = userDao.checkCoinUserByName(idUser);

        if (coinUser < hours * pricePlayer) {
            request.setAttribute("coin", Integer.parseInt(request.getParameter("coin")));
            requestDAO.deleteRecordByRequestId(requestId);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Đã hủy đơn đặt hàng do người thuê không đủ tiền !");
            displayProfile(request, response);
        } else {
            userDAO.updateCoinUser(idUser, coinUser - hours * pricePlayer);
            playerDAO.updateCoinPlayer(idPlayer, player.getCoin() + hours * pricePlayer);
            requestDAO.deleteRecordByRequestId(requestId);

            request.setAttribute("coin", coinUser - hours * pricePlayer);

            HttpSession session = request.getSession();
            session.setAttribute("message", "Đã nhận lời thuê từ user !");
            displayProfile(request, response);
        }

        displayProfile(request, response);
    }

    private void showUserEditForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            AccountDao accountDao = new AccountDao();

            User user = accountDao.getUserByAccountId(Integer.parseInt(request.getParameter("account_id")));

            ProfileDao profileDao = new ProfileDao();

            Profile profile = profileDao.getProfileByAccountId(Integer.parseInt(request.getParameter("account_id")));

            profile.setName(user.getName());

            request.setAttribute("profile", profile);

            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/UserEdit.jsp");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int accountID = Integer.parseInt(request.getParameter("account_id"));

        AccountDao accountDao = new AccountDao();
        accountDao.updateUserNameByAccountId(accountID, name);
        accountDao.updateProfileUserByAccountId(accountID, age, address, email);
        response.setContentType("html/text");
        response.setCharacterEncoding("UTF-8");
        displayProfile(request, response);
    }

    private void showFormRent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerRent.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void confirmRentRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int hours = Integer.parseInt(request.getParameter("hour"));
        int playerId = Integer.parseInt(request.getParameter("player_id"));
        String des = request.getParameter("description");

        PlayerDAO playerDAO = new PlayerDAO();
        Player player = playerDAO.searchPlayerById(playerId);

        int priceRent = hours * player.getPrice();


        AccountDao accountDao = new AccountDao();
        User user = accountDao.getUserByAccountId(Integer.parseInt(request.getParameter("account_id")));

        UserDAO userDao = new UserDAO();
        int coinUser = user.getCoin();
        HttpSession session = request.getSession();

        if (coinUser < priceRent) {
            session.setAttribute("message", "Số tiền của bạn không đủ để thuê người chơi !");

        } else {
            RequestDAO requestDAO = new RequestDAO();
            requestDAO.insertRequest(hours,des,user.getId(),player.getPlayer_id());
            session.setAttribute("message", "Chúc mừng bạn đã thuê thành công !");
        }

        goHomePage(request,response);
    }
}

