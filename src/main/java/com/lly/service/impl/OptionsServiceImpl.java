package com.lly.service.impl;

import com.lly.dao.OptionsDao;
import com.lly.pojo.Options;
import com.lly.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsDao od;

    //根据标题id查询投票选项
    @Override
    public List<Options> selectByIdOptons(int otid) {
        return od.selectByIdOptions(otid);
    }

    //根据标题id查询投票选项以及每个选项的票数
    @Override
    public List<Options> selectOptionsAll(int otid){

        return od.selectOptionsAll(otid);

    }

    @Override
    public int updateOptions(Options options) {

        return od.updateOptions(options);

    }

    @Override
    public int deleteByIdOptions(int oid) {

        return od.deleteByIdOptions(oid);
    }
}
