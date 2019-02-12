package com.ourcuet.mehedi.halthyfoodmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class FamilyMember extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;
    EditText ageText,feetText,inchText, weightText;
    CheckBox maleBox, femaleBox, sedentaryBox, lightActiveBox, moderateActiveBox, activeBox, veryActiveBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member);
        init();

    }
    private void init(){
        submitButton = (Button)findViewById(R.id.submitButton);
        ageText = (EditText)findViewById(R.id.ageText);
        feetText = (EditText)findViewById(R.id.feetText);
        inchText = (EditText)findViewById(R.id.inchText);
        weightText = (EditText)findViewById(R.id.weightText);
        maleBox = (CheckBox)findViewById(R.id.maleBox);
        femaleBox = (CheckBox)findViewById(R.id.femaleBox);
        sedentaryBox = (CheckBox)findViewById(R.id.sedentaryBox);
        lightActiveBox = (CheckBox)findViewById(R.id.lightActiveBox);
        moderateActiveBox = (CheckBox)findViewById(R.id.moderateActiveBox);
        activeBox = (CheckBox)findViewById(R.id.activeBox);
        veryActiveBox = (CheckBox)findViewById(R.id.veryActiveBox);
        submitButton.setOnClickListener(this);
    }
    public float calculation(){

        float weight = (float) (Float.parseFloat(weightText.getText().toString())*2.20462);
        float height = (float) (Float.parseFloat(feetText.getText().toString())*12)+(float) (Float.parseFloat(inchText.getText().toString()));
        float age = (float) (Float.parseFloat(ageText.getText().toString()));

        float bmr = (femaleBox.isChecked())?female(weight,height,age):male(weight,height,age);

        float amr = 0;
        if(sedentaryBox.isChecked()){
            return (float) (bmr * 1.2);
        }else if(lightActiveBox.isChecked()){
            return (float) (bmr * 1.375);
        }else if(moderateActiveBox.isChecked()){
            return (float) (bmr * 1.55);
        }else if(activeBox.isChecked()){
            return (float) (bmr * 1.725);
        }else if(veryActiveBox.isChecked()){
            return (float) (bmr * 1.9);
        }
        return amr;
    }

    public float female(float weight, float height, float age){
        return (float)(655+ (4.35*weight)+ (4.7*height)-4.7*age);
    }
    public float male(float weight, float height, float age){
        return (float)(66+ (6.23*weight)+ (12.7*height)-6.8*age);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submitButton){
            String amr = Float.toString(calculation());
            Intent intent = new Intent();
            intent.putExtra("amr", amr);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
