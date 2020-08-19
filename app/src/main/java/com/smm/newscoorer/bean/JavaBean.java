package com.smm.newscoorer.bean;

import java.util.List;

public class JavaBean {


    /**
     * id : 10000
     * title : 新闻
     * type : 1
     * children : [{"id":10007,"title":"北京","type":1,"url":"/10007/list_1.json"}]
     */

    private int id;
    private String title;
    private int type;
    private List<ChildrenBean> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        /**
         * id : 10007
         * title : 北京
         * type : 1
         * url : /10007/list_1.json
         */

        private int id;
        private String title;
        private int type;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", children=" + children +
                '}';
    }
}
