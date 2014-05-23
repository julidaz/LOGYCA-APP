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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class EstandarFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	CambiarEntreFragmentos mListener;
	ArrayList<Estandar> estandares;
	EstandarAdapter adaptador=null;
	Dialog progress;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		this.estandares = new ArrayList<Estandar>();
		adaptador = new EstandarAdapter(getActivity(),
				estandares);

		// Lista en el layout...
		ListView listaEstandars;
		listaEstandars = (ListView) rootView
				.findViewById(R.id.ServicesListView);
		// Ponerle el adaptador
		listaEstandars.setAdapter(adaptador);
		listaEstandars
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView,
							View view, int position, long l) {
						// Toast.makeText(getActivity(),
						// "Clicked position: "+position,
						// Toast.LENGTH_SHORT).show();
						Bundle elBundle = new Bundle();
						Estandar estandar = estandares.get(position);
						elBundle.putString("titulo", estandar.getTitulo());
						elBundle.putString("descripcion",
								estandar.getDescripcion());
						elBundle.putString("link", estandar.getLink());
						mListener.cambiarFragmento(4, elBundle);
					}
				});

		cargarEstandars();

		return rootView;
	}

	private void cargarEstandars() {
		progress = ProgressDialog.show(getActivity(), "Loading data", "Please wait...");
		AsyncHttpClient client = new AsyncHttpClient();
		// Data
		// FORMAT URL :
		// www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
		String URL_complete = "http://www.tecnoeficiencia.com/movil/service.estandares.php";

		RequestParams params = new RequestParams();
		params.put("correo", "julian.acevedo@colfuturo.org");
		client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				progress.dismiss();
				// String
				Log.e("response",response);
				JSONParser parser = new JSONParser();
				JSONObject jOb = new JSONObject();
				try {
					jOb = (JSONObject) parser.parse(response);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONArray news = (JSONArray) jOb.get("estandares");
				for (int i = 0; i < news.size(); i++) {
					JSONObject auxNew = (JSONObject) news.get(i);
					final String titulo = (String) auxNew.get("titulo");
					final String descripcion = (String) auxNew
							.get("descripcionEstandar");
					final String enlace = (String) auxNew.get("enlace");
					Estandar estandar = new Estandar(titulo, descripcion,
							enlace);
					adaptador.add(estandar);
					//Log.e("estandar",titulo);
				}
			}
		});
		this.adaptador.notifyDataSetChanged();
	}

	public static EstandarFragment newInstance(int sectionNumber) {
		EstandarFragment fragment = new EstandarFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public EstandarFragment() {
	}

	private void loadEstandars() {
		// TODO Auto-generated method stub
		// llamar ws {"usuario":"pepe"}
		String estandares = "{\"estandares\":[{\"titulo\":\"estandar1\",\"descripcionEstandar\":\"blah\",\"enlace\":\"www.google.com\"},{\"titulo\":\"estandar2\",\"descripcionEstandar\":\"blah\",\"enlace\":\"www.facebook.com\"}]}";
		JSONParser parser = new JSONParser();
		JSONObject jOb = new JSONObject();
		try {
			jOb = (JSONObject) parser.parse(estandares);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray news = (JSONArray) jOb.get("estandares");
		for (int i = 0; i < news.size(); i++) {
			JSONObject auxNew = (JSONObject) news.get(i);
			final String titulo = (String) auxNew.get("titulo");
			final String descripcion = (String) auxNew
					.get("descripcionEstandar");
			final String enlace = (String) auxNew.get("enlace");
			Estandar n = new Estandar(titulo, descripcion, enlace);

		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (CambiarEntreFragmentos) activity;
			mListener.cambiarTitulo("Estándares");
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CambiarEntreFragmentos");
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListener.cambiarTitulo("Estándares");
	}
}
