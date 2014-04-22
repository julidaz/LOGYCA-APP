package com.logyca;

import java.util.Arrays;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.*;
import com.facebook.android.*;

public class LoginActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.wvLayout) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}
	
	public void selfDestruct(View view) {
		Intent i;
		switch (view.getId()) {
		case R.id.btnLogin:
			//Action for login button, rigth now we're going to use it for testing purpose in a nfc Activity call
			i = new Intent(LoginActivity.this, HomeActivity.class );
			startActivity(i);
			break;
		case R.id.btnRegister:
			// Action to register
		    i = new Intent(LoginActivity.this, MainActivity.class );
			startActivity(i);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Facebook login part
	 * */
	/*
	private Session.StatusCallback statusCallback = new SessionStatusCallback();
		private void onClickLogin() {
		    Session session = Session.getActiveSession();
		    if (!session.isOpened() && !session.isClosed()) {
		        session.openForRead(new Session.OpenRequest(this)
		            .setPermissions(Arrays.asList("basic_info"))
		            .setCallback(statusCallback));
		    } else {
		        Session.openActiveSession(getActivity(), this, true, statusCallback);
		    }
		}
		private class SessionStatusCallback implements Session.StatusCallback {
		    @Override
		    public void call(Session session, SessionState state, Exception exception) {
		            // Respond to session state changes, ex: updating the view
		    }
		}
	*/
}
