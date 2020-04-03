package com.creation.android.folkapp2019.feeds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FeedsListAdapter extends RecyclerView.Adapter<FeedsListAdapter.GenericViewHolder> {

    private Context context;
    private List<Feed> feedList;
    private List<Status> statusList;
    //public StatusListAdapter statusListAdapter;
    private static final int TYPE_STATUS = 0;//"status";
    private static final int TYPE_FEED = 1;//"feed";
    private FirebaseFirestore firebaseFirestore;

    public FeedsListAdapter(Context context, List<Feed> feedList, List<Status> statusList) {
        this.context = context;
        this.feedList = feedList;
        this.statusList = statusList;
        //this.statusListAdapter = new StatusListAdapter(context, feedList);

    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        GenericViewHolder customItemViewHolder = null;
        switch (viewType) {
            case TYPE_STATUS:
                view = LayoutInflater.from(context).inflate(R.layout.status_list, parent, false);
                customItemViewHolder = new StatusViewHolder(view);
                break;
            case TYPE_FEED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds_list_item, parent, false);
                customItemViewHolder = new FeedViewHolder(view);
                break;
            default:
                break;

        }
        return customItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder genericViewHolder, int position) {
        if (genericViewHolder instanceof StatusViewHolder) {
            bindStatusViewHolder((StatusViewHolder) genericViewHolder, position);
        } else if (genericViewHolder instanceof FeedViewHolder) {
            bindFeedViewHolder((FeedViewHolder) genericViewHolder, position);
        }
    }

    private void bindStatusViewHolder(StatusViewHolder statusViewHolder, int position) {
        /*final String statusImageUrl = feedList.get(position).getFeedImageUrl();
        final String statusTitle = feedList.get(position).getChannelName();

        statusViewHolder.statusTitle.setText(statusTitle);
        Glide.with(context)
                .load(statusImageUrl)
                .into(statusViewHolder.statusImage);*/
    }

    private void bindFeedViewHolder(final FeedViewHolder holder, final int position) {
            final Feed feed = feedList.get(position);

            Typeface robotoBold = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Bold.ttf");
            Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
            holder.channelName.setText(feed.getChannelName());
            //holder.channelName.setTypeface(robotoBold);
            holder.feedDate.setText(feed.getFeedTimeStamp());
            holder.feedText.setText(feed.getFeedText());
            //holder.feedText.setTypeface(robotoRegular);
            holder.feedUrl.setText("www.google.com");
            holder.likeCount.setText(feed.getLikeCount());

            Glide.with(context)
                    .load(feed.getChannelImageUrl())
                    .into(holder.channelImage);

            Glide.with(context)
                    .load(feed.getFeedImageUrl())
                    .into(holder.feedImage);

            holder.feedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("weburl", "www.google.com");
                    context.startActivity(intent);
                }
            });

            holder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_STREAM, "");
                    sendIntent.setType("image/jpeg");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    context.startActivity(shareIntent);
                }
            });

            holder.comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = feedList.get(position).getDocumentId();//firebaseFirestore.collection("feeds").document().getId();
                    Intent intent = new Intent(context, FeedDetailActivity.class);
                    intent.putExtra("documentId", id);

                    context.startActivity(intent);
                }
            });
            final View likeView = holder.like;
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    likeView.setBackgroundColor(5604344);

                }
            });

        }
    @Override
    public int getItemCount() {
        return feedList.size();
    }

    class StatusViewHolder extends GenericViewHolder {

        //public ImageView statusImage;
        //public TextView statusTitle;
        public RecyclerView statusRecycler;
        public StatusListAdapter statusListAdapter;

        public StatusViewHolder(View view) {
            super(view);

            statusRecycler = view.findViewById(R.id.status_recycler_view);
           // statusImage = view.findViewById(R.id.statusImage);
            //statusTitle = view.findViewById(R.id.statusTitle);
            statusList = new ArrayList<>();
            firebaseFirestore = FirebaseFirestore.getInstance();
            firebaseFirestore.collection("status")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {
                                for(DocumentSnapshot doc : task.getResult())
                                {
                                    Status status = doc.toObject(Status.class);

                                    statusList.add(status);
                                }

                                if(statusList.size() == 0)
                                {
                                    Toast.makeText(context, "No Status!", Toast.LENGTH_LONG).show();
                                }

                                statusListAdapter.notifyDataSetChanged();
                                // progressBar.setVisibility(View.GONE);
                            }
                            else
                            {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                            }
                        }
                    });
            statusListAdapter = new StatusListAdapter(context, statusList);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            // for Recycler view
            statusRecycler.setLayoutManager(mLayoutManager);
            statusRecycler.setItemAnimator(new DefaultItemAnimator());
            statusRecycler.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
            statusRecycler.setAdapter(statusListAdapter);

        }
    }

    static class GenericViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener clickListener;

        public GenericViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.clickListener.onClick(v, getPosition());
        }
    }

    class FeedViewHolder extends GenericViewHolder {

        public TextView channelName, feedDate, feedText, feedUrl, likeCount;
        public ImageView channelImage;
        public ImageView feedImage;
        public ImageView share, comments, like;
        public RecyclerView statusRecycler;

        public FeedViewHolder(View view) {
            super(view);
            channelImage = view.findViewById(R.id.channelImage);
            feedImage = view.findViewById(R.id.feedImageIV);
            channelName = view.findViewById(R.id.channelName);
            feedDate = view.findViewById(R.id.feedDate);
            feedText = view.findViewById(R.id.feedText);
            feedUrl = view.findViewById(R.id.feedUrl);
            share = view.findViewById(R.id.shareIV);
            comments = view.findViewById(R.id.commentIV);
            like = view.findViewById(R.id.likeIV);
            likeCount = view.findViewById(R.id.likeCount);
            statusRecycler = view.findViewById(R.id.statusRecyclerView);
        }

        @Override
        public void onClick(View v) {
            // do nothing
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:
                return Feed.TYPE_STATUS;
            default:
                return Feed.TYPE_FEED;
        }
    }
}
