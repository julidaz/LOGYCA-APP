package com.logyca;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrendsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trends);
		loadTrends();
	}

	private void loadTrends() {
		// TODO Auto-generated method stub
		//llamar ws {"usuario":"pepe"}
    	String noticias="{\"tendencias\":[{\"titulo\":\"tend1\",\"descripcionTendencia\":\"blah\",\"enlace\":\"www.google.com\"},{\"titulo\":\"tend2\",\"descripcionTendencia\":\"blah\",\"enlace\":\"www.google.com\"}]}";
    	JSONParser parser=new JSONParser();
		JSONObject jOb = new JSONObject();
		try {
			jOb = (JSONObject) parser.parse(noticias);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray news = (JSONArray) jOb.get( "tendencias" );
		LinearLayout ll=(LinearLayout) this.findViewById(R.id.linearLayout);
		for(int i=0;i<news.size();i++)
		{
			JSONObject auxNew = (JSONObject) news.get(i);
			final String titulo= (String) auxNew.get("titulo");
			final String descripcion=(String) auxNew.get("descripcionTendencia");
			final String enlace= (String) auxNew.get("enlace");
			LinearLayout lay=new LinearLayout(this);
			lay.setBackgroundResource(R.drawable.ic_launcher);
			TextView title=new TextView(this);
			title.setText(titulo);
			TextView description=new TextView(this);
			description.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
			description.setText(descripcion);
			lay.addView(title);
			lay.addView(description);
			lay.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i=new Intent(TrendsActivity.this, DescriptionActivity.class);
					Bundle b=new Bundle();
					b.putString("title", titulo);
					b.putString("description", descripcion);
					b.putString("link", enlace);
					i.putExtras(b);
					startActivity(i);
				}
			});
			ll.addView(lay);
		}   
	}
}
