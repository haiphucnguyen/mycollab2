<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table border="1">
    <tr>
      <th>Count: ${count}</th>
    </tr>
  </table>
  <br/>
  <table border="1">
    <tr>
      <th>User name</th>
      <th>Email</th>
      <th>Registered time</th>
      <th>Last accessed time</th>
      <th>Subdomain</th>
    </tr>
    <#list lstUser as user>
    <tr>
      <th>${user.username}</th>
      <th>${user.email}</th>
      <th>${user.registeredtime?datetime}</th>
      <th>${user.lastaccessedtime?datetime}</th>
      <th>${user.subdomain!}</th>
    </tr>
    </#list>
  </table>
</body>
</html>