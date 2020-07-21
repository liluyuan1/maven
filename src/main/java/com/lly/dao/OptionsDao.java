package com.lly.dao;

import com.lly.pojo.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionsDao {


    public List<Options> selectOptionsAll(Integer otid);

    public int addOptions(@Param("options") String options, @Param("otid") int otid);

    public List<Options> selectByIdOptions(int otid);

    public int deleteOptions(int tid);

    public int updateOptions(Options options);

    public int deleteByIdOptions(int oid);

}
