package com.logyca;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//Analytics Part, DO NOT DELETE THIS LINE
		EasyTracker easyTracker = EasyTracker.getInstance(this);
				
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home, container,
					false);
			return rootView;
		}
	}
	
	public void selfDestruct(View view) {
		Intent i;
		switch (view.getId()) {
		case R.id.btnTrends:
			//Action for login button, rigth now we're going to use it for testing purpose in a nfc Activity call
			i = new Intent(HomeActivity.this, TrendsActivity.class );
			startActivity(i);
			break;
		case R.id.btnNFC:
			// Action to register
		    i = new Intent(HomeActivity.this, NFCReaderActivity.class );
			startActivity(i);
			break;
		case R.id.btnNews:
			// Action to register
		    i = new Intent(HomeActivity.this, NewsActivity.class );
			startActivity(i);
			break; 
		default:
			break;
		}
	}

}
