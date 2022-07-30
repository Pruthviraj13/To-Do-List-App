 package com.example.to_dolistapp;

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

import com.example.to_dolistapp.databinding.ActivityMainBinding;

import java.util.List;

 public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Database_Activity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);

            }
        });
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);
        RVAdapter rvAdapter=new RVAdapter();
        binding.rv.setAdapter(rvAdapter);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                rvAdapter.submitList(notes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT){
                    noteViewModel.delete(rvAdapter.getNotes(viewHolder.getAdapterPosition()));

                }
                else{
                   Intent intent=new Intent(MainActivity.this,Database_Activity.class);
                   intent.putExtra("type","update");
                   intent.putExtra("title",rvAdapter.getNotes(viewHolder.getAdapterPosition()).getTitle());
                   intent.putExtra("disp",rvAdapter.getNotes(viewHolder.getAdapterPosition()).getDisp());
                   intent.putExtra("id",rvAdapter.getNotes(viewHolder.getAdapterPosition()).getId());
                   startActivityForResult(intent,2);
                }

            }

        }).attachToRecyclerView(binding.rv);
    }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if(requestCode==1){
             String title=data.getStringExtra("title");
             String disp=data.getStringExtra("disp");
             Notes notes=new Notes(title,disp);
             noteViewModel.insert(notes);
             Toast.makeText(this, "note added", Toast.LENGTH_SHORT).show();
         }
         else if(requestCode==2){
             String title=data.getStringExtra("title");
             String disp=data.getStringExtra("disp");
             Notes notes=new Notes(title,disp);
             notes.setId(data.getIntExtra("id",0));
             noteViewModel.update(notes);
             Toast.makeText(this, "note updated", Toast.LENGTH_SHORT).show();
         }
     }
 }