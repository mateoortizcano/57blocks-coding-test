package com.music.app.infrastructure.wrappers;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema
public class SongWrapper {
    @NotNull
    @Schema(description = "Tittle of the song")
    private String title;
    @Schema(description = "Genre of the song")
    private String genre;
    @NotNull
    @Size(min = 1)
    @Schema(description = "Size in bytes of the song")
    private long size;
    @NotNull
    @Schema(description = "Author of the song")
    private String author;
    @Schema(description = "Album name")
    private String album;
    @NotNull
    @Schema(description = "Is the song public?")
    private boolean isPublic;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public long getSize() {
        return size;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum() {
        return album;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
