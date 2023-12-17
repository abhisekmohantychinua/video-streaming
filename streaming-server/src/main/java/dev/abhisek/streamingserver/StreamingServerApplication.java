package dev.abhisek.streamingserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class StreamingServerApplication implements CommandLineRunner {

    private final VideoService videoService;

    @Autowired
    public StreamingServerApplication(VideoService videoService) {
        this.videoService = videoService;
    }


    public static void main(String[] args) {
        SpringApplication.run(StreamingServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        videoService.getAllVideoTitle().forEach(System.out::println);
    }

    @GetMapping(value = "/{title}", produces = "video/mp4")
    public Mono<Resource> getVideoByTitle(@PathVariable String title) throws MalformedURLException {
        return videoService.getVideoByTitle(title);
    }

    @GetMapping(value = "/")
    public Mono<List<String>> getAllVideo() throws IOException {
        return Mono.just(videoService.getAllVideoTitle());
    }
}
