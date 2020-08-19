package com.smm.newscoorer.bean;

import java.util.List;

public class NewsBean {

    public String id;
    public String title;//二级菜单的标题
    public String type;
    public String url;//当前二级菜单数据的url
    public String url1;
    public String dayurl;
    public String excurl;
    public String weekurl;
    public List<Children> children;//二级菜单，新闻对应的不同类型的新闻

    public class Children {
        public String id;
        public String title;//不同类型的新闻的标题
        public String type;
        public String url;//不同类型的新闻 数据的url

        @Override
        public String toString() {
            return "Children{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }


    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", url1='" + url1 + '\'' +
                ", dayurl='" + dayurl + '\'' +
                ", excurl='" + excurl + '\'' +
                ", weekurl='" + weekurl + '\'' +
                ", children=" + children.toString() +
                '}';
    }
}
