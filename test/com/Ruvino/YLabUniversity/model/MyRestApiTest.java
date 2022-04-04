package com.Ruvino.YLabUniversity.model;

import http.BasicHttp;
import org.junit.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

public class MyRestApiTest {

    private BasicHttp http;

    @Before
    public void setupBasicHttp(){
        http = new BasicHttp("http","localhost", 8080);
    }

    @After
    public void tearDown(){
        http = null;
    }

    @Test
    public void canAccessServer() throws IOException {
        HttpURLConnection con = (HttpURLConnection)new URL("http","localhost", 8080, "").openConnection();
        int status = con.getResponseCode();

        assertEquals(200, status);
    }

    @Test
    public void canAccessPage(){
        assertTrue(http.isPageAt("/gameplay/player1"));
        assertTrue(http.isPageAt("/gameplay/player2"));
    }


}