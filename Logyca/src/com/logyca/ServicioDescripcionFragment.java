package com.logyca;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class ServicioDescripcionFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String ARG_PARAM3 = "param3";
	private static final String ARG_PARAM4 = "param4";
	private static final String ARG_PARAM5 = "param5";
	private static final String ARG_PARAM6 = "param6";
	private static final String ARG_PARAM7 = "param7";
	private static final String ARG_PARAM8 = "param8";
	private static final String ARG_PARAM9 = "param9";
	private static final String ARG_PARAM10 = "param10";
	private static final String ARG_PARAM11 = "param11";
	private static final String ARG_PARAM12 = "param12";


	// TODO: Rename and change types of parameters
	private String titulo;
	private String descripcion;
	private String link;

	private String tipo;
	private String fechaInicio;
	private String fechaFin;
	private String hora;
	private String duracion;
	private String direccion;
	private String ciudad;
	private String pais;
	private String encargado;
	Dialog progress;
	CambiarEntreFragmentos mListener;


	public static ServicioDescripcionFragment newInstance(String titulo, String descripcion, String link, String tipo, String fechaInicio, String fechaFin,
			String hora, String duracion, String direccion, String ciudad, String pais, String encargado) {
		ServicioDescripcionFragment fragment = new ServicioDescripcionFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, titulo);
		args.putString(ARG_PARAM2, descripcion);
		args.putString(ARG_PARAM3, link);
		args.putString(ARG_PARAM4, tipo);
		args.putString(ARG_PARAM5, fechaInicio);
		args.putString(ARG_PARAM6, fechaFin);
		args.putString(ARG_PARAM7, hora);
		args.putString(ARG_PARAM8, duracion);
		args.putString(ARG_PARAM9, direccion);
		args.putString(ARG_PARAM10, ciudad);
		args.putString(ARG_PARAM11, pais);
		args.putString(ARG_PARAM12, encargado);

		fragment.setArguments(args);
		return fragment;
	}
	public ServicioDescripcionFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			titulo = getArguments().getString(ARG_PARAM1);
			descripcion = getArguments().getString(ARG_PARAM2);
			link = getArguments().getString(ARG_PARAM3);
			tipo = getArguments().getString(ARG_PARAM4);
			fechaInicio = getArguments().getString(ARG_PARAM5);
			fechaFin = getArguments().getString(ARG_PARAM6);
			hora = getArguments().getString(ARG_PARAM7);
			duracion = getArguments().getString(ARG_PARAM8);
			direccion = getArguments().getString(ARG_PARAM9);
			ciudad = getArguments().getString(ARG_PARAM10);
			pais = getArguments().getString(ARG_PARAM11);
			encargado = getArguments().getString(ARG_PARAM12);
		}
		//this.setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View fragmentview =  inflater.inflate(R.layout.fragment_servicio_descripcion, container, false);

		//Buscar los items dentro de este view
		TextView titulo = (TextView) fragmentview.findViewById(R.id.tituloTv);
		TextView descripcion = (TextView) fragmentview.findViewById(R.id.descripcionTv);
		TextView tipo = (TextView) fragmentview.findViewById(R.id.tipoTv);
		TextView fechaInicio = (TextView) fragmentview.findViewById(R.id.fechaDesdeTv);
		TextView fechaFin = (TextView) fragmentview.findViewById(R.id.fechaHastaTv);
		TextView hora = (TextView) fragmentview.findViewById(R.id.horaComienzoTv);
		TextView duracion = (TextView) fragmentview.findViewById(R.id.tiempoDuracionTv);
		TextView direccion = (TextView) fragmentview.findViewById(R.id.direccionTv);
		TextView ciudad = (TextView) fragmentview.findViewById(R.id.ciudadTv);
		TextView pais = (TextView) fragmentview.findViewById(R.id.paisTv);
		TextView encargado = (TextView) fragmentview.findViewById(R.id.responsableTv);
		//FILL THEM WITH DATA!!
		titulo.setText(this.titulo);
		descripcion.setText(this.descripcion);
		tipo.setText(this.tipo);
		fechaInicio.setText(this.fechaInicio);
		fechaFin.setText(this.fechaFin);
		hora.setText(this.hora);
		duracion.setText(this.duracion);
		direccion.setText(this.direccion);
		ciudad.setText(this.ciudad);
		pais.setText(this.pais);
		encargado.setText(this.encargado);

		return fragmentview;
	}

	@Override
	public void onStart() {
		super.onStart();
		Button b=(Button) getView().findViewById(R.id.buttonOk);
		b.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
		progress = ProgressDialog.show(getActivity(), "Loading data", "Please wait...");
				AsyncHttpClient client = new AsyncHttpClient();
				//Data 
				// FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
				String URL_complete = "http://www.tecnoeficiencia.com/movil/service.enviarCorreo.php";
				
				RequestParams params = new RequestParams();
				params.put("correo", "julian.acevedo@colfuturo.org");
				params.put("servicio", titulo);
				EditText et=(EditText) getView().findViewById(R.id.solicitarEt);
				params.put("mensaje", et.getText());
				params.put("tipo","servicio");
				client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						progress.dismiss();

						//String noticias="{\"tendencias\":[{\"titulo\":\"tend1\",\"descripcionTendencia\":\"blah\",\"enlace\":\"www.google.com\"},{\"titulo\":\"tend2\",\"descripcionTendencia\":\"blah\",\"enlace\":\"www.google.com\"}]}";
						//					    	JSONParser parser=new JSONParser();
						//							JSONObject jOb = new JSONObject();
						//							try {
						//								jOb = (JSONObject) parser.parse(response);
						//							} catch (org.json.simple.parser.ParseException e) {
						//								// TODO Auto-generated catch block
						//								e.printStackTrace();
						//							}
						//							JSONArray news = (JSONArray) jOb.get( "noticias" );
						//							for(int i=0;i<news.size();i++)
						//							{
						//								JSONObject auxNew = (JSONObject) news.get(i);
						//								final String titulo= (String) auxNew.get("titulo");
						//								final String descripcion=(String) auxNew.get("descripcionNoticia");
						//								final String enlace= (String) auxNew.get("enlace");
						//								Noticia noticia=new Noticia(titulo, descripcion, enlace);
						//								noticias.add(noticia);
						//							}
					}
				});			
				createAlert("Servicio solicitado correctamente");
			}
		});
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (CambiarEntreFragmentos) activity;
			mListener.cambiarTitulo("Servicio");
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement CambiarEntreFragmentos");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	private void createAlert(String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
		alertDialogBuilder.setTitle("Envío correcto");
		alertDialogBuilder.setMessage(string);
		alertDialogBuilder.setCancelable(true);
		alertDialogBuilder.setNeutralButton("Aceptar",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				mListener.cambiarFragmento(3,new Bundle());
				dialog.cancel();
			}
		});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListener.cambiarTitulo("Servicio");
	}
}
