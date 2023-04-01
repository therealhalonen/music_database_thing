package servlet;

import database.AlbumDao;
import database.ArtistDao;
import model.Album;
import model.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/albums")
public class AlbumsServlet extends HttpServlet {

    private final AlbumDao albumDao = new AlbumDao();
    private final ArtistDao artistDao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String artistIdParam = request.getParameter("ArtistId");

        if (artistIdParam != null) {
            // Show albums of a specific artist
            long artistId = Long.parseLong(artistIdParam);

            List<Album> albums = albumDao.getAlbumsByArtist(artistId);
            Artist artist = artistDao.getArtistById(artistId);

            // Set the artist and formatted album titles as request attributes
            request.setAttribute("artist", artist);
            request.setAttribute("albumTitles", albums);
            request.getRequestDispatcher("/WEB-INF/albumList.jsp").forward(request, response);

        } else {
            // Show all albums
            List<Album> allAlbums = albumDao.getAllAlbums();

            request.setAttribute("allAlbums", allAlbums);
            request.getRequestDispatcher("/WEB-INF/albumList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Get the form data
            String title = request.getParameter("title");
            long artistId = Long.parseLong(request.getParameter("artistId"));

            // Add the new album to the database using the ArtistDao object
            ArtistDao artistDao = new ArtistDao();
            artistDao.addAlbumToArtist(artistId, title);

            // Redirect back to the albums list page of a specific artist
            response.sendRedirect(request.getContextPath() + "/albums?ArtistId=" + artistId);

        } else if ("remove".equals(action)) {
            // Get the album ID from the request parameter
            long albumId = Long.parseLong(request.getParameter("albumId"));

            // Remove the album with the given ID from the database
            albumDao.removeAlbum(albumId);

            // Redirect back to the 'all albums' page
            String artistId = request.getParameter("ArtistId");
            response.sendRedirect(request.getContextPath() + "/albums?ArtistId=" + artistId);
        }
    }
}