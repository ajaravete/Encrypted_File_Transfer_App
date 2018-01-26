package encryptedFileTransfer.restServer.service;

import encryptedFileTransfer.restServer.dao.FileDao;
import encryptedFileTransfer.restServer.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collection;

@Service
public class FileService {
    private FileDao fileDao;

    @Autowired
    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public Collection<FileInfo> getNewFilesInfo(String email) {
        return fileDao.getNewFilesInfo(email);
    }

    public InputStreamResource getNewFileContent(int id) {
        return fileDao.getNewFileContent(id);
    }

    public void insertNewFile(InputStream file, String sender, String receiver, String fileName) {
        fileDao.insertNewFile(file, sender, receiver, fileName);
    }

    public void deleteFile(int id) {
        fileDao.deleteFile(id);
    }
}
