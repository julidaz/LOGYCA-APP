package com.logyca;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewsActivity extends Activity {

	private String newsJson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		//Analytics Part, DO NOT DELETE THIS LINE
		EasyTracker easyTracker = EasyTracker.getInstance(this);
				
		getNewsFromWS();
		loadNews();
	}
	
	private void getNewsFromWS() {
//		//Constantes para la invocacion del web service
//		String NAMESPACE = "http://tempuri.org/";
//		String URL="http://192.168.0.231/EjemploWS/Service.asmx";
//		String METHOD_NAME = "getAllAndroidOS";
//		String SOAP_ACTION ="http://tempuri.org/getAllAndroidOS";
//
//		//Declaracion de variables para consuymir el web service
//		SoapObject request=null;
//		SoapSerializationEnvelope envelope=null;
//		SoapPrimitive  resultsRequestSOAP=null;
////		Se crea un objeto SoapObject para poder realizar la peticion
////		para consumir el ws SOAP. El constructor recibe
////		el namespace. Por lo regular el namespace es el dominio
////		donde se encuentra el web service
//		request = new SoapObject(NAMESPACE, METHOD_NAME);
//		request.addProperty("user", "pepe");
//
////		Se crea un objeto SoapSerializationEnvelope para serealizar la
////		peticion SOAP y permitir viajar el mensaje por la nube
////		el constructor recibe la version de SOAP
//		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//		envelope.dotNet = true; //se asigna true para el caso de que el WS sea de dotNet
//
//		//Se envuelve la peticion soap
//		envelope.setOutputSoapObject(request);
//
//		//Objeto que representa el modelo de transporte
//		//Recibe la URL del ws
//		HttpTransportSE transporte = new HttpTransportSE(URL);
//
//		try {
//			//Hace la llamada al ws
//			transporte.call(SOAP_ACTION, envelope);
//
//			//Se crea un objeto SoapPrimitive y se obtiene la respuesta
//			//de la peticion
//			resultsRequestSOAP = (SoapPrimitive)envelope.getResponse();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//Almacenamos el resultado en un String ya que lo que represa
//		//el ws es una cadena json, representando una lista AndroidOS
//		//de objetos del tipo
//		this.newsJson = resultsRequestSOAP.toString();
	}

	private void loadNews() {
		// TODO Auto-generated method stub
		//llamar ws {"usuario":"pepe"}
    	String noticias="{\"noticias\":[{\"titulo\":\"noticia1\",\"descripcionNoticia\":\"blah\",\"enlace\":\"www.google.com\"},{\"titulo\":\"noticia2\",\"descripcionNoticia\":\"blah\",\"enlace\":\"www.facebook.com\"}]}";
    	JSONParser parser=new JSONParser();
		JSONObject jOb = new JSONObject();
		try {
			jOb = (JSONObject) parser.parse(noticias);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray news = (JSONArray) jOb.get( "noticias" );
		LinearLayout ll=(LinearLayout) this.findViewById(R.id.linearLayout);
		for(int i=0;i<news.size();i++)
		{
			JSONObject auxNew = (JSONObject) news.get(i);
			final String titulo= (String) auxNew.get("titulo");
			final String descripcion=(String) auxNew.get("descripcionNoticia");
			final String enlace= (String) auxNew.get("enlace");
			LinearLayout lay=new LinearLayout(this);
			lay.setBackgroundResource(R.drawable.noticia);
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
					Intent i=new Intent(NewsActivity.this, DescriptionActivity.class);
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
