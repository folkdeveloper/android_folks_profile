package com.creation.android.folkapp2019.feeds;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creation.android.folkapp2019.MainActivity;
import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FeedsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FeedsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private List<Feed> feedList;
    private List<Status> statusList;
    private FeedsListAdapter adapter;

    private static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseFirestore firebaseFirestore;

    public FeedsFragment() {
        // Required empty public constructor
    }

    public static FeedsFragment newInstance(String param1, String param2) {
        FeedsFragment fragment = new FeedsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        // Set Title for Fragment
        //((MainActivity)getActivity()).getSupportActionBar().setTitle("Feeds");

        recyclerView = view.findViewById(R.id.recycler_view);

        feedList = new ArrayList<>();
        statusList = new ArrayList<>();
        adapter = new FeedsListAdapter(getContext(), feedList, statusList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

       // final ProgressBar progressBar = view.findViewById(R.id.progress);

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
                                Toast.makeText(getActivity(), "No Status!", Toast.LENGTH_LONG).show();
                            }

                            adapter.notifyDataSetChanged();
                            // progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        firebaseFirestore.collection("feeds")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        Log.d("Feed Doc:","Feed Doc" +task);
                        if(task.isSuccessful())
                        {
                            for(DocumentSnapshot doc : task.getResult())
                            {
                                Log.d("Feed Doc:","Feed Doc" +doc);
                                Feed feed = doc.toObject(Feed.class);

                                feedList.add(feed);


                            }

                            if(feedList.size() == 0)
                            {
                                Toast.makeText(getContext(), "No project in Inbox!", Toast.LENGTH_LONG).show();
                            }

                            adapter.notifyDataSetChanged();
                            //progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
