package com.ourcuet.mehedi.halthyfoodmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MemberCHart extends RecyclerView.ViewHolder{

    TextView memberText ;
    TextView amrText;
    public MemberCHart(@NonNull View itemView) {
        super(itemView);
        init();
    }
    private void init(){
        memberText = (TextView)itemView.findViewById(R.id.memberText);
        amrText = (TextView)itemView.findViewById(R.id.amrText);
    }
}
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String>name =  new ArrayList<String>();
    private ArrayList<String>amr =  new ArrayList<String>();

    private Context context;

    public MyAdapter(ArrayList<String> name, ArrayList<String> amr, Context context) {
        this.name = name;
        this.amr = amr;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return  new MemberCHart(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String s = name.get(i);
        String s1 = amr.get(i);
        MemberCHart vHolder = (MemberCHart)viewHolder;
        vHolder.memberText.setText(s);
        vHolder.amrText.setText(s1);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
}