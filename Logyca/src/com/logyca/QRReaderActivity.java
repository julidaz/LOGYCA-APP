package com.logyca;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QRReaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrreader);
		
		//Analytics Part, DO NOT DELETE THIS LINE
		EasyTracker easyTracker = EasyTracker.getInstance(this);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
    protected void onStart() {
        super.onStart();
        ImageButton scanBtn = (ImageButton) findViewById(R.id.btnScanQR);
		//in some trigger function e.g. button press within your code you should add:
		scanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					
					Intent intent = new Intent("com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();

				}

			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qrreader, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_qrreader,
					container, false);
			return rootView;
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	   if (requestCode == 0) {
	      if (resultCode == RESULT_OK) {
	         String contents = intent.getStringExtra("SCAN_RESULT");
	         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	         // Handle successful scan
	         // vibrate to notify the event
         	 Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
         	 // Vibrate for 250 milliseconds
         	 v.vibrate(250);
	         //TextView txt = (TextView)findViewById(R.id.qrreader_tittle);
	         //txt.setText("Resultado:"+contents);
         	 //loadTagWebView(contents);
   	         loadTagWebView("https://docs.google.com/gview?embedded=true&url=http://fzs.sve-mo.ba/sites/default/files/dokumenti-vijesti/sample.pdf");
	         //loadTagWebView("https://docs.google.com/gview?embedded=true&url=http://www.colfuturo.org/movil/pdf.php?correo=jmasmq100@gmail.com");
	         //myWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=http://www.colfuturo.org/movil/pdf.php?correo=jmasmq100@gmail.com");
	      } else if (resultCode == RESULT_CANCELED) {
	         // Handle cancel
	      }
	   }
	}
	
	private void loadTagWebView(String tag_text) {
		// TODO Auto-generated method stub
		WebView mView = (WebView)findViewById(R.id.webQRView);
		//posible close button showup
		LinearLayout myLayout =(LinearLayout)findViewById(R.id.webviewQRLayout);
		myLayout.setVisibility(View.VISIBLE);
		mView.setWebViewClient(new WebViewClient());
		mView.loadUrl(tag_text);
		//mView.getSettings().setAllowFileAccessFromFileURLs(true);
		mView.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36");
		mView.getSettings().setJavaScriptEnabled(true);
		mView.getSettings().setSupportZoom(true);
		mView.getSettings().setBuiltInZoomControls(false);
		mView.getSettings().setUseWideViewPort(true);
		Log.d("TAG","TICK "+ tag_text );
	}
	
	public void selfDestruct(View view) {
		//Action for login button, rigth now we're going to use it for testing purpose in a nfc Activity call
		LinearLayout myLayout =(LinearLayout)findViewById(R.id.webviewQRLayout);
		myLayout.setVisibility(View.INVISIBLE);
	}
}
