package com.logyca;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
