package com.music.app.domain.services.song;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.exceptions.NotAllowedOperationException;
import com.music.app.domain.exceptions.NotFoundExxeption;
import com.music.app.domain.model.UpdateSongData;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.validators.ArgumentsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateSongService {
    public static final String THE_SONG_YOU_ARE_TRYING_TO_UPDATE_DOES_NOT_EXIST = "The song you are trying to update does not exist.";
    public static final String THIS_SONG_IS_NOT_CREATED_BY_YOU_SO_YOU_CAN_NOT_MODIFY_IT = "This song is not created by you so you can not modify it.";
    public static final String THE_SONG_ID_TO_UPDATE_IS_REQUIRED = "The song id to update is required.";
    public static final String THE_AUTHENTICATED_USER_IS_REQUIRED = "The authenticated user is required.";

    @Autowired
    private ISongRepository songRepository;

    public void execute(String songId, UpdateSongData song, String idRequestingUser) {
        ArgumentsValidator.verifyNotNullOrEmpty(songId, THE_SONG_ID_TO_UPDATE_IS_REQUIRED);
        ArgumentsValidator.verifyNotNullOrEmpty(idRequestingUser, THE_AUTHENTICATED_USER_IS_REQUIRED);
        SongDto songDto = this.songRepository.findById(songId).orElseThrow(
                () -> new NotFoundExxeption(THE_SONG_YOU_ARE_TRYING_TO_UPDATE_DOES_NOT_EXIST));
        if (!songDto.getCreatedById().equals(idRequestingUser)){
            throw  new NotAllowedOperationException(THIS_SONG_IS_NOT_CREATED_BY_YOU_SO_YOU_CAN_NOT_MODIFY_IT);
        }
        assignModifiedAttributes(song, songDto);

        this.songRepository.updateSongAttributes(songDto);

    }

    private static void assignModifiedAttributes(UpdateSongData song, SongDto songDto) {
        if (song.title() != null)
            songDto.setTitle(song.title());
        if (song.genre() != null)
            songDto.setGenre(song.genre());
        if (song.size() != null)
            songDto.setSize(song.size());
        if (song.author() != null)
            songDto.setAuthor(song.author());
        if (song.album() != null)
            songDto.setAlbum(song.album());
        if (song.isPublic() != null)
            songDto.setPublic(song.isPublic());
    }
}
