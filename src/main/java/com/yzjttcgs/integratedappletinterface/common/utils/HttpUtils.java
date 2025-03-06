package com.yzjttcgs.integratedappletinterface.common.utils;

import com.yzjttcgs.integratedappletinterface.common.exception.ServiceException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Author Miaoyu
 * Date 2025-02-19 16:57:34
 */
public class HttpUtils {
    public static String sendPost(String urlStr, String postData) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlStr);

        // 设置请求头
        httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");

        // 设置请求体
        StringEntity entity = new StringEntity(postData, "UTF-8");
        httpPost.setEntity(entity);

        try {
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            return EntityUtils.toString(responseEntity, "UTF-8");
        } catch (IOException e) {
            throw new ServiceException("接口调用异常");
        }
    }

    public static String post(String url, Map<String, String> params, int timeout, String charset, Map<String, String> headers)
            throws IOException {
        String encoding = "UTF-8";
        String result = "";
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        try {
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
            if (headers != null) {
                for (Map.Entry<String, String> param : headers.entrySet()) {
                    httpPost.addHeader(param.getKey(), param.getValue());
                }
            }
            httpPost.setEntity(entity);
            long beginTime = System.currentTimeMillis();
//			log.info("=================开始请求接口【headers={}】，【url={}】", JSON.toJSONString(headers), url);
            HttpResponse response = httpclient.execute(httpPost);
            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity, encoding);
//				log.info("=================请求接口结束，【耗时={}】", (System.currentTimeMillis() - beginTime));
            } else {
//				log.error("=================请求接口结束，【耗时={}】，【响应状态={}】", (System.currentTimeMillis() - beginTime), response.getStatusLine().getStatusCode());
            }

        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }
}
