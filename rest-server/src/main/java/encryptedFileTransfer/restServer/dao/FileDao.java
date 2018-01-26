package encryptedFileTransfer.restServer.dao;

import encryptedFileTransfer.restServer.entity.FileInfo;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

@Repository
public class FileDao {
    private DataSource dataSource;

    @Autowired
    public void FileDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Collection<FileInfo> getNewFilesInfo(String email) {
        LinkedList<FileInfo> files = new LinkedList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id, sender, receiver, file_name " +
                    "FROM encrypted_file WHERE receiver = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FileInfo fileInfo = new FileInfo(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                files.add(fileInfo);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }

    public InputStreamResource getNewFileContent(int id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT file_data FROM encrypted_file WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            InputStreamResource inputStream = new InputStreamResource(rs.getBinaryStream(1));
            rs.close();
            ps.close();
            return inputStream;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewFile(InputStream file, String sender, String receiver, String fileName) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO encrypted_file(sender, " +
                    "receiver, file_name, file_data) VALUES (?, ?, ?, ?)");
            ps.setString(1, sender);
            ps.setString(2, receiver);
            ps.setString(3, fileName);
            ps.setBinaryStream(4, file);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(int id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM encrypted_file WHERE id = ?");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
