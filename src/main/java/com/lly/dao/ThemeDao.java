package com.lly.dao;

import com.lly.pojo.Theme;

public interface ThemeDao {

    public Theme selectByTitle(String title);

    public int addTheme(Theme theme);

    public int deleteTheme(int tid);

    public int updateTheme(Theme theme);
}
