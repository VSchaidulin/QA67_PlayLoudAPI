package com.payLoad.tests;


import com.payLoad.core.TestBase;
import com.playLoad.dto.login.LoginRequestDto;
import com.playLoad.dto.login.LoginResponseDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTests extends TestBase {
    LoginRequestDto requestDto = LoginRequestDto.of(EMAIL,PASSWORD);

    @Test
    public void loginSuccessTest(){
        LoginResponseDto loginResponseDto = given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(201)
                .extract().response().as(LoginResponseDto.class);

        System.out.println(loginResponseDto.accessToken());
    }
    @Test
    public void loginWithInvalidEmailFormatTest() {

        LoginRequestDto dto =
                LoginRequestDto.of("string", PASSWORD);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(400);
    }
    @Test
    public void loginWithEmptyPasswordTest() {

        LoginRequestDto dto =
                LoginRequestDto.of(EMAIL, "");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(400);
    }
    @Test
    public void loginWithWrongEmailTest() {

        LoginRequestDto dto =
                LoginRequestDto.of("wrong@gmail.com", PASSWORD);

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(404);
    }
    @Test
    public void loginWithWrongPasswordTest() {

        LoginRequestDto dto =
                LoginRequestDto.of(EMAIL, "WrongPassword123");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post(LOGIN_PATH)
                .then()
                .statusCode(401);
    }
}
