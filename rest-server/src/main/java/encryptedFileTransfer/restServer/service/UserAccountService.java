package encryptedFileTransfer.restServer.service;

import encryptedFileTransfer.restServer.dao.UserAccountDao;
import encryptedFileTransfer.restServer.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserAccountService {
    private final UserAccountDao userAccountDao;

    @Autowired
    public UserAccountService(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    public Collection<UserAccount> getUserAccountsByEmail(String email) {
        return userAccountDao.getUserAccountsByEmail(email);
    }

    public UserAccount getUserAccountByEmail(String email) {
        return userAccountDao.getUserAccountByEmail(email);
    }

    public void updateUserAccount(UserAccount UserAccount) {
        userAccountDao.updateUserAccount(UserAccount);
    }

    public void insertUserAccount(UserAccount UserAccount) {
        userAccountDao.insertUserAccount(UserAccount);
    }

}
