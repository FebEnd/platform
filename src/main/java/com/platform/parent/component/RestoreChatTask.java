package com.platform.parent.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.parent.easemob.api.impl.EasemobChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

/**
 * Created by tqyao.
 */
@Component
public class RestoreChatTask {
    private static final EasemobChatMessage easemobChatMessage = new EasemobChatMessage();
    private static final Logger logger = LoggerFactory.getLogger(RestoreChatTask.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHH");

    /**
     * 每50分钟拉取一次聊天记录
     */
    @Scheduled(fixedRate = 3000*1000)
    public void restoreChatTask() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -2);
        String timeStr = SDF.format(calendar.getTime());
        Object result = easemobChatMessage.exportChatMessages(timeStr);
        String url = getUrl((String) result);
        String savePath = "C:/platform/";
        String filename = url.substring(url.lastIndexOf("/"), url.indexOf("?"));
        try {
            downLoadFromUrl(url, filename,"C:/platform/");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getUrl(String json) {
        JSONObject o = JSON.parseObject(json);
        JSONArray data = o.getJSONArray("data");
        return data.getJSONObject(0).getString("url");
    }

    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

        System.out.println(new String(getData,"utf-8"));

        System.out.println("info:"+url+" download success");

    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    private static void readRecordAndRestore(String filename) {
        InputStream in = null;
        try {
            in = new GZIPInputStream(new FileInputStream(filename));
            Scanner sc=new Scanner(in);
            List<String> lines=new ArrayList();
            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            String all = lines.toString();
            JSONArray msgs = JSON.parseArray(all);
            for (Object o : msgs) {

            }
            System.out.println(lines.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        try{
//            downLoadFromUrl("http://ebs-chatmessage-a1.easemob.com/history/3D/1171170802178680/testdemo/2017110820.gz?Expires=1510152499&OSSAccessKeyId=LTAIlKPZStPokdA8&Signature=UsJo3LXlz8pQ2MVHrJB9XWdjKx0%3D",
//                    "test.gz","e:/");
//        }catch (Exception e) {
//            // TODO: handle exception
//        }
        String filename = "E:/test.gz";
        InputStream in = null;
        try {
            in = new GZIPInputStream(new FileInputStream(filename));
            Scanner sc=new Scanner(in);
            List<String> lines=new ArrayList();
            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            String all = lines.toString();
            System.out.println(all.substring(1,all.length()-1));
//            System.out.println(lines.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String result = "{\n" +
//                "  \"action\" : \"get\",\n" +
//                "  \"application\" : \"01676110-772f-11e7-98fa-93ecab3ef3f2\",\n" +
//                "  \"uri\" : \"http://a1.easemob.com/1171170802178680/testdemo/chatmessages/2017110820\",\n" +
//                "  \"data\" : [ {\n" +
//                "    \"url\" : \"http://ebs-chatmessage-a1.easemob.com/history/3D/1171170802178680/testdemo/2017110820.gz?Expires=1510152499&OSSAccessKeyId=LTAIlKPZStPokdA8&Signature=UsJo3LXlz8pQ2MVHrJB9XWdjKx0%3D\"\n" +
//                "  } ],\n" +
//                "  \"timestamp\" : 1510150699457,\n" +
//                "  \"duration\" : 0,\n" +
//                "  \"organization\" : \"1171170802178680\",\n" +
//                "  \"applicationName\" : \"testdemo\"\n" +
//                "}";
//        JSONObject o = JSON.parseObject(result);
//        JSONArray data = o.getJSONArray("data");
//        System.out.println(data.getJSONObject(0).getString("url"));
    }
}
