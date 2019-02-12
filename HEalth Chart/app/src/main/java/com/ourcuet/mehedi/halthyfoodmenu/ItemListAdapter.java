package com.ourcuet.mehedi.halthyfoodmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<FoodsItems> items = new ArrayList<FoodsItems>();
    Context context;

    public ItemListAdapter(ArrayList<FoodsItems> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chart_list_load, viewGroup, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder vHolder = (ViewHolder)viewHolder;
        vHolder.nameText.setText(items.get(i).getFname());
        vHolder.quantityText.setText(items.get(i).getQuantity());
        vHolder.unitText.setText(items.get(i).getFunit());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder{

    TextView nameText, quantityText, unitText;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        init();
    }
    public void init(){
        nameText = (TextView)itemView.findViewById(R.id.nameText);
        quantityText = (TextView)itemView.findViewById(R.id.quantityText);
        unitText = (TextView)itemView.findViewById(R.id.unitText);
    }
}