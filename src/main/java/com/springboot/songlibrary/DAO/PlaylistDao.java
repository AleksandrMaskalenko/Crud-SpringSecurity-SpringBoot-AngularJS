package com.springboot.songlibrary.DAO;


import com.springboot.songlibrary.model.Playlist;
import com.springboot.songlibrary.model.Song;
import javafx.print.PageLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistDao extends JpaRepository<Playlist, Integer> {

}