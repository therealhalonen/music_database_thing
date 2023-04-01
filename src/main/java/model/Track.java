package model;

public class Track {
    private int id;
    private String name;
    // private int albumId;

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //  public Track(int albumId) {
    //     this.albumId = albumId;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public int getAlbumId() {
    //    return albumId;
    // }

    // public void setAlbumId(int albumId) {
    //    this.albumId = albumId;
    //}
}