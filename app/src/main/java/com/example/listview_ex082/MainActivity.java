package com.example.listview_ex082;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button geometricalBtn, mathematicalBtnv, ResultBtn;
    EditText FirstNumberEt, MultOrDiffEt;

    boolean math;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geometricalBtn = findViewById(R.id.geometricalBtn);
        mathematicalBtnv = findViewById(R.id.mathematicalBtn);
        ResultBtn = findViewById(R.id.ResultBtn);

        FirstNumberEt = findViewById(R.id.FirstNumberEt);
        MultOrDiffEt = findViewById(R.id.MultOrDiffEt);

    }

    public void mathematical(View view) {
        math = true;

        MultOrDiffEt.setHint("Enter difference");
        FirstNumberEt.setVisibility(View.VISIBLE);
        MultOrDiffEt.setVisibility(View.VISIBLE);
        ResultBtn.setVisibility(View.VISIBLE);
    }

    public void geometrical(View view) {
        math = false;

        MultOrDiffEt.setHint("Enter multiplier");
        FirstNumberEt.setVisibility(View.VISIBLE);
        MultOrDiffEt.setVisibility(View.VISIBLE);
        ResultBtn.setVisibility(View.VISIBLE);
    }

    public void Result(View view) {
        String first = FirstNumberEt.getText().toString();
        String mulOrDiff = MultOrDiffEt.getText().toString();

        if(first.isEmpty() || mulOrDiff.isEmpty()) {
            Toast.makeText(this, "One or more input is empty", Toast.LENGTH_SHORT).show();
        } else if ((first.equals("+.") || first.equals("+") || first.equals("-.") || first.equals("-") || first.equals(".")) || (mulOrDiff.equals("+.") || mulOrDiff.equals("+") || mulOrDiff.equals("-.") || mulOrDiff.equals("-") || mulOrDiff.equals("."))) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        } else{
            Intent si = new Intent(this,ResultsActivity.class);
            double firstNum = Double.parseDouble(first);
            double numAction = Double.parseDouble(mulOrDiff);

            si.putExtra("numAction", numAction);
            si.putExtra("firstNum", firstNum);
            si.putExtra("action", math);

            startActivity(si);
        }
    }
}