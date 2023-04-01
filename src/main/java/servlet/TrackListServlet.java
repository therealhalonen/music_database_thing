package servlet;

import database.AlbumDao;
import database.TrackDao;
import model.Album;
import model.Track;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tracks")
public class TrackListServlet extends HttpServlet {
    private final TrackDao dao = new TrackDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String albumId = request.getParameter("AlbumId");

        if (albumId == null || albumId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "AlbumId parameter is missing");
            return;
        }

        List<Track> tracks;

        AlbumDao albumDao = new AlbumDao();
        Album album = albumDao.getAlbumById(Integer.parseInt(albumId));
        tracks = dao.getTracksByAlbumId(Integer.parseInt(albumId));

        request.setAttribute("tracks", tracks);
        request.setAttribute("album", album);

        request.getRequestDispatcher("/WEB-INF/trackList.jsp").forward(request, response);
    }
}