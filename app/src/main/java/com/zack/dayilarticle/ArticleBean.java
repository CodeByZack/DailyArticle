package com.zack.dayilarticle;

/**
 * Created by Zackv on 2016/8/7.
 */
public class ArticleBean {
    private String title;
    private String author;
    private String context_html;

    public ArticleBean() {
    }

    public ArticleBean(String title, String context_html, String author) {
        this.title = title;
        this.context_html = context_html;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContext_html() {
        return context_html;
    }

    public void setContext_html(String context_html) {
        this.context_html = context_html;
    }
}
