package com.lly.service;

import com.lly.pojo.Vote;

import java.util.List;
import java.util.Map;

public interface VoteService {

   /* public List<Map<String,Object>> selectAll(int pageIndex,String title);*/
    public List<Map<String,Object>> selectAll(String title);

    public boolean addVote(Vote vote,int[] options);
}
