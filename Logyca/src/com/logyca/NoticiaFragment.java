package com.logyca;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.logyca.MainActivity.PlaceholderFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NoticiaFragment extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";
	CambiarEntreFragmentos mListener;
	ArrayList<Noticia> noticias;
	NoticiaAdapter adaptador=null;
	Dialog progress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		this.noticias = new ArrayList<Noticia>();
		adaptador = new NoticiaAdapter(getActivity(), noticias);

		//Lista en el layout...
		ListView listaNoticias;
		listaNoticias = (ListView) rootView.findViewById(R.id.ServicesListView);
		//Ponerle el adaptador
		listaNoticias.setAdapter(adaptador);
		listaNoticias.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				//Toast.makeText(getActivity(), "Clicked position: "+position, Toast.LENGTH_SHORT).show();
				Bundle elBundle = new Bundle();
				Noticia noticia = noticias.get(position);
				elBundle.putString("titulo",noticia.getTitulo());
				elBundle.putString("descripcion",noticia.getDescripcion());
				elBundle.putString("link",noticia.getLink());
				mListener.cambiarFragmento(1,elBundle);
			}
		});

		cargarNoticias();
		return rootView;
	}

	private void cargarNoticias() {
		progress = ProgressDialog.show(getActivity(), "Loading data", "Please wait...");
		AsyncHttpClient client = new AsyncHttpClient();
		//Data 
		// FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org
		String URL_complete = "http://www.tecnoeficiencia.com/movil/service.noticias.php";

		RequestParams params = new RequestParams();
		params.put("correo", "julian.acevedo@colfuturo.org");
		client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				progress.dismiss();
				JSONParser parser=new JSONParser();
				JSONObject jOb = new JSONObject();
				try {
					jOb = (JSONObject) parser.parse(response);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONArray news = (JSONArray) jOb.get( "noticias" );
				for(int i=0;i<news.size();i++)
				{
					JSONObject auxNew = (JSONObject) news.get(i);
					final String titulo= (String) auxNew.get("titulo");
					final String descripcion=(String) auxNew.get("descripcionNoticia");
					final String enlace= (String) auxNew.get("enlace");
					Noticia noticia=new Noticia(titulo, descripcion, enlace);
					adaptador.add(noticia);
				}
			}
		});
	}

	public static NoticiaFragment newInstance(int sectionNumber) {
		NoticiaFragment fragment = new NoticiaFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public NoticiaFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (CambiarEntreFragmentos) activity;
			mListener.cambiarTitulo("Noticias");
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CambiarEntreFragmentos");
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListener.cambiarTitulo("Noticias");
	}


}
