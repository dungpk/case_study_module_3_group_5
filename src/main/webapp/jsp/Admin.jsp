<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        margin-right: 20px;
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
        display: flex;
        flex-direction: row;
        justify-content: center;
        height: 2000px;
      }
      .content{
        height: 100%;
        margin-top: 100px;
        background-color: #1f1f22;
        padding-top: 100px;
      }
      table{
        font-family: Tahoma;
        margin: auto;
        border: 1px solid #03e9f4;
        color: #03e9f4;
        border-collapse: collapse;
      }
      th{
        border: 0.5px solid #03e9f4;
        border-collapse: collapse;
        width: 100px;
        padding: 10px 30px;
      }
      td{
        border: 0.5px solid #03e9f4;
        padding: 10px 30px;
      }
      table button{
        padding: 5px;
        margin-left: 5px;
      }
    </style>
</head>
<body>
<div class="header col-12">
  <div class="header1 col-6">
    <a href=""><h2 style="color: #fff" id="logo">QUAT<span style="font-size: 120%; color: red" >DUO</span></h2></a>
  </div>
  <div class="header2 col-6">
    <a href="#"><button>Trang chủ</button></a>
  </div>
</div>
<div class="main col-12">
    <div class="content col-10">
        <table>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Password</th>
              <th>Role</th>
              <th>Action</th>
            </tr>
            <c:forEach items="${requestScope['list']}" var="account">
                <tr>
                    <td>${account.getId()}</td>
                    <td>${account.getUsername()}</td>
                    <td>${account.getPassword()}</td>
                    <td>${account.getRole()}</td>
                    <td><a href="/admin?action=edit&id=${account.getId()}&role=${account.getRole()}"><button>Edit</button></a><a href="/admin?action=delete?id=${account.getId()}&role=${account.getRole()}"><button>Delete</button></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>