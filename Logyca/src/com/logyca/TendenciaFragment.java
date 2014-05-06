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
import android.app.Fragment;
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

public class TendenciaFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	CambiarEntreFragmentos mListener;
	ArrayList<Tendencia> tendencias;
	TendenciaAdapter adaptador=null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		this.tendencias = new ArrayList<Tendencia>();
		adaptador = new TendenciaAdapter(getActivity(),
				tendencias);

		// Lista en el layout...
		ListView listaTendencias;
		listaTendencias = (ListView) rootView
				.findViewById(R.id.ServicesListView);
		// Ponerle el adaptador
		listaTendencias.setAdapter(adaptador);
		listaTendencias
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView,
							View view, int position, long l) {
						// Toast.makeText(getActivity(),
						// "Clicked position: "+position,
						// Toast.LENGTH_SHORT).show();
						Bundle elBundle = new Bundle();
						Tendencia tendencia = tendencias.get(position);
						elBundle.putString("titulo", tendencia.getTitulo());
						elBundle.putString("descripcion",
								tendencia.getDescripcion());
						elBundle.putString("link", tendencia.getLink());
						mListener.cambiarFragmento(1, elBundle);
					}
				});

		cargarTendencias();
//		Tendencia nuevo = new
//		 Tendencia("Consultoria","Haga uso de este Tendencia para poder dar fin a todos sus dolores de cabeza con respecto a consutoria",
//		 "ESTE ES EL LINK");
//		 Tendencia nuevo2 = new
//		 Tendencia("Asesoria","Cada que piense en asesores, piense en Logyca, tenemos una amplia gama de charlatanes dispuestos a cobrar lo que no se gana su empresa, solamente por criticar!",
//		 "ESTE ES EL LINK");
//		 Tendencia nuevo3= new
//		 Tendencia("Logistica a su puert","Ha pensado en logistica como piensa en pizza ??? si usted es así, con Logyca deberia quedarse!",
//		 "ESTE ES EL LINK");
//		 Tendencia nuevo4 = new
//		 Tendencia("Fin","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nunc ligula, feugiat ut accumsan id, ultricies eu lorem. Aenean dapibus, odio nec aliquam blandit, velit ante faucibus urna, et dapibus enim neque et nibh. Suspendisse vitae leo augue. Sed quis suscipit elit, sed gravida justo. Phasellus a suscipit ligula, at tempus ligula. Proin eget sagittis neque, vel dapibus tellus. Quisque laoreet iaculis risus, ut posuere ligula vehicula nec. Vestibulum eu vestibulum est. Phasellus condimentum eros ac bibendum feugiat. Vivamus hendrerit mauris non nisl aliquam, ac malesuada elit auctor. Duis mollis commodo ullamcorper. Sed molestie congue volutpat. Cras convallis metus a aliquam condimentum. Fusce tincidunt elit a lorem suscipit dignissim. Sed sed quam pretium, semper urna ac, ullamcorper erat. Cras pulvinar tincidunt augue, sit amet scelerisque odio elementum aliquam.",
//		 "ESTE ES EL LINK");
//		
//		 tendencias.add(nuevo);
//		 tendencias.add(nuevo2);
//		 tendencias.add(nuevo3);
//		 tendencias.add(nuevo4);

		return rootView;
	}

	private void cargarTendencias() {
		AsyncHttpClient client = new AsyncHttpClient();
		// Data
		// FORMAT URL :
		// www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
		String URL_complete = "http://www.colfuturo.org/movil/service.tendencias.php";

		RequestParams params = new RequestParams();
		params.put("correo", "julian.acevedo@colfuturo.org");
		client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
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
				JSONArray news = (JSONArray) jOb.get("tendencias");
				for (int i = 0; i < news.size(); i++) {
					JSONObject auxNew = (JSONObject) news.get(i);
					final String titulo = (String) auxNew.get("titulo");
					final String descripcion = (String) auxNew
							.get("descripcionTendencia");
					final String enlace = (String) auxNew.get("enlace");
					Tendencia tendencia = new Tendencia(titulo, descripcion,
							enlace);
					adaptador.add(tendencia);
					//Log.e("tendencia",titulo);
				}
			}
		});
		this.adaptador.notifyDataSetChanged();
	}

	public static TendenciaFragment newInstance(int sectionNumber) {
		TendenciaFragment fragment = new TendenciaFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public TendenciaFragment() {
	}

	private void loadTendencias() {
		// TODO Auto-generated method stub
		// llamar ws {"usuario":"pepe"}
		String tendencias = "{\"tendencias\":[{\"titulo\":\"tendencia1\",\"descripcionTendencia\":\"blah\",\"enlace\":\"www.google.com\"},{\"titulo\":\"tendencia2\",\"descripcionTendencia\":\"blah\",\"enlace\":\"www.facebook.com\"}]}";
		JSONParser parser = new JSONParser();
		JSONObject jOb = new JSONObject();
		try {
			jOb = (JSONObject) parser.parse(tendencias);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray news = (JSONArray) jOb.get("tendencias");
		for (int i = 0; i < news.size(); i++) {
			JSONObject auxNew = (JSONObject) news.get(i);
			final String titulo = (String) auxNew.get("titulo");
			final String descripcion = (String) auxNew
					.get("descripcionTendencia");
			final String enlace = (String) auxNew.get("enlace");
			Tendencia n = new Tendencia(titulo, descripcion, enlace);

		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (CambiarEntreFragmentos) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CambiarEntreFragmentos");
		}
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
