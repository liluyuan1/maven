package com.lly.dao;

import com.lly.pojo.Vote;

import java.util.List;
import java.util.Map;

public interface VoteDao {

    public List<Map<String,Object>> selectAll(String title);

    public int deleteVote(int tid);

    public int insertVote(Vote vote);

    public Vote selectVote(Vote vote);

}
