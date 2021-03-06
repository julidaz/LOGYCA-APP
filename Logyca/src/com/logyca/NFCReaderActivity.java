package com.logyca;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.net.http.SslError;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NFCReaderActivity extends Activity{
	NfcAdapter myNfcAdapter;
	public static final String MIME_TEXT_PLAIN = "text/plain";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfcreader);
		
		myNfcAdapter = NfcAdapter.getDefaultAdapter(this);
				
        if (myNfcAdapter == null) {
            // Stop here, we definitely need NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
         
        handleIntent(getIntent());
		
        EasyTracker easyTracker = EasyTracker.getInstance(this);
        
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void selfDestruct(View view) {
		//Action for login button, rigth now we're going to use it for testing purpose in a nfc Activity call
		LinearLayout myLayout =(LinearLayout)findViewById(R.id.wvLayout);
		myLayout.setVisibility(View.INVISIBLE);
	}
	
	private void handleIntent(Intent intent) {
	    String action = intent.getAction();
	    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
	         
	        String type = intent.getType();
	        TextView text = (TextView)findViewById(R.id.NFC_placeholder_text);
	        if (MIME_TEXT_PLAIN.equals(type)) {
	            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
	            NdefMessage[] messages = null;
	            if (rawMsgs != null) {
	                messages = new NdefMessage[rawMsgs.length];
	                for (int i = 0; i < rawMsgs.length; i++) {
	                    messages[i] = (NdefMessage) rawMsgs[i];
	                }}
	            if(messages[0] != null) {
	                String result="";
	                byte[] payload = messages[0].getRecords()[0].getPayload();
	                for (int b = 1; b<payload.length; b++) { // skip SOH
	                    result += (char) payload[b];
	                }
	                Log.d("NFC", result);
	                	// vibrate to notify the event
	                	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	                	// Vibrate for 250 milliseconds
	                	v.vibrate(250);
	                text.setText("Tag encontrado." );
		            SystemClock.sleep(500);
		            loadTagWebView( result.substring(2) );
		            //loadTagWebView("https://docs.google.com/a/colfuturo.org/gview?embedded=true&url=http://www.colfuturo.org/movil/pdf.php?correo=jmasmq100@gmail.com");
		            
		            /*WebSettings webSettings = myWebView.getSettings();
		            webSettings.setJavaScriptEnabled(true);
		            //String urlDir="http://web.presidencia.gov.co/constitucion/index.pdf";
		    		myWebView.loadUrl("https://docs.google.com/a/colfuturo.org/gview?embedded=true&url=http://www.colfuturo.org/movil/pdf.php?correo=jmasmq100@gmail.com");
	            */}
	        } else {
	            Log.d("NFC", "Wrong mime type: " + type);
	            text.setText("");
	        }
	    } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
	         
	        // In case we would still use the Tech Discovered Intent
	        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
	        String[] techList = tag.getTechList();
	        String searchedTech = Ndef.class.getName();
	    }
	}
	
	private void loadTagWebView(String tag_text) {
		// TODO Auto-generated method stub
		WebView mView = (WebView)findViewById(R.id.webNFCView);
		//posible close button showup
		LinearLayout myLayout =(LinearLayout)findViewById(R.id.wvLayout);
		myLayout.setVisibility(View.VISIBLE);
		mView.setWebViewClient(new WebViewClient());
		mView.loadUrl("http://"+tag_text);
		//mView.getSettings().setAllowFileAccessFromFileURLs(true);
		mView.getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36");
		mView.getSettings().setJavaScriptEnabled(true);
		mView.getSettings().setSupportZoom(true);
		mView.getSettings().setBuiltInZoomControls(false);
		mView.getSettings().setUseWideViewPort(true);
		Log.d("TAG","TICK "+ tag_text );
	}

	@Override
    protected void onPause() {
        /**
         * Call this before onPause, otherwise an IllegalArgumentException is thrown as well.
         */
        stopForegroundDispatch(this, myNfcAdapter);
        TextView text = (TextView)findViewById(R.id.NFC_placeholder_text);
        text.setText("");
        super.onPause();
    }

	/**
     * @param activity The corresponding {@link BaseActivity} requesting to stop the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
    public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }
	
	@Override
    protected void onNewIntent(Intent intent) { 
        /**
         * This method gets called, when a new Intent gets associated with the current activity instance.
         * Instead of creating a new activity, onNewIntent will be called. For more information have a look
         * at the documentation.
         * 
         * In our case this method gets called, when the user attaches a Tag to the device.
         */
        handleIntent(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nfcreader, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_nfcreader,
					container, false);
			return rootView;
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		/**
         * It's important, that the activity is in the foreground (resumed). Otherwise
         * an IllegalStateException is thrown. 
         */
        setupForegroundDispatch(this, myNfcAdapter);
	}

	/**
     * @param activity The corresponding {@link Activity} requesting the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 
        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);
 
        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};
 
        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (MalformedMimeTypeException e) {
            throw new RuntimeException("Check your mime type.");
        }
         
        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }
    
    @Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
}