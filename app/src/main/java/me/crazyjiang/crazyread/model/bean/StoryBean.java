package me.crazyjiang.crazyread.model.bean;

import java.util.List;

/**
 * Created by Jiangwenjin on 2017/3/4.
 * ZhiHu News Information:
 * "images": [
 * "http://pic4.zhimg.com/6bac69b042858a758e73ccf6207e898b.jpg"
 * ],
 * "type": 0,
 * "id": 9259161,
 * "ga_prefix": "030422",
 * "title": "小事 · 念念不忘，有了回响又怎样"
 */
public class StoryBean {
    private List<String> images;
    private int type;
    private Integer id;
    private String ga_prefix;
    private String title;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
