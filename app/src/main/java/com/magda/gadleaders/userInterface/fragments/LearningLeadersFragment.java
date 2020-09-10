package com.magda.gadleaders.userInterface.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.magda.gadleaders.apis.LearnersApi;
import com.magda.gadleaders.R;
import com.magda.gadleaders.adapter.TopLearnerAdapter;
import com.magda.gadleaders.models.Leader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LearningLeadersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TopLearnerAdapter mTopLearnerAdapter;
    private List <Leader> mTopLearners;
    private ProgressBar mProgressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        mRecyclerView = view.findViewById(R.id.learnerRecyclerView);
        mProgressBar = view.findViewById(R.id.progress_horizontal);


        return view;
    }


    @Override
    public void onResume() {
        if(mTopLearners==null){
            mProgressBar.setVisibility(View.VISIBLE);
        }else mProgressBar.setVisibility(View.GONE);
        retrieveData();
        super.onResume();
    }

    private void retrieveData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LearnersApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LearnersApi api = retrofit.create(LearnersApi.class);
        Call<List<Leader>> topLearnersCall = api.getLeaders();

        topLearnersCall.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(Call<List<Leader>> call, Response<List<Leader>> response) {
                mProgressBar.setVisibility(View.GONE);
                mTopLearners = response.body();
                bindingRecycler();
            }

            @Override
            public void onFailure(Call<List<Leader>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error occurred", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void bindingRecycler() {
        mTopLearnerAdapter = new TopLearnerAdapter(mTopLearners);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mTopLearnerAdapter);
    }
}