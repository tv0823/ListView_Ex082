package com.example.listview_ex082;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Intent gi;
    ListView firstTwentyLv;
    TextView firstNumTv, numDTv, placeTv, sumToNumTV;
    float firstNum, numD, seqSum;
    int math;
    String[] seqArr;
    float[] sumValuesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        firstTwentyLv = findViewById(R.id.firstTwentyLv);
        firstNumTv = findViewById(R.id.firstNumTv);
        numDTv = findViewById(R.id.numD);
        placeTv = findViewById(R.id.placeTv);
        sumToNumTV = findViewById(R.id.sumToNumTV);

        gi = getIntent();
        firstNum = gi.getFloatExtra("firstNum",0);
        numD = gi.getFloatExtra("numD",0);
        math = gi.getIntExtra("action", -1);

        seqSum = 0;
        seqArr = new String[20];
        sumValuesArr = new float[20];

        calcArrValues();

        firstTwentyLv.setOnItemClickListener(this);
        firstTwentyLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seqArr);
        firstTwentyLv.setAdapter(adp);

        firstNumTv.setText("X1 = " + String.format("%.2f", firstNum));
        numDTv.setText("d = " + String.format("%.2f", numD));
    }

    public void calcArrValues(){
        for (int i = 0; i < 20; i++){
            if (math == 1){
                seqArr[i] = String.format("%.2f",(firstNum + i * numD));
            }
            else {
                seqArr[i] = String.format("%.2f",(firstNum * Math.pow(numD, i)));
            }
            seqSum += Float.parseFloat(seqArr[i]);
            sumValuesArr[i] = seqSum;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        firstNumTv.setVisibility(View.VISIBLE);
        numDTv.setVisibility(View.VISIBLE);
        placeTv.setVisibility(View.VISIBLE);
        sumToNumTV.setVisibility(View.VISIBLE);

        placeTv.setText("n = " + (pos+1));
        sumToNumTV.setText("Sn = " + String.format("%.2f", (sumValuesArr[pos])));
    }

    public void goBack(View view) {
        setResult(RESULT_OK, gi);
        finish();
    }
}