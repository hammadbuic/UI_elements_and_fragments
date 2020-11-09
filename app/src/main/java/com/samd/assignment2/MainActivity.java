package com.samd.assignment2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EditText date, email, name, number, etContry;
    private ImageView country;
    private ImageView calender, close;
    private AutoCompleteTextView autoCity;
    private String [] city;
    private LinearLayout linearLayout;
    private Fragment openFragment;
    private Button signUp;
    // Taking image from the user
    private static final int pickImage=1;
    private ImageView userImage;
    Uri imageUri;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------------------------------------------------------------------------------------------------------------------------------------
        //casting the xml items
        email=(EditText)findViewById(R.id.etEmail);
        name=(EditText)findViewById(R.id.etName);
        number=(EditText)findViewById(R.id.etMobile);
        date=(EditText)findViewById(R.id.etDate);
        calender=(ImageView)findViewById(R.id.ivDateSelect);
        autoCity=(AutoCompleteTextView)findViewById(R.id.autCity);
        country=(ImageView)findViewById(R.id.ivCountrySelect);
        close=(ImageView)findViewById(R.id.close_fregment);
        linearLayout=(LinearLayout)findViewById(R.id.parentLinearLayout);
        etContry=(EditText)findViewById(R.id.etCountry);
        signUp=(Button)findViewById(R.id.btnSignup);
        userImage=(ImageView)findViewById(R.id.ivUser);
        //-----------------------------------------------------------------------------------------------------------------------------------------------
        //Listener for the calender to open
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        //Getting array from resource file
        city = getResources().getStringArray(R.array.City);
        //Setting array adapter
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,city);
        autoCity.setAdapter(cityAdapter);           //Displaying cities through adpter
        //Listener for the country
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment = new Country_Fragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_continer,openFragment).commit();
                linearLayout.setVisibility(View.INVISIBLE);
                close.setVisibility(View.VISIBLE);
            }
        });
        // Listener to close the fregment
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().remove(openFragment).commit();
                linearLayout.setVisibility(View.VISIBLE);
                close.setVisibility(View.INVISIBLE);
            }
        });
        //---------------------------------------------------------------------------------------------------------------------------------------------
        //Checking for the null values and opening new Activity if Not
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(date.getText().toString())||TextUtils.isEmpty(autoCity.getText().toString())||TextUtils.isEmpty(number.getText().toString())||TextUtils.isEmpty(etContry.getText().toString())){
                    Toast.makeText(getApplication(),"Any Input is Empty",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("mail",email.getText().toString());
                    intent.putExtra("name",name.getText().toString());
                    intent.putExtra("date",date.getText().toString());
                    intent.putExtra("city",autoCity.getText().toString());
                    intent.putExtra("number",number.getText().toString());
                    intent.putExtra("country",etContry.getText().toString());
                    intent.putExtra("image",imageUri);
                    startActivity(intent);
                    Toast.makeText(getApplication(),"Registration Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //-----------------------------------------------------------------------------------------------------------------
        //Taking image from user
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Pick a Picture"),pickImage);
            }
        });
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String dateDisplay = dayOfMonth +"/" + month + "/" + year;
        date.setText(dateDisplay);
        Toast.makeText(this,"Date Selected is: "+dateDisplay,Toast.LENGTH_SHORT).show();
    }
    public void recieveCountry(String cont){
        etContry.setText(cont);
    }
    //Creating function to show date picker
    private void showDatePickerDialog(){
        DatePickerDialog datePicker= new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==pickImage && resultCode==RESULT_OK){
            imageUri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                userImage.setImageBitmap(bitmap);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
