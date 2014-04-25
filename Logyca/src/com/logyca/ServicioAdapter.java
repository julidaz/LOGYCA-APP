package com.logyca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dacarden on 4/20/2014.
 */
public class ServicioAdapter extends ArrayAdapter <Servicio> {


    public ServicioAdapter(Context context, ArrayList<Servicio> servicios) {
        super(context, R.layout.service_item_layout, servicios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtener el servicio
        Servicio servicio = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item_layout, null);
        }
        //Buscar los items dentro de este view
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloTv);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcionTv);
        //FILL THEM WITH DATA!!
        titulo.setText(servicio.getTitulo());
        descripcion.setText(servicio.getDescripcion());

        return convertView;
    }
}
