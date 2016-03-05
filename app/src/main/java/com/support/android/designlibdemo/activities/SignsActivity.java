package com.support.android.designlibdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.support.android.designlibdemo.R;
import com.support.android.designlibdemo.model.Sign;

public class SignsActivity extends FragmentActivity {

    Sign data;

    TextView title;
    LinearLayout signs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_fragment_layout);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("sign");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(data.getTitle());

        title = (TextView) findViewById(R.id.sign_title);
        signs = (LinearLayout) findViewById(R.id.signs_container);

        if (data != null)  setData();
    }

    private void setData() {
            if (data.getTitle() != null) title.setText(data.getTitle());
            else title.setVisibility(View.GONE);
            if (data.getImages() != null) {
                Log.d("SIGNS", "not null");
                for (int i=0; i<data.getImages().length; i++) {
                    Log.d("SIGNS", "i");
                    LayoutInflater inflater = LayoutInflater.from(this);
                    View sign = inflater.inflate(R.layout.sign_item, null);
                    TextView tv = (TextView) sign.findViewById(R.id.sign_description);
                    tv.setText(data.getImageDescriptions().get(i));
                    ImageView iv = (ImageView) sign.findViewById(R.id.sign_image);
                    iv.setImageDrawable(ContextCompat.getDrawable(this, data.getImages()[i]));
                    signs.addView(sign);
                }
            }

    }

}
