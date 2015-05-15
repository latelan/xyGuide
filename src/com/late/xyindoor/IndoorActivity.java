package com.late.xyindoor;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

public class IndoorActivity extends Activity {

	private ImageView indoorMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indoor);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		indoorMap = (ImageView)findViewById(R.id.indoor_map);
		indoorMap.setImageResource(R.drawable.indoor_dongqu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			finish();
			return true;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
