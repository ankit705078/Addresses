package com.example.addresses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class DataModelAdapter extends RecyclerView.Adapter<DataModelAdapter.ViewHolder>{
    private ArrayList<DataModel> mModels;
    private Context context;



    public DataModelAdapter(Context context, ArrayList<DataModel> Models) {
        this.context = context;
        this.mModels=Models;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View idVew = LayoutInflater.from(parent.getContext()).inflate( R.layout.single_list_item, parent ,false );

        return new ViewHolder(idVew);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelAdapter.ViewHolder holder, int position) {



    DataModel dataModel = mModels.get(position);

    holder.titletxt.setText(mModels.get(position).getTitle());
    holder.desciptiontxt.setText(dataModel.getDescription());
    holder.addresstxt.setText(dataModel.getPlace());
    if(dataModel.getIdpic()==null)
    {
        holder.idimg.getLayoutParams().width = 0;


    }
    else {
        holder.idimg.getLayoutParams().width = 100;
        holder.idimg.setVisibility(View.VISIBLE);


        Glide.with(context)
                .load(dataModel.getIdpic())
                .into(holder.idimg);

    }





        holder.addresstxt.setOnClickListener(v -> {

            double lat=dataModel.getAddresslat();
            double longitude=dataModel.getAddresslong();
            String fulladdress = "geo:0,0?q="+lat+","+longitude+"(label)";

            Uri mapUri = Uri.parse(fulladdress);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);

        Toast.makeText(context,dataModel.getAddresslat()+","+dataModel.getAddresslong(),Toast.LENGTH_LONG).show();
    });

    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        public TextView titletxt,desciptiontxt,addresstxt;
        public ImageView idimg;



        public ViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;

            titletxt=itemView.findViewById(R.id.title);
            desciptiontxt=itemView.findViewById(R.id.description);
            addresstxt=itemView.findViewById(R.id.address);
            idimg=itemView.findViewById(R.id.idpic);
        }
    }
}
