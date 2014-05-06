package com.logyca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TendenciaAdapter extends ArrayAdapter <Tendencia> {


    public TendenciaAdapter(Context context, ArrayList<Tendencia> tendencias) {
        super(context, R.layout.trends_item_layout, tendencias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtener el servicio
    	Tendencia tendencia = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trends_item_layout, null);
        }
        //Buscar los items dentro de este view
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloTv);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcionTv);
        //FILL THEM WITH DATA!!
        titulo.setText(tendencia.getTitulo());
        descripcion.setText(tendencia.getDescripcion());

        return convertView;
    }
}
