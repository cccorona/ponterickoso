package mx.com.baseapplication.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mx.com.baseapplication.R;

public class EpisodioHolder extends RecyclerView.ViewHolder {

    public View root;
    public TextView title, subtitle, content;

    public EpisodioHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        title = itemView.findViewById(R.id.title);
        subtitle = itemView.findViewById(R.id.subtitle);
        content = itemView.findViewById(R.id.thirdline);

    }
}
