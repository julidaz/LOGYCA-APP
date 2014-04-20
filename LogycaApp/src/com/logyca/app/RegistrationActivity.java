package com.logyca.app;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.security.KeyStore.PasswordProtection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xmlpull.v1.XmlPullParserException;

import android.R.color;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class RegistrationActivity extends Activity {

	private String userJson = null;
	private String interests = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		TextView loginLink = (TextView) findViewById(R.id.link_to_login);
		loginLink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(RegistrationActivity.this,
						LoginActivity.class);
				startActivity(i);
			}
		});

		Button b = (Button) findViewById(R.id.btnRegistration);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// validate
				TextView email = (TextView) findViewById(R.id.email_text);
				TextView pw = (TextView) findViewById(R.id.password_text);
				TextView error = (TextView) findViewById(R.id.registrationError);
				email.setTextColor(Color.BLACK);
				pw.setTextColor(Color.BLACK);
				error.setText("");


				if (!isValidEmail(email.getText())) {
					error.setText("Email is not valid");
					email.setTextColor(Color.RED);
				} else if (pw.getText().length() < 6) {
					error.setText("Password is too short");
					pw.setTextColor(Color.RED);
				} else {
					updateUserJson();

					if (validateUserInfo()) {
						Intent i = new Intent(RegistrationActivity.this,
								InterestSelectionActivity.class);
						Bundle b = new Bundle();
						b.putString("userInformation", userJson);
						i.putExtras(b);
						startActivity(i);
					} else {
						error.setText("Email is already in use");
					}
				}
			}
		});
	}

	private boolean validateUserInfo() {
		//return ws.validate(userJson);
		return true;
	}

	private void getInterests() {
		// TODO Auto-generated method stub
		// interests=ws.getInterests();
	}

	public boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

	private void updateUserJson() {
		// TODO Auto-generated method stub

		TextView email = (TextView) findViewById(R.id.email_text);
		TextView pw = (TextView) findViewById(R.id.password_text);

		JSONObject jOb = new JSONObject();
		try {
			jOb.put("usuario", email.getText());
			jOb.put("clave", pw.getText());
			userJson = jOb.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
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
