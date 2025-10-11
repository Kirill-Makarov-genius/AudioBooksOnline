package com.example.AudioBooksOnline.services;

import com.example.AudioBooksOnline.config.StorageProperties;
import com.example.AudioBooksOnline.exceptions.StorageException;
import com.example.AudioBooksOnline.exceptions.StorageFileNotFoundException;
import com.example.AudioBooksOnline.services.interfaces.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {


    private final Path rootLocation;
//    private final Transliterator toLatin = Transliterator.getInstance("Cyrillic-Latin");



    @Autowired
    public FileSystemStorageService(StorageProperties properties){

//  Check blank or not our file upload location
        if (properties.getLocation().trim().isEmpty()){
            throw  new StorageException("File upload location can not be Empty");
        }

        this.rootLocation = Paths.get(properties.getLocation());


    }

    @Override
    public String store(MultipartFile file){

        try{
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file");
            }

            String fileName = file.getOriginalFilename().replaceAll(" ", "-");
            fileName = Transliterator.transliterate(fileName);

            Path destinationFile = this.rootLocation.resolve(Paths.get(fileName))
                    .normalize()
                    .toAbsolutePath();
//      Security check
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
                throw new StorageException("Cannot store file oustide current directory");
            }
            try (InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        }
        catch (IOException e){
            throw new StorageException("Failed to store file.", e);
        }
    }
    @Override
    public Stream<Path> loadAll(){

        try{
//          With method walk we can get all files and subdirectories.
//          In this case we defined maxDepth = 1 so we check only top-level files and directories
//          walk() returns Stream<Path>
            return Files.walk(this.rootLocation, 1)
                    //Delete root path from the stream
                    .filter(path -> !path.equals(this.rootLocation))
                    //Converts absolute paths into relative paths(relative to the root)
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e){
            throw new StorageException("Failed to read stored files", e);
        }
    }
    @Override
    public Path load(String filename){
        return this.rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename){
        try{
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }
            else{
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e){
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    @Override
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(this.rootLocation.toFile());
    }

    @Override
    public void init(){
        try{
            Files.createDirectories(rootLocation);
        }
        catch (IOException e){
            throw new StorageException("Cound not initialize storage", e);
        }
    }
}
























