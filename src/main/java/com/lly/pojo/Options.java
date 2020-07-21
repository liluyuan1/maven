package com.lly.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Options {
    private Integer oid;
    private String options;
    private Integer otid;
    private double votes;
}
