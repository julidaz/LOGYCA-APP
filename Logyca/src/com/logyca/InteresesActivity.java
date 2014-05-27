package com.logyca;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.analytics.tracking.android.EasyTracker;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class InteresesActivity extends Activity {
	final Context int_context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

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
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
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
			View rootView = inflater.inflate(R.layout.fragment_intereses,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);  // Add this method.

		Button btn = (Button)findViewById(R.id.btn_guardar);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				StringBuilder sb=new StringBuilder();
				LinearLayout ll = (LinearLayout) v.findViewById(R.id.linearLayout);
				int count = ll.getChildCount();
				for(int i = 0;i<count;i++)
				{
					View auxView = ll.getChildAt(i);
					if(auxView instanceof Switch)
					{
						Switch s = (Switch) v;
						sb.append(getResources().getResourceName(s.getId()));
						sb.append("-");
					}
				}
				if(sb.charAt(sb.length()-1)=='-')
					sb.deleteCharAt(sb.length()-1);
				
				final String intereses=sb.toString();
				AsyncHttpClient client = new AsyncHttpClient();
				String URL_complete = "http://www.tecnoeficiencia.com/movil/service.grabaIntereses.php";

				RequestParams params = new RequestParams();
				params.put("correo", "julian.acevedo@colfuturo.org");
				params.put("intereses", intereses);
				client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						Toast.makeText(int_context, "Intereses guardados: "+intereses, Toast.LENGTH_SHORT).show();

					}
				});
			}
		});
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	}
}
