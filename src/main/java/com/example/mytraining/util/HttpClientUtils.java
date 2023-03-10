package com.example.mytraining.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HttpClientUtils {

    public static String getContentByUrl(String url){
        String asinUrl = url;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(asinUrl);
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");
        httpPost.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        httpPost.addHeader("Accept-Encoding","gzip, deflate, br");
        httpPost.addHeader("Accept-Language","zh-CN,zh;q=0.9");
        httpPost.addHeader("Cache-Control","max-age=0");
        httpPost.addHeader("Connection","keep-alive");
        httpPost.addHeader("Cookie","session-id=457-0425967-3090739; i18n-prefs=CNY; ubid-acbcn=461-2096995-4590402; session-token=\"/gvtmSGaYLLoWVWGxkjhNULIoL8l4yAsVg0xP8qpLncHk1Fnsk0PpkdkOnKRumiH2W2u201Ra2jdXThsMRKmH2HiOAuWQPPm2oXMF9QNXbawfZshAKCNh1os0xZe5sUf5buNbHNmDKDVBNTyvpAC5QAzUPpi83R3VlReRKAFBr4fB9YIAjyDv6SRqNFljMbuAq1R42AP7k3zAkkD6AaPRGUxloJuCZh20FyYMF0nMv4=\"; csm-hit=tb:XZT29QYSFAM305VX0YYQ+s-R4WHG5S4BMGKSQDSH0B5|1678158620559&t:1678158620559&adb:adblk_no; session-id-time=2082729601l");
        httpPost.addHeader("downlink","8.5");
        httpPost.addHeader("ect","4g");
        httpPost.addHeader("Host","www.amazon.cn");
        httpPost.addHeader("rtt","150");
        httpPost.addHeader("sec-ch-ua","\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"");
        httpPost.addHeader("sec-ch-ua-mobile","?0");
        httpPost.addHeader("sec-ch-ua-platform","Windows");
        httpPost.addHeader("sec-ch-ua-platform-version","10.0.0");
        httpPost.addHeader("Sec-Fetch-Dest","document");
        httpPost.addHeader("Sec-Fetch-Mode","navigate");
        httpPost.addHeader("Sec-Fetch-Site","same-origin");
        httpPost.addHeader("Sec-Fetch-User","?1");
        httpPost.addHeader("Upgrade-Insecure-Requests","1");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            String html = EntityUtils.toString(response.getEntity(), "utf-8");
            return html;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
