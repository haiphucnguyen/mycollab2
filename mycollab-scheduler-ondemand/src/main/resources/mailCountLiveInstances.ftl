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
      <th>App Version</th>
      <th>Java Version</th>
      <th>Installed time</th>
      <th>Last accessed time</th>
      <th>Properties</th>
      <th>Num of Users</th>
      <th>Num of Projects</th>
    </tr>
    <#list instances as instance>
    <tr>
      <th>${instance.appversion!}</th>
      <th>${instance.javaversion!}</th>
      <th>${instance.installeddate?date!}</th>
      <th>${instance.lastupdateddate?date!}</th>
      <th>${instance.sysproperties!}</th>
      <th>${instance.numusers!}</th>
      <th>${instance.numprojects!}</th>
    </tr>
    </#list>
  </table>
</body>
</html>