package model;

public class Artist {
    private final long id;

    private final String name;
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


    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    // This I wonder, if removed, everything failed, still says "no usages" -Antti
    public int getAlbumCount() {
        return albumCount;
    }

}
