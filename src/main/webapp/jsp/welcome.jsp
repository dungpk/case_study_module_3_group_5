<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
  <style>
    .header{
        float: left;
        width: 100%;
        height: 100px;
        background: black;
        display: flex;
        flex-direction: row;
        align-items: center;
    }
    .header1{
        width: 50%;
        display: flex;
        flex-direction: row;
    }
    .header2{
        width: 50%;
        display: flex;
        justify-content: right;
    }
    .header2 a button{
        font-weight: 600;
        margin-right: 20px;
        width: 100px;
        height: 35px;
        color: #03e9f4;
        background: #fff;
        border: none;
        cursor: pointer;
        border-radius: 5px;
    }
    .header2 a button:hover{
        background: #03e9f4;
        color: #fff;
        box-shadow: 0 0 5px #03e9f4,
        0 0 25px #03e9f4,
        0 0 50px #03e9f4,
        0 0 100px #03e9f4;
    }
    .content{
        width: 100%;
    }
    body{
        width: 100%;
        margin: 0;
        padding: 0;
        font-family: sans-serif;
        background: linear-gradient(
                #141e30, #243b55);
    }
    .sidebar{
        float: left;
        width: 20%;
        background: #141e30;
        height: 800px;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
    .sidebar a{
        padding: 20px;
        font-size: 150%;
        color: #fff;
        text-decoration: none;
        /*margin-left: 50px;*/
    }
    .sidebar a:hover{
        display: block;
        /*width: 100%;*/
        /*margin-right: 50px;*/
        background: #03e9f4;
    }
    .container{
        width: 80%;
        display: list-item;
        /*margin-left: 30%;*/
        float: right;
    }
    h2{
        margin-left: 60px;
        margin-bottom: 30px;
        letter-spacing: 3px;
        font-size: 150%;
        font-weight: 800;
        font-family: sans-serif Arial;
    }
    h2 span{
        font-weight: bold;
        font-family: sans-serif Azonix;
        letter-spacing: 2px;
        animation: flex 2s infinite;
        cursor: pointer;
    }
    @keyframes flex {
        from{text-shadow: 0 0 5px #03e9f4,
                        0 0 25px #03e9f4;
            border: none;
        }
        to{text-shadow: 0 0 50px #03e9f4,
            0 0 100px #03e9f4;
        }
    }

    img{
        margin-top: 50px;
        width: 500px;
        height: 290px;
        margin-left: 60px;
    }
    .container a img:hover{
        cursor: pointer;
        background: #03e9f4;
        box-shadow: 0 0 5px #03e9f4,
        0 0 25px #03e9f4,
        0 0 50px #03e9f4,
        0 0 100px #03e9f4;
        border-radius: 5px;
    }
    .footer{
        clear: both;
        width: 100%;
        height: 150px;
        background: black;
        color: #fff;
        display: flex;
        justify-content: right;
        /*align-items: center;*/
        flex-direction: row;
    }
    .footer a{
        margin-top: 10px;
        margin-right: 20px;
        font-size: 120%;

    }
  </style>
</head>
<body>
<div class="header">
    <div class="header1">
        <h2 style="color: #fff">QUAT<span style="font-size: 120%; color: #03e9f4" >DUO</span></h2>
        <h2 style="color: #fff">About Us</h2>
    </div>
    <div class="header2">
        <a href="/Admin?action=login"><button>Login</button></a>
        <a href="/Admin?action=register"><button>
            Register</button></a>
    </div>

</div>
<div class="content">
    <div class="sidebar">
        <a href="#">WebSite</a>
        <a href="#">Fan Page</a>
        <a href="#">Players</a>
        <a href="#">News</a>
        <a href="#">Setting</a>
    </div>
    <div class="container">
        <a href="/Admin?action=login"><img src="Welcome/fb_image.png" alt=""></a>
        <a href="/Admin?action=login"><img src="Welcome/open-graph-2e582ae9fae8b0b396ca46ff21fd47a8.jpg" alt=""></a>
        <a href="/Admin?action=login"><img src="Welcome/Valorant-Mobile.jpg" alt=""></a>
        <a href="/Admin?action=login"><img src="Welcome/cau-hinh-may-tinh-choi-fo4.jpg" alt=""></a>
    </div>
</div>
<div class="footer">
    <a>Lucky88</a>
    <a>1xBet</a>
</div>
</body>
</html>