<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
    <head>
        <title>Admin panel</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/panel-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/navbar-style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/admin-panel-style.css">
        <script src="https://kit.fontawesome.com/37f59747b1.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="../../scripts/admin-panel-script.js"></script>
        <link href="https://fonts.googleapis.com/css2?family=Alegreya&family=Fredoka+One&family=Josefin+Sans:wght@200&family=Lato:wght@300&family=Patua+One&family=Poppins:wght@200&family=Varela+Round&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../segments/navbar.jspf"%>
        <div id="page-spacer"></div>
        <main id="page-container">
            <div id="page-items">
                <section id="panel-menu">
                    <button onclick="showPostTab()" class="menu-button" type="button">
                        <i class="fa-solid menu-button-icon fa-clone fa-xl"></i>
                        <p class="menu-button-signature">New post</p>
                    </button>
                    <button onclick="showGeneralTab()" class="menu-button" type="button">
                        <i class="fa-solid menu-button-icon fa-user fa-xl"></i>
                        <p class="menu-button-signature">General</p>
                    </button>
                    <button onclick="showStatisticsTab()" class="menu-button" type="button">
                        <i class="fa-solid menu-button-icon fa-chart-column fa-xl"></i>
                        <p class="menu-button-signature">Statistics</p>
                    </button>
                    <button onclick="showSettingsTab()" class="menu-button" type="button">
                        <i class="fa-solid menu-button-icon fa-gear fa-xl"></i>
                        <p class="menu-button-signature">Settings</p>
                    </button>
                </section>
                <div id="panel-views-container">
                    <div id="new-post-view" class="panel-view">
                        <div class="panel-items">
                            <form method="get" action="${pageContext.request.contextPath}/new" id="new-post-form">
                                <div class="new-post-element-container">
                                    <label class="new-post-label" for="post-title-input">Title</label>
                                    <textarea name="title" id="post-title-input" spellcheck="false" class="new-post-input"></textarea>
                                </div>
                                <div class="new-post-element-container">
                                    <select name="categories" form="new-post-form" title="post-categories" id=categories>
                                        <c:forEach var="category" items="${requestScope.categories}">
                                            <c:set var="description" value="${category.description}"/>
                                            <option title="${description}"><c:out value="${category.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="new-post-element-container">
                                    <label class="new-post-label" for="post-description-input">Description</label>
                                    <textarea name="description" id="post-description-input" spellcheck="false" class="new-post-input"></textarea>
                                </div>
                                <div class="new-post-element-container">
                                    <label class="new-post-label" for="post-content-input">Content</label>
                                    <textarea name="content" id="post-content-input" spellcheck="false" class="new-post-input"></textarea>
                                </div>
                                <div class="new-post-element-container">
                                    <input value="Add post" id="post-submit-button" type="submit">
                                </div>
                            </form>
                        </div>
                    </div>
                    <div id="general-view" class="panel-view">
                        <div class="panel-items">
                            <div class="panel-items-wrapper">
                                <div class="user-information-item">
                                    <p class="user-information-label">Username</p>
                                    <p class="user-information-display">${requestScope.user.username}</p>
                                </div>
                                <div class="user-information-item">
                                    <p class="user-information-label">E-mail</p>
                                    <p class="user-information-display">${requestScope.user.email}</p>
                                </div>
                                <div class="user-information-item">
                                    <p class="user-information-label">Registration date</p>
                                    <p class="user-information-display">${requestScope.user.registrationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="statistics-view" class="panel-view">
                        <div class="panel-items">
                            <div class="panel-items-wrapper">
                                <div class="user-information-item">
                                    <p class="user-information-label">Posted comments</p>
                                    <p class="user-information-display">0</p>
                                </div>
                                <div class="user-information-item">
                                    <p class="user-information-label">Liked posts</p>
                                    <p class="user-information-display">${requestScope.user.voteUp}</p>
                                </div>
                                <div class="user-information-item">
                                    <p class="user-information-label">Disliked posts</p>
                                    <p class="user-information-display">${requestScope.user.voteDown}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="settings-view" class="panel-view">
                        <div class="panel-items">
                            <div id="settings-forms-wrapper">
                                <form class="settings-form">
                                    <div class="settings-item">
                                        <label for="new-username-input" class="settings-item-label">New username</label>
                                        <input id="new-username-input" name="username" spellcheck="false" type="text" class="settings-item-input">
                                    </div>
                                    <div class="settings-item">
                                        <input value="Submit" class="settings-submit-button" type="submit">
                                    </div>
                                </form>
                                <form class="settings-form">
                                    <div class="settings-item">
                                        <label for="old-pass-input" class="settings-item-label">Old password</label>
                                        <input id="old-pass-input" name="oldpassword" spellcheck="false" type="password" class="settings-item-input">
                                    </div>
                                    <div class="settings-item">
                                        <label for="new-pass-input" class="settings-item-label">New password</label>
                                        <input type="password" spellcheck="false" name="newpassword" id="new-pass-input" class="settings-item-input">
                                    </div>
                                    <div class="settings-item">
                                        <input value="Submit" class="settings-submit-button" type="submit">
                                    </div>
                                </form>
                                <form class="settings-form">
                                    <div class="settings-item">
                                        <label for="new-email-input" class="settings-item-label">New e-mail</label>
                                        <input id="new-email-input" spellcheck="false" type="email" name="email" class="settings-item-input">
                                    </div>
                                    <div class="settings-item">
                                        <input value="Submit" class="settings-submit-button" type="submit">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>