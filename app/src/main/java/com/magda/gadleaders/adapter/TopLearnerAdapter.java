package com.magda.gadleaders.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magda.gadleaders.R;
import com.magda.gadleaders.models.Leader;

import java.util.List;

public class TopLearnerAdapter extends RecyclerView.Adapter<TopLearnerAdapter.ViewHolder> {
    public static final String TAG = "Top Learner Adapter";
    private List <Leader> mLearners;


    public TopLearnerAdapter(List<Leader> learner) {
        mLearners = learner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.learner_leader_cardview,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leader learner = mLearners.get(position);
        holder.bind(learner);
        Log.d(TAG, "onBindViewHolder: binded view");

    }

    @Override
    public int getItemCount() {
        return mLearners!=null? mLearners.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mHoursAndCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mName = itemView.findViewById(R.id.tvNames);
            mHoursAndCountry= itemView.findViewById(R.id.tvLearningHours);
        }

        public void bind(Leader learner) {
            mName.setText(learner.getName());
            String hoursAndCountry =learner.getHours()+" learning hours, "+ learner.getCountry();
            mHoursAndCountry.setText(hoursAndCountry);
        }
    }
}
