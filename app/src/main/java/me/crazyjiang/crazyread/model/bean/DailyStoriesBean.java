package me.crazyjiang.crazyread.model.bean;

import java.util.List;

/**
 * Created by Jiangwenjin on 2017/3/4.
 * ZhiHu daily stories information
 * "date": "20170304",
 * "stories": []
 */
public class DailyStoriesBean {
    private String date;
    private List<StoryBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoryBean> getStories() {
        return stories;
    }

    public void setStories(List<StoryBean> stories) {
        this.stories = stories;
    }
}
