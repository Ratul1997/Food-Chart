package com.ourcuet.mehedi.halthyfoodmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.transform.Source;

public class FoodChart extends AppCompatActivity {

    float mxCost,cap;
    RecyclerView recyclerChartView;
    RecyclerView.Adapter adapter;
    String[] days={"SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"};
    int countMember = 0;
    ArrayList<Item>breakfast = new ArrayList<Item>();
    ArrayList<Item>lunch = new ArrayList<Item>();
    ArrayList<Item>dinners = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_chart);

        mxCost = (float) (Float.parseFloat(getIntent().getStringExtra("Cost")));
        cap = (float) (Float.parseFloat(getIntent().getStringExtra("amr")));
        countMember = (Integer)(Integer.parseInt(getIntent().getStringExtra("members")));
        init();
        initialAllValues();

    }
    private void initialAllValues(){
        recyclerChartView = (RecyclerView)findViewById(R.id.recyclerChartView);
        recyclerChartView.setHasFixedSize(true);
        recyclerChartView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Days(days, this, breakfast, lunch, dinners, countMember);
        recyclerChartView.setAdapter(adapter);

    }

    private void init(){
        mxCost /= 30;
        Breakfast breakfasts = new Breakfast(cap, mxCost);

        breakfast = breakfasts.getBreakfast();
        lunch = breakfasts.getLunch();
        dinners = breakfasts.getDinners();

        /*for(int i = 0;i<breakfast.size();i++){
            System.out.println(breakfast.get(i).getFoodName()+ " "+breakfast.get(i).getDay() );
        }
        System.out.println("/////");
        for(int i = 0;i<lunch.size();i++){
            System.out.println(lunch.get(i).getFoodName()+" "+lunch.get(i).getDay());
        }

        System.out.println("/////");
        for(int i = 0;i<dinners.size();i++){
            System.out.println(dinners.get(i).getFoodName()+" "+dinners.get(i).getDay());
        }*/

    }
}


