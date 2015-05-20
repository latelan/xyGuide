package com.late.xyindoor.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.util.Log;

public class HttpRequest {

	private DefaultHttpClient httpClient;
	private List<NameValuePair> postList;
	private String strResponse;

	public HttpRequest() {
		// TODO Auto-generated constructor stub
		//httpClient = new DefaultHttpClient();
	}

	public void postRequest(String url, String data) {
		AsyncHttpClient client =  new AsyncHttpClient();
		RequestParams  params =  new RequestParams();
		params.put("content", data);
		
		client.post(url, params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] responseBody) {
				// TODO Auto-generated method stub
				if(statusCode == 200){
					//发送成功
					strResponse = responseBody.toString();
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				// 发送失败
			}
		});
		
		// 分割线
		///////////////////////////////////////////////
//		HttpPost post = new HttpPost(url);
//		postList = new ArrayList<NameValuePair>();
//		NameValuePair pair = new BasicNameValuePair("content", data);
//		postList.add(pair);
//
//		try {
//			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postList,
//					"UTF-8");
//			post.setEntity(entity);
//
//			HttpResponse response = httpClient.execute(post);
//
//			Log.d("http", response.getStatusLine().toString());
//			if (response.getStatusLine().getStatusCode() == 200) {
//				strResponse = EntityUtils.toString(response.getEntity());
//				
//				return true;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//			strResponse = e.getMessage();
//		}
//		
//		return false;
	}
	
	public String getResponseContent(){
		return this.strResponse;
	}
	
	public void Start() {
		
	}
}
