package com.springboot.songlibrary.controller;

import com.springboot.songlibrary.model.Song;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= DEFINED_PORT)
public class SongControllerTestIT {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void listSongTest() throws Exception {

        ResponseEntity<List<Song>> responseEntity = restTemplate.exchange("http://localhost:8080/songs", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Song>>() {
        });

        List<Song> actualList = responseEntity.getBody();

        assertThat(actualList.size(), is(7));
        List<Integer> actualListId = actualList.stream()
                .map(Song::getId)
                .collect(toList());
        assertThat(actualListId, containsInAnyOrder(1, 2,3,4,5,6,7));
    }

    @Test
    public void getSongTest() throws Exception {

        ResponseEntity<Song> responseEntity = restTemplate.getForEntity("http://localhost:8080/song/{id}", Song.class,
                2);

        Song actualSong = responseEntity.getBody();
        assertEquals("Clint Eastwood", actualSong.getName());
    }

    @Test
    public void playlistLoadTest() throws Exception {
        ResponseEntity<List<Song>> responseEntity = restTemplate.exchange("http://localhost:8080/playlist/{id}", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Song>>() {}, 2);

        List<Song> actualList = responseEntity.getBody();

        assertThat(actualList.size(), is(3));

    }


}