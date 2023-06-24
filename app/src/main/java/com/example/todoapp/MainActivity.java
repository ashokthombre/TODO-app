package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.todoapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
               intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });
        binding.rv.setLayoutManager(new LinearLayoutManager(this));

        binding.rv.setHasFixedSize(true);
        RVAdapter adapter=new RVAdapter();
        binding.rv.setAdapter(adapter);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if (direction==ItemTouchHelper.RIGHT)
                {  Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("title",adapter.getNotes(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("desc",adapter.getNotes(viewHolder.getAdapterPosition()).getDisc());
                    startActivityForResult(intent,2);

                }
                else
                {
                    noteViewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));
                }


            }
        }).attachToRecyclerView(binding.rv);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            String title=data.getStringExtra("title");
            String desc=data.getStringExtra("desc");
            Notes notes=new Notes(title,desc);
            noteViewModel.insert(notes);
            Toast.makeText(this, "Note is Added", Toast.LENGTH_SHORT).show();

        }
        else
        {
            String title=data.getStringExtra("title");
            String desc=data.getStringExtra("desc");
            Notes notes=new Notes(title,desc);
            noteViewModel.update(notes);
            Toast.makeText(this, "Note is Updated", Toast.LENGTH_SHORT).show();

        }
    }
}