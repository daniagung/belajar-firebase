package com.buahbatu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "MAIN_ACTIVITY";

    EditText editText;
    Button save_button, move_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.input_text);

        // buttons
        save_button = (Button) findViewById(R.id.save_button);
        move_button = (Button) findViewById(R.id.see_button);

        save_button.setOnClickListener(this);
        move_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_button:
                Log.i(TAG, "onClick: ");
                saveData();
                break;
            case R.id.see_button:
                Intent moveIntent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(moveIntent);
                break;
        }
    }

    void saveData(){
        if (!TextUtils.isEmpty(editText.getText())){
            String data = editText.getText().toString();

            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("data");

            myRef.push().setValue(data);

            editText.setText("");

        }else {
            Log.i(TAG, "saveData");
            editText.setError("Tolong diisi");
        }
    }
}
