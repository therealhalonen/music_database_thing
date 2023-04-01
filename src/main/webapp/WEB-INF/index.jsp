<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!-- Cool classless css: https://github.com/Kimeiga/bahunya -->
    <!-- Because everything possible should be in Dark Mode! -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/kimeiga/bahunya/dist/bahunya.min.css" type="text/css">
    <title>Music Database Thing</title>
</head>
<body>
<h1>Music Database Thing!</h1>
<p>
    You can view full list of artists and albums included in the database! <br/>
    Or you can search artists by artist name (even partial name is valid)
</p>
<a href="<c:out value='${pageContext.request.contextPath}'/>/artists">List All Artists</a>
<a href="<c:out value='${pageContext.request.contextPath}'/>/albums">Show all albums</a>

<h3>Search Artists:</h3>
<form method="GET" action="<c:out value='${pageContext.request.contextPath}'/>/artists/search">
    <label for="artistName">Artist Name:</label>
    <input type="text" id="artistName" name="artistName" placeholder="Search Artists" required/>
    <button type="submit">Search</button>
</form>

<h3>Add a new artist</h3>
<form method="POST" action="<c:out value='${pageContext.request.contextPath}'/>/artists">
    <label for="artistName2">Artist Name</label>
    <input type="text" id="artistName2" name="artistName" placeholder="Add Artist" required/>
    <input type="hidden" name="action" value="add">
    <button type="submit">Add Artist</button>
</form>
</body>
</html>