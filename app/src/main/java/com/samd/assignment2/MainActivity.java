package com.samd.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private EditText date;
    private Button country;
    private ImageView calender, close;
    private AutoCompleteTextView autoCity;
    private String [] city;
    private LinearLayout linearLayout;
    private Fragment openFragment;

    //Creating function to show date picker
    private void showDatePickerDialog(){
        DatePickerDialog datePicker= new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //casting the xml items
        date=(EditText)findViewById(R.id.etDate);
        calender=(ImageView)findViewById(R.id.ivDateSelect);
        autoCity=(AutoCompleteTextView)findViewById(R.id.autCity);
        country=(Button)findViewById(R.id.btnCountry);
        close=(ImageView)findViewById(R.id.close_fregment);
        linearLayout=(LinearLayout)findViewById(R.id.parentLinearLayout);
        //Getting array from resource file
        city = getResources().getStringArray(R.array.City);
        //Setting array adapter
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,city);
        autoCity.setAdapter(cityAdapter);           //Displaying cities through adpter
        //Listener for the calender to open
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
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
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String dateDisplay = dayOfMonth +"/" + month + "/" + year;
        date.setText(dateDisplay);
        Toast.makeText(this,"Date Selected is: "+dateDisplay,Toast.LENGTH_SHORT).show();
    }
}
