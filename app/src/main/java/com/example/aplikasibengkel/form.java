package com.example.aplikasibengkel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class form extends AppCompatActivity {

    private Spinner spinner1;
    private Spinner spinner2;
    private final String[] RodaDua = {
            "Sport Bike",
            "Moped"
    };
    private final String[] RodaEmpat = {
            "SUV",
            "Sedan"
    };

    protected String jenisKendaraan, modelKendaraan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RodaDua);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, RodaEmpat);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jenisKendaraan = spinner1.getSelectedItem().toString();
                if(jenisKendaraan.equals("Roda Dua")){
                    spinner2.setAdapter(adapter);
                } else if(spinner1.getSelectedItem().toString().equals("Roda Empat")){
                    spinner2.setAdapter(adapter2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                modelKendaraan = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void Detail(View view) {
        Intent intent = new Intent(form.this, detail.class);
        intent.putExtra("jenisKendaraan", jenisKendaraan);
        intent.putExtra("modelKendaraan", modelKendaraan);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(form.this, MainActivity.class);
        startActivity(intent);
    }

}