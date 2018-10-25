package com.matrix.morpheus.contactlist;

import android.annotation.SuppressLint;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends ArrayAdapter<person_detail> {
    private Context mContext;
    private List<person_detail> contact_arr;
    public ContactListAdapter(Context mContext, ArrayList<person_detail> list){
        super(mContext,0,list);
        this.mContext = mContext;
        this.contact_arr = list;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RecyclerView.ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView==null){
           // viewHolder = new ViewCache();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_row_layout, parent, false);
           // viewHolder.name = (TextView) convertView.findViewById(R.id.name);
           // viewHolder.phone = (TextView) convertView.findViewById(R.id.phone);
           // convertView.setTag(viewHolder);
        }

        person_detail current_person = getItem(position);

        ImageView image = (ImageView)convertView.findViewById(R.id.photo);
        image.setImageResource(current_person.getPhoto());

        TextView name = (TextView)convertView.findViewById((R.id.name));
        name.setText(current_person.getName());

        TextView phone = (TextView)convertView.findViewById((R.id.phone));
        phone.setText(String.valueOf(current_person.getNumber()));
        return convertView;
    }
}
