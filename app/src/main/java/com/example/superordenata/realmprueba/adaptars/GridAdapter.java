package com.example.superordenata.realmprueba.adaptars;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superordenata.realmprueba.R;
import com.example.superordenata.realmprueba.models.Nota;

import io.realm.RealmResults;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private RealmResults<Nota> result;

    public GridAdapter(Context context, int layout, RealmResults<Nota> list) {
        this.context = context;
        this.layout = layout;
        this.result = list;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int i) {
        return result.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(layout, null);
            holder = new ViewHolder();
            holder.tvTitle = view.findViewById(R.id.tvTitle);
            holder.tvDate = view.findViewById(R.id.tvDate);
            holder.alarmButton = view.findViewById(R.id.alarmButton);
            //holder.color = view.findViewById(R.id.imageViewIcon);
            holder.alarmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Alarma pulsada " + i, Toast.LENGTH_SHORT).show();
                }
            });
            view.setTag(holder);
        } else {
            // Obtenemos la referencia que posteriormente pusimos dentro del convertView
            // Y así, reciclamos su uso sin necesidad de buscar de nuevo, referencias con FindViewById
            holder = (ViewHolder) view.getTag();
        }

        final Nota currentNota = (Nota) getItem(i);
        holder.tvTitle.setText(currentNota.getTitle());
        holder.tvDate.setText(currentNota.getDate().toString());
        //holder.icon.setImageResource(currentNota.getIcon());

        return view;
    }

    static class ViewHolder {
        private TextView tvTitle;
        private TextView tvDate;
        private ImageView color;
        private ImageView alarmButton;
    }

}
