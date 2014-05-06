package com.logyca;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class EstandarDescripcionFragment extends Fragment {
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


    public static EstandarDescripcionFragment newInstance(String titulo, String descripcion, String link ) {
        EstandarDescripcionFragment fragment = new EstandarDescripcionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, titulo);
        args.putString(ARG_PARAM2, descripcion);
        args.putString(ARG_PARAM3, link);

        fragment.setArguments(args);
        return fragment;
    }
    public EstandarDescripcionFragment() {
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
        View fragmentview =  inflater.inflate(R.layout.fragment_estandar_descripcion, container, false);

        //Buscar los items dentro de este view
        TextView titulo = (TextView) fragmentview.findViewById(R.id.tituloTv);
        TextView descripcion = (TextView) fragmentview.findViewById(R.id.descripcionTv);
        TextView link = (TextView) fragmentview.findViewById(R.id.linkTv);
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
