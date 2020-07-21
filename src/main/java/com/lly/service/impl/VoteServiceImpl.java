package com.lly.service.impl;

import com.lly.dao.VoteDao;
import com.lly.pojo.Vote;
import com.lly.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Transactional
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao vd;
/*
    @Override
    public List<Map<String,Object>> selectAll(int pageIndex,String title){
        Page<Options> page = PageHelper.startPage(pageIndex,3);
        PageHelper.orderBy("tid DESC");
        System.out.println("selectAll************"+page.toString());
        List<Map<String,Object>> list =  vd.selectAll(title);
        System.out.println("selectAll************"+list.toString());

        return list;
    }
*/

    @Override
    public List<Map<String,Object>> selectAll(String title){

        return vd.selectAll(title);
    }
    //用戶投票
    @Override
    public boolean addVote(Vote vote,int[] options) {
        if(vd.selectVote(vote)==null){
            int v = 0;
            for (int i=0;i<options.length;i++){
                vote.setVono(options[i]);
                v+=vd.insertVote(vote);
            }
            if(v>0){
                return true;
            }
        }
        return false;
    }
}
