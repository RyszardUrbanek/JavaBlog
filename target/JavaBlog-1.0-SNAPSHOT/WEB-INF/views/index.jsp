<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <title>JavaBlog</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/index.css">
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
                <section id="post-container">
                    <c:forEach var="post" items="${requestScope.posts}">
                        <div class="post">
                            <p class="post-title">
                                <c:out value="${post.title}"/>
                            </p>
                            <p class="post-info">
                                <c:out value='${post.username}, ${post.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}'/>
                            </p>
                            <p class="post-intro">
                                <c:out value="${post.introduction}"/>
                            </p>
                            <div class="post-details">
                                <div class="like">
                                    <p class="like-number">${post.voteUp}</p>
                                    <a href="${pageContext.request.contextPath}/post/vote?id=${post.id}&type=UP" class="post-vote-link"><i class="fa-solid rating-icon fa-circle-arrow-up"></i></a>
                                </div>
                                <div class="dislike">
                                    <p class="dislike-number">${post.voteDown}</p>
                                    <a href="${pageContext.request.contextPath}/post/vote?id=${post.id}&type=DOWN" class="post-vote-link"><i class="fa-solid rating-icon fa-circle-arrow-down"></i></a>
                                </div>
                                <a title="View full post" class="post-link" href="${pageContext.request.contextPath}/post${post.url}"><i class="fa-solid post-link fa-angle-right"></i></a>
                                <div class="arrow-link-spacer"></div>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <%@ include file="../segments/sidebar.jspf"%>
            </div>
        </div>
    </body>
</html>