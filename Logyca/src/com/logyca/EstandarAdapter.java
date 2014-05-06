package com.logyca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EstandarAdapter extends ArrayAdapter <Estandar> {


    public EstandarAdapter(Context context, ArrayList<Estandar> estandars) {
        super(context, R.layout.estandar_item_layout, estandars);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtener el servicio
    	Estandar estandar = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.estandar_item_layout, null);
        }
        //Buscar los items dentro de este view
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloTv);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcionTv);
        //FILL THEM WITH DATA!!
        titulo.setText(estandar.getTitulo());
        descripcion.setText(estandar.getDescripcion());

        return convertView;
    }
}
