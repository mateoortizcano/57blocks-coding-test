package com.music.app.domain.services.song;

import com.music.app.domain.dtos.SongDto;
import com.music.app.domain.ports.ISongRepository;
import com.music.app.domain.validators.ArgumentsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongsService {

    public static final String PAGE_NUMBER_SHOULD_BE_0_OR_BIGGER = "Page number should be 0 or bigger.";
    public static final String PAGE_SIZE_SHOULD_BE_1_OR_BIGGER = "Page size should be 1 or bigger.";
    public static final String THE_AUTHENTICATED_USER_IS_NECESSARY = "The authenticated user is necessary.";
    @Autowired
    private ISongRepository songRepository;

    public List<SongDto> getAllPublicSongs(int pageNumber, int pageSize) {
        ArgumentsValidator.verifyIsBiggerThan(pageNumber, 0, PAGE_NUMBER_SHOULD_BE_0_OR_BIGGER);
        ArgumentsValidator.verifyIsBiggerThan(pageSize, 1, PAGE_SIZE_SHOULD_BE_1_OR_BIGGER);
        return songRepository.getAllPublicSongs(pageNumber, pageSize);
    }

    public List<SongDto> getAllMySongs(String requestingUserId, int pageNumber, int pageSize) {
        ArgumentsValidator.verifyNotNullOrEmpty(requestingUserId, THE_AUTHENTICATED_USER_IS_NECESSARY);
        ArgumentsValidator.verifyIsBiggerThan(pageNumber, 0, PAGE_NUMBER_SHOULD_BE_0_OR_BIGGER);
        ArgumentsValidator.verifyIsBiggerThan(pageSize, 1, PAGE_SIZE_SHOULD_BE_1_OR_BIGGER);
        return songRepository.getAllMySongs(requestingUserId, pageNumber, pageSize);
    }
}
