package com.example.ep_projekt.authorities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;



public enum UserRole {
    GUEST(List.of(GET.getPermission())),
    USER(List.of(GET.getPermission(), POST.getPermission())),
    ADMIN(List.of(GET.getPermission(), POST.getPermission(), DELETE.getPermission(), PUT.getPermission()));

    private final List<String> permission;

    UserRole(List<String> permission) {
        this.permission = permission;
    }

    public List<String> getPermission () {
        return permission;
    }

    public List<SimpleGrantedAuthority> getAuthorities () {


        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>(

        );

        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        simpleGrantedAuthorityList.addAll(getPermission().stream().map(SimpleGrantedAuthority::new).toList());

        return simpleGrantedAuthorityList;
    }
}
