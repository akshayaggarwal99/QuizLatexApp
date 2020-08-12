package com.android.quizapp.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.android.quizapp.R;
import com.android.quizapp.views.models.QuestionModel;
import com.android.quizapp.views.utils.MyWebView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<QuestionModel> results = new ArrayList<>();
    private ViewPager2 viewPager2;
    private static MyClickListener myClickListener;
    List<String> options = new ArrayList<>();
    private int selectedPos = -1;

    public QuestionAdapter(List<QuestionModel> resultList, ViewPager2 viewPager2) {
        this.results = resultList;
        this.viewPager2 = viewPager2;
    }

    public void setResults(List<QuestionModel> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        QuestionAdapter.myClickListener = myClickListener;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        QuestionModel result = results.get(position);
        Context context = holder.itemView.getContext();

        holder.engQuesWebView.setDisplayText(result.getQuestionEnglish());
        holder.engQuesWebView.load();
        holder.hindiQuesWebView.setDisplayText(result.getQuestionHindi());
        holder.hindiQuesWebView.load();

        holder.btn_option1.setDisplayText(" A. " + result.getOptionA());
        holder.btn_option1.load();

        holder.btn_option2.setDisplayText(" B. " + result.getOptionB());
        holder.btn_option2.load();

        holder.btn_option3.setDisplayText(" C. " + result.getOptionC());
        holder.btn_option3.load();

        holder.btn_option4.setDisplayText(" D. " + result.getOptionD());
        holder.btn_option4.load();


        String imageUrl = result.getExplanationMedia();
        Glide.with(context)
                //.load("https://picsum.photos/"+"400/300?random=" + position)
                .load(imageUrl)
                .centerInside()
                .placeholder(R.drawable.ic_placeholder_image)
                .into(holder.imageView);


    }


    private void incorrectAns(Context context, Button btn_option) {
        btn_option.setBackgroundResource(R.drawable.fill_color_button_incorrect);
        btn_option.setTextColor(Color.parseColor("#ffffff"));
    }

    private void correctAns(Context context, Button btn_option) {
        btn_option.setBackgroundResource(R.drawable.fill_color_button_correct);
        btn_option.setTextColor(Color.parseColor("#ffffff"));
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.card_image)
        ImageView imageView;
        @BindView(R.id.web_ques_english)
        MyWebView engQuesWebView;

        @BindView(R.id.web_ques_hindi)
        MyWebView hindiQuesWebView;
        @BindView(R.id.activity_game_option1_btn)
        MyWebView btn_option1;
        @BindView(R.id.activity_game_option2_btn)
        MyWebView btn_option2;
        @BindView(R.id.activity_game_option3_btn)
        MyWebView btn_option3;
        @BindView(R.id.activity_game_option4_btn)
        MyWebView btn_option4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getPosition(), view);
        }
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }
}
