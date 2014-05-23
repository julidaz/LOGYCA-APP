package com.logyca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.GetChars;
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

public class ExpertoFragment extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";
	CambiarEntreFragmentos mListener;
	ArrayList<Experto> expertos;
	static ExpertoAdapter adaptador=null;
	GPSTracker gps;
	static String locationString;
	static myLocationAsyncTask mlat;
	static Dialog progress;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		this.expertos = new ArrayList<Experto>();
		adaptador = new ExpertoAdapter(getActivity(), expertos);

		//Lista en el layout...
		ListView listaExpertos;
		listaExpertos = (ListView) rootView.findViewById(R.id.ServicesListView);
		//Ponerle el adaptador
		listaExpertos.setAdapter(adaptador);
		listaExpertos.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Bundle elBundle = new Bundle();
				Experto experto = expertos.get(position);
				elBundle.putString("nombre",experto.getNombre());
				elBundle.putString("correo",experto.getCorreo());
				elBundle.putString("telefono",experto.getTelefono());
				mListener.cambiarFragmento(5,elBundle);
			}
		});
		
		progress = ProgressDialog.show(getActivity(), "Loading data", "Please wait...");
		calcularGeoLocalizacion();
		return rootView;
	}

	private void calcularGeoLocalizacion() {
		/*gps = new GPSTracker(getActivity());
		gps.getLocation();

		if(gps.canGetLocation()){

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			Geocoder geocoder=new Geocoder(getActivity(), Locale.getDefault());
			List<Address> addresses;
			try {
				addresses = geocoder.getFromLocation(latitude, longitude, 1);

				if(addresses.size()>0)
				{
					String locationString=addresses.get(0).getAddressLine(2)+", "+addresses.get(0).getAddressLine(3);
					Toast.makeText(getActivity(), "La geoLocalizacion es "+locationString, Toast.LENGTH_LONG).show(); 
					Log.wtf("pais", "La geoLocalizacion es "+locationString);
				}
				else
					Toast.makeText(getActivity(), "La localizacion es: - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 

				cargarExpertos();
				Experto nuevo = new Experto("Pepe","pepe@mail.com", "1234567");
				expertos.add(nuevo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			// no se puede obtener posición
			// GPS o Red no disponible
			// Preguntar al usuario para Activar GPS
			gps.showSettingsAlert();
		}*/
		mlat=new myLocationAsyncTask(getActivity());
		mlat.execute();
	}
	
	public static void cargarExpertos(String location) {
		
		locationString=location;
		Log.wtf("pais", "La geoLocalizacion es "+locationString);
		AsyncHttpClient client = new AsyncHttpClient();
		//Data 
		// FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
		String URL_complete = "http://www.tecnoeficiencia.com/movil/service.expertos.php";

		RequestParams params = new RequestParams();
		params.put("correo", "julian.acevedo@colfuturo.org");
		params.put("geolocalizacion", locationString);
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
				JSONArray news = (JSONArray) jOb.get( "expertos" );
				for(int i=0;i<news.size();i++)
				{
					JSONObject auxNew = (JSONObject) news.get(i);
					final String nombre= (String) auxNew.get("nombre");
					final String correo=(String) auxNew.get("correo");
					final String telefono= (String) auxNew.get("telefono");
					Experto experto=new Experto(nombre, correo, telefono);
					adaptador.add(experto);
				}
			}
		});
	}

	public static ExpertoFragment newInstance(int sectionNumber) {
		ExpertoFragment fragment = new ExpertoFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ExpertoFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (CambiarEntreFragmentos) activity;
			mListener.cambiarTitulo("Expertos");
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CambiarEntreFragmentos");
		}

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListener.cambiarTitulo("Expertos");
	}
}
