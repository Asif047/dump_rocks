package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities.DetailsIdeaActivity;
import com.cbc_app_poc.rokomari.rokomarians.Interfaces.ItemClickListener;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelIdea;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterIdeas extends RecyclerView.Adapter<RecyclerAdapterIdeas.MyViewHolder> {

    private List<ModelIdea> modelIdeas;
    private Context context;
    private String id = "";
    private String status = "";

    public RecyclerAdapterIdeas(Context context, List<ModelIdea> modelIdeas) {

        this.modelIdeas = modelIdeas;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_ideas,
                                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvName.setText(modelIdeas.get(position).getFirstName());
        holder.tvDate.setText(modelIdeas.get(position).getDate());
        holder.tvTitle.setText(modelIdeas.get(position).getTitle());
        holder.tvDetails.setText(modelIdeas.get(position).getIdea());

        Picasso.get().load(modelIdeas.get(position).getImage()).into(holder.ivIdeaMan);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(context, DetailsIdeaActivity.class);
                intent.putExtra("idea_id", modelIdeas.get(pos).getId());
                context.startActivity(intent);

               // Toast.makeText(context, ""+modelIdeas.get(pos).getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelIdeas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener itemClickListener;
        ImageView ivIdeaMan;
        TextView tvName, tvDate, tvTitle, tvDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivIdeaMan = itemView.findViewById(R.id.imageview_rokomarian_idea);
            tvName = itemView.findViewById(R.id.textview_name_idea);
            tvDate = itemView.findViewById(R.id.textview_date_idea);
            tvTitle = itemView.findViewById(R.id.textview_title_idea);
            tvDetails = itemView.findViewById(R.id.textview_details_idea);
        }


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            this.itemClickListener.onItemClick(this.getAdapterPosition());

        }
    }


}
