package com.cbc_app_poc.rokomari.rokomarians.GoodWork.ShowNominatedList;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.GoodWork.PostLikeGoodWork;
import com.cbc_app_poc.rokomari.rokomarians.Interfaces.ItemClickListener;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelNominated;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterNominated extends RecyclerView.Adapter<RecyclerAdapterNominated.MyViewHolder> {

    private List<ModelNominated> modelNominateds;
    private Context context;
    private String id = "";
    private String status = "";
    private String account_id = "";
    private PostLikeGoodWork postLikeGoodWork;
    private static final String BASE_URL = "http://192.168.11.231:9090/";

    public RecyclerAdapterNominated( Context context, List<ModelNominated> modelNominateds, String account_id) {
        this.modelNominateds = modelNominateds;
        this.context = context;
        this.account_id = account_id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_item_good_work, parent, false);

        postLikeGoodWork = new PostLikeGoodWork(context);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvNameWork.setText(modelNominateds.get(position).getUserFirstName());
        holder.tvNominatedname.setText(modelNominateds.get(position).getNominatedUserFirstname());
        holder.tvDetailsWork.setText(modelNominateds.get(position).getReason());
        holder.tvDateWork.setText(modelNominateds.get(position).getNominationDate());
        holder.tvNumOfLikes.setText(""+modelNominateds.get(position).getNumberOfLikes());

        if(modelNominateds.get(position).getLiked()==true){
            holder.ivLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like_blue));
        } else {
            holder.ivLike.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_good_work));
        }

        Picasso.get().load(modelNominateds.get(position).getUserImage()).into(holder.ivRokomarian);
        Picasso.get().load(modelNominateds.get(position).getNominatedUserImage()).into(holder.ivNominated);

        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLikeGoodWork.postData(BASE_URL,
                        modelNominateds.get(position).getId(), account_id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelNominateds.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener itemClickListener;
        ImageView ivRokomarian, ivNominated, ivLike;
        TextView tvNameWork, tvDateWork, tvDetailsWork, tvNominated, tvNominatedname, tvNumOfLikes;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivRokomarian = itemView.findViewById(R.id.imageview_rokomarian_work);
            ivNominated = itemView.findViewById(R.id.imageview_nominated);
            ivLike = itemView.findViewById(R.id.imageview_like_work);

            tvNameWork = itemView.findViewById(R.id.textview_name_work);
            tvDateWork = itemView.findViewById(R.id.textview_date_work);
            tvDetailsWork = itemView.findViewById(R.id.textview_details_work);
            tvNominated = itemView.findViewById(R.id.textview_nominated);
            tvNominatedname = itemView.findViewById(R.id.textview_nominated_name);
            tvNumOfLikes = itemView.findViewById(R.id.textview_num_like_work);
        }


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }

}
