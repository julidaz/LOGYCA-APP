package com.logyca;

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
import android.widget.TextView;
import android.os.Build;

public class RegisterActivity extends Activity {
	final Context reg_context = this;
	
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
			View rootView = inflater.inflate(R.layout.fragment_register,
					container, false);
			return rootView;
		}
	}

	@Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance(this).activityStart(this);  // Add this method.
      
      Button btn = (Button)findViewById(R.id.btn_registrar);
      btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView name = (TextView)findViewById(R.id.reg_name);
				boolean name_b = true;
				if(name.getText().toString().isEmpty()){ 
					createAlert("Por favor digite el nombre del usuario."); 
					name_b = false;
				}
				TextView email = (TextView)findViewById(R.id.reg_mail);
				boolean email_b = true;
				if(email.getText().toString().contains("@") == false || email.getText().toString().isEmpty()){
					createAlert("Por favor digite la direccion de correo o el campo de correo no es valido.");
					email_b = false;
				}
				TextView password = (TextView)findViewById(R.id.reg_psw);
				boolean password_b = true;
				if(password.getText().toString().isEmpty()){
					createAlert("Por favor digite la contraseña.");
					password_b = false;
				}
				TextView password_conf = (TextView)findViewById(R.id.reg_psw_cnf);
				boolean password_conf_b = true;
				if(password_conf.getText().toString().isEmpty()){
					createAlert("Por escriba la contraseña nuevamente.");
					password_conf_b = false;
				}else if(password.getText().toString().equals(password_conf.getText().toString()) == false){
					createAlert("Por verifique las contraseñas.");
					password_conf_b = false;
				}
				//TO DO REGISTER IF ALL IS TRUE
				Log.i("Register", "Can register?");
				if(name_b && 
				   email_b && 
				   password_b && 
				   password_conf_b && 
				   password.getText().toString().equals(password_conf.getText().toString()) ){
					Log.i("Register", "Going to register :)");
					AsyncHttpClient client = new AsyncHttpClient();
					String URL_complete = "http://www.tecnoeficiencia.com/movil/service.registros.php";
					RequestParams params = new RequestParams();
					params.put("correo", email.getText().toString());
					params.put("clave", password.getText().toString());
					params.put("nombre", name.getText().toString());
					
					client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
					    @Override
					    public void onSuccess(String response) {
					    	if (response.contains("resultadoLogin\":\"true\"")){
					    		Log.i("HTTPGet",response);
					    		createAlert("Usuario registrado correctamente, sera dirigido a la pantalla principal.");
					    		Intent i = new Intent(RegisterActivity.this, MainActivity.class );
					    		startActivity(i);
							}else{
								createAlert("El registro no pudo ser realizado, por favor intente nuevamente.");
							}
					    }
					    //Alert for the request if it's false
					    private void createAlert(String string) {
							// TODO Auto-generated method stub
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(reg_context);
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
			}
			
			//Alert for the request if it's false
		    private void createAlert(String string) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(reg_context);
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

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
}
