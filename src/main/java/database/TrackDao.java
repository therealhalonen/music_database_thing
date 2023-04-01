package database;

import model.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDao {

    private final Database db = new Database();

    public List<Track> getTracksByAlbumId(int albumId) {
        String selectAlbum = "SELECT TrackId, Name FROM Track WHERE AlbumId = ?";

        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(selectAlbum)) {
            statement.setLong(1, albumId);

            try (ResultSet results = statement.executeQuery()) {
                List<Track> tracks = new ArrayList<>();
                while (results.next()) {
                    Track track = new Track(results.getInt("TrackId"), results.getString("Name"));
                    tracks.add(track);
                }
                return tracks;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
