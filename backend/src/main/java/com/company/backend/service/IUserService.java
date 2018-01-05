package com.company.backend.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.company.backend.persistence.model.PasswordResetToken;
import com.company.backend.persistence.model.User;
import com.company.backend.persistence.model.VerificationToken;
import com.company.backend.web.dto.UserDto;
import com.company.backend.web.error.UserAlreadyExistException;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;
    
    User registerNewSocialAccount(User user);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);




    List<String> getUsersFromSessionRegistry();

}
