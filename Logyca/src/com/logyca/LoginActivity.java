package com.logyca;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.android.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.loopj.android.http.*;

public class LoginActivity extends Activity{
	final Context context = this;
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = 
	    new Session.StatusCallback() {
	    @Override
	    public void call(Session session, 
	            SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//Analytics Part, DO NOT DELETE THIS LINE
		EasyTracker easyTracker = EasyTracker.getInstance(this);
		
		uiHelper = new UiLifecycleHelper(this, callback);
	    uiHelper.onCreate(savedInstanceState);
	    
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
		EasyTracker easyTracker = EasyTracker.getInstance(this);
		switch (view.getId()) {
		case R.id.btnLogin:
			/* 
			 * analytics Event
			 */
			easyTracker.send(MapBuilder
			    .createEvent("ui_action",     // Event category (required)
			                 "button_press",  // Event action (required)
			                 "Login Button",   // Event label
			                 null)            // Event value
			    .build()
			);
			/*
			 * End Analytics 
			 */
			//Action for login button, rigth now we're going to use it for testing purpose in a nfc Activity call
			AsyncHttpClient client = new AsyncHttpClient();
			//Data 
			EditText user_edit = (EditText)findViewById(R.id.email_text);
			EditText pssw_edit = (EditText)findViewById(R.id.password_text);
			String USER = user_edit.getText().toString();
			String PSSW = pssw_edit.getText().toString();
			// FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
			String URL_complete = "http://www.tecnoeficiencia.com/movil/service.login.php";
			
			if( USER.isEmpty()==false && PSSW.isEmpty()==false ){
				RequestParams params = new RequestParams();
				params.put("correo", USER);
				params.put("clave", PSSW);
				client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
				    @Override
				    public void onSuccess(String response) {
				    	if (response.contains("resultadoLogin\":\"true\"")){
				    		Log.i("HTTPGet",response);
				    		EditText user_edit = (EditText)findViewById(R.id.email_text);
				    		String USER = user_edit.getText().toString();
				    		Intent i = new Intent(LoginActivity.this, MainActivity.class );
				    		i.putExtra("User", USER);
				    		startActivity(i);
						}else{
							createAlert("Usuario o Contrase�a incorrectos.");
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
				if(USER.isEmpty() || PSSW.isEmpty()){ createAlert("Usuario o contrase�a incorrectos."); break; }
			}
			//go to next intent
			//i = new Intent(LoginActivity.this, HomeActivity.class );
			//startActivity(i);
			break;
		case R.id.btnRegister:
			// Action to register
			/* Analytics Code */
			easyTracker.send(MapBuilder
			    .createEvent("ui_action",     // Event category (required)
			                 "button_press",  // Event action (required)
			                 "Register Button",   // Event label
			                 null)            // Event value
			    .build()
			);
			/* End Analytics */
			Intent i = new Intent(LoginActivity.this, RegisterActivity.class );
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
	
	@Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance(this).activityStart(this);  // Add this method.
      
      LoginButton authButton = (LoginButton) findViewById(R.id.btnFacebook);
	  authButton.setReadPermissions(Arrays.asList("user_location", "user_birthday", "user_likes", "email"));
    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
    
    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }
    
    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i("Fb", "Logged in...");
            //Verification code
            // Request user data and show the results
            Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
                @Override
                public void onCompleted(GraphUser user, Response response) {
                    if (user != null) {
                        // Display the parsed user info
                    	Log.e("Fb", user.getName());
                    	String email;
						try {
							email = user.getInnerJSONObject().getString("email");
							Log.e("Fb", email);
							facebookLoginflow(user);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }

				private void facebookLoginflow(final GraphUser user) throws JSONException {
					// TODO Auto-generated method stub
					AsyncHttpClient client = new AsyncHttpClient();
					String URL_complete = "http://www.tecnoeficiencia.com/movil/service.login.php";
					RequestParams params = new RequestParams();
					final String email = user.getInnerJSONObject().getString("email");
					params.put("correo", email);
					params.put("clave", "f4c3b00kl0gyc4");
					client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
					    @Override
					    public void onSuccess(String response) {
					    	if (response.contains("resultadoLogin\":\"true\"")){
					    		Log.i("HTTPGet",response);
					    		Intent i = new Intent(LoginActivity.this, MainActivity.class );
					    		i.putExtra("User", email);
					    		startActivity(i);
							}else{
								//create a facebook user
								AsyncHttpClient client = new AsyncHttpClient();
								String URL_complete = "http://www.tecnoeficiencia.com/movil/service.registros.php";
								RequestParams params = new RequestParams();
								params.put("correo", email);
								params.put("clave", "f4c3b00kl0gyc4");
								params.put("nombre", user.getName());
								
								client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
								    @Override
								    public void onSuccess(String response) {
								    	if (response.contains("resultadoLogin\":\"true\"")){
								    		Log.i("HTTPGet",response);
								    		createAlert("Usuario registrado correctamente, sera dirigido a la pantalla principal.");
								    		Intent i = new Intent(LoginActivity.this, MainActivity.class );
								    		i.putExtra("User", email);
								    		startActivity(i);
										}else{
											//nothing
										}
								    }
								});
							}
					    }
					});
				}
            });
            //end code
            Intent i = new Intent(LoginActivity.this, MainActivity.class );
			startActivity(i);
        } else if (state.isClosed()) {
            Log.i("Fb", "Logged out...");
        }
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }
}
