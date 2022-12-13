package com.ProjectPlatform.ProjectPlatform.project;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final Path root = Paths.get("uploads");
    private ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }

    public String uploadFile(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            } else if (file.isEmpty()) {
                return "You did not choose a file!";
            }

            throw new RuntimeException(e.getMessage());
        }
        return file.getOriginalFilename();
    }

    public UrlResource download(String filename) throws MalformedURLException {
        Path file = root.resolve(filename);
        UrlResource urlResource = new UrlResource(file.toUri());
        return urlResource;
    }

    public Result save(Result result){
        return resultRepository.save(result);
    }

//    @Override
//    public List<Result> findAllByProjectsId(Long id){
//        return resultRepository.getAllByProjectsId(id);
//    }

}
