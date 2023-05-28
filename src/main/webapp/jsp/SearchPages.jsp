<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .header{
            position: fixed;
            padding-bottom: 10px;
            padding-left: 15px;
            padding-right: 15px;
            padding-top: 10px;
            height: 65.8px;
            z-index: 10;
            display: flex;
            background: rgb(51, 51, 51);
        }
        .col-1{
            width: 8.33%;
        }
        .col-2{
            width: 16.66%;
        }
        .col-3{
            width: 25%;
        }
        .col-4{
            width: 33.33%;
        }
        .col-5{
            width: 41.66%;
        }
        .col-6{
            width: 50%;
        }
        .col-7{
            width: 58.33%;
        }
        .col-8{
            width: 66.66%;
        }
        .col-9{
            width: 75%;
        }
        .col-10{
            width: 83.33%;
        }
        .col-11{
            width: 91.66%;
        }
        .col-12{
            width: 100%;
        }
        body{
            margin: 0;
            padding: 0;
            background: rgb(41, 41, 43)
        }
        .header1{
            display: flex;
            height: 100%;
        }
        #logo{
            font-family: Arial;
            letter-spacing: 4px;
            text-indent: 50px;
        }
        #logo span{
            animation: flxx 2s infinite;
        }
        @keyframes flxx {
            from{text-shadow: 0 0  5px #03e9f4,
            0  0 25px #03e9f4}
            to{text-shadow: 0 0  50px #03e9f4,
            0 0  100px mediumvioletred}
        }
        .header2{
            font-family: Tahoma;
            letter-spacing: 2px;
            display: flex;
            height: 100%;
            justify-content: right;
        }
        .header2 a{
            color: #fff;
        }
        .sidebar_items{
            cursor: pointer;
            align-items: center;
            display: block;
            height: 40px;
            padding-top: 20px;

            font-family: Arial;
            font-size: 14px;
            font-weight: 200;
        }
        .imgside:hover{
            display: block;
            background: rgb(51, 51, 51);
            width: 100%;
        }
        .imgside{
            margin-top: 10px;
            height: 50px;
            display: block;
            padding-top: 14px;
        }
        .imgside a{
            margin-left: 15px;
            position: relative;
            bottom: 8px;
            text-decoration: none;
            color: white;
            font-weight: 600;
        }
        .imgside img{
            border-radius: 20%;
            margin-left: 10px;
            width: 60px;
        }
        .sidebar{
            max-height: 600px;
            height: 800px;
            background: rgb(41, 41, 43);
            display: block;
            padding-top: 100px;
            position: fixed;
            z-index: 9;
            border-right: 1px solid rgb(51, 51, 51);
            overflow-y: scroll;
        }
        ::-webkit-scrollbar{
            border-radius: 10px;
            background: grey;
            width: 5px;
        }
        .sidebar h3{
            text-indent: 30px;
            color: white;
            font-family: Tahoma;
        }
        .content{
            float: right;
            height: 1000px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .contentheader{
            height: 90px;
        }
        .main{
            font-family: Tahoma;
            letter-spacing: 2px;
            height: 80%;
            padding: 20px 20px;
            color: white;
            display: block;
        }
        .main h2{
            margin-top: 8px;
        }
        .detail_player{
            display: flex;
            height: 340px;
            margin-top: 20px;
            margin-left: 30px;
            flex-direction: column;
            border-radius: 10px;
            float: left;
        }
        .vip{
            border: 1px solid violet;
        }
        .detail_pic{
            height: 75%;
        }
        .detail_pic img{
            width: 100%;
            height: 100%;
            border-radius: 10px;
        }
        .detail{
            text-indent: 10px;
            font-family: Tahoma;
            color: white;
            font-size: 14px;
            height: 25%;
            display: block;
        }
        a{
            text-decoration: none;
        }
        .price{
            padding: 5px;
            background-color: red;
            border-radius: 10px;
            font-size: 13px;
            font-family: Arial;
        }
        .coin{
            color: red;
            font-size: 17px;
        }
        .coin span{
            color: yellow;
            font-size: 120%;
        }
        .header2 button{
            height: 35px;
            background: #1f1f22;
            border: none;
            color: red;
            border-radius: 8px;
            height: 38px;
            font-size: 15px;
            cursor: pointer;
            font-family: Tahoma;
            font-weight: 900;
            margin-right: 10px;
        }
        .header2 button:hover{
            color: #fff;
        }
    </style>
</head>
<body>
<div class="header col-12">
    <div class="header1 col-6">
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}&coin=3000"><h2 style="color: #fff" id="logo">QUAT<span style="font-size: 120%; color: red" >DUO</span></h2></a>
    </div>
    <div class="header2 col-6">
        <a href="/quat?action=deposit&account_id=${requestScope['id']}&coin=3000"><p class="coin">Coin: <span>${requestScope['coin']}</span></p></a>
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}&coin=3000"><button>Trang chủ</button></a>
    </div>
</div>
<div class="sidebar col-2">
    <h3>Danh mục game</h3>
    <div class="sidebar_items col-12">
        <c:forEach items="${requestScope['gameList']}" var="game">
            <div class="imgside">
                <a href="/quat?action=search_player_by_game&id=${game.getId()}&account_id=${requestScope['id']}&coin=3000" ><img src="${game.getImageSource()}" alt=""></a>
                <a href="/quat?action=search_player_by_game&id=${game.getId()}&account_id=${requestScope['id']}&coin=3000" >${game.getName()}</a>
            </div>
        </c:forEach>

    </div>
</div>
<div class="content col-10">
    <div class="contentheader col-12"></div>
    <div class="main col-10">
        <h2 style="color: white">Kết quả:</h2>
        <c:forEach items="${requestScope['playerList']}" var="player">
            <div class="detail_player col-3 vip">
                <div class="detail_pic col-12">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}&account_id=${requestScope['id']}&coin=3000"><img src="${player.getImg()}" alt=""></a>
                </div>
                <div class="detail">
                    <a href="/quat?action=display_player&id=${player.getPlayer_id()}&account_id=${requestScope['id']}&coin=3000"><p>Name: ${player.getName()}</p></a>
                    <p>Giá thuê: <span class="price">${player.getPrice()}coin/trận</span></p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>