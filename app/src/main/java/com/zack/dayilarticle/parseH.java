package com.zack.dayilarticle;


import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


/**
 * Created by zack on 2016/3/6.
 */
public class parseH {
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
            Log.d("doc2", "网络连接失败！");
            return "<p>网络连接失败！</p>";
        }

        //if(doc2.toString()==null)return  null;
        String context = doc2.body().toString();
        Log.d("doc2", context);
        context=context.replace("id=\"article_show\"", "class=\"container\"");
        context=context.replace("article_author", "articleAuthorName");
        context=context.replace("article_text", "articleContent");
        context=context.replaceAll("<h1>", "<h1 class=\"articleTitle\">");
        context=context.replaceAll("<a.*/a>","");
        context=context.replaceAll("<p style.*/p>","");
        doc2 = Jsoup.parse(context);
        String title = head+doc2.getElementsByAttributeValue("class","container").toString();
        return title;

    }


}
