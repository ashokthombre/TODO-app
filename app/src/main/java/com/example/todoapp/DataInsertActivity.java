package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todoapp.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
 ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type=getIntent().getStringExtra("type");

        if (type.equals("update"))
        {
            String title=getIntent().getStringExtra("title");
            String desc=getIntent().getStringExtra("desc");

            binding.inputtitle.setText(title);
            binding.inputnote.setText(desc);
            binding.submit.setText("Update");

            binding.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("title",binding.inputtitle.getText().toString());
                    intent.putExtra("desc",binding.inputnote.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });


        }
        else {

            binding.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent();
                    intent.putExtra("title",binding.inputtitle.getText().toString());
                    intent.putExtra("desc",binding.inputnote.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();

                }
            });


        }





    }
}