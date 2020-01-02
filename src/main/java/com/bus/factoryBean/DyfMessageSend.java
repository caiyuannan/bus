package com.bus.factoryBean;

import com.bus.javabean.DyfMessageBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;
@Component
public class DyfMessageSend {
	private int num ;
	protected static String send(String appid, String appkey, String to, String content) {
		String URL = "http://api.mysubmail.com/message/send.json";
		HashMap<String, String> paramer = new HashMap<String, String>();
		paramer.put("appid", appid);
		paramer.put("signature", appkey);
		paramer.put("to", to);
		paramer.put("content", content);
		return executePostByUsual(URL, paramer);

	}

	protected static String executePostByUsual(String actionURL, HashMap<String, String> parameters) {
		String response = "";
		try {
			URL url = new URL(actionURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置请求数据内容
			String requestContent = "";
			Set<String> keys = parameters.keySet();
			for (String key : keys) {
				requestContent = requestContent + key + "=" + URLEncoder.encode(parameters.get(key), "UTF-8") + "&";
			}
			requestContent = requestContent.substring(0, requestContent.lastIndexOf("&"));

			DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
			// 使用write(requestContent.getBytes())是为了防止中文出现乱码
			ds.write(requestContent.getBytes());
			ds.flush();
			try {
				// 获取URL的响应
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String s = "";
				String temp = "";
				while ((temp = reader.readLine()) != null) {
					s += temp;
				}

				response = s;
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("No response get!!!");
			}
			ds.close();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Request failed!");
		}
		return response;
	}

	public DyfMessageBean messageReturn(String to) {
        num=(int)((Math.random()*9+1)*100000);
		String response = DyfMessageSend.send("44962", "b3cb27bee6dbb9f1d717950da9fbd627", to, "【传一智能公交车】亲爱的用户,您的验证码为："+num);
		ObjectMapper objectMapper = new ObjectMapper();
		DyfMessageBean dyf = null;
		try
		{
			 dyf = objectMapper.readValue(response,DyfMessageBean.class);
			 dyf.setNum(num);
			return dyf;
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return dyf;
	}


}
