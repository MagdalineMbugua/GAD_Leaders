package com.magda.gadleaders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magda.gadleaders.R;
import com.magda.gadleaders.models.Leader;

import java.util.List;

public class TopSkillIQAdapter extends RecyclerView.Adapter<TopSkillIQAdapter.ViewHolder> {
    private List<Leader> skillsLeaders;

    public TopSkillIQAdapter(List<Leader> skillsLeaders) {
        this.skillsLeaders = skillsLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.skill_iq_leader,parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Leader skillLeader = skillsLeaders.get(position);
        holder.bind(skillLeader);

    }

    @Override
    public int getItemCount() {
        return skillsLeaders!=null? skillsLeaders.size():0;
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mNames;
        private TextView mScoreAndCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);

        }

        private void initViews(View itemView) {
            mNames = itemView.findViewById(R.id.tvNames);
            mScoreAndCountry = itemView.findViewById(R.id.tvLearningScore);

        }

        public void bind(Leader skillLeader) {
            mNames.setText(skillLeader.getName());

            String scoreAndCountry = skillLeader.getScore() + ", "+ skillLeader.getCountry();
            mNames.setText(scoreAndCountry);
        }
    }
}
