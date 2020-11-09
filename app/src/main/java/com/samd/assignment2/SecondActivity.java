package com.samd.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    private EditText secMail,secName,secDate,secCity,secMobile,secCountry;
    private ImageView userDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //----------------------------------------------------------------------------------------------------------
        //Casting with xml
        secMail=(EditText)findViewById(R.id.secMail);
        secName=(EditText)findViewById(R.id.secName);
        secDate=(EditText)findViewById(R.id.secDob);
        secCity=(EditText)findViewById(R.id.secCity);
        secMobile=(EditText)findViewById(R.id.secMobile);
        secCountry=(EditText)findViewById(R.id.secCountry);
        userDisplay=(ImageView)findViewById(R.id.ivSecUser);
        //----------------------------------------------------------------------------------------------------------
        //Getting data from first intent and displaying in EditText
        Intent intent=getIntent();
        secMail.setText(intent.getStringExtra("mail"));
        secName.setText(intent.getStringExtra("name"));
        secDate.setText(intent.getStringExtra("date"));
        secCity.setText(intent.getStringExtra("city"));
        secMobile.setText(intent.getStringExtra("number"));
        secCountry.setText(intent.getStringExtra("country"));
        try{
            Uri uri =intent.getParcelableExtra("image");
            Bitmap bmp= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            userDisplay.setImageBitmap(bmp);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
