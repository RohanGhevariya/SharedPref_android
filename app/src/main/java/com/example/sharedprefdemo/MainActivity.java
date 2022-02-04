package com.example.sharedprefdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.sharedprefdemo.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String Tag="MainActivity";
    public static final String Shared_preference_name="user_name";
    public static final String key_username="key_username";
    public static final String NAme_serilized="name_serilized";

    private SharedPreferences  sharedPreferences;
    private EditText name, age;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //name = findViewById(R.id.edit1);
       // age = findViewById(R.id.edit2);
        //1st step - construct

        sharedPreferences=getSharedPreferences(Shared_preference_name,MODE_PRIVATE);
        //String s1 = sharedPreferences.getString(key_username, "");
       // int a = sharedPreferences.getInt("age", 0);
       // name.setText(s1);
        //age.setText(String.valueOf(a));
        //2nd step-write into shared preference

       // sharedPreferences.edit()
        //.putString("name", name.getText().toString())
        //.putInt("age", Integer.parseInt(age.getText().toString()))
       // .apply();


        //3rd read from shared preference

       // String name=sharedPreferences.getString("name","N/A");
       // int age=sharedPreferences.getInt("age",0);

       // Log.d(Tag,"on create:" + name +age) ;

        ArrayList<String>nameList=new ArrayList<>();
        String deString=sharedPreferences.getString(NAme_serilized,"");
        try{
            nameList=(ArrayList)ObjectSerializer.deserialize(deString);

        }catch(IOException e){
            e.printStackTrace();

        }
        Log.d(Tag,"Oncreate:"+nameList);
    }
    public void addcontact(View v){
        Contacts contacts=new Contacts(
                binding.edit1.getText().toString().trim(),
                Integer.parseInt(binding.edit2.getText().toString().trim())
        );
        try {
            sharedPreferences.edit()
                    .putString("contact_serialized",ObjectSerializer.serialize(contacts))
                    .apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getcontact();

    }

    private void getcontact(){
        String receiveConatct=sharedPreferences.getString("contact_serialized","NA");
        try {
            Contacts contacts=(Contacts) ObjectSerializer.deserialize(receiveConatct);
            Log.d(Tag,"getContact:"+contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}