package com.logyca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Top10Adapter extends ArrayAdapter <Top10> {


    public Top10Adapter(Context context, ArrayList<Top10> top10s) {
        super(context, R.layout.top10_item_layout, top10s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtener el servicio
        Top10 top10 = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.top10_item_layout, null);
        }
        //Buscar los items dentro de este view
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloTv);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcionTv);
        //FILL THEM WITH DATA!!
        titulo.setText(top10.getTitulo());
        descripcion.setText(top10.getDescripcion());

        return convertView;
    }
}
