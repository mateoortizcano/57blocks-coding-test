package com.music.app.infrastructure.repositories.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "songs")
@Entity
public class SongEntity {
    @Id
    @UuidGenerator
    private String id;
    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = true)
    private String genre;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private String author;
    @Column(nullable = true)
    private String album;
    @Column(nullable = false)
    private Boolean isPublic;
    @ManyToOne(optional = false)
    private UserAccountEntity createdBy;

    public SongEntity(String id, String title, String genre, Long size, String author, String album, Boolean isPublic, UserAccountEntity createdBy) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.size = size;
        this.author = author;
        this.album = album;
        this.isPublic = isPublic;
        this.createdBy = createdBy;
    }

    public SongEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public UserAccountEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserAccountEntity createdBy) {
        this.createdBy = createdBy;
    }
}
