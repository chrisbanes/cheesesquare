package com.support.android.designlibdemo.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.support.android.designlibdemo.QuestionView;
import com.support.android.designlibdemo.R;
import com.support.android.designlibdemo.model.CategoryObject;
import com.support.android.designlibdemo.model.Question;

public class CategoryObjectActivity extends FragmentActivity {

    public static final String EXTRA_NAME = "cheese_name";
    public static final String EXTRA_DATA = "detail_data";

    CategoryObject detailData;

    TextView title;
    TextView description;
    Button link;
    LinearLayout questionContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);
        detailData = intent.getParcelableExtra(EXTRA_DATA);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(detailData.getTitle());

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        questionContainer = (LinearLayout) findViewById(R.id.question_container);
        link = (Button) findViewById(R.id.link_button);

        if (detailData != null)  setData();
    }

    private void setData() {
        if (detailData.getTitle() != null){
            title.setText(detailData.getTitle());
        } else title.setVisibility(View.GONE);
        if (detailData.getFullDescription() != null) description.setText(detailData.getFullDescription());
        else description.setVisibility(View.GONE);
        if (detailData.getQuestions() != null) {
            for (Question q : detailData.getQuestions()){
                QuestionView qw = new QuestionView(getApplicationContext());
                qw.setValues(q.getQuestion(), q.getAnswer());
                questionContainer.addView(qw);
            }
        } else {
            questionContainer.setVisibility(View.GONE);
        }
        if (detailData.getLinkToWebsite() != null) {

            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(detailData.getLinkToWebsite());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        } else {
            link.setVisibility(View.GONE);
        }
    }

}
