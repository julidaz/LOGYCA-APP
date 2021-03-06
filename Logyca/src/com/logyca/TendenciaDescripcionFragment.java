package com.logyca;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;



public class TendenciaDescripcionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";


    // TODO: Rename and change types of parameters
    private String titulo;
    private String descripcion;
    private String link;

    CambiarEntreFragmentos mListener;


    public static TendenciaDescripcionFragment newInstance(String titulo, String descripcion, String link ) {
        TendenciaDescripcionFragment fragment = new TendenciaDescripcionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, titulo);
        args.putString(ARG_PARAM2, descripcion);
        args.putString(ARG_PARAM3, link);

        fragment.setArguments(args);
        return fragment;
    }
    public TendenciaDescripcionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titulo = getArguments().getString(ARG_PARAM1);
            descripcion = getArguments().getString(ARG_PARAM2);
            link = getArguments().getString(ARG_PARAM3);
        }
        //this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentview =  inflater.inflate(R.layout.fragment_tendencia_descripcion, container, false);

        //Buscar los items dentro de este view
        TextView tituloTv = (TextView) fragmentview.findViewById(R.id.tituloTv);
        TextView descripcionTv = (TextView) fragmentview.findViewById(R.id.descripcionTv);
        TextView linkTv = (TextView) fragmentview.findViewById(R.id.linkTv);
        
        tituloTv.setText(this.titulo);
        descripcionTv.setText(this.descripcion);
        linkTv.setText("Ver mas");
		final String myLink=this.link;

        linkTv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String url = myLink;
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
        
        return fragmentview;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (CambiarEntreFragmentos) activity;
            mListener.cambiarTitulo("Tendencia");
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

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mListener.cambiarTitulo("Tendencia");
	}

}
