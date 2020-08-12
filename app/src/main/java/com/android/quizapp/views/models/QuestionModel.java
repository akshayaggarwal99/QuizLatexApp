package com.android.quizapp.views.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionModel implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("question_english")
    @Expose
    private String questionEnglish;
    @SerializedName("question_hindi")
    @Expose
    private String questionHindi;
    @SerializedName("question_media")
    @Expose
    private String questionMedia;
    @SerializedName("option_a")
    @Expose
    private String optionA;
    @SerializedName("option_b")
    @Expose
    private String optionB;
    @SerializedName("option_c")
    @Expose
    private String optionC;
    @SerializedName("option_d")
    @Expose
    private String optionD;
    @SerializedName("has_latex")
    @Expose
    private Boolean hasLatex;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("explanation")
    @Expose
    private String explanation;
    @SerializedName("explanation_video")
    @Expose
    private Object explanationVideo;
    @SerializedName("explanation_media")
    @Expose
    private String explanationMedia;

    protected QuestionModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        questionEnglish = in.readString();
        questionHindi = in.readString();
        questionMedia = in.readString();
        optionA = in.readString();
        optionB = in.readString();
        optionC = in.readString();
        optionD = in.readString();
        byte tmpHasLatex = in.readByte();
        hasLatex = tmpHasLatex == 0 ? null : tmpHasLatex == 1;
        answer = in.readString();
        explanation = in.readString();
        explanationMedia = in.readString();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getQuestionEnglish() {
        return questionEnglish;
    }

    public String getQuestionHindi() {
        return questionHindi;
    }

    public String getQuestionMedia() {
        return questionMedia;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public Boolean getHasLatex() {
        return hasLatex;
    }

    public String getAnswer() {
        return answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public Object getExplanationVideo() {
        return explanationVideo;
    }

    public String getExplanationMedia() {
        return explanationMedia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(questionEnglish);
        parcel.writeString(questionHindi);
        parcel.writeString(questionMedia);
        parcel.writeString(optionA);
        parcel.writeString(optionB);
        parcel.writeString(optionC);
        parcel.writeString(optionD);
        parcel.writeByte((byte) (hasLatex == null ? 0 : hasLatex ? 1 : 2));
        parcel.writeString(answer);
        parcel.writeString(explanation);
        parcel.writeString(explanationMedia);
    }
}
