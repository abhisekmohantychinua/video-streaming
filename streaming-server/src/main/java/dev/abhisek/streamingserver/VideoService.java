package dev.abhisek.streamingserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoService {
    private static final String PATH_TO_VIDEOS = "./videos/";

    public Mono<Resource> getVideoByTitle(String title) throws MalformedURLException {
            Path pdfPath = Paths.get(PATH_TO_VIDEOS).resolve(title);
            Resource resource = new UrlResource(pdfPath.toUri());
        return Mono.just(resource);
    }

    public List<String> getAllVideoTitle() throws IOException {
        File folder= new File(PATH_TO_VIDEOS);
        return Arrays.stream(folder.list()).toList();
    }
}
