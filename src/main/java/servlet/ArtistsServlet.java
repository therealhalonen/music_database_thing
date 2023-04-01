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

// UPDATE: Testing with two paths in one servlet
// https://www.codejava.net/java-ee/servlet/webservlet-annotation-examples
@WebServlet(urlPatterns = {"/artists", "/artists/search"})
public class ArtistsServlet extends HttpServlet {

    private final ArtistDao dao = new ArtistDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assign "path" to variable
        String path = request.getServletPath();

        // Get all artists
        if ("/artists".equals(path)) {

            List<Artist> artists = dao.getAllArtists();
            request.setAttribute("artists", artists);
            request.getRequestDispatcher("/WEB-INF/artistList.jsp").forward(request, response);

            // Search artists
        } else if ("/artists/search".equals(path)) {
            searchArtists(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the action from Index
        String action = request.getParameter("action");

        // Add
        if ("add".equals(action)) {
            // Get the artist name from the request parameter
            String artistName = request.getParameter("artistName");

            // Add the artist to the database
            dao.addArtist(artistName);

            // Redirect back to the artist list page if the action is "add"
            response.sendRedirect(request.getContextPath() + "/artists");
            // Remove
        } else if ("remove".equals(action)) {
            // Get the artist ID from the request parameter
            long artistId = Long.parseLong(request.getParameter("artistId"));

            // Remove the artist with the given ID from the database
            dao.removeArtist(artistId);

            // Get the return URL from the request parameter
            String returnUrl = request.getParameter("returnUrl");

            // Redirect back to the previous page
            response.sendRedirect(returnUrl);
        }
    }

    // UPDATED: Implement search method to inside the servlet
    // https://stackoverflow.com/questions/53470530/calling-java-methods-from-inside-servlet
    private void searchArtists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String artistName = request.getParameter("artistName");

        List<Artist> artists = dao.getArtistByName(artistName);

        request.setAttribute("artists", artists);
        request.getRequestDispatcher("/WEB-INF/artistList.jsp").forward(request, response);
    }
}
