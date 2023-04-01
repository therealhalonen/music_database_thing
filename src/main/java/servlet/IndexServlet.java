package servlet;

import database.ArtistDao;
import model.Artist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final ArtistDao artistDao = new ArtistDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        if (query != null && !query.trim().isEmpty()) {
            // Search for artists based on user input
            List<Artist> artists;
            artists = artistDao.getArtistByName(query);
            request.setAttribute("artists", artists);
        }
        request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
    }
}