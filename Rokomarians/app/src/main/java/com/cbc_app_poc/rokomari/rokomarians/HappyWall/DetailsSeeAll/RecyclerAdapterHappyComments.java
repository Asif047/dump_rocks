package com.cbc_app_poc.rokomari.rokomarians.HappyWall.DetailsSeeAll;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DeleteComment.DeleteCommentRequest;
import com.cbc_app_poc.rokomari.rokomarians.Interfaces.ItemClickListener;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelCommentsHappyPost;
import com.cbc_app_poc.rokomari.rokomarians.R;

import java.util.List;

public class RecyclerAdapterHappyComments extends RecyclerView.Adapter<RecyclerAdapterHappyComments.MyViewHolder> {

    private List<ModelCommentsHappyPost> modelCommentsHappyPosts;
    private Context context;
    private String id = "";
    private String status = "";

    private String BASE_URL = "http://192.168.11.231:9090/";
    private DeleteCommentRequest deleteCommentRequest;

    private String account_id = "";
    private String name = "";

    public RecyclerAdapterHappyComments(Context context, List<ModelCommentsHappyPost> modelCommentsHappyPosts,
                                        String account_id, String name) {
        this.modelCommentsHappyPosts = modelCommentsHappyPosts;
        this.context = context;
        this.account_id = account_id;
        this.name = name;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_comments_happy_post, parent, false);

        deleteCommentRequest = new DeleteCommentRequest(context);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvName.setText(modelCommentsHappyPosts.get(position).getName());
        holder.tvComment.setText(modelCommentsHappyPosts.get(position).getComment());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {


                if (name.equals(modelCommentsHappyPosts.get(pos).getName())) {
                    new AlertDialog.Builder(context)
                            .setMessage("Are you sure you want to delete?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    //finish();
                                    deleteCommentRequest.deleteData(BASE_URL, modelCommentsHappyPosts.get(position).getPostId(),
                                            modelCommentsHappyPosts.get(position).getCommentId(), account_id);

                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return modelCommentsHappyPosts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvName, tvComment;
        private ImageView ivCommentor;

        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.textview_name_happy_post_commentor);
            tvComment = itemView.findViewById(R.id.textview_comment_happy_post);
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
