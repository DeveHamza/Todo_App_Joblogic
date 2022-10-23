package com.joblogic_hamzashahid.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Sell_List_Activity extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_list);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Display Title on Action bar
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Sell List");
        DataBaseHelper db = new DataBaseHelper(this);
        ArrayList<HashMap<String, String>> itemList = db.GetItems();
        ListView lv = (ListView) findViewById(R.id.listview_sell_list);
        ListAdapter adapter = new SimpleAdapter(Sell_List_Activity.this, itemList, R.layout.list_items_sell,new String[]{"name","price","quantity"}, new int[]{R.id.name, R.id.price, R.id.quantity});
        lv.setAdapter(adapter);
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sell_list,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.add_sell:
                Intent i = new Intent(Sell_List_Activity.this, Add_Sell_List_Activity.class);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}