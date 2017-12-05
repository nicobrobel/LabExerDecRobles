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

    FileOutputStream fos;
    FileInputStream fis;
    EditText EtMess;
    Button BtDis;
    Button BtSav;
    Button BtCle;
    TextView TvDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtMess = findViewById(R.id.EtMess);
        BtDis = findViewById(R.id.BtDisp);
        BtSav = findViewById(R.id.BtSave);
        BtCle = findViewById(R.id.BtClea);
        TvDis = findViewById(R.id.TvMess);
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
        TvDis.setText(buffer.toString());
    }

    public void clear (View view) {
        EtMess.setText("");
    }

    public void saveStorage (View view) {
        String message = EtMess.getText().toString();
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
        Toast.makeText(this, "Message has been saved!", Toast.LENGTH_SHORT).show();
    }
}
