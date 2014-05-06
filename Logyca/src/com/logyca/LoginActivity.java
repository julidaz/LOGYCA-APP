package com.logyca;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.android.*;
import com.loopj.android.http.*;

public class LoginActivity extends Activity{
	final Context context = this;
	
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
		switch (view.getId()) {
		case R.id.btnLogin:
			//Action for login button, rigth now we're going to use it for testing purpose in a nfc Activity call
			AsyncHttpClient client = new AsyncHttpClient();
			//Data 
			EditText user_edit = (EditText)findViewById(R.id.email_text);
			EditText pssw_edit = (EditText)findViewById(R.id.password_text);
			String USER = user_edit.getText().toString();
			String PSSW = pssw_edit.getText().toString();
			// FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
			String URL_complete = "http://www.colfuturo.org/movil/service.login.php";
			
			if( USER.isEmpty()==false && PSSW.isEmpty()==false ){
				RequestParams params = new RequestParams();
				params.put("correo", USER);
				params.put("clave", PSSW);
				client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
				    @Override
				    public void onSuccess(String response) {
				    	if (response.contains("resultadoLogin\":\"true\"")){
				    		Log.i("HTTPGet",response);
				    		Intent i = new Intent(LoginActivity.this, MainActivity.class );
				    		startActivity(i);
						}else{
							createAlert("Usuario o Contraseña incorrectos.");
						}
				    }
				    //Alert for the request if it's false
				    private void createAlert(String string) {
						// TODO Auto-generated method stub
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
						alertDialogBuilder.setTitle("Alerta");
						alertDialogBuilder.setMessage(string);
						alertDialogBuilder.setCancelable(true);
						alertDialogBuilder.setNeutralButton("Aceptar",
						        new DialogInterface.OnClickListener() {
						    		public void onClick(DialogInterface dialog, int id) {
						    			dialog.cancel();
						    		}
								});
						AlertDialog alert = alertDialogBuilder.create();
						alert.show();
					}
				});
			}
			//Validation for empty values in the fields to dont do trash petitions
			else{
				if(USER.isEmpty() || PSSW.isEmpty()){ createAlert("Servidor: Usuario o contraseña incorrectos."); break; }
			}
			//go to next intent
			//i = new Intent(LoginActivity.this, HomeActivity.class );
			//startActivity(i);
			break;
		case R.id.btnRegister:
			// Action to register
			Intent i = new Intent(LoginActivity.this, QRReaderActivity.class );
			startActivity(i);
			break;
		default:
			break;
		}
	}
	
	private void createAlert(String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Alerta");
		alertDialogBuilder.setMessage(string);
		alertDialogBuilder.setCancelable(true);
		alertDialogBuilder.setNeutralButton("Aceptar",
		        new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog, int id) {
		    			dialog.cancel();
		    		}
				});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
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
