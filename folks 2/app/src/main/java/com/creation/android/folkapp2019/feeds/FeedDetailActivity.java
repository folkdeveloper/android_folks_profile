package com.creation.android.folkapp2019.feeds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FeedDetailActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    public TextView channelName, feedDate, feedText, feedUrl, likeCount;
    public ImageView channelImage;
    public ImageView feedImage;
    public ImageView share, comments, like;
    public RecyclerView commentsRecycler;
    public CommentsListAdapter commentsListAdapter;
    ArrayList<Comments> commentsList;
    Feed feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        channelImage = findViewById(R.id.channelImage);
        feedImage = findViewById(R.id.feedImageIV);
        channelName = findViewById(R.id.channelName);
        feedDate = findViewById(R.id.feedDate);
        feedText = findViewById(R.id.feedText);
        feedUrl = findViewById(R.id.feedUrl);
        share = findViewById(R.id.shareIV);
        comments = findViewById(R.id.commentIV);
        like = findViewById(R.id.likeIV);
        likeCount = findViewById(R.id.likeCount);
        commentsRecycler = findViewById(R.id.commentsRecyclerView);
        commentsList = new ArrayList<>();
        Intent intent = getIntent();
        String documentId = intent.getStringExtra("documentId");
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("feeds")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot doc : task.getResult())
                            {
                                feed = doc.toObject(Feed.class);
                                channelName.setText(feed.getChannelName());
                                //holder.channelName.setTypeface(robotoBold);
                                feedDate.setText(feed.getFeedTimeStamp());
                                feedText.setText(feed.getFeedText());
                                //feedText.setTypeface(robotoRegular);
                                feedUrl.setText("www.google.com");
                                likeCount.setText(feed.getLikeCount());

                                Glide.with(FeedDetailActivity.this)
                                        .load(feed.getChannelImageUrl())
                                        .into(channelImage);

                                Glide.with(FeedDetailActivity.this)
                                        .load(feed.getFeedImageUrl())
                                        .into(feedImage);

                                feedImage.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(FeedDetailActivity.this, WebViewActivity.class);
                                        intent.putExtra("weburl", "www.google.com");
                                        startActivity(intent);
                                    }
                                });

                                share.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent sendIntent = new Intent();
                                        sendIntent.setAction(Intent.ACTION_SEND);
                                        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                                        sendIntent.setType("text/plain");
                                        sendIntent.putExtra(Intent.EXTRA_STREAM, "");
                                        sendIntent.setType("image/jpeg");
                                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                                        startActivity(shareIntent);
                                    }
                                });

                                comments.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //
                                    }
                                });
                                final View likeView = like;
                                like.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        likeView.setBackgroundColor(5604344);

                                    }
                                });

                            }
                            //progressBar.setVisibility(GONE);
                        }
                        else
                        {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

        firebaseFirestore.collection("feeds").document(documentId).collection("comments")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot doc : task.getResult())
                            {
                                Comments comments = doc.toObject(Comments.class);
                                Comments comments1 = new Comments("https://www.gstatic.com/webp/gallery/3.jpg","Great", null);
                                Comments comments2 = new Comments("https://api.androidhive.info/feed/img/nat.jpg","Nice", null);
                                Comments comments3 = new Comments("https://api.androidhive.info/feed/img/nat.jpg", "very informative", null);
                                commentsList.add(comments);
                                commentsListAdapter = new CommentsListAdapter(FeedDetailActivity.this, commentsList);

                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(FeedDetailActivity.this, 1);
                                commentsRecycler.setLayoutManager(mLayoutManager);
                                commentsRecycler.setItemAnimator(new DefaultItemAnimator());
                                //commentsRecycler.addItemDecoration(new DividerItemDecoration(FeedDetailActivity.this, LinearLayoutManager.VERTICAL));
                                commentsRecycler.setAdapter(commentsListAdapter);

                            }
                        }
                        else
                        {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });


       // Feed feed = feedList.get(position);

        /*Typeface robotoBold = Typeface.createFromAsset(context.getAssets(),
                "font/Roboto-Bold.ttf");
        Typeface robotoRegular = Typeface.createFromAsset(context.getAssets(),
                "font/Roboto-Regular.ttf");*/

    }
}
