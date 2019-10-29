package com.ernestoledesma.cervecerosdemo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ernestoledesma.cervecerosdemo.R;
import com.ernestoledesma.cervecerosdemo.model.Picture;
import com.ernestoledesma.cervecerosdemo.view.PictureDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {


    // Estas tres variables manejan los elementos de objetos pictures
    private ArrayList<Picture> pictures;
    //aqui pasamos como paramentro el obejto activity
    private  int resource;
    private Activity activity;

    // Generamos el constructor de estos tres elementos
    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, FragmentActivity activity) {

        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }


    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inicializamos la clase viewHolder paa que encuentre todos las vistas
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PictureViewHolder holder, int position) {

        // Aqui trabajamos con la lista de objetos, va ir cacheando las cards que se van creando
        Picture picture = pictures.get(position);
        // a travez del objeto holder, obtengo los datos
        holder.usernameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLike_number());


        //Modificamos el contexto, y llamamos a picture para insertar laas imagenes desde nuestra card
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

       // utilizamos el objeto holder, pasa el parametro
        //hacemos clickelable la card
        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                activity.startActivity(intent);
            }
        });

        }



    @Override
    public int getItemCount() {
        return pictures.size();
    }

    // ....
    // esta es la clase inner, que trabaja todos los view de las tarjetas
    // Esta clase  PictureViewHolder trabaja con todos los view de las cardView que la componen
    public class PictureViewHolder extends RecyclerView.ViewHolder{


        // definimos todos los view Encapsulamos los objetos
        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;


        public PictureViewHolder(View itemView) {
            super(itemView);

            // Instanciamos sobre las clases inners

            pictureCard    =   itemView.findViewById(R.id.pictureCard);
            usernameCard   = itemView.findViewById(R.id.userNameCard);
            timeCard       =  itemView.findViewById(R.id.timeCard);
            likeNumberCard =  itemView.findViewById(R.id.likeNumberCard);


        }
    }
}
