package mx.com.baseapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mx.com.baseapplication.R;
import mx.com.baseapplication.holder.PagerHolder;
import mx.com.baseapplication.interfaces.ActionInterface;
import mx.com.baseapplication.utils.Enums;


public class PagerAdapter extends RecyclerView.Adapter {


    private Integer numbers;
    private ActionInterface actionInterface;

    public PagerAdapter(Integer numbers, ActionInterface actionInterface) {
        this.numbers = numbers;
        this.actionInterface = actionInterface;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pager_item_layout,parent,false);
        return  new PagerHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        PagerHolder pagerHolder = (PagerHolder) holder;
          pagerHolder.number.setText(""+position);
          pagerHolder.root.setOnClickListener(v->{
              if(actionInterface!=null){
                  actionInterface.OnActionSelected(Enums.ACCIONES.PAGER_SELECTED,position);
              }
          });

    }

    @Override
    public int getItemCount() {
        return numbers;
    }
}
