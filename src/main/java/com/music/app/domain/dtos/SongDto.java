package com.music.app.domain.dtos;

public record SongDto(String id, String title, String genre, long size, String author, String album, boolean isPublic) {
}
