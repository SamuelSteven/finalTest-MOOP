package com.example.aplikasibengkel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "data_user.txt";

    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void login(View view) throws IOException {
        File directory = null;
        boolean status = false;

        String text1 = username.getText().toString();
        String text2 = password.getText().toString();

        File[] f = new File(String.valueOf(getFilesDir())).listFiles();
        for(int i = 0; i < f.length; i++){
            String dir = getFilesDir() + "/" + FILE_NAME;
            String dirf = String.valueOf(f[i]);
            if(dirf.equals(dir)){
                status = true;
                break;
            }
        }
        if (!status) {
            // If true (file discovered), it will make a file named data_user.txt
            directory = getFilesDir();
            String text = text1 + "," + text2;

            try{
                FileOutputStream fos = null;
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                fos.write(text.getBytes());

                Toast.makeText(this, "Saved to" + directory + "/" + FILE_NAME, Toast.LENGTH_LONG).show();

                username.getText().clear();
                password.getText().clear();

                if(fos != null){
                    try{
                        fos.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            // while the file has been made, program will go to form class
            Intent intent = new Intent(MainActivity.this, form.class);
            startActivity(intent);
        }
        else {
            // If false (file not discovered), it will read a user data
            FileInputStream fis = null;
            try{
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String txt;

                while((txt = br.readLine()) != null){
                    sb.append(txt).append("\n");
                }
                String load = sb.toString();
                String[] parts = load.split(",");
                // then, program will validate data user from file
                validate(text1, parts[0], text2, parts[1]);

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void validate(String newUsername, String username, String newPassword, String password){
        if(newUsername.equals(username) && newPassword.equals(password.replaceAll("\\s+",""))) {
            Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, form.class);
            startActivity(intent);
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Wrong Username or Password!").setCancelable(true)
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null);

            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}