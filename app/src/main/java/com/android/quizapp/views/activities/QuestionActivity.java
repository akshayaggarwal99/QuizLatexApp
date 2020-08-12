package com.android.quizapp.views.activities;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.quizapp.R;
import com.android.quizapp.views.adapters.QuestionAdapter;
import com.android.quizapp.views.adapters.QuestionStateAdapter;
import com.android.quizapp.views.base.BaseActivity;
import com.android.quizapp.views.models.QuestionModel;
import com.android.quizapp.views.models.ResultModel;
import com.android.quizapp.views.utils.AppUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;

public class QuestionActivity extends BaseActivity {

    private static final String TAG = "QuestionActivity";

    @BindView(R.id.view_pager2)
    ViewPager2 viewPager;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;

    private QuestionAdapter questionAdapter;
    private QuestionStateAdapter questionStateAdapter;
    private ResultModel resultModel;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_ques;
    }

    @Override
    protected void init() {
        progressBar.setVisibility(View.VISIBLE);
        loadJsonData();
//        questionAdapter = new QuestionAdapter(resultModel.getQuestions(), viewPager);
        questionStateAdapter = new QuestionStateAdapter(this);
        questionStateAdapter.setQuestionModels(resultModel.getQuestions());
        viewPager.setAdapter(questionStateAdapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager.setOffscreenPageLimit(3);
        progressBar.setVisibility(View.GONE);

    }

    private void loadJsonData() {
        String data = AppUtils.getAssetJsonData(getApplicationContext());
        Log.d(TAG, "init: data " + data);
        Type type = new TypeToken<ResultModel>() {
        }.getType();
        resultModel = new Gson().fromJson(data, type);
        Log.d(TAG, "init: data size " + String.valueOf(resultModel.getQuestions().size()));
    }


}