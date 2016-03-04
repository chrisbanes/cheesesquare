package com.support.android.designlibdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class QuestionView extends LinearLayout {

    TextView question;
    TextView answer;
    ToggleButton showHideQ;

    public QuestionView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.question_view, this);

        question = (TextView) findViewById(R.id.question);
        answer = (TextView) findViewById(R.id.answer);
        showHideQ = (ToggleButton) findViewById(R.id.show_answer);

        answer.setVisibility(GONE);

        showHideQ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                answer.setVisibility(isChecked ? VISIBLE : GONE);
            }
        });
    }

    public void setValues(String q, String a){
        question.setText(q);
        answer.setText(a);

    }
}
