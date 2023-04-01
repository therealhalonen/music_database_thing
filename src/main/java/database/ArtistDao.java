package database;

import model.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDao {
    private final Database db = new Database();

    public Artist getArtistById(long artistId) {
        String selectById = "SELECT Name FROM Artist WHERE ArtistId = ?;";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectById)) {

            statement.setLong(1, artistId);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    String name = results.getString("Name");
                    int albumCount = getAlbumCount(artistId);
                    return new Artist(artistId, name, albumCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Artist> getArtistByName(String name) {
        String selectByPartialName = "SELECT Artist.ArtistId, Artist.Name " +
                "FROM Artist " +
                "WHERE Artist.Name LIKE ? " +
                "ORDER BY Artist.Name ASC;";

        List<Artist> artists = new ArrayList<>();

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectByPartialName)) {

            statement.setString(1, "%" + name + "%");
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    long id = results.getLong("ArtistId");
                    String artistName = results.getString("Name");
                    int albumCount = getAlbumCount(id);
                    Artist artist = new Artist(id, artistName, albumCount);
                    artists.add(artist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    public List<Artist> getAllArtists() {
        String selectAll = "SELECT Artist.ArtistId, Name " +
                "FROM Artist " +
                "ORDER BY Name ASC;";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet results = statement.executeQuery()) {

            List<Artist> allArtists = new ArrayList<>();
            while (results.next()) {
                long id = results.getLong("ArtistId");
                String name = results.getString("Name");
                int albumCount = getAlbumCount(id);

                Artist a = new Artist(id, name, albumCount);
                allArtists.add(a);
            }
            return allArtists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getAlbumCount(long artistId) {
        String selectAlbumCount = "SELECT COUNT(*) AS AlbumCount FROM Album WHERE ArtistId = ?;";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectAlbumCount)) {

            statement.setLong(1, artistId);
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return results.getInt("AlbumCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addArtist(String artistName) {
        String insertArtist = "INSERT INTO Artist (Name) VALUES (?)";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(insertArtist)) {

            statement.setString(1, artistName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeArtist(long artistId) {
        String removeArtist = "DELETE FROM Artist WHERE ArtistId = ?";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(removeArtist)) {

            statement.setLong(1, artistId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAlbumToArtist(long artistId, String albumTitle) {
        String addAlbumToArtist = "INSERT INTO Album (Title, ArtistId) VALUES (?, ?)";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(addAlbumToArtist)) {

            statement.setString(1, albumTitle);
            statement.setLong(2, artistId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}