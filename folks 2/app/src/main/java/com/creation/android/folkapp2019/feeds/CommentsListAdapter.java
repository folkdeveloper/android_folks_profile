package com.creation.android.folkapp2019.feeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.creation.android.folkapp2019.R;

import java.util.List;

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.ItemViewHolder> {
    private Context context;
    private List<Comments> commentsList;


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView commentatorImage;
        public TextView comments;

        public ItemViewHolder(View view) {
            super(view);

            comments = view.findViewById(R.id.commentatorTitle);
            commentatorImage = view.findViewById(R.id.commentatorImage);

        }
    }

    public CommentsListAdapter(Context context, List<Comments> commentsList) {
        this.context = context;
        this.commentsList = commentsList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_list_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final String commentator = commentsList.get(position).getCommentator();
        final String commentText = commentsList.get(position).getComment();

        holder.comments.setText(commentText);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(14));
        Glide.with(context)
                .load(commentator)
                .override(72, 72) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .apply(RequestOptions.circleCropTransform())
                //.apply(requestOptions)
                .into(holder.commentatorImage);


    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }
}
