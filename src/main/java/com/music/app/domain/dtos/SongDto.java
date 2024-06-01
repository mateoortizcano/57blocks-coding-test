package com.music.app.domain.dtos;

public class SongDto {
    private String id;
    private String title;
    private String genre;
    private long size;
    private String author;
    private String album;
    private boolean isPublic;
    private String createdById;

    public SongDto(String id, String title, String genre, long size, String author, String album, boolean isPublic, String createdById) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.size = size;
        this.author = author;
        this.album = album;
        this.isPublic = isPublic;
        this.createdById = createdById;
    }

    // Getters and setters for the fields

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getCreatedById() {
        return createdById;
    }
}

