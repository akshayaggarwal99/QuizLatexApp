package com.android.quizapp.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.quizapp.R;
import com.android.quizapp.views.models.QuestionModel;
import com.android.quizapp.views.utils.MyWebView;
import com.bumptech.glide.Glide;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import butterknife.Unbinder;

import static android.graphics.Color.WHITE;


public class QuestionFragment extends Fragment {

    private static final String ARG_QUESTION = "question_model";

    private QuestionModel questionModel;

    private Unbinder unbinder;

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

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(QuestionModel questionModel) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, questionModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionModel = getArguments().getParcelable(ARG_QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        unbinder = ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {

        engQuesWebView.setDisplayText(questionModel.getQuestionEnglish());
        engQuesWebView.load();
        hindiQuesWebView.setDisplayText(questionModel.getQuestionHindi());
        hindiQuesWebView.load();

        btn_option1.setDisplayText(" A. " + questionModel.getOptionA());
        btn_option1.load();

        btn_option2.setDisplayText(" B. " + questionModel.getOptionB());
        btn_option2.load();

        btn_option3.setDisplayText(" C. " + questionModel.getOptionC());
        btn_option3.load();

        btn_option4.setDisplayText(" D. " + questionModel.getOptionD());
        btn_option4.load();


        String imageUrl = questionModel.getExplanationMedia();
        Glide.with(getActivity())
                //.load("https://picsum.photos/"+"400/300?random=" + position)
                .load(imageUrl)
                .centerInside()
                .placeholder(R.drawable.ic_placeholder_image)
                .into(imageView);


    }

    private void correctAnswer(String option) {
        switch (option) {
            case "a":
                btn_option1.setTextColor(WHITE);
                btn_option1.setBackgroundResource(R.drawable.fill_color_button_correct);
                break;
            case "b":
                btn_option2.setTextColor(WHITE);
                btn_option2.setBackgroundResource(R.drawable.fill_color_button_correct);
                break;
            case "c":
                btn_option3.setTextColor(WHITE);
                btn_option3.setBackgroundResource(R.drawable.fill_color_button_correct);
                break;
            case "d":
                btn_option4.setTextColor(WHITE);
                btn_option4.setBackgroundResource(R.drawable.fill_color_button_correct);
                break;

        }
    }

    private void inCorrectAnswer(String option) {
        switch (option) {
            case "a":
                btn_option1.setTextColor(WHITE);
                btn_option1.setBackgroundResource(R.drawable.fill_color_button_incorrect);
                break;
            case "b":
                btn_option2.setTextColor(WHITE);
                btn_option2.setBackgroundResource(R.drawable.fill_color_button_incorrect);
                break;
            case "c":
                btn_option3.setTextColor(WHITE);
                btn_option3.setBackgroundResource(R.drawable.fill_color_button_incorrect);
                break;
            case "d":
                btn_option4.setTextColor(WHITE);
                btn_option4.setBackgroundResource(R.drawable.fill_color_button_incorrect);
                break;

        }
    }


    @OnTouch(R.id.activity_game_option1_btn)
    public void onOption1Clicked() {
        if (questionModel.getAnswer().equals("a")) {
            correctAnswer("a");
        } else {
            inCorrectAnswer("a");
            correctAnswer(questionModel.getAnswer());
        }
    }

    @OnTouch(R.id.activity_game_option2_btn)
    public void onOption2Clicked() {
        if (questionModel.getAnswer().equals("b")) {
            correctAnswer("b");
        } else {
            inCorrectAnswer("b");
            correctAnswer(questionModel.getAnswer());
        }
    }

    @OnTouch(R.id.activity_game_option3_btn)
    public void onOption3Clicked() {
        if (questionModel.getAnswer().equals("c")) {
            correctAnswer("c");
        } else {
            inCorrectAnswer("c");
            correctAnswer(questionModel.getAnswer());
        }
    }

    @OnTouch(R.id.activity_game_option4_btn)
    public void onOption4Clicked() {
        if (questionModel.getAnswer().equals("d")) {
            correctAnswer("d");
        } else {
            inCorrectAnswer("d");
            correctAnswer(questionModel.getAnswer());
        }
    }

}