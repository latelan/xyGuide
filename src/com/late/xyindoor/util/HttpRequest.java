package com.late.xyindoor.util;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
	}
	
	public String getResponseContent(){
		return this.strResponse;
	}
	
	public void Start() {
		
	}
}
