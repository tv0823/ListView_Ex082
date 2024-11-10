package com.example.listview_ex082;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView firstTwentyLv;
    TextView firstNumTv, mulOrDiffTv, placeTv, sumToNumTV, x1,d,n,Sn;
    double firstNum, mulOrDiff;
    int math;
    String[] firstTwenty = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        firstTwentyLv = findViewById(R.id.firstTwentyLv);
        firstNumTv = findViewById(R.id.firstNumTv);
        mulOrDiffTv = findViewById(R.id.mulOrDiffTv);
        placeTv = findViewById(R.id.placeTv);
        sumToNumTV = findViewById(R.id.sumToNumTV);

        x1 = findViewById(R.id.x1);
        d = findViewById(R.id.d);
        n = findViewById(R.id.n);
        Sn = findViewById(R.id.Sn);


        Intent gi = getIntent();
        firstNum = gi.getDoubleExtra("firstNum",0);
        mulOrDiff = gi.getDoubleExtra("mulOrDiff",0);
        math = gi.getIntExtra("action", -1);

        firstNumTv.setText("" + firstNum);
        mulOrDiffTv.setText("" + mulOrDiff);

        firstTwentyLv.setOnItemClickListener(this);
        firstTwentyLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if(math == 1) {
            firstTwenty = mathematical(firstTwenty);
        } else if (math == 0) {
            firstTwenty = geometrical(firstTwenty);
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, firstTwenty);
        firstTwentyLv.setAdapter(adp);
    }

    public String[] mathematical(String[] math){
        math[0] = "" + firstNum;
        for(int i = 1; i<math.length; i++){
            math[i] = "" + (firstNum+(i)*mulOrDiff);
        }
        return math;
    }

    public String[] geometrical(String[] geom){
        geom[0] = "" + firstNum;
        for(int i = 1; i<geom.length; i++){
            geom[i] = "" + (firstNum*Math.pow(mulOrDiff,i));
        }
        return geom;
    }

    public double sum(long i){
        double sum = firstNum;
        if(math == 1) {
            for(int j = 0; j<i; i++){
                sum += (firstNum+(i)*mulOrDiff);
            }
        } else if (math == 0) {
            for (int j = 0; j < i; i++) {
                sum += (firstNum*Math.pow(mulOrDiff,i));
            }
        }
        return sum;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        x1.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        n.setVisibility(View.VISIBLE);
        Sn.setVisibility(View.VISIBLE);

        firstNumTv.setVisibility(View.VISIBLE);
        mulOrDiffTv.setVisibility(View.VISIBLE);
        placeTv.setVisibility(View.VISIBLE);
        sumToNumTV.setVisibility(View.VISIBLE);

        placeTv.setText("" + id+1);
        double sumToNum = sum(id);
        sumToNumTV.setText("" + sumToNumTV);
    }
}