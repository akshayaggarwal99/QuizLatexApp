package com.android.quizapp.views.adapters;

import com.android.quizapp.views.fragments.QuestionFragment;
import com.android.quizapp.views.models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuestionStateAdapter extends FragmentStateAdapter {
    private List<QuestionModel> questionModels = new ArrayList<>();

    public QuestionStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void setQuestionModels(List<QuestionModel> questionModels){
        this.questionModels = questionModels;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return QuestionFragment.newInstance(questionModels.get(position));
    }


    @Override
    public int getItemCount() {
        return questionModels.size();
    }
}
