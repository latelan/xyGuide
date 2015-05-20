package com.late.xyindoor.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.late.xyindoor.R;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;


public class WifiSniffer {
	
	private WifiManager wm;
	private BroadcastReceiver wifiReceiver;
	private Handler mHandler;
	private Context context;
	private boolean running = false;
	
	private boolean sendRunning = false;
	private long scanTimestamp;
	private Finger finger;
	private List<ScanResult> results;
	private Map<String, Integer> map;
	private int op = 1;
	private HttpRequest sender;
	private String url;
	private int cnt; // 采集多少次
	private int i;   // 计数器
	
	public boolean isSendRunning() {
		return sendRunning;
	}

	public WifiSniffer(Context ctx, Handler handler) {
		context = ctx;
		mHandler = handler;
		sender = new HttpRequest();
		wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		wifiReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				
				if(i>=cnt){
					return ;
				}
				results = wm.getScanResults();
				scanTimestamp = System.currentTimeMillis()/1000;
				// 排序
				Collections.sort(results, new Comparator<ScanResult>() {
					public int compare(ScanResult a, ScanResult b) {
						return b.level - a.level;
					}
				});
				
				try {
					map = new HashMap<String, Integer>();
					int cnt = 0;
					for(int i=0; i<results.size(); i++){
						map.put(results.get(i).BSSID, results.get(i).level);
						if(++cnt >= 8){
							break;
						}
					}
					
					finger.setRSSIS(map);
					finger.setTimestamp(scanTimestamp);
					Log.d("finger", finger.getJsonString());

					if(op == 1){
						url = context.getResources().getString(R.string.server_info_collect);
						sender.postRequest(url, finger.getJsonString());
						i++;
						Message msg = mHandler.obtainMessage();
						msg.what = 0x123;
						msg.arg1 = i;
						mHandler.sendMessage(msg);
						
					} else {
						
					}
					//sender.postRequest(collect_url, finger.getJsonString());
				} catch(Exception e) { System.out.println(e); }
			}
		};
	}
	
	/**
	 * 获取信号强度最强的前八个wifi信号
	 * @return
	 */
	public Map<String, Integer> getWifiList() {
		Collections.sort(results, new Comparator<ScanResult>() {
			public int compare(ScanResult a, ScanResult b) {
				return b.level - a.level;
			}
		});
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int cnt = 0;
		for(int i=0; i<results.size(); i++){
			map.put(results.get(i).BSSID, results.get(i).level);
			if(++cnt >= 8){
				break;
			}
		}
		return map;
	}
	
	public void Start() {
		if(!running) {
			context.registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
			mHandler.post(new TimerProcess());
			running = true;
		}
	}
	
	public void Stop() {
		if(running) {
			context.unregisterReceiver(wifiReceiver);
			running = false;
		}
	}

	private class TimerProcess implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			wm.startScan();
			mHandler.postDelayed(this, 100);
		}
	}
	
	public void setFinger(Finger finger){
		this.finger = finger;
	}
	
	public void setCollectUrl(String url){
	}
	
	/**
	 * 设置选项，采集数据（1）还是请求定位（0）
	 * @param op
	 */
	public void setOp(int op){
		this.op = op;
	}
	
	public void setCnt(int cnt){
		this.cnt = cnt;
	}
}

