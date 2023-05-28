<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body{
            margin: 0;
            padding: 0;
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
            display: flex;
            background-color: #243b55;
            justify-content: center;
        }
        .header{
            height: 64px;
            background-color: #141e30;
            position: fixed;
            display: flex;
            flex-direction: row;
            align-items: center;
            text-indent: 40px;
            z-index: 1;
        }
        .header a{
            text-decoration: none;
            font-size: 20px;
            font-family: Arial;
            font-weight: bold;
            color: #ffff;
            letter-spacing: 2px;
        }
        .header a span{
            font-size: 120%;
            color: #03e9f4;
            animation: flexing 2s infinite;
        }
        @keyframes flexing {
            from{text-shadow: 0 0 5px #03e9f4,
            0 0 25px #03e9f4}
            to{text-shadow: 0 0 50px #03e9f4,
            0 0 100px #03e9f4;}
        }
        .main{
            margin-top: 64.8px;
            height: 600px;
            background-color: #1f1f22;
            display: block;
            border-radius: 10px;
        }
        .real-money{
            height: 300px;
            border: 1px solid #03e9f4;
            margin-top: 50px;
            margin-left: 70px;
            border-radius: 10px;
            float: left;
            width: 18%;
            display: flex;
            flex-direction: column;
        }
        .deposit-way{
            margin-top: 45px;
            height: 200px;
            /*border: 1px solid #03e9f4;*/
            float: left;
            display: flex;
            flex-direction: row;
            overflow-x: auto;
            align-items: center;
            margin-left: 240px;
        }
        ::-webkit-scrollbar-track{
            height: 5px;
            background-color: #1f1f22;
        }
        ::-webkit-scrollbar{
            height: 5px;
        }
        ::-webkit-scrollbar-thumb{
            background-color: #03e9f4;
        }
        .real-money img{
            width: 100%;
            height: 65%;
            border-radius: 10px;
        }
        .real-money a{
            text-indent: 80px;
            margin-top: 25px;
        }
        a{
            text-decoration: none;
            color: #03e9f4;
        }
        button{
            padding: 8px 10px;
            border-radius: 5px;
            background-color: #03e9f4;
            color: #fff;
            box-shadow: 0 0 0 1px #03e9f4;
            border: none;
            cursor: pointer;
            font-family: sans-serif Arial;
            font-weight: 600;
        }
        button:hover{
            color: #03e9f4;
            background: #fff;
        }
        .deposit-way img{
            height: 60%;
            width: 200px;
            margin-left: 30px;
            border-radius: 10px;
        }
        .header2{
            display: flex;
            justify-content: right;
            align-items: center;
        }
        .header2 button{
            margin-right: 20px;
        }
        .coin{
            color: #03e9f4;
            font-size: 17px;
        }
        .coin span{
            color: yellow;
            font-size: 120%;
        }
    </style>
</head>
<body>
<div class="header col-12">
    <div class="header1 col-6">
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}">QUAT<span>DUO</span></a>
    </div>
    <div class="header2 col-6">
        <a href="/quat?action=deposit&account_id=${requestScope['id']}"><p class="coin">Coin: <span>${requestScope['coin']}</span></p></a>
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}"><button>Trang chủ</button></a>
        <a href="/quat?action=profile&account_id=${requestScope['id']}"><button>Trang cá nhân</button></a>
    </div>
</div>
<div class="main col-10">
    <div class="real-money ">
        <img src="../image/Coins/coin.png" alt="">
        <a href="/quat?action=coin_deposit&account_id=${requestScope['id']}&coin=100"><button>100 coin</button></a>
    </div>
    <div class="real-money">
        <img src="../image/Coins/coin.png" alt="">
        <a href="/quat?action=coin_deposit&account_id=${requestScope['id']}&coin=500"><button>500 coin</button></a>
    </div>
    <div class="real-money">
        <img src="../image/Coins/coin.png" alt="">
        <a href="/quat?action=coin_deposit&account_id=${requestScope['id']}&coin=1000"><button>1000 coin</button></a>
    </div>
    <div class="real-money">
        <img src="../image/Coins/coin.png" alt="">
        <a href="/quat?action=coin_deposit&account_id=${requestScope['id']}&coin=1500"><button>1500 coin</button></a>
    </div>
    <div class="deposit-way col-8">
        <img src="../image/Coins/MasterCard-Logo-1990.png" alt="">
        <img src="../image/Coins/visa.png" alt="">
        <img src="../image/Coins/viettin.jpg" alt="">
        <img src="../image/Coins/vietcombank.jpg" alt="">
        <img src="../image/Coins/tech.png" alt="">
    </div>
</div>
</body>
</html>