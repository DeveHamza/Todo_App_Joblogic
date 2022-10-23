package com.joblogic_hamzashahid.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Sell_List_Activity extends AppCompatActivity {
    ActionBar actionBar;
    EditText itemName, itemPrice, itemQuantity;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sell_list);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Display Title on Action bar
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Add Sell Items");

        itemName = findViewById(R.id.item_name_sell_list);
        itemPrice = findViewById(R.id.item_price_sell_list);
        itemQuantity = findViewById(R.id.item_quantity_sell_list);
        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemName.length() > 0 && itemPrice.length() > 0 && itemQuantity.length() > 0) {
                    String name = "Item Name: " + itemName.getText().toString();
                    String price = "Price: " + itemPrice.getText().toString();
                    String quantity = "Quantity: " + itemQuantity.getText().toString();

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Add_Sell_List_Activity.this);
                    dataBaseHelper.insertItemDetails(name, price, quantity);
                    Toast.makeText(getApplicationContext(), "Itemms Inserted Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}