package com.logyca.app;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Build;

public class DescriptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		
		Bundle b=getIntent().getExtras();
		LinearLayout lay=(LinearLayout) findViewById(R.id.linearLayout);
		lay.setBackgroundResource(R.drawable.noticia);
		TextView pageTitle=(TextView) findViewById(R.id.title);
		pageTitle.setText(b.getString("title"));
		TextView description=new TextView(this);
		description.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		description.setText(b.getString("description"));
		TextView link = new TextView(this);
		link.setText(Html.fromHtml(
	            "<a href=\""+b.getString("link")+"\">Ver mas</a>"));
		//link.setMovementMethod(LinkMovementMethod.getInstance());
		Linkify.addLinks(link, Linkify.ALL); 
		lay.addView(description);
		lay.addView(link);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
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
