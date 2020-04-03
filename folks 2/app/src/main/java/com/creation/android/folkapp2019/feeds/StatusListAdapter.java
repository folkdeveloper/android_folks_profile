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

public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.ItemViewHolder> {
    private Context context;
    private List<Status> statusList;


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView statusImage;
        public TextView statusTitle;

        public ItemViewHolder(View view) {
            super(view);

            statusImage = view.findViewById(R.id.statusImage);
            statusTitle = view.findViewById(R.id.statusTitle);

        }
    }

    public StatusListAdapter(Context context, List<Status> statusList) {
        this.context = context;
        this.statusList = statusList;
    }

   /* @Override
    public FeedsListAdapter.GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        FeedsListAdapter.GenericViewHolder customItemViewHolder = null;
        switch (viewType) {
            case TYPE_STATUS_SMALL:
                view = LayoutInflater.from(context).inflate(R.layout.status_list_item, parent, false);
                customItemViewHolder = new FeedsListAdapter.StatusViewHolder(view);
                break;
            case TYPE_STATUS_LARGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_list_item_large, parent, false);
                customItemViewHolder = new FeedsListAdapter.FeedViewHolder(view);
                break;
            default:
                break;

        }
        return customItemViewHolder;
    }*/
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.status_list_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final String statusImageUrl = statusList.get(position).getStatusImageUrl();
        final String statusTitle = statusList.get(position).getStatusTitle();

        holder.statusTitle.setText(statusTitle);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(14));
        Glide.with(context)
                .load(statusImageUrl)
                .override(240, 360) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .apply(requestOptions)
                .into(holder.statusImage);
        if(position == 1)
            Glide.with(context)
                    .load(statusImageUrl)
                    .override(480, 360) // resizes the image to these dimensions (in pixel)
                    .centerCrop()
                    .apply(requestOptions)
                    .into(holder.statusImage);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }
}
