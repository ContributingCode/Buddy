<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>Access token: ${accessToken}. </P>
<P>Token expires on: ${tokenExpires}. </P>
<P>Keywords: ${matcherKeywords}. </P>
</body>
</html>
