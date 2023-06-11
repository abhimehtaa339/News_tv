package com.example.calculator.newstv;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements catogery_adapetr.onlclick {

    ProgressBar loading;
    private RecyclerView catogerytv;
    private RecyclerView newstv;
    private ArrayList<catogeries_modal>list;
    private catogery_adapetr adapt;

    private ArrayList<Newsmodal>arr;
    private News_adapter adapter;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = findViewById(R.id.loading);
        tv = findViewById(R.id.newstv1);

        catogerytv = findViewById(R.id.catogery);
        list = new ArrayList<>();
        adapt = new catogery_adapetr(this , list , this::onCatogeryclick);
        getcatogeries();
        catogerytv.setAdapter(adapt);


        newstv = findViewById(R.id.newstv);
        newstv.setLayoutManager(new LinearLayoutManager(this));
        arr = new ArrayList<>();
        adapter = new News_adapter(this , arr);
        newstv.setAdapter(adapter);
        getdata("Social");


    }

    public void getcatogeries(){
        list.add(new catogeries_modal("All" , "https://images.unsplash.com/photo-1598128558393-70ff21433be0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=789&q=80"));
        list.add(new catogeries_modal("Technology" , "https://images.unsplash.com/photo-1617854818583-09e7f077a156?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"));
        list.add(new catogeries_modal("Science" , "https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1472&q=80"));
        list.add(new catogeries_modal("Sports" , "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"));
        list.add(new catogeries_modal("General" , "https://images.unsplash.com/photo-1487088678257-3a541e6e3922?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80"));
        list.add(new catogeries_modal("Business" , "https://images.unsplash.com/39/lIZrwvbeRuuzqOoWJUEn_Photoaday_CSD%20%281%20of%201%29-5.jpg?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"));
        list.add(new catogeries_modal("Health" , "https://images.unsplash.com/photo-1535914254981-b5012eebbd15?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"));
        adapt.notifyDataSetChanged();
    }

    public void getdata(String catogery){
        loading.setVisibility(View.VISIBLE);

        String url = "https://gnews.io/api/v4/search?q="+catogery+"&country=in&apikey=f3fea546ce436bc5d78963ffca001480";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        arr.clear();

        JsonObjectRequest obj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    loading.setVisibility(View.GONE);
                    JSONArray array = response.getJSONArray("articles");
                    for (int i = 0  ; i <= array.length()-1 ; i++){
                        String content = response.getJSONArray("articles").getJSONObject(i).getString("content");
                        String heading = response.getJSONArray("articles").getJSONObject(i).getString("title");
                        String img = response.getJSONArray("articles").getJSONObject(i).getString("image");
                        String url = response.getJSONArray("articles").getJSONObject(i).getString("url");
                        String descp = response.getJSONArray("articles").getJSONObject(i).getString("description");
                        if(img.equals("null")){
                            img = "https://www.shutterstock.com/image-vector/modern-network-technology-error-no-600w-1917006467.jpg";
                        }
                        Log.d("img" , img);
                        arr.add(new Newsmodal(img , heading , content , url , descp));
                    }


                } catch (JSONException e) {
                    String ext = e.toString();
                    Toast.makeText(MainActivity.this, "please check your internet connection", Toast.LENGTH_SHORT).show();
                    Log.d("ext" , ext);
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Please check you connection...", Toast.LENGTH_SHORT).show();
                        catogerytv.setVisibility(View.GONE);
                        tv.setText("Connection Time out.");

                    }
                });

        queue.add(obj);
    }

    @Override
    public void onCatogeryclick(int position) {
       String catogery = list.get(position).getCatogery();
        getdata(catogery);
        Log.d("gg" , catogery);
    }
}