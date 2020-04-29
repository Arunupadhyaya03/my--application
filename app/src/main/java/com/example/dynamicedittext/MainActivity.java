package com.example.dynamicedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {



        private LinearLayout parentLinearLayout;
        private Button submit;
        private EditText number;
        private String number1;
    private int count = 1;

    public void onAddField(View v) {



        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        for(int i=1;i<=11;i++){
            TextView tv = new TextView(this);  //create Text View
            tv.setId(i);
            Log.v(TAG, "result gottttt"+i);
            //then set the id to i
            parentLinearLayout.addView(tv);
            //add TV to example Layout
        }


        number = findViewById(R.id.number_edit_text);


        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "result gottttt");
                number1 = number.getText().toString();
                number1=number.getText().toString();

                Log.v(TAG, "result gottttt");
                String res = "";
                Log.v(TAG, "result gottttt");
                try {
                    res = new BackgroundWorker().execute("number", number1 ).get();
                    if(res.equals("success") ) {
                        Toast.makeText(getApplicationContext(),"Submitted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error on submission try again", Toast.LENGTH_SHORT).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }

        });

    }
    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            parentLinearLayout=(LinearLayout) findViewById(R.id.parent_linear_layout);



        }



}
