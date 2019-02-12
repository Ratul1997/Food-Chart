package com.ourcuet.mehedi.halthyfoodmenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Breakfast {
    double cap, mxCost;
    int n;
    double mainmxCost, maincap;
    boolean notDinner;
    ArrayList<Item>breakfast = new ArrayList<Item>();
    ArrayList<Item>lunch = new ArrayList<Item>();
    ArrayList<Item>dinners = new ArrayList<Item>();

    public Breakfast(double cap, double mxCost) {
        this.cap = cap;
        this.mxCost = mxCost;

        func();
    }

    public ArrayList<Item> getBreakfast() {
        return breakfast;
    }

    public ArrayList<Item> getLunch() {
        return lunch;
    }

    public ArrayList<Item> getDinners() {
        return dinners;
    }

    public  void func(){
        System.out.println(cap + " " + mxCost);
        ArrayList<Foods> food = new ArrayList<Foods>();
        food.add( new Foods("Bread Jelly", 544, 32, 4, "Piece" ));
        food.add( new Foods("Paratha Omelette", 600, 22, 3, "Piece" ));
        food.add( new Foods("Paratha Dal", 610, 22, 3, "Piece" ));
        food.add( new Foods("Sandwich", 650, 50, 2, "Piece" ));
        food.add( new Foods("Aloo Paratha", 508, 20, 2, "Piece" ));
        food.add( new Foods("Khichuri", 216, 12, 100, "Gram" ));
        food.add( new Foods("Toast + Butter", 500, 15, 3, "Piece" ));
        food.add( new Foods("Egg + Toast + Butter", 650, 25, 3, "Piece" ));
        food.add( new Foods("Pancake + Banana", 550, 22, 2, "Piece" ));


        ArrayList<Foods> food2 = new ArrayList<Foods>();


        food2.add( new Foods("Chicken Curry", 243, 35, 235, "Gram" ));
        food2.add( new Foods("Beef", 250, 53, 100, "Gram" ));
        food2.add( new Foods("Mutton", 294, 75, 100, "Gram" ));
        food2.add( new Foods("Fried Eggplant", 101, 5, 2, "Piece" ));
        food2.add( new Foods("Egg Curry", 170, 12, 1, "Piece" ));
        food2.add( new Foods("Rui Fish + Potato", 370, 40, 100, "Gram" ));
        food2.add( new Foods("Telapia Fish + Potato", 160, 28, 100, "Gram" ));
        food2.add( new Foods("Shrimp Curry", 262, 116, 2, "Piece" ));
        food2.add( new Foods("Potato Fry", 85, 5, 100, "Gram" ));
        food2.add( new Foods("Moong Dal", 347, 12, 100, "Gram" ));
        food2.add( new Foods("Masoor Dal", 230, 12, 100, "Gram" ));
        food2.add( new Foods("Khichuri", 216, 12, 100, "Gram" ));
        food2.add( new Foods("Vegetable Curry", 410, 18, 400, "Gram" ));


        Collections.sort(food, new Sortbyx());
        Collections.sort(food2, new Sortbyx());


        int n1 = food.size();
        int n2 = food2.size();


        int[] taken, taken2, dinner;
        taken = new  int[n1];
        taken2 = new int[n2];
        dinner = new int[n2];

        for(int i=0; i<n1; i++){
            taken[i] = -1;
        }

        for(int i=0; i<n2; i++){
            dinner[i] =  taken2[i] = -1;
        }


        n1 = Math.min((int) Math.ceil(n1 *30* mxCost/5000), n1);
        n2 = Math.min((int) Math.ceil(n2 *30* mxCost/5000), n2);


        for(int day = 1; day <= 7; day++) {

            double newMxCost = mxCost*.35;
            double newCap = cap*.35;

            double currentCal = 0, ansPrice = 0;


            for (int i = 0; i < n1; i++) {
                food.get(i).rat = -1;
            }

            boolean noVacant = true;
            for (int k = 0; k < n1; k++) {
                if (taken[k] == -1)
                    noVacant = false;
            }

            if (noVacant) {
                for (int i = 0; i < n1; i++) {
                    taken[i] = -1;
                }
            }

            for (int i = 0; i < n1 && currentCal < newCap && ansPrice < newMxCost; i++) {
                if (taken[i] != -1)
                    continue;

                if ( (currentCal + food.get(i).Calorie <= newCap) && (ansPrice + food.get(i).Price <= newMxCost) ) {

                    food.get(i).rat = 1;
                    currentCal += food.get(i).Calorie;

                    ansPrice += food.get(i).Price;
                    taken[i] = day;

                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat*food.get(i).Qty + " " + food.get(i).unit);
                }

                else {

                    food.get(i).rat = Math.min((newCap - currentCal) / food.get(i).Calorie, (newMxCost - ansPrice) / food.get(i).Price);
                    currentCal += food.get(i).Calorie * food.get(i).rat;
                    ansPrice += food.get(i).Price * food.get(i).rat;

                    taken[i] = day;

                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat*food.get(i).Qty + " " + food.get(i).unit);
                }


                if (i == n1 - 1 && currentCal < newCap && ansPrice < newMxCost) {
                    i = -1;
                    for (int k = 0; k < n1; k++) {
                        if (taken[k] != day) {
                            taken[k] = -1;
                            food.get(k).rat = -1;
                        }
                    }

                }
            }

            //output for breakfast
            for (int i = 0; i < n1; i++) {
                if (food.get(i).rat > 0){
                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat * food.get(i).Qty + " " + food.get(i).unit);
                      Item item =new Item(food.get(i).name,food.get(i).rat * food.get(i).Qty, food.get(i).unit,day-1);
                      breakfast.add(item);
                }
            }

            //System.out.println("\n" + ansPrice + " " + currentCal + "\n\n");

            double cap1 = currentCal + 2*300, cost1 = ansPrice + 30;

            //lunch@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            //lunch@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

            newMxCost = (mxCost - cost1)*.65;
            newCap = (cap - cap1)*.65;

            currentCal = 0;
            ansPrice = 0;


            for (int i = 0; i < n2; i++) {
                food2.get(i).rat = -1;
            }

            noVacant = true;
            for (int k = 0; k < n2; k++) {
                if (taken2[k] == -1)
                    noVacant = false;
            }

            if (noVacant) {
                for (int i = 0; i < n2; i++) {
                    taken2[i] = -1;
                }
            }

            //System.out.println(cap + " " + mxCost);
            for (int i = 0; i < n2 && currentCal < newCap && ansPrice < newMxCost; i++) {
                if (taken2[i] != -1)
                    continue;

                if ( (currentCal + food2.get(i).Calorie <= newCap) && (ansPrice + food2.get(i).Price <= newMxCost) ) {

                    food2.get(i).rat = 1;
                    currentCal += food2.get(i).Calorie;

                    ansPrice += food2.get(i).Price;
                    taken2[i] = day;

                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat*food.get(i).Qty + " " + food.get(i).unit);
                }

                else {

                    food2.get(i).rat = Math.min((newCap - currentCal) / food2.get(i).Calorie, (newMxCost - ansPrice) / food2.get(i).Price);
                    currentCal += food2.get(i).Calorie * food2.get(i).rat;
                    ansPrice += food2.get(i).Price * food2.get(i).rat;

                    taken2[i] = day;

                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat*food.get(i).Qty + " " + food.get(i).unit);
                }


                if (i == n2 - 1 && currentCal < newCap && ansPrice < newMxCost) {
                    i = -1;
                    for (int k = 0; k < n2; k++) {
                        if (taken2[k] != day) {
                            taken2[k] = -1;
                            food2.get(k).rat = -1;
                        }
                    }

                }
            }

            //output for lunch
            for (int i = 0; i < n2; i++) {
                if (food2.get(i).rat > 0)
                {
                    //System.out.println(i + " " + food2.get(i).name + " " + food2.get(i).rat * food2.get(i).Qty + " " + food2.get(i).unit);
                    Item item =new Item(food2.get(i).name,food2.get(i).rat * food2.get(i).Qty, food2.get(i).unit,day-1);
                    lunch.add(item);
                }
            }

            //System.out.println("\n" + ansPrice + " " + currentCal + "\n\n\n");

            double cap2 = currentCal, cost2 = ansPrice;


            //dinner@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            //dinner@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

            newMxCost = mxCost - cost1 - cost2;
            newCap = cap - cap1 - cap2;

            currentCal = 0;
            ansPrice = 0;

            /*
            for (int i = 0; i < n2; i++) {
                food2.get(i).rat = -1;
            }
            */

            noVacant = true;
            for (int k = 0; k < n2; k++) {
                if (taken2[k] == -1)
                    noVacant = false;
            }

            if (noVacant) {
                for (int i = 0; i < n2; i++) {
                    taken2[i] = -1;
                }
            }


            //System.out.println(cap + " " + mxCost);
            for (int i = 0; i < n2 && currentCal < newCap && ansPrice < newMxCost; i++) {
                if (taken2[i] != -1)
                    continue;

                if ( (currentCal + food2.get(i).Calorie <= newCap) && (ansPrice + food2.get(i).Price <= newMxCost) ) {

                    food2.get(i).rat = 1;
                    currentCal += food2.get(i).Calorie;

                    ansPrice += food2.get(i).Price;
                    taken2[i] = day;
                    dinner[i] = day;

                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat*food.get(i).Qty + " " + food.get(i).unit);
                }

                else {

                    food2.get(i).rat = Math.min((newCap - currentCal) / food2.get(i).Calorie, (newMxCost - ansPrice) / food2.get(i).Price);
                    currentCal += food2.get(i).Calorie * food2.get(i).rat;
                    ansPrice += food2.get(i).Price * food2.get(i).rat;

                    taken2[i] = day;
                    dinner[i] = day;

                    //System.out.println(i + " " + food.get(i).name + " " + food.get(i).rat*food.get(i).Qty + " " + food.get(i).unit);
                }


                if (i == n2 - 1 && currentCal < newCap && ansPrice < newMxCost) {
                    i = -1;
                    for (int k = 0; k < n2; k++) {
                        if (taken2[k] != day) {
                            taken2[k] = -1;
                            dinner[k] = -1;
                            food2.get(k).rat = -1;
                        }
                    }

                }
            }

            //output for dinner
            for (int i = 0; i < n2; i++) {
                if (food2.get(i).rat > 0 && dinner[i] == day)
                {
                    //System.out.println(i + " " + food2.get(i).name + " " + food2.get(i).rat * food2.get(i).Qty + " " + food2.get(i).unit);
                    Item item =new Item(food2.get(i).name,food2.get(i).rat * food2.get(i).Qty, food2.get(i).unit,day-1);
                    dinners.add(item);
                }
            }
            //System.out.println("\n" + ansPrice + " " + currentCal + "===============");
        }
    }
}

class Sortbyx implements Comparator<Foods>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Foods a, Foods b)
    {
        return Double.compare(b.x, a.x);
    }
}