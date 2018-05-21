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
<hr/>
<form method="post" action="/save">
  <input type="hidden" name="userId" value="${user.userId}"/>
  First name:<br>
  <input type="text" name="firstName" value="${user.firstName}"/>
  <br>
  Last name:<br>
  <input type="text" name="lastName" value="${user.lastName}">
  <br>
  Birthday:<br>
  <input type="date" name="birthday" value="${user.birthday}">
  <br>
  Gender:<br>
  <input type="radio" name="gender" value="${user.gender}" > Male <br>
  <input type="radio" name="gender" value="${user.gender}" > Female <br>
  <input type="radio" name="gender" value="${user.gender}" checked > Other <br>
  <br><br>
  <input type="submit" value="Submit">
</form>


</body>
</html>
