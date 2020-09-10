package com.magda.gadleaders.userInterface.fragments;

import android.os.Bundle;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.magda.gadleaders.R;
import com.magda.gadleaders.apis.SkillIQApi;
import com.magda.gadleaders.adapter.TopSkillIQAdapter;
import com.magda.gadleaders.models.Leader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SkillIqLeadersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TopSkillIQAdapter mTopSkillIQAdapter;
    private List <Leader> mTopSkillIQ;
    private ContentLoadingProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);

        mRecyclerView = view.findViewById(R.id.iqRecyclerView);
        mProgressBar = view.findViewById(R.id.progress_horizontal);

        return view;
    }

    @Override
    public void onResume() {
        if(mTopSkillIQ==null){
            mProgressBar.setVisibility(View.VISIBLE);
        }else mProgressBar.setVisibility(View.GONE);
        retrieveData();
        super.onResume();
    }

    private void retrieveData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SkillIQApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SkillIQApi api = retrofit.create(SkillIQApi.class);
        Call<List<Leader>> topSkillIQCall = api.getIQLeaders();

        topSkillIQCall.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(Call<List<Leader>> call, Response<List<Leader>> response) {
                mProgressBar.setVisibility(View.GONE);
                mTopSkillIQ = response.body();
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mTopSkillIQAdapter = new TopSkillIQAdapter(mTopSkillIQ);
        mRecyclerView.setAdapter(mTopSkillIQAdapter);
    }
}