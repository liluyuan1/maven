package com.lly.service.impl;

import com.lly.dao.OptionsDao;
import com.lly.dao.ThemeDao;
import com.lly.dao.VoteDao;
import com.lly.pojo.Theme;
import com.lly.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

//alt+shift+p
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeDao td;

    @Autowired
    private OptionsDao od;

    @Autowired
    private VoteDao vd;

    //查询标题是否存在
    @Override
    public Boolean selectByTitle(String title) {
        if(td.selectByTitle(title) != null){
            return true;
        }
        return false;
    }
    //添加投票标题
    @Override
    public boolean addVote(Theme theme, HashSet<String> options) {
        if(theme.getTitle() != null){
            td.addTheme(theme);
        }else {
            td.updateTheme(theme);
        }
        int i = 0;
        for (String option:options){
            i += od.addOptions(option,theme.getTid());
        }
        if(i>1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteVotes(int tid) {
        vd.deleteVote(tid);
        if(od.deleteOptions(tid)>0){
            if(td.deleteTheme(tid)>0){
                return true;
            }
        }

        return false;
    }
}
