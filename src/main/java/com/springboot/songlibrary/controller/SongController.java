package com.springboot.songlibrary.controller;

import com.springboot.songlibrary.model.Content;
import com.springboot.songlibrary.service.ContentService;
import com.springboot.songlibrary.service.SongService;
import com.springboot.songlibrary.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class SongController {

    private byte[] bytes;

    private Song downloadSong;

    @Autowired
    private ContentService contentService;

    @Autowired
    private SongService songService;

    @RequestMapping("/songs")
    public List<Song> getAllSongs() {
        return songService.listSong();
    }

    @RequestMapping(value = "/song/{id}")
    public Song getSong(@PathVariable int id) {
        return songService.getSong(id);
    }

    @RequestMapping(value = "/song/add", method = RequestMethod.POST)
    public void addSong(@RequestBody Song song) {

        Content content = new Content(bytes);
        contentService.saveContent(content);

        song.setContent(content);

        songService.addSong(song);

    }

    @RequestMapping(value = "/song/update", method = RequestMethod.POST)
    public void updateSong(@RequestBody Song song) {

        songService.addSong(song);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSong(@PathVariable int id) {
        try {
            songService.deleteSong(id);
        }catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/download/song/{id}", method = RequestMethod.POST) //from angularJS
    public void findSongForDownload(@PathVariable int id) throws IOException {

        downloadSong = songService.getSong(id);
    }

    @RequestMapping(value = "/downloadSong")  //from html
    public HttpEntity<byte[]> downloadSong() throws IOException {

        byte[] fileByte = downloadSong.getContent().getBytes();

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("audio", "mpeg3"));
        header.set("Content-Disposition", "attachment; filename=" + downloadSong.getAuthor().getName() + " - " + downloadSong.getName() + ".mp3");
        header.setContentLength(fileByte.length);

        return new HttpEntity<byte[]>(fileByte, header);
    }

    @PostMapping("/upload")   //from html
    public void singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        bytes = file.getBytes();

    }

    @RequestMapping("/playlist/{id}")
    public List<Song> loadPlaylist(@PathVariable Long id) {
        return songService.playlist(id);
    }

    @RequestMapping(value = "/playlist/add/{id}", method = RequestMethod.POST)
    public ResponseEntity addSongPlaylist(@PathVariable int id) {
        try {
            songService.addSongPlaylist(id);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/playlist/delete/{id}", method = RequestMethod.DELETE)
    public void deleteSongFromPlaylist(@PathVariable int id) {
        songService.deleteSongFromPlaylist(id);
    }

    @RequestMapping("/find/{name}")
    public List<Song> findSongsByName(@PathVariable String name) {
        return songService.findSongByName(name);
    }



//    @RequestMapping("/play")
//    public String playSong() {
//        Song song = songService.getSong(3);
//        byte[] fileByte = song.getContent();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("data:audio/mp3;base64,");
//        sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(fileByte, false)));
//
//        return sb.toString();
//    }
}