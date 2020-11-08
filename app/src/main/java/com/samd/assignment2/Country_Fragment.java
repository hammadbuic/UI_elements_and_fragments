package com.samd.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.zip.Inflater;

public class Country_Fragment extends Fragment {
    private String [] country;
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country,container,false);
        //Getting list of countries from resources
        lv=(ListView)rootView.findViewById(R.id.lvCountry);
        country=getResources().getStringArray(R.array.Country);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.country_list_view,country);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listClick);
        //getFragmentManager().beginTransaction().remove(Country_Fragment.this).commit();
        return rootView;
    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String itemValue = (String) lv.getItemAtPosition(i);
            Toast.makeText(getActivity(),"Selected Country: "+itemValue,Toast.LENGTH_SHORT).show();
        }
    };
}
