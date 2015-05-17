package com.late.xyindoor;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class SniffeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sniffe);
		ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(true);
		
		
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

	public SniffeActivity() {
		// TODO Auto-generated constructor stub
	}

}
