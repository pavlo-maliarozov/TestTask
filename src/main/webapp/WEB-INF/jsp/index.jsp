<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head><title>SpringBoot</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>

<table>
  <tr>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Birthday</th>
    <th>Gender</th>
    <th>Action</th>
  </tr>
   <c:forEach var = "userInfo" items = "${userList}">
  <tr>
    <td>${userInfo.userId}</td>
    <td>${userInfo.firstName}</td>
    <td>${userInfo.lastName}</td>
      <td>${userInfo.birthday}</td>
      <td>${userInfo.gender}</td>
    <td>
        <a href="/view/${userInfo.userId}">View</a>
        <a href="/delete/${userInfo.userId}">Delete</a>
        <a href="/edit/${userInfo.userId}">Edit</a>
    </td>
  </tr>
  </c:forEach>
</table>
<hr/>
<form method="post" action="/save">
<input type="hidden" name="userId" value=""/>
  First name:<br>
  <input type="text" name="firstName"/>
  <br>
  Last name:<br>
  <input type="text" name="lastName" >
    <br>
    Birthday:<br>
    <input type="date" name="birthday" >
    <br>
    Gender:<br>
    <input type="radio" name="gender" value="M" > Male <br>
    <input type="radio" name="gender" value="F" > Female <br>
    <input type="radio" name="gender" value="O" checked > Other <br>
  <br><br>
  <input type="submit" value="Submit">
</form>

</body>
</html>
