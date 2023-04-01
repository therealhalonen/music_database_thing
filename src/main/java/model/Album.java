package model;

public class Album {
    private long id;
    private final String title;

    public Album(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Album(String title) {
        this.title = title;
    }

    public String getTitle() {
         return title;
    }

    public long getId() {
        return id;
    }

}