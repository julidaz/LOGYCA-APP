package com.logyca;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticiaAdapter extends ArrayAdapter <Noticia> {


    public NoticiaAdapter(Context context, ArrayList<Noticia> noticias) {
        super(context, R.layout.news_item_layout, noticias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Obtener el servicio
        Noticia noticia = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item_layout, null);
        }
        //Buscar los items dentro de este view
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloTv);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcionTv);
        //FILL THEM WITH DATA!!
        titulo.setText(noticia.getTitulo());
        descripcion.setText(noticia.getDescripcion());

        return convertView;
    }
}
