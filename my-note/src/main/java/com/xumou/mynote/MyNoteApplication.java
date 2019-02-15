package com.xumou.mynote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 *
 */
@EnableJpaRepositories
@SpringBootApplication
public class MyNoteApplication {

    public static int port;
    public static RestTemplate restTemplate;
    public static String url;
    public static Desktop desktop;

    static{
        FileInputStream fis = null;
        try {
            File file = new File(ClassLoader.getSystemClassLoader().getResource("").getPath());
            fis = new FileInputStream(new File(file, "application.properties"));
            Properties properties = new Properties();
            properties.load(fis);
            port = Integer.valueOf(properties.getProperty("server.port"));
            restTemplate = new RestTemplate();
            url = "http://localhost:" + port;
            if(Desktop.isDesktopSupported()) {
                Desktop temp = Desktop.getDesktop();
                if (temp.isSupported(Desktop.Action.BROWSE)) {
                    desktop = temp;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(fis != null)
                    fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean hasNotStarted() {
        try{
            return !restTemplate.postForObject(url + "/note/isStart", null, Boolean.class);
        }catch (Exception e){
            return true;
        }
    }

    private static void gotoHome() {
        try {
            if(desktop != null)
                desktop.browse(new URI(url + "/note"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(hasNotStarted()){
            SpringApplication.run(MyNoteApplication.class, args);
        }
        gotoHome();
    }

}