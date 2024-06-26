package com.dnakolor.api.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {
    public static final String HOST = "http://localhost:8080";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String SECRET = "123456789";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/services/controller/user";
//    public static final String ACCOUNT_URL = HOST + "/api/v1/accounts";
//    public static final String USER_URL = HOST + "/api/v1/users";
//    public static final String IMAGE_URL = HOST + "/api/v1/images";
//    public static final String UPLOAD_FILE_DIRECTORY = "E:/Self-Project/Upload_File/user/";
}
