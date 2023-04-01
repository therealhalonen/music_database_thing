<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="artists" type="java.util.List"--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Cool classless css: https://github.com/Kimeiga/bahunya -->
    <!-- Because everything possible should be in Dark Mode! -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/kimeiga/bahunya/dist/bahunya.min.css" type="text/css">
    <!-- Fix the title, according to where the user came from (From "List All" or "Search specific") -->
    <title>
        <c:choose>
            <c:when test="${not empty param.artistName}">
                Search Results for "<c:out value="${param.artistName}"/>"
            </c:when>
            <c:otherwise>
                All artists in database
            </c:otherwise>
        </c:choose>
    </title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Index</a> <a href="#" onclick="history.back();">Back</a>
<!-- Fix the header, according to where the user came from (From "List All" or "Search specific") -->
<h1>
    <c:choose>
        <c:when test="${not empty param.artistName}">
            Search Results for "<c:out value="${param.artistName}"/>"
        </c:when>
        <c:otherwise>
            All artists in database
        </c:otherwise>
    </c:choose>
</h1>
<!-- Loop through all artists found and add with remove button-->
<ol>
    <c:forEach items="${artists}" var="artist">
        <li>
            <a href="${pageContext.request.contextPath}/albums?ArtistId=<c:out value="${artist.getId()}"/>"><c:out
                    value="${artist.getName()}"/></a> <i>Albums: <c:out value="${artist.getAlbumCount()}"/></i>
            <!-- Remove -->
            <form method="post" action=${pageContext.request.contextPath}"/artists">
                <input type="hidden" name="artistId" value="<c:out value="${artist.getId()}"/>">
                <input type="hidden" name="action" value="remove">
                <!-- Handle the passing of "returnUrl", based on the variable: Is the current page "List All" or "Search results -->
                <c:choose>
                    <c:when test="${not empty param.artistName}">
                        <input type="hidden" name="returnUrl"
                               value="${pageContext.request.contextPath}/artists/search?artistName=<c:out value="${param.artistName}"/>">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="returnUrl" value="/artists">
                    </c:otherwise>
                </c:choose>
                <input type="submit" value="Remove">
            </form>
        </li>
    </c:forEach>
</ol>
</body>
</html>