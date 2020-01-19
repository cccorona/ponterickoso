package mx.com.baseapplication.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mx.com.baseapplication.R;

public class PagerHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView number;

    public PagerHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        number = itemView.findViewById(R.id.text);
    }
}
