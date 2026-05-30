package com.playLoad.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDto(@JsonProperty("accessToken")String accessToken) {
    public static LoginResponseDto of (String accessToken){
        return new LoginResponseDto(accessToken);
    }
}
