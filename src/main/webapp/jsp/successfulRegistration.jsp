<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
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
        body{
            background-color: #243b55;
            padding: 0;
            margin: 0;
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
        ::-webkit-scrollbar-track{
            background: #141e30;
        }
        ::-webkit-scrollbar-thumb{
            background: #03e9f4;
            border-radius: 2px;
        }
        .header2{
            display: flex;
            flex-direction: row;
            justify-content: right;
            padding-right: 10px;
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
        .header2 button{
            margin-right: 10px;
        }
        .container{
            margin: auto;
            display: flex;
            height: 600px;
            background: #1f1f22;
            align-items: center;
            justify-content: center;
            font-family: Tahoma;
            font-size: 20px;
        }
        a{
            text-decoration: none;
            color: #03e9f4;
        }
    </style>
</head>
<body>
<div class="header col-12">
    <div class="header1 col-6">
        <a href="jsp/welcome.jsp">QUAT<span>DUO</span></a>
    </div>
    <div class="header2 col-6">
        <a href="jsp/welcome.jsp"><button>Trang chủ</button></a>
    </div>
</div>
<div class="container col-10">
    <a href="/quat?action=login"><h1>Đăng kí thành công</h1></a>
</div>
</div>
</body>