<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <title>${requestScope.post.title} - JavaBlog</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/post-page-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/navbar-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/sidebar-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/searchbar-style.css">
        <script src="https://kit.fontawesome.com/37f59747b1.js" crossorigin="anonymous"></script>
        <link href="https://fonts.googleapis.com/css2?family=Alegreya&family=Fredoka+One&family=Josefin+Sans:wght@200&family=Lato:wght@300&family=Patua+One&family=Poppins:wght@200&family=Varela+Round&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../segments/navbar.jspf"%>
        <div id="spacer"></div>
        <div id="main-section">
            <%@include file="../segments/searchbar.jspf"%>
            <div id="main-container">
                <main id="post">
                    <div id="post-children-container">
                        <p id="post-title">${requestScope.post.title}</p>
                        <hr id="post-children-separator">
                        <p id="post-info">${requestScope.post.username}, ${requestScope.post.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
                        <p id="main-post-content">${requestScope.post.content}</p>
                    </div>
                </main>
                <%@ include file="../segments/sidebar.jspf"%>
            </div>
        </div>
    </body>
</html>