package com.example.calculator.newstv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class News_screen extends AppCompatActivity {
    ImageView img;
    TextView heading , subheading , content;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_screen);

        heading = findViewById(R.id.heading);
        subheading = findViewById(R.id.descp);
        content = findViewById(R.id.content);
        img = findViewById(R.id.image);
        btn = findViewById(R.id.button);

        Intent i = getIntent();
        String image = i.getStringExtra("img");
        String head = i.getStringExtra("title");
        String suhead = i.getStringExtra("descp");
        String cont = i.getStringExtra("content");
        String url = i.getStringExtra("url");

        try {
            Picasso.get().load(image).into(img);
            heading.setText(head);
            subheading.setText(suhead);
            content.setText(cont);
        }catch (Exception e){
            String ext = e.toString();
            Toast.makeText(this, "Something went error", Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });



    }
}