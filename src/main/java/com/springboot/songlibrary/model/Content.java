package com.springboot.songlibrary.model;


import javax.persistence.*;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private byte[] bytes;


    public Content() {
    }

    public Content(byte[] content) {
        this.bytes = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
