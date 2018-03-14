package com.cbc_app_poc.rokomari.rokomarians.HappyWall;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.Model.ModelHappySeeAll;
import com.cbc_app_poc.rokomari.rokomarians.R;

import java.util.List;

public class RecyclerAdapterSeeAll extends RecyclerView.Adapter<RecyclerAdapterSeeAll.MyViewHolder>{

    private List<ModelHappySeeAll> modelHappySeeAlls;
    private Context context;
    private int id = 0;
    private String status = "";

    public RecyclerAdapterSeeAll( Context context, List<ModelHappySeeAll> modelHappySeeAlls) {
        this.modelHappySeeAlls = modelHappySeeAlls;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_see_all, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvName.setText(modelHappySeeAlls.get(position).getName());
        holder.tvDetails.setText(modelHappySeeAlls.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return modelHappySeeAlls.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textview_name_see_all);
            tvDetails = itemView.findViewById(R.id.textview_happy_post_see_all);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
