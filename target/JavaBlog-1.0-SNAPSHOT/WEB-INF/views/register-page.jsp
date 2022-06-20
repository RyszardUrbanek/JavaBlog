<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign up</title>
        <link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/styles/form-style.css">
        <link rel=stylesheet type="text/css" href="${pageContext.request.contextPath}/styles/navbar-style.css">
        <link href="https://fonts.googleapis.com/css2?family=Alegreya&family=Fredoka+One&family=Josefin+Sans:wght@200&family=Lato:wght@300&family=Patua+One&family=Poppins:wght@200&family=Varela+Round&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../segments/navbar.jspf"%>
        <main id="page-container">
            <div id="form-container">
                <form id="form" method="post" action="register">
                    <p id="form-header">Sign up</p>
                    <div class="form-item">
                        <label class="form-label" for="username-input">Username</label>
                        <input id="username-input" type="text" name="username" class="form-input" spellcheck="false">
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="email-input">E-mail</label>
                        <input id="email-input" type="email" name="email" class="form-input" spellcheck="false">
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="password-input">Password</label>
                        <input id="password-input" type="password" name="password" class="form-input" spellcheck="false">
                    </div>
                    <div class="form-item">
                        <label class="form-label" for="repeat-password-input">Repeat password</label>
                        <input id="repeat-password-input" type="password" name="repeatpassword" class="form-input" spellcheck="false">
                    </div>
                    <div class="form-item">
                        <input id="submit-button" type="submit" value="Register">
                    </div>
                    <div id="form-asterisk">
                        <p id="form-asterisk-line">Already have an account? <a id="asterisk" href="login">Sign in!</a></p>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>