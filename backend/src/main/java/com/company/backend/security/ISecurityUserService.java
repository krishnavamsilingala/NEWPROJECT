package com.company.backend.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
