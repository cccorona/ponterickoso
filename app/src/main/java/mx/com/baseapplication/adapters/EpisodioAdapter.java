package mx.com.baseapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import mx.com.baseapplication.R;
import mx.com.baseapplication.holder.EpisodioHolder;
import mx.com.baseapplication.model.Episodio;
import mx.com.baseapplication.model.GenericalError;
import mx.com.baseapplication.utils.Constants;
import mx.com.baseapplication.utils.NetHelper;

public class EpisodioAdapter extends RecyclerView.Adapter {

    public ArrayList<String> episodios;

    public EpisodioAdapter(ArrayList<String> episodios) {
        this.episodios = episodios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episodio_item,parent,false);
        return  new EpisodioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String url = episodios.get(position);
           if(url!=null){
               EpisodioHolder episodioHolder = (EpisodioHolder) holder;
               NetHelper netHelper = new NetHelper(episodioHolder.root.getContext());
               netHelper.setOnDataResultInterface(new NetHelper.OnDataResultInterface() {
                   @Override
                   public void OnResultOk(Object object) {
                       Episodio episodio = (Episodio) object;
                       episodioHolder.title.setText(episodio.getName());
                       episodioHolder.subtitle.setText(episodio.getEpisode());
                       episodioHolder.content.setText(episodio.getAir_date());
                   }

                   @Override
                   public void OnError(GenericalError error) {

                   }
               });

               String method = url.replace(Constants.BASE_URL,"");
               netHelper.getRequestWithParams(Episodio.class,
                       method,new HashMap<>(),new HashMap<>());
           }

    }

    @Override
    public int getItemCount() {
        return episodios.size();
    }
}
