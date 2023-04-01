<%--@elvariable id="album" type="java"--%>
<%--@elvariable id="tracks" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Cool classless css: https://github.com/Kimeiga/bahunya -->
    <!-- Because everything possible should be in Dark Mode! -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/kimeiga/bahunya/dist/bahunya.min.css" type="text/css">
    <title>Tracks List</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Index</a> <a href="#" onclick="history.back();">Back</a>
<h1>Tracks of <c:out value="${album.getTitle()}"/>:</h1>
<p><i>Note: clicking the track name leads to external page; DuckDuckGo!<br/>Made purely for testing purposes!</i></p>
<ol>
    <c:forEach items="${tracks}" var="track">
        <li>
            <!-- For testing purposes, made a lyrics search link -->
            <a href="https://duckduckgo.com/?q=lyrics+<c:out value="${track.getName()}"/>"><c:out
                    value="${track.getName()}"/></a>
        </li>
    </c:forEach>
</ol>
</body>
</html>