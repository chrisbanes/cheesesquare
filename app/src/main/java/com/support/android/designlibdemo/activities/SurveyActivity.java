package com.support.android.designlibdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.support.android.designlibdemo.MySingleton;
import com.support.android.designlibdemo.R;
import com.support.android.designlibdemo.model.SurveyQuestion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurveyActivity extends FragmentActivity {

    String type;

    Button link;
    LinearLayout surveyContainer;

    List<SurveyQuestion> survey = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_activity);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        surveyContainer = (LinearLayout) findViewById(R.id.topContainer);

        SurveyQuestion s1 = new SurveyQuestion("Године старости:", Arrays.asList("мање од 18 година", "између 18-25", "између 25-35"));
        SurveyQuestion s2 = new SurveyQuestion("Да ли су учесници у саобраћају само возачи?", Arrays.asList("Да", "не, и возачи и пешаци", "не знам"));
        SurveyQuestion s3 = new SurveyQuestion("Регулисање саобраћаја у зони школе могу вршити:", Arrays.asList("саобраћајне патроле", "саобраћајне патроле и саобраћајне патроле грађана", "не знам"));
        SurveyQuestion s4 = new SurveyQuestion("Године старости:", Arrays.asList("мање од 18 година", "између 18-25", "између 25-35"));
        // todo dodaj ostale

         // todo ovaj napravi sam po sablonu od ovog starog za prekrsaje i dole dodas

        Intent intent = getIntent();
        type = intent.getExtras().getString("type");

        if (type != null && type.equals("young")){
            toolbar.setTitle("Упитник - млади");
            survey.add(s1);
            survey.add(s2);
            survey.add(s3);
            survey.add(s4);
        } else {
            toolbar.setTitle("Упитник - прекршаји и осигурање");
            survey.add(s1); // todo ovo promeni
        }
        link = (Button) findViewById(R.id.survey_button);
        link.setText("Пошаљи одговоре");

        setData();
    }

    private void setData() {
        for (SurveyQuestion sq : survey){
            LayoutInflater inflater = LayoutInflater.from(this);
            View v = inflater.inflate(R.layout.survey_item, null);
            v.setTag(sq);
            TextView q = (TextView) v.findViewById(R.id.survey_question);
            RadioButton r1 = (RadioButton) v.findViewById(R.id.answer_one);
            RadioButton r2 = (RadioButton) v.findViewById(R.id.answer_two);
            RadioButton r3 = (RadioButton) v.findViewById(R.id.answer_three);
            q.setText(sq.getsQuestion());
            r1.setText(sq.getAnswers().get(0));
            r2.setText(sq.getAnswers().get(1));
            r3.setText(sq.getAnswers().get(2));
            surveyContainer.addView(v);
        }


        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo send answers
                JSONObject json = new JSONObject();
                 try {
                     if (type.equals("young")) json.put("tipankete", "mladi");
                     else json.put("tipankete", "prekrsaji");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i< survey.size(); i++){
                    SurveyQuestion sq = survey.get(i);
                    View view = surveyContainer.findViewWithTag(sq);
                    RadioButton rb1 = (RadioButton) view.findViewById(R.id.answer_one);
                    RadioButton rb2 = (RadioButton) view.findViewById(R.id.answer_two);
                    RadioButton rb3 = (RadioButton) view.findViewById(R.id.answer_three);
                    try {
                        if (rb1.isChecked()){
                            json.put("p"+(i+1), 1);
                        }
                        if (rb2.isChecked()){
                            json.put("p"+(i+1), 2);
                        }
                        if (rb3.isChecked()){
                            json.put("p"+(i+1), 3);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Log.d("ANKETA", json.toString(2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest surveyRequest = new JsonObjectRequest(Request.Method.POST, "http://46.101.91.164/skoleproduction/index.php/api/anketa", json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Хвала на учешћу!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        finish();
                    }
                });
                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(surveyRequest);
            }
        });
    }
}
