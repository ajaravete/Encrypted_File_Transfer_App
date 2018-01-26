package encryptedFileTransfer.restServer.controller;

import encryptedFileTransfer.restServer.entity.FileInfo;
import encryptedFileTransfer.restServer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/file", produces = "application/json")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public Collection<FileInfo> getNewFilesInfo(@RequestParam("email") String email) {
        return fileService.getNewFilesInfo(email);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<InputStreamResource> getNewFileContent(@PathVariable("id") int id) {
        InputStreamResource resource = fileService.getNewFileContent(id);
        if (resource == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertNewFile(@RequestBody InputStreamResource file, @RequestParam("sender") String sender,
                              @RequestParam("receiver") String receiver, @RequestParam("file_name") String fileName) {
        try {
            fileService.insertNewFile(file.getInputStream(), sender, receiver, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("id") int id) {
        fileService.deleteFile(id);
    }
}
