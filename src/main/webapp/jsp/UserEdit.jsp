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
        body{
            padding: 0;
            margin: 0;
            background-color: #243b55;
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
        ::-webkit-scrollbar{
            border-radius: 10px;
            background: #8e8fb5;
            height: 5px;
            width: 5px;
        }
        .header2{
            display: flex;
            flex-direction: row;
            justify-content: right;
            padding-right: 10px;
        }
        .header2 button{
            margin-right: 10px;
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
        .main{
            margin: auto;
            background-color: #1f1f22;
            height: 800px;
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            color: #03e9f4;
            font-size: 17px;
            font-family: Tahoma;
            padding-left: 30px;
        }
        img{
            border-radius: 50%;
            width: 200px;
        }
        fieldset{
            /*margin-bottom: 30px;*/
            border: 1px solid #03e9f4;
        }
        fieldset input{
            margin-top: 60px;
            width: 200px;
            height: 30px;
            border-radius: 10px;
            margin-left: 20px;
        }
        #submit{
            color: white;
            background-color: #03e9f4;
            width: 150px;
            height: 40px;
            cursor: pointer;
            font-size: 17px;
        }
        #submit:hover{
            color: #03e9f4;
            background-color: white;
        }
        .coin{
            color: red;
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
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}"></a><button>Trang chủ</button>
        <a href="/quat?action=deposit&account_id=${requestScope['id']}"></a><button>Nạp tiền</button>
        <a href="/quat?action=logout"></a><button>Đăng xuất</button>
    </div>
</div>
<div class="main col-10">
    <form method="post" action="">
        <input type="hidden" name="action" value="user_edit"    >
        <fieldset>
            <legend><img src="../image/Admin/default.jpg" alt=""></legend>
            <label>Tên: </label><input type="text" name="name"><br>
            <label>Tuổi: </label>
            <input type="text" name="age"><br>
            <label>Email: </label>
            <input type="text" name="email"><br>
            <label>Địa chỉ: </label>
            <input type="text" name="address"><br>
            <input type="submit" value="Đồng ý" id="submit">
        </fieldset>
    </form>
</div>
</body>
</html>