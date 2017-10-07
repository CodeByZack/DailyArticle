package com.zack.dayilarticle;


import android.os.Handler;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * Created by zack on 2016/3/6.
 */
public class parseH {

    private static ArticleBean articleBean ;

    public static ArticleBean getArticleBean() {
        return articleBean;
    }





    static String head ="<head>\n" +
            "<style>\n" +
            "body{padding:0;margin:0;background:#ffffff;}\n" +
            "a{text-decoration:none;}\n" +
            ".articleTitle,.articleAuthorName{text-align:center;}\n" +
            ".articleTitle{font-size:1.7em;margin:0.8em 0;}\n" +
            ".articleAuthorName{font-size:0.9em;margin:0.5em;}\n" +
            ".articleContent{font-size:1.1em;padding:0.5em 0.9em;}\n" +
            ".articleContent p{margin:1.0em 0;text-align:justify;line-height:140%;text-indent: 2em}\n" +
            "</style>\n" +
            "</head>";
    public static String getContext(String url) throws IOException {
        // 从 URL 直接加载 HTML 文档
        Document doc2=null;
        try {
            doc2 = Jsoup.connect(url).get();
        }
        catch (Exception e){
            return "<p>网络连接失败！</p>";
        }

        //if(doc2.toString()==null)return  null;
        String context = doc2.body().toString();
        context=context.replace("id=\"article_show\"", "class=\"container\"");
        context=context.replace("article_author", "articleAuthorName");
        context=context.replace("article_text", "articleContent");
        context=context.replaceAll("<h1>", "<h1 class=\"articleTitle\">");
        context=context.replaceAll("<a.*/a>","");
        context=context.replaceAll("<p style.*/p>","");
        doc2 = Jsoup.parse(context);
        String title = doc2.getElementsByAttributeValue("class","articleTitle").text();
        String author = doc2.getElementsByAttributeValue("class","articleAuthorName").text();
//        String text = doc2.getElementsByAttributeValue("class","articleContent").toString();
//        text = text.replace("<div class=\"articleContent\">","");
//        text = text.replace("</div>","");
//        text = text.replaceAll("<.*?>","");
        String context_html = head+doc2.getElementsByAttributeValue("class","container").toString();
        articleBean = new ArticleBean(title,context_html,author);
        //return context_html;
        return context_html;
    }


}
