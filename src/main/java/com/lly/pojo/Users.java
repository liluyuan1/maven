package com.lly.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private Integer uid;
    private String password;
    private String uname;
    private String realName;
    private Integer state;
}
