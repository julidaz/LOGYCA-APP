package com.logyca;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class ExpertoDescripcionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_PARAM7 = "param7";


    // TODO: Rename and change types of parameters
    private int idExperto;
	private int idInteres;
	private String nombre;
	private String correo;
	private String geoLocalizacion;
	private int rating;
	private String telefono;
	private String asuntoTxt, contactoTxt;

    CambiarEntreFragmentos mListener;


    public static ExpertoDescripcionFragment newInstance(String nombre, String correo, String telefono)
    {
        ExpertoDescripcionFragment fragment = new ExpertoDescripcionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, nombre);
        args.putString(ARG_PARAM2, correo);
        args.putString(ARG_PARAM3, telefono);

        fragment.setArguments(args);
        return fragment;
    }
    public ExpertoDescripcionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_PARAM1);
            correo = getArguments().getString(ARG_PARAM2);
            telefono = getArguments().getString(ARG_PARAM3);
        }
        //this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentview =  inflater.inflate(R.layout.fragment_experto_descripcion, container, false);

        //Buscar los items dentro de este view
        TextView nombre = (TextView) fragmentview.findViewById(R.id.nombreTv);
        TextView correo = (TextView) fragmentview.findViewById(R.id.correoTv);
        TextView telefono= (TextView) fragmentview.findViewById(R.id.telefonoTv);
        TextView asunto= (TextView) fragmentview.findViewById(R.id.asuntoEditText);
        TextView contacto= (TextView) fragmentview.findViewById(R.id.contactoEditText);
        
        //asuntoTxt=(String)asunto.getText();
        //contactoTxt=(String)contacto.getText();
        
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button enviar= (Button) rootView.findViewById(R.id.enviarMensajeButton);
        enviar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AsyncHttpClient client = new AsyncHttpClient();
				//Data 
				//FORMAT URL : www.colfuturo.org/movil/service.login.php?correo=julian.acevedo@colfuturo.org&clave=10101010
				String URL_complete = "http://www.tecnoeficiencia.com/movil/service.contactarExperto.php";
				
					RequestParams params = new RequestParams();
					params.put("correo", "julian.acevedo@colfuturo.org");
					/*params.put("id", "id");
					params.put("asunto", asuntoTxt);
					params.put("contenido", contactoTxt);*/
					client.get(null, URL_complete, params, new AsyncHttpResponseHandler() {
					    @Override
					    public void onSuccess(String response) {
					    	//hacer algo
					    	Toast.makeText(getActivity(),"Clicked position: ", Toast.LENGTH_SHORT).show();
					    }
					});
			}
		});
        
        return fragmentview;
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


}
