package com.wyj.coolweather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
	public static void sendHttpRequest(final String address, final HttpCallBack callBack){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(5000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while((line = reader.readLine()) != null){
						response.append(line);
					}
					if(callBack != null){
						callBack.onFinish(response.toString());
					}
				} catch (Exception e) {
					if(callBack != null){
						callBack.onError(e);
					}
				} finally{
					if(connection != null){
						connection.disconnect();
					}
				}
			}
		}).start();
	}
	
	
	public interface HttpCallBack{
		void onFinish(String response);
		
		void onError(Exception e);
	}
}
