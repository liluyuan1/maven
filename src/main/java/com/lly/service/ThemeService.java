package com.lly.service;

import com.lly.pojo.Theme;

import java.util.HashSet;

public interface ThemeService {


    public Boolean selectByTitle(String title);

    public boolean addVote(Theme theme, HashSet<String> options);

    public Boolean deleteVotes(int tid);
}
