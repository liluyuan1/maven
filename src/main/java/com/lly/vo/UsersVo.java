package com.lly.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersVo {
    private Integer uid;
    private String password;
    private String uname;
    private String realName;
    private Integer state;
}
