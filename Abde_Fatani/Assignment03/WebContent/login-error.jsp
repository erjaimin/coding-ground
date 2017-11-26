<html>
<head>
    <title>Login Error</title>
    <style type="text/css">
        input[type=text], input[type=password] {
            display: block;
        } 
    </style>
</head>
<body>
	<h1>Sign In</h1>
	<p style="color: #D8000C; font-weight: bold;">Incorrect username or password, please check the credentials and try again.</p>
	<form action="j_security_check" method="post" name="loginForm">
	    <label for="user">User name:</label>
	    <input id="user" type="text" name="j_username" size="30">
	
	    <label for="password">Password </label>
	    <input id="password" type="password" name="j_password" size="30">
	
	    <input type="submit" value="Sign In">
	</form>
</body>
</html>