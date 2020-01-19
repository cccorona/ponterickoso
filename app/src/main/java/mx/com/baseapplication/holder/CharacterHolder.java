package mx.com.baseapplication.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mx.com.baseapplication.R;

public class CharacterHolder extends RecyclerView.ViewHolder {


    public View root;
    public ImageView icon;
    public TextView name;

    public CharacterHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        icon = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);

    }
}
