package com.support.android.designlibdemo.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.support.android.designlibdemo.R;
import com.support.android.designlibdemo.model.QuizQuestion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends FragmentActivity {

    Button link;
    LinearLayout surveyContainer;

    List<QuizQuestion> quiz = new ArrayList<>();
    int [] correctAnswers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity_layout);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        surveyContainer = (LinearLayout) findViewById(R.id.topContainer);

        QuizQuestion q1 = new QuizQuestion(R.drawable.cheese_1, "Ovo je znak za:",  Arrays.asList("•\tblizinu pešačkog prelaza", "•\tzabranu za pešake", "•\tpešački prelaz koji više ne postoji"));
        QuizQuestion q2 = new QuizQuestion(R.drawable.cheese_4, "Да ли су учесници у саобраћају само возачи?", Arrays.asList("Да", "не, и возачи и пешаци", "не знам"));
        QuizQuestion q3 = new QuizQuestion(R.drawable.cheese_4, "Да ли су учесници у саобраћају само возачи?", Arrays.asList("Да", "не, и возачи и пешаци", "не знам"));
        QuizQuestion q4 = new QuizQuestion(R.drawable.cheese_4, "Да ли су учесници у саобраћају само возачи?", Arrays.asList("Да", "не, и возачи и пешаци", "не знам"));
        // todo dodaj ostale

        toolbar.setTitle("Квиз");

        quiz.add(q1);
        quiz.add(q2);
        quiz.add(q3);
        quiz.add(q4);

        correctAnswers = new int[quiz.size()];
        correctAnswers[0] = 2;
        correctAnswers[1] = 1;
        correctAnswers[2] = 3;
        correctAnswers[3] = 2;
        // todo rest
        link = (Button) findViewById(R.id.quiz_button);
        link.setText("Провери одговоре"); // todo promeni naziv

        setData();
    }

    private void setData() {
        for (QuizQuestion quizQuestion : quiz){
            LayoutInflater inflater = LayoutInflater.from(this);
            View v = inflater.inflate(R.layout.quiz_item, null);
            v.setTag(quizQuestion);
            ImageView signImage = (ImageView)v.findViewById(R.id.quiz_sign_image);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                signImage.setImageDrawable(ContextCompat.getDrawable(this, quizQuestion.getImage()));
            } else {
                signImage.setImageDrawable(getResources().getDrawable(quizQuestion.getImage()));
            }
            TextView q = (TextView) v.findViewById(R.id.survey_question);
            RadioButton r1 = (RadioButton) v.findViewById(R.id.answer_one);
            RadioButton r2 = (RadioButton) v.findViewById(R.id.answer_two);
            RadioButton r3 = (RadioButton) v.findViewById(R.id.answer_three);
            q.setText(quizQuestion.getQuestion());
            r1.setText(quizQuestion.getAnswers().get(0));
            r2.setText(quizQuestion.getAnswers().get(1));
            r3.setText(quizQuestion.getAnswers().get(2));
            surveyContainer.addView(v);
        }

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correct = 0;
                for (int i = 0; i< quiz.size(); i++) {
                    QuizQuestion quizQuestion = quiz.get(i);
                    View view = surveyContainer.findViewWithTag(quizQuestion);
                    RadioButton rb1 = (RadioButton) view.findViewById(R.id.answer_one);
                    RadioButton rb2 = (RadioButton) view.findViewById(R.id.answer_two);
                    RadioButton rb3 = (RadioButton) view.findViewById(R.id.answer_three);
                    if (rb1.isChecked() && correctAnswers[i]==1) {
                        correct++;
                    }
                    if (rb2.isChecked() && correctAnswers[i]==2) {
                        correct++;
                    }
                    if (rb3.isChecked() && correctAnswers[i]==3) {
                        correct++;
                    }
                }
                String message;
                if (correct < 12) message = "Pogodili ste " + correct + "/12 odgovora! Poznavanje znakova je važno za bezbednost u saobraćaju! Naučite znakove!";
                else message = "Bravo! Pogodili ste 12/12 odgovora! Poznavanje znakova je važno za bezbednost u saobraćaju! ";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                finish();
                Log.d("Correct ", "tacno "+correct);
            }
        });


    }
}
