<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href='https://unpkg.com/css.gg@2.0.0/icons/css/search.css' rel='stylesheet'>
    <link rel="stylesheet" href="../css/home.css">

</head>
<body>
<div class="header col-12">
    <div class="header1 col-6">
        <h2 style="color: #fff" id="logo">QUAT<span style="font-size: 120%; color: red" >DUO</span></h2>
        <form action="/quat" method = "post">
            <input hidden="hidden" name="action" value = "search_player">
            <input type="text" name="search" id="search" placeholder="  Enter something">
            <a href="" id="btn-search"><i class="gg-search"></i></a>
            <input type="submit" id="submit">
        </form>
    </div>
    <div class="header2 col-6">
        <a href=""><button>Trang cá nhân</button></a>
        <a href=""><button>Đăng xuất</button></a>
        <a href=""><button>Nạp tiền</button></a>
    </div>
</div>
<div class="sidebar col-2">
    <h3>Danh mục game</h3>
    <div class="sidebar_items col-12">
        <c:forEach items="${requestScope['gameList']}" var="game">
            <div class="imgside">
                <a href="/quat?action=search_player_by_game&id=${game.getId()}" ><img src="${game.getImageSource()}" alt=""></a>
                <a href="/quat?action=search_player_by_game&id=${game.getId()}" >${game.getName()}</a>
            </div>
        </c:forEach>
    </div>
</div>
<div class="content col-10">
    <div class="banner col-11">
        <img src="../image/game/banner.jpg" alt="" >
    </div>
    <h2 id="vip">VIP PLAYERS</h2>
    <div class="player vip col-11">
        <c:forEach var="player" items="${requestScope['vipList']}">
            <div class="detail_player col-2 vip">
                <div class="detail_pic col-12">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}"><img src="${player.getImg()}" alt=""></a>
                </div>
                <div class="detail">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}"><p>Name: ${player.getName()}</p></a>
                    <p>Giá thuê: <span class="price">${player.getPrice()}/trận</span></p>
                </div>
            </div>
        </c:forEach>

    </div>
    <h2 id="hot">HOT PLAYERS</h2>
    <div class="player hot col-11">
        <c:forEach items="${requestScope['hotList']}" var="player">
            <div class="detail_player col-2 hot">
                <div class="detail_pic col-12">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}"><img src="${player.getImg()}" alt=""></a>
                </div>
                <div class="detail">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}"><p>Name: ${player.getName()}</p></a>
                    <p>Giá thuê: <span class="price">${player.getPrice()}/trận</span></p>
                </div>
            </div>
        </c:forEach>
    </div>
    <h2 id="new">NEW PLAYERS</h2>
    <div class="player new col-11" id="contain">
        <c:forEach items="${requestScope['playerList']}" var="player">
            <div class="detail_player col-2 new">
                <div class="detail_pic col-12">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}"><img src="${player.getImg()}" alt=""></a>
                </div>
                <div class="detail">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}"><p>Name: ${player.getName()}</p></a>
                    <p>Giá thuê: <span class="price">${player.getPrice()}/trận</span></p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="footer col-12"></div>
</body>
</html>