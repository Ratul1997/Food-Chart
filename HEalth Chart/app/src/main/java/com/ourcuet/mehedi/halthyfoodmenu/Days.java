package com.ourcuet.mehedi.halthyfoodmenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Days extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String[] days;
    Context context;
    ArrayList<Item> breakfast = new ArrayList<Item>();
    ArrayList<Item>lunch = new ArrayList<Item>();
    ArrayList<Item>dinners = new ArrayList<Item>();
    int countMembers;

    public Days(String[] days, Context context, ArrayList<Item> breakfast, ArrayList<Item> lunch, ArrayList<Item> dinners, int countMembers) {
        this.days = days;
        this.context = context;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinners = dinners;
        this.countMembers = countMembers;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.days, viewGroup, false);
        return new VierHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        VierHolder vHold = (VierHolder)viewHolder;
        vHold.daysText.setText(days[i]);

        vHold.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                workFlow(i);
                //System.out.println(days[i]);

            }
        });
    }
    private void workFlow(int position){
        ArrayList<FoodsItems>breakfastForDay = new ArrayList<FoodsItems>();
        ArrayList<FoodsItems>lunchForDay = new ArrayList<FoodsItems>();
        ArrayList<FoodsItems>dinnerForDay = new ArrayList<FoodsItems>();

        for(int i = 0;i<breakfast.size();i++){
            if(breakfast.get(i).getDay() == position){
                double num = breakfast.get(i).getQuantity();
                num *=countMembers;

                num = Double.parseDouble(String.format("%.2f",num));

                FoodsItems food= new FoodsItems(breakfast.get(i).getFoodName(),Double.toString(num),breakfast.get(i).getUnitName());
                breakfastForDay.add(food);
            }
        }
        for(int i = 0;i<lunch.size();i++){
            if(lunch.get(i).getDay() == position){
                double num = lunch.get(i).getQuantity();
                num *=countMembers;

                num = Double.parseDouble(String.format("%.2f",num));
                FoodsItems food= new FoodsItems(lunch.get(i).getFoodName(),Double.toString(num),lunch.get(i).getUnitName());
                lunchForDay.add(food);
            }
        }
        for(int i = 0;i<dinners.size();i++){
            if(dinners.get(i).getDay() == position){
                double num = dinners.get(i).getQuantity();
                num *=countMembers;

                num = Double.parseDouble(String.format("%.2f",num));
                FoodsItems food= new FoodsItems(dinners.get(i).getFoodName(),Double.toString(num),dinners.get(i).getUnitName());
                dinnerForDay.add(food);
            }
        }
        Intent intent = new Intent(context, DaysDetails.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("brkfast",breakfastForDay);
        bundle.putSerializable("lunch",lunchForDay);
        bundle.putSerializable("dinner",dinnerForDay);
        intent.putExtra("days", days[position]);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return days.length;
    }

    private class VierHolder extends RecyclerView.ViewHolder{

        TextView daysText;
        LinearLayout linearLayout;
        public VierHolder(@NonNull View itemView) {
            super(itemView);
            init();
        }
        public void init(){
            daysText =(TextView)itemView.findViewById(R.id.daysText);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linearLayout);
        }
    }

}
