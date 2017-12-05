package com.robles.labexerdecrobles;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_message;
    Button btn_display;
    Button btn_save;
    Button btn_clear;
    TextView tv_display;
    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_message = findViewById(R.id.etmessage);
        btn_display = findViewById(R.id.btndisplay);
        btn_save = findViewById(R.id.btnsave);
        btn_clear = findViewById(R.id.btnclear);
        tv_display = findViewById(R.id.tvmessage);
    }

    public void saveStorage (View view) {
        String message = et_message.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
    }

    public void displayStorage (View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;
        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_display.setText(buffer.toString());
    }

    public void clear (View view) {
        et_message.setText("");
    }
}
