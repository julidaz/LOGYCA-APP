package com.logyca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpertoAdapter extends ArrayAdapter <Experto> {


    public ExpertoAdapter(Context context, ArrayList<Experto> expertos) {
        super(context, R.layout.expert_item_layout, expertos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtener el servicio
        Experto experto = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.expert_item_layout, null);
        }
        //Buscar los items dentro de este view
        TextView nombre = (TextView) convertView.findViewById(R.id.tituloTv);
        //FILL THEM WITH DATA!!
        nombre.setText(experto.getNombre());
        return convertView;
    }
}
