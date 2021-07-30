package com.example.aplikasibengkel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    TextView jenis, model, gear, engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        jenis = (TextView) findViewById(R.id.jenis);
        model = (TextView) findViewById(R.id.model);
        gear = (TextView) findViewById(R.id.gear);
        engine = (TextView) findViewById(R.id.engine);

        jenis.setText("");
        model.setText("");
        engine.setText("");
        gear.setText("");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value1 = extras.getString("jenisKendaraan");
            String value2 = extras.getString("modelKendaraan");

            jenis.setText(value1);
            model.setText(value2);

            if(value1.equals("Roda Dua")){
                if(value2.equals("Sport Bike")){
                    SportBike sportBike = new SportBike();
                    engine.setText(sportBike.engine);
                    gear.setText(sportBike.gear);
                } else if(value2.equals("Moped")){
                    Moped moped = new Moped();
                    engine.setText(moped.engine);
                    gear.setText(moped.gear);
                }
            } else if(value1.equals("Roda Empat")){
                if (value2.equals("SUV")){
                    SUV suv = new SUV();
                    engine.setText(suv.engine);
                    gear.setText(suv.gear);
                } else if(value2.equals("Sedan")){
                    Sedan sedan = new Sedan();
                    engine.setText(sedan.engine);
                    gear.setText(sedan.gear);
                }
            }
        }
    }

    public void logout(View view) {
        Intent intent = new Intent(detail.this, MainActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(detail.this, form.class);
        startActivity(intent);
    }
}