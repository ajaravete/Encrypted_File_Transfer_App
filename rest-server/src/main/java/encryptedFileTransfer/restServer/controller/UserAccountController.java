package encryptedFileTransfer.restServer.controller;

import encryptedFileTransfer.restServer.entity.UserAccount;
import encryptedFileTransfer.restServer.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/user", produces = "application/json")
public class UserAccountController {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Collection<UserAccount> getUserAccountsByEmail(@RequestParam("email") String email) {
        return userAccountService.getUserAccountsByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserAccount getUserAccountByEmail(@RequestParam("email") String email) {
        return userAccountService.getUserAccountByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUserAccount(@RequestBody UserAccount userAccount) {
        userAccountService.insertUserAccount(userAccount);
    }
}
