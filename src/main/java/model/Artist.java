package model;

public class Artist {
    private final long id;

    private String name;

    private int albumCount;

    public Artist(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist(long id, String name, int albumCount) {
        this.id = id;
        this.name = name;
        this.albumCount = albumCount;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
    return this.id;
    }

    // This I wonder... The IDE tells me; it's not used, but everything fails if removed! -Antti

    public int getAlbumCount() {
        return this.albumCount;
    }
}
