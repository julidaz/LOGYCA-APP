package com.logyca.app;

import java.util.HashSet;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

public class InterestSelectionActivity extends Activity {

	String interests=null;
	String userInfo=null;
	HashSet<String> interesesSeleccionados=null;
	JSONObject jsonIntereses=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interest_selection);
		Bundle b=getIntent().getExtras();
		userInfo = b.getString("userInformation");
		try {
			loadInterests();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonIntereses=new JSONObject();
		final JSONArray arrayIntereses=new JSONArray();
		try {
			jsonIntereses.put("usuario", b.getString("usuario"));
			jsonIntereses.put("clave", b.getString("clave"));
			jsonIntereses.put("correo", b.getString("usuario"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		interesesSeleccionados=new HashSet<String>();
		Button saveButton=(Button) findViewById(R.id.btnSave);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//{"usuario":"pepe","clave":"qwerty","correo":"pepe@mail.com","intereses":[{"idInteres":"1"},{"idInteres":"2"}]} 
				
				Iterator<String> it=interesesSeleccionados.iterator();
				while(it.hasNext())
				{
					String tituloInteres=it.next();
					JSONObject auxInteres=new JSONObject();
					try {
						auxInteres.put("idInteres", tituloInteres);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					arrayIntereses.put(auxInteres);
				}
				try {
					jsonIntereses.put("intereses", arrayIntereses);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				saveUserInterests();
			}
		});
	}

	private void saveUserInterests() {
		// TODO Auto-generated method stub
		//ws.saveUserInterests(jsonIntereses);
	}
	
	private void loadInterests() throws JSONException {
		// TODO Auto-generated method stub
		//interests=ws.getInterests();
		interests="{\"intereses\":[{\"titulo\":\"int1\",\"descripcionInteres\":\"blah\"},{\"titulo\":\"int2\",\"descripcionInteres\":\"blah\"}]}";
		JSONObject jOb=new JSONObject(interests);
		JSONArray intereses=jOb.getJSONArray("intereses");
		
		LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linearLayout);
		linearLayout.setHorizontalScrollBarEnabled(true);
		for (int i = 0; i < intereses.length(); i++) {
			JSONObject interes=(JSONObject) intereses.get(i);
			final CheckBox ch=new CheckBox(this);
			ch.setText(interes.getString("titulo"));
			ch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(!interesesSeleccionados.contains(ch.getText()))
					{
						//add to hashSet
						interesesSeleccionados.add(ch.getText().toString());
					}
					else
					{
						//remove from hashSet
						interesesSeleccionados.remove(ch.getText().toString());
					}
				}
			});
			TextView desc=new TextView(this);
			desc.setText(interes.getString("descripcionInteres"));
			LinearLayout ll=new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			ch.setTextColor(Color.BLACK);
			desc.setTextColor(Color.BLACK);
			ll.addView(ch);
			ll.addView(desc);
			linearLayout.addView(ll);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interest_selection, menu);
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

}
