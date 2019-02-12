package com.ourcuet.mehedi.halthyfoodmenu;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog.Builder;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button familyButton, calculateButton;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    int countMember = 0;
    float mxCost;
    EditText foodCost;


    ArrayList<String> name  =  new ArrayList<String>();
    ArrayList<String> amr  =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        familyButton = (Button)findViewById(R.id.familyButton);
        calculateButton = (Button)findViewById(R.id.calculateButton);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        foodCost = (EditText)findViewById(R.id.foodCost);

        familyButton.setOnClickListener(this);
        calculateButton.setOnClickListener(this);

        showRecyclerView();
    }
    private void showRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(name,amr, this);
        recyclerView.setAdapter(adapter);
    }
    private float calculation(){
        float avg =0;
        for(int i=0;i<amr.size();i++){
            avg =avg + (float)(Float.parseFloat(amr.get(i)));
        }
        avg /=amr.size();
        return avg;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK) {

                String strEditText = data.getStringExtra("amr");
                name.add("Member"+ ++countMember);
                amr.add(strEditText);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.familyButton){
            Intent intent = new Intent(this,FamilyMember.class);
            startActivityForResult(intent,1);
        }
        if(v.getId() == R.id.calculateButton){

          String Cost = foodCost.getText().toString();
          if(Cost.length()!=0 && countMember!=0){

              double dailyCost = Double.parseDouble(Cost) / (countMember);                           //new add
              Cost = Double.toString(dailyCost);                                                         //new add
              Intent intent = new Intent(this, FoodChart.class);
              //System.out.println(Cost);
              intent.putExtra("amr", Float.toString(calculation()));
              // intent.putExtra("amr", "2100.000");
              intent.putExtra("Cost", Cost);
              intent.putExtra("members", Integer.toString(amr.size()));
              //intent.putExtra("Cost", "3500.00");
              startActivity(intent);

          }
        }
    }

}
