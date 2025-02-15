<%@page import="java.awt.Color"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forget Password</title>
<style>
    .forget {
        min-height: 100vh;
        background:white;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
    }
    .forget-card {
        background: white;
        padding: 80px;
        border-radius: 12px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
        text-align: center;
    }

    .forget-lock-icon {
        font-size: 40px;
        margin-bottom: 16px;
    }

    .forget-card h1 {
        color: #2d3748;
        font-size: 28px;
        margin-bottom: 32px;
        font-weight: 700;
    }

    .forget-card input {
        width: 100%;
        padding: 12px;
        margin-bottom: 24px;
        border: 1px solid #d71010;
        border-radius: 6px;
        font-size: 16px;
        outline: none;
    }

    .forget-card input:focus {
        border-color: #4c84f3;
    }

    .forget-card button {
        width: 100%;
        padding: 12px;
        background:blue;
        color: white;
        border: none;
        border-radius: 6px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.2s;
    }

    .forget-card button:hover {
        background-color: #4c84f3;
    }

    .error-message {
        color: red;
        margin-bottom: 10px;
        display: <%=(request.getParameter("error") != null) ? "block" : "none"%>;
    }
    .invalid-meaasage{
    	color: red;
    }
</style>
</head>
<body>
	
	 		<% 
                String message=null;
                String invalidmessage = (String) session.getAttribute("invalidmail");
                if(invalidmessage!=null){
                	message=invalidmessage;
                }else{
                	message="";
                }
               %>
    <div class="forget">
        <form action="fetchemail" method="post" class="forget-card">
            <div class="forget-lock-icon">🔒</div>
            <h1>Reset Password</h1>
           
            <p class="invalid-meaasage" class="error-message">
                <%= message %>
            </p>
            <input type="email" name="email" placeholder="Enter Email Id" required>
            <button type="submit" value="fetchemail">Reset Password</button>
        </form>
    </div>

</body>
</html>
