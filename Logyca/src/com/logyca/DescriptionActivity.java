package com.logyca;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.util.Linkify;
import android.widget.LinearLayout;
import android.widget.TextView;

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

}
