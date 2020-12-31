package com.example.transfers.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.transfers.R;
import com.example.transfers.businessObject.DataBase;
import com.example.transfers.dataObject.Const;
import com.example.transfers.dataObject.User;
import com.example.transfers.view.AddUserActivity;

import java.util.ArrayList;
import java.util.List;

public class UserAdapterList extends RecyclerView.Adapter<UserAdapterList.UserViewHolder>{

    private List<User> data;
    private Context context;
    private DataBase dataBase;


    public UserAdapterList(List<User> data, Context applicationContext, DataBase dataBase) {
        this.data = data;
        this.context = applicationContext;
        this.dataBase = dataBase;

    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User userViewHolder = data.get(position);
        String com = "";
        holder.name.setText("Nombre: "+ userViewHolder.getNombrePersonaJuridica()+"");
        holder.tipoId.setText("Ide: "+userViewHolder.getTipoNit() + "");
        if(userViewHolder.getTipoNit().equals(" NIT")){
            com = " -"+userViewHolder.getDigitoVerificacion();
        }
        holder.id.setText("N_ide: "+userViewHolder.getId() + com );
        //edit
        holder.btn_view_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddUserActivity.class);
                intent.putExtra(Const.USER_SEL, userViewHolder);
                intent.putExtra(Const.USER_EDI, true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        holder.btn_view_detele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             dataBase.eliminarUsuario(String.valueOf(userViewHolder.getId()));
             notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView id;
        TextView tipoId;
        Button btn_view_post, btn_view_detele;


        public UserViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            id = (TextView) itemView.findViewById(R.id.phone);
            tipoId = (TextView) itemView.findViewById(R.id.email);
            btn_view_post = (Button) itemView.findViewById(R.id.btn_view_post);
            btn_view_detele = (Button) itemView.findViewById(R.id.btn_view_detele);
        }
    }

    public void filterList (ArrayList<User> filterName){
        data = filterName;
        notifyDataSetChanged();
    }

}