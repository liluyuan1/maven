package com.lly.service;

import com.lly.pojo.Options;

import java.util.List;

public interface OptionsService {

    public List<Options> selectOptionsAll(int otid);

    public List<Options> selectByIdOptons(int otid);

    public int updateOptions(Options options);

    public int deleteByIdOptions(int oid);

}
