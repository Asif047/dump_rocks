package com.cbc_app_poc.rokomari.rokomarians.GoodWork.GettingNominationList;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.GoodWork.PostNominateActivity;
import com.cbc_app_poc.rokomari.rokomarians.Interfaces.ItemClickListener;
import com.cbc_app_poc.rokomari.rokomarians.Model.User;
import com.cbc_app_poc.rokomari.rokomarians.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterNominationList extends RecyclerView.Adapter<RecyclerAdapterNominationList.MyViewHolder> {

    private List<User> users;
    private Context context;
    private int nominated_id = 0;
    private String nominated_name = "", nominated_image = "";

    public RecyclerAdapterNominationList(Context context, List<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_item_nominate, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvNameNominate.setText(users.get(position).getFirstName()+ " "+
                                        users.get(position).getLastName());
        holder.tvTeamNominate.setText(users.get(position).getUserOfficeInfo().getTeam());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                nominated_id = users.get(pos).getId();
                nominated_name = users.get(pos).getFirstName();
                nominated_image = users.get(pos).getImagePath();

                Intent intent = new Intent(context, PostNominateActivity.class);
                intent.putExtra("nominated_id", nominated_id);
                intent.putExtra("nominated_name", nominated_name);
                intent.putExtra("nominated_image", nominated_image);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }



    public void setfilter(List<User> listitem) {
        users = new ArrayList<>();
        users.addAll(listitem);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClickListener itemClickListener;
        TextView tvNameNominate, tvTeamNominate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvNameNominate = itemView.findViewById(R.id.textview_name_nominate);
            tvTeamNominate = itemView.findViewById(R.id.textview_team_nominate);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }

}
