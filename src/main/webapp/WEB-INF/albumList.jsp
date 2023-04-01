<%--@elvariable id="artist" type="jdk.javadoc.internal.doclets.toolkit.PropertyUtils"--%>
<%--@elvariable id="albumTitles" type="java.util.List"--%>
<%--@elvariable id="allAlbums" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Cool classless css: https://github.com/Kimeiga/bahunya -->
    <!-- Because everything possible should be in Dark Mode! -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/kimeiga/bahunya/dist/bahunya.min.css" type="text/css">

    <!-- Define title according to presence of ArtistId -->
    <title>
        <c:choose>
            <c:when test="${not empty param.ArtistId}">
                Albums of artist
            </c:when>
            <c:otherwise>
                All albums in database
            </c:otherwise>
        </c:choose>
    </title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Index</a> <a href="#" onclick="history.back();">Back</a>

<!-- Define what to show based on the ArtistId presence.
Either 'Albums of specific artist' or the 'Full list of albums in database' -->
<c:choose>
    <c:when test="${not empty param.ArtistId}">
        <h1>Albums of <c:out value="${artist.getName()}"/>: </h1>
        <c:forEach items="${albumTitles}" var="albumTitle">
            <li>
                <a href="${pageContext.request.contextPath}/tracks?AlbumId=<c:out value="${albumTitle.getId()}"/>"><c:out
                        value="${albumTitle.getTitle()}"/></a>
                <!-- Remove Album -->
                <form action="${pageContext.request.contextPath}/albums?ArtistId=<c:out value="${param.ArtistId}"/>" method="post">
                    <input type="hidden" name="action" value="remove" placeholder="Remove Album" required>
                    <input type="hidden" name="albumId" value="<c:out value="${albumTitle.getId()}"/>">
                    <button type="submit" class="btn btn-danger">Remove</button>
                </form>
            </li>
        </c:forEach>
        <h1>Add Album</h1>
        <!-- Add album -->
        <form action="${pageContext.request.contextPath}/albums" method="post">
            <label>
                <input type="text" name="title" placeholder="Album Title" required>
            </label>
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="artistId" value="<c:out value="${param.ArtistId}"/>">
            <input type="submit" value="Add Album">
        </form>
    </c:when>
    <c:otherwise>
        <h1>All albums in database: </h1>
        <ol>
            <c:forEach items="${allAlbums}" var="allAlbum">
                <li>
                    <a href="${pageContext.request.contextPath}/tracks?AlbumId=<c:out value="${allAlbum.getId()}"/>"><c:out
                            value="${allAlbum.getTitle()}"/></a>
                </li>
            </c:forEach>
        </ol>
    </c:otherwise>
</c:choose>
</body>
</html>