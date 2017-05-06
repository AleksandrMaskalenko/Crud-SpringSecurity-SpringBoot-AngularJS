package com.springboot.songlibrary.service;

import com.springboot.songlibrary.DAO.ContentDao;
import com.springboot.songlibrary.DAO.PlaylistDao;
import com.springboot.songlibrary.DAO.UserDao;
import com.springboot.songlibrary.DAO.SongDao;
import com.springboot.songlibrary.model.User;
import com.springboot.songlibrary.model.Song;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    private final static Logger logger = Logger.getLogger(SongService.class);

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private SongDao songDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PlaylistDao playlistDao;

    private List<Song> songs;
    private User user;

    public List<Song> listSong() {
        logger.info("Load all song service method");

        return songDao.findAll();
    }

    public Song getSong(int id) {
        return songDao.findOne(id);
    }

    public void addSong(Song song) {
        songDao.save(song);
    }

    public void deleteSong(int id) {
        if (playlistDao.findOne(id) != null) {

            playlistDao.delete(id);

            logger.info("Delete song from playlist service method");
        }
        Song song = songDao.findOne(id);

        songDao.delete(id);

        logger.info("Delete song service method");

        if (song.getContent() != null) {
            contentDao.delete(song.getContent().getId());
            logger.info("Delete song content");
        }


    }

    public List<Song> playlist(Long userId) {

        User user = userDao.findOne(userId);

        return user.getSongList();

    }

    public void addSongPlaylist(int id) throws Exception {
        authentication();

        for (Song song: songs) {
            if (song.getId() == id) {

                throw new RuntimeException("This song already in a playlist");
            }
        }
        songs.add(songDao.findOne(id));

        logger.info("Add song  user " + user.getName() +" playlist");

        saveUser();
    }

    public void deleteSongFromPlaylist(int id) {
        authentication();

        songs.removeIf(s -> s.getId() == id);

        logger.info("Delete song from user " + user.getName() +" playlist");

        saveUser();
    }

    public List<Song> findSongByName(String name) {
        return songDao.findSongsByName(name);
    }

    private void authentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUsername = auth.getName();
        user = userDao.findOneByUsername(loggedUsername);
        songs = new ArrayList<>();
        songs.addAll(user.getSongList());

        logger.info("Get current " + user.getName() +" playlist");
    }

    private void saveUser() {
        user.setSongList(songs);
        userDao.save(user);
        logger.info("Save new " + user.getName() +" playlist");
    }

}
