<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>

    <div class="loginMainContainer">
        <div class="reg">
            <img alt="" src="./assets/Naruto.jpg" class="image-register" height="500px">
        </div>
        <div class="login-container">
            <div class="login-header">
                <h1>Welcome Back</h1>
                <p>Please enter your credentials to login</p>
            </div>

            <!-- Display session messages -->
            <%
			    String loginMessage = (String) session.getAttribute("pass");
			    if (loginMessage != null) {
			%>
			    <p class="msg"><%= loginMessage %></p>
			<%
			        session.removeAttribute("pass"); // Corrected to match the stored attribute name
			    }
			%>
			
			<%
			    String loginError = (String) session.getAttribute("loginError");
			    if (loginError != null) {
			%>
			    <p class="error-msg"><%= loginError %></p>
			<%
			        session.removeAttribute("loginError"); // Remove after displaying
			    }
			%>

            <form action="login" method="post">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="email" placeholder="Enter your email" required />
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" placeholder="Enter your password" required />
                </div>
                <div class="remember-forgot">
                    <label class="remember-me">
                        <input type="checkbox" />
                        Remember me
                    </label>
                    <a href="forget.jsp" class="forgot-password">
                        Forgot Password?
                    </a>
                </div>
                <button type="submit" class="login-button">
                    Log In
                </button>
                <div class="signup-link">
                    Don't have an account? <a href="register.jsp">Sign up</a>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
