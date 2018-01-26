package encryptedFileTransfer.restServer.dao;

import encryptedFileTransfer.restServer.entity.UserAccount;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

@Repository
public class UserAccountDao {
    private DataSource dataSource;

    @Autowired
    public void UserAccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Collection<UserAccount> getUserAccountsByEmail(String email) {
        LinkedList<UserAccount> users = new LinkedList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT email, name, public_key FROM user_account " +
                    "WHERE email LIKE ?");
            ps.setString(1, "%" + email + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserAccount account = new UserAccount(rs.getString(1), rs.getString(2), "",
                        rs.getString(3));
                users.add(account);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserAccount getUserAccountByEmail(String email) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT email, name, public_key FROM user_account " +
                    "WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            UserAccount account = new UserAccount(rs.getString(1), rs.getString(2), "",
                    rs.getString(3));
            rs.close();
            ps.close();
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserAccount(UserAccount userAccount) {

    }

    public void insertUserAccount(UserAccount userAccount) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_account(email, password_hash, name," +
                    " public_key) VALUES (?, ?, ?, ?)");
            ps.setString(1, userAccount.getEmail());
            ps.setString(2, userAccount.getPassword());
            ps.setString(3, userAccount.getName());
            ps.setString(4, userAccount.getPublicKey());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
