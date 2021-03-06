package com.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    private String id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 14)
    private String tel;

    private String url;

    protected User() {}

    public User(String id, String password, String name, String tel, String url) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.url = url;
    }

    public boolean matched(Authentication authentication) {
        if (StringUtils.isEmpty(this.name)) {
            return false;
        }

        if (authentication == null) {
            return false;
        }

        return this.name.equals(authentication.getName());
    }
}
