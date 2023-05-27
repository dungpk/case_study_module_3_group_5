<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
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
      .content{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 600px;
      }
      .form{
          justify-content: center;
        display: flex;
          flex-direction: column;
        height: 600px;
        background-color: #1f1f22;
        color: #fff;
          font-family: Tahoma;
          font-size: 17px;
          border-radius: 10px;
      }
      .form input{
        margin-left: 20px;
          margin-top: 20px;
      }
      #submit{
          background-color: #03e9f4;
          color: white;
          padding: 5px 8px;
          border: none;
          border-radius: 5px;
          font-size: 15px;
          font-family: Tahoma;
          width: 100px;
      }
      #submit:hover{
          color: #03e9f4;
          background-color: white;
          cursor: pointer;
      }
      .form h2{
          margin-left: 20px;
      }

  </style>
</head>
<body>
<div class="header col-12">
      <div class="header1 col-6">
          <a href="#">QUAT<span>DUO</span></a>
      </div>
      <div class="header2 col-6"></div>
</div>
<div class="content col-12">
    <form action="#" class="form col-4" method="post">

        <h2>Check the game you play:</h2>
        <div class="group">
            <input type="checkbox" name="game[]" value="CSGO" id="cs">
            <label for="cs">CSGO</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" value="FO4" id="fifa">
            <label for="fifa">FO4</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" id="lol" value="Liên minh huyền thoại">
            <label for="lol">Liên minh huyền thoại</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" id="ff" value="Free fire">
            <label for="ff">Free fire</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" id="pubg" value="PUBG">
            <label for="pubg">PUBG</label>
        </div>
        <div class="group">
             <input type="checkbox" name="game[]" id="cod" value="COD">
            <label for="cod">COD</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" id="val" value="Valorant">
            <label for="val">Valorant</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" id="gta" value="GTA V">
            <label for="gta">GTA V</label>
        </div>
        <div class="group">
            <input type="checkbox" name="game[]" id="tocchien" value="Liên minh tốc chiến">
            <label for="tocchien">Liên minh tốc chiến</label>
        </div>
        <div class="group">
             <input type="checkbox" name="game[]" id="dota" value="Dota 2">
            <label for="dota">Dota 2</label>
        </div>
        <input type="submit" id="submit" value="Submit">
    </form>
</div>

</body>
</html>