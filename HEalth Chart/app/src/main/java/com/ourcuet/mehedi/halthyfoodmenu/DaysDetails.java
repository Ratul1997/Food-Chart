package com.ourcuet.mehedi.halthyfoodmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class DaysDetails extends AppCompatActivity {

    TextView dayText;
    RecyclerView breakfastRecylerView, lunchRecylerView, dinnerRecylerView;

    ArrayList<FoodsItems>breakfast = new ArrayList<FoodsItems>();
    ArrayList<FoodsItems>lunch = new ArrayList<FoodsItems>();
    ArrayList<FoodsItems>dinner = new ArrayList<FoodsItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_details);

        init();
        initialAllValuesForBreakfast();
        initialAllValuesForLunch();
        initialAllValuesForDinner();
    }

    private void initialAllValuesForDinner() {
        dinnerRecylerView.setHasFixedSize(true);
        dinnerRecylerView.setLayoutManager(new LinearLayoutManager(this));
        ItemListAdapter adapter = new ItemListAdapter(dinner , this);
        dinnerRecylerView.setAdapter(adapter);

    }

    private void initialAllValuesForLunch() {
        lunchRecylerView.setHasFixedSize(true);
        lunchRecylerView.setLayoutManager(new LinearLayoutManager(this));
        ItemListAdapter adapter = new ItemListAdapter(lunch , this);
        lunchRecylerView.setAdapter(adapter);

    }


    private void init() {
        dayText = (TextView)findViewById(R.id.dayText);
        String day = getIntent().getStringExtra("days");
        Bundle bundle = getIntent().getExtras();
        breakfast = (ArrayList<FoodsItems>)bundle.getSerializable("brkfast");
        lunch = (ArrayList<FoodsItems>)bundle.getSerializable("lunch");
        dinner = (ArrayList<FoodsItems>)bundle.getSerializable("dinner");

        dayText.setText(day);
        breakfastRecylerView = (RecyclerView)findViewById(R.id.breakfastRecylerView);
        lunchRecylerView = (RecyclerView)findViewById(R.id.lunchRecylerView);
        dinnerRecylerView = (RecyclerView)findViewById(R.id.dinnerRecylerView);
    }
    private void initialAllValuesForBreakfast(){

        breakfastRecylerView.setHasFixedSize(true);
        breakfastRecylerView.setLayoutManager(new LinearLayoutManager(this));
        ItemListAdapter adapter = new ItemListAdapter(breakfast , this);
        breakfastRecylerView.setAdapter(adapter);
    }
}
