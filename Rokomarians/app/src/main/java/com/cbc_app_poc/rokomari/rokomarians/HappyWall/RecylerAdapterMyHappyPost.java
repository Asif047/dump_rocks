package com.cbc_app_poc.rokomari.rokomarians.HappyWall;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.Interfaces.ItemClickListener;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelHappySeeAll;
import com.cbc_app_poc.rokomari.rokomarians.R;

import java.util.List;

public class RecylerAdapterMyHappyPost extends RecyclerView.Adapter<RecylerAdapterMyHappyPost.MyViewHolder>{

    private List<ModelHappySeeAll> modelHappySeeAlls;
    private Context context;
    private int id = 0;
    private String status = "";

    public RecylerAdapterMyHappyPost( Context context, List<ModelHappySeeAll> modelHappySeeAlls) {
        this.modelHappySeeAlls = modelHappySeeAlls;
        this.context = context;
    }


    @Override
    public RecylerAdapterMyHappyPost.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_see_all, parent, false);
        return  new RecylerAdapterMyHappyPost.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecylerAdapterMyHappyPost.MyViewHolder holder, int position) {

        holder.tvName.setText(modelHappySeeAlls.get(position).getName());
        holder.tvDetails.setText(modelHappySeeAlls.get(position).getDetails());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                id = modelHappySeeAlls.get(pos).getId();

                Intent intent = new Intent(context, DetailsMyHappyPostActivity.class);
                intent.putExtra("happy_post_id", id);
                Log.e("###POST_ID: ", ""+id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelHappySeeAlls.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvDetails;
        ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textview_name_see_all);
            tvDetails = itemView.findViewById(R.id.textview_happy_post_see_all);
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
