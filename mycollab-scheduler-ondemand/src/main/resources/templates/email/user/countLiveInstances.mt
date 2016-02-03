<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table border="1">
    <tr>
      <th>Count: $!count</th>
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
    </tr>
    #foreach( $instance in $instances )
    <tr>
      <th>$!instance.appVersion</th>
      <th>$!instance.javaVersion</th>
      <th>$!instance.installedDate</th>
      <th>$!instance.lastUpdatedDate</th>
      <th>$!instance.sysProperties</th>
    </tr>
    #end
  </table>
</body>
</html>