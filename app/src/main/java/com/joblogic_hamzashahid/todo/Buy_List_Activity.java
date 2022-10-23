package com.joblogic_hamzashahid.todo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Buy_List_Activity extends AppCompatActivity {
    ActionBar actionBar;

    private String TAG = Buy_List_Activity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> buyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_list);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Display Title on Action bar
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Buy List");


        buyList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listview_buy_list);

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(Buy_List_Activity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String url = "https://my-json-server.typicode.com/imkhan334/demo-1/buy";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonarray = new JSONArray(jsonStr);


                    // looping through All Buy

                    for(int i=0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name    = "Name: "+jsonobject.getString("name");
                        String price  = "Price: "+jsonobject.getString("price");
                        String quantity  = "Quantity: "+jsonobject.getString("quantity");


                        // tmp hash map for single buy
                        HashMap<String, String> buy = new HashMap<>();

                        // adding each child node to HashMap key => value

                        buy.put("name", name);
                        buy.put("price", price);
                        buy.put("quantity", quantity);
                        // adding buys to buy list
                        buyList.add(buy);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(Buy_List_Activity.this, buyList,
                    R.layout.list_items_buy, new String[]{ "name","price","quantity"},
                    new int[]{R.id.name, R.id.price, R.id.quantity});
            lv.setAdapter(adapter);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}