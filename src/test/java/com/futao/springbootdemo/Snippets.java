package com.futao.springbootdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.lazyer.httpclient.AbstractBaseRequest;
import com.lazyer.httpclient.GetRequest;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * 有意思的代码片段
 *
 * @author futao
 * Created on 2019-01-16.
 */
public class Snippets {

    @SneakyThrows
    @Test
    public void test3() {
        String url = "http://localhost:8887/v2/api-docs";
        AbstractBaseRequest request = new GetRequest(url);
        String result = request.send();
        JSONObject resultJson = JSON.parseObject(result);

        String host = resultJson.getString("host");
        String basePath = resultJson.getString("basePath");


        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iText11.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16f, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);
        document.add(chunk);
        document.add(new Anchor(1, "anchor"));
        document.add(new Annotation(1, 1, 1, 1, "http://localhost:8887"));
        document.add(new Chapter("chapter", 2));
        document.add(new Header("header", "content"));
        //
//        document.add(new Jpeg(new URL("https", "upload.jianshu.io", 80, "admin_banners/web_images/4592/22f5cfa984d47eaf3def6a48510cc87c157bf293.png?imageMogr2/auto-orient/strip|imageView2/1/w/1250/h/540")));
        document.add(new List(true, true));
        document.add(new ListItem(1, "213213"));
        document.add(new Meta("meatTag", "mateContent"));
        document.add(new Paragraph());
        document.add(new Phrase());
        document.add(new Rectangle(1, 1, 1, 1));
        loadPaths(resultJson.getJSONObject("paths"));
        document.close();
    }


    public void loadInfo(JSONObject jsonObject) {
        JSONObject info = jsonObject.getJSONObject("info");
        String description = info.getString("description");
        String version = info.getString("version");
        String title = info.getString("title");
    }

    public void loadPaths(JSONObject jsonObject) {
        jsonObject.forEach((key, value) -> System.out.println(key + ":::" + value));
    }

    @Test
    public void test2() {
        outer:
        for (int i = 0; i < 100; i++) {
            in:
            for (int j = 0; j < 100; j++) {
                if (i == 1) {
                    break outer;
                }
                break in;
            }
        }
    }
//    @Test
//    public void test2() {
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("-");
//            }
//        };
//        /**
//         * 使用的时候会在主线程之外起一个单独的线程执行指定的计划任务，可以指定执行一次或者反复执行多次
//         * 是否设置为守护进程
//         */
//        new Timer().schedule(task, 0, 2000);
//    }


    /**
     * 让当前线程sleep一会
     */
    @Test
    public void test1() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
