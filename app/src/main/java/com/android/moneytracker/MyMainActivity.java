package com.android.moneytracker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application.R;


public class MyMainActivity extends AppCompatActivity {
    static class Item{
        String name;
        int price;

        Item(String name, int price){
        this.name = name;
        this.price = price;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_money);
        final EditText viewName= findViewById(R.id.productName);
        final EditText viewPrice = findViewById(R.id.productPrice);
        final Button addButton = findViewById(R.id.approve);
        final ListView items = findViewById(R.id.list_item);
        final ItemsAdapter adapter = new ItemsAdapter();
        items.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewName.getText().toString().length() == 0 || viewPrice.getText().toString().length()== 0){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "All field can't not be empty", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else adapter.add(new Item(viewName.getText().toString(), Integer.valueOf(viewPrice.getText().toString())));
            }
        });
    }

    private class ItemsAdapter extends ArrayAdapter<Item> {
        public ItemsAdapter() {
            super(MyMainActivity.this, R.layout.item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          final View view =  getLayoutInflater().inflate(R.layout.item, null);
          final  Item item = getItem(position);
            ((TextView)view.findViewById(R.id.name)).setText(item.name);
            ((TextView)view.findViewById(R.id.price)).setText(String.valueOf(item.price));
            return view;
        }
    }
}
