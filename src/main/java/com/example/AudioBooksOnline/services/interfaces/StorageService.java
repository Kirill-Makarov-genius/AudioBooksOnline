package com.example.AudioBooksOnline.services.interfaces;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {


    void init();

//  Multipart is an interface that represents an uploaded files in multipart/form-data request
//  It provided by Spring to work with uploaded files
    String store(MultipartFile file);

//  Path represents a filesystem path in an object-oriented and platform- independent way
    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
