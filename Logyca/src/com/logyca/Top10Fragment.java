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

public class Top10Fragment extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";
	CambiarEntreFragmentos mListener;
	ArrayList<Top10> tops10;
	Top10Adapter adaptador=null;
	Dialog progress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		this.tops10 = new ArrayList<Top10>();
		adaptador = new Top10Adapter(getActivity(), tops10);

		//Lista en el layout...
		ListView listaTop10s;
		listaTop10s = (ListView) rootView.findViewById(R.id.ServicesListView);
		//Ponerle el adaptador
		listaTop10s.setAdapter(adaptador);
		listaTop10s.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				//Toast.makeText(getActivity(), "Clicked position: "+position, Toast.LENGTH_SHORT).show();
				Bundle elBundle = new Bundle();
				Top10 top10 = tops10.get(position);
				elBundle.putString("titulo",top10.getTitulo());
				elBundle.putString("descripcion",top10.getDescripcion());
				elBundle.putString("link",top10.getLink());
				mListener.cambiarFragmento(6,elBundle);
			}
		});

		cargarTop10s();

		return rootView;
	}

	private void cargarTop10s() {
		progress = ProgressDialog.show(getActivity(), "Loading data", "Please wait...");
		AsyncHttpClient client = new AsyncHttpClient();
		//Data 
		// FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org
		String URL_complete = "http://www.tecnoeficiencia.com/movil/service.top10.php";

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
				JSONArray news = (JSONArray) jOb.get( "tops10" );
				for(int i=0;i<news.size();i++)
				{
					JSONObject auxNew = (JSONObject) news.get(i);
					final String titulo= (String) auxNew.get("titulo");
					final String descripcion=(String) auxNew.get("descripcionTop10");
					final String enlace= (String) auxNew.get("enlace");
					Top10 top10=new Top10(titulo, descripcion, enlace);
					adaptador.add(top10);
				}
			}
		});
	}

	public static Top10Fragment newInstance(int sectionNumber) {
		Top10Fragment fragment = new Top10Fragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Top10Fragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (CambiarEntreFragmentos) activity;
			mListener.cambiarTitulo("Top10");
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CambiarEntreFragmentos");
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListener.cambiarTitulo("Top10");
	}

}
