package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.databinding.RvBgBinding;

public class RVAdapter extends ListAdapter<Notes,RVAdapter.Viewolder>
{

    protected RVAdapter(@NonNull DiffUtil.ItemCallback<Notes> diffCallback) {
        super(diffCallback);
    }

    protected RVAdapter(@NonNull AsyncDifferConfig<Notes> config) {
        super(config);
    }

    public RVAdapter()
    {
        super(CALLBACK);

    }

    private static final DiffUtil.ItemCallback<Notes> CALLBACK=new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    &&oldItem.getDisc().equals(newItem.getDisc());
        }
    };


    @NonNull
    @Override
    public RVAdapter.Viewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bg,parent,false);
        return new Viewolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RVAdapter.Viewolder holder, int position) {

        Notes notes=getItem(position);
   holder.binding.titlerv.setText(notes.getTitle());
   holder.binding.discrv.setText(notes.getDisc());



    }
    public Notes getNotes(int position)
    {
        return getItem(position);
    }

    public class Viewolder extends RecyclerView.ViewHolder {
       RvBgBinding binding;
        public Viewolder(@NonNull View itemView) {
            super(itemView);
            binding=RvBgBinding.bind(itemView);
        }
    }
}
