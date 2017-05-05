package com.springboot.songlibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Playlist {
    @Id
    private int song_id;
    private int user_id;


    public Playlist() {
    }

    public Playlist(int user_id, int song_id) {
        this.user_id = user_id;
        this.song_id = song_id;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
