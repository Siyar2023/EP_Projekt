package com.example.EP_Projekt.authorities;

public enum UserPermission {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission () {
        return permission;
    }
}
