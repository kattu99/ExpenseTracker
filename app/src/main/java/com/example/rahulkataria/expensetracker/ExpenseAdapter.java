package com.example.rahulkataria.expensetracker;

import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ExpenseAdapter extends ArrayAdapter<Expense>{

    private final Context context;
    private final ArrayList<Expense> itemsArrayList;

    public ExpenseAdapter(Context context, ArrayList<Expense> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row,parent,false);

        TextView nameView = (TextView) rowView.findViewById(R.id.name);
        TextView typeView = (TextView) rowView.findViewById(R.id.type);
        TextView priceView = (TextView) rowView.findViewById(R.id.price);
        TextView dateView = (TextView) rowView.findViewById(R.id.date);

        nameView.setText(itemsArrayList.get(position).getPlace());
        typeView.setText(String.valueOf(itemsArrayList.get(position).getType()));
        priceView.setText(String.valueOf(itemsArrayList.get(position).getPrice()));
        dateView.setText(String.valueOf(itemsArrayList.get(position).getDate()));

        return rowView;
    }

}
