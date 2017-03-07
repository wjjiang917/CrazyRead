package me.crazyjiang.crazyread.model.bean;

import java.util.List;

/**
 * Created by Jiangwenjin on 2017/3/4.
 * ZhiHu daily stories information
 * "date": "20170304",
 * "stories": [],
 * "top_stories": []
 */
public class DailyStoriesBean {
    private String date;
    private List<StoryBean> stories;
    private List<TopStoryBean> top_stories;

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

    public List<TopStoryBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoryBean> top_stories) {
        this.top_stories = top_stories;
    }

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
    public static class StoryBean {
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

    /**
     * ZhiHu banner News Information:
     * <p>
     * "image": "http://pic4.zhimg.com/994882d7bda69db8966a5d65bbf0432b.jpg",
     * "type": 0,
     * "id": 9270915,
     * "ga_prefix": "030714",
     * "title": "不要让几张截图成了狂欢，这本书在认真把性教育做得更好"
     */
    public static class TopStoryBean {
        private String image;
        private int type;
        private Integer id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
}
