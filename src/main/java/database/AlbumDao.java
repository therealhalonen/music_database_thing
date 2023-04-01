package database;

import model.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao {
    private final Database db = new Database();

    public Album getAlbumById(long albumId) {
        String selectById = "SELECT ArtistId, Title FROM Album WHERE AlbumId = ?;";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectById)) {
            statement.setLong(1, albumId);

            try (ResultSet results = statement.executeQuery()) {
                List<Album> albums = new ArrayList<>();
                while (results.next()) {
                    String title = results.getString("Title");
                    Album album = new Album(albumId, title);
                    albums.add(album);
                }
                if (!albums.isEmpty()) {
                    return albums.get(0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> getAlbumsByArtist(long artistId) {
        String selectByArtist = "SELECT AlbumId, Title FROM Album WHERE ArtistId = ? ORDER BY Title ASC;";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectByArtist)) {
            statement.setLong(1, artistId);

            try (ResultSet results = statement.executeQuery()) {
                List<Album> albums = new ArrayList<>();
                while (results.next()) {
                    long id = results.getLong("AlbumId");
                    String title = results.getString("Title");

                    Album album = new Album(id, title);
                    albums.add(album);
                }
                return albums;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Album> getAllAlbums() {
        String selectAll = "SELECT * FROM Album";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet results = statement.executeQuery()) {

            List<Album> albums = new ArrayList<>();
            while (results.next()) {
                long id = results.getLong("AlbumId");
                String title = results.getString("Title");
                Album album = new Album(id, title);
                albums.add(album);
            }
            return albums;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeAlbum(long albumId) {
        String removeAlbum = "DELETE FROM Album WHERE AlbumId = ?";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(removeAlbum)) {

            statement.setLong(1, albumId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}