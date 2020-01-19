package mx.com.baseapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mx.com.baseapplication.R;
import mx.com.baseapplication.holder.CharacterHolder;
import mx.com.baseapplication.interfaces.ActionInterface;
import mx.com.baseapplication.model.Character;

import static mx.com.baseapplication.utils.Enums.ACCIONES.SELECTED;

public class CharactersAdapter  extends RecyclerView.Adapter implements Filterable {


    private List<Character> characters;
    private List<Character> filteredList = new ArrayList<>();

    private ActionInterface actionInterface;


    public CharactersAdapter(List<Character> characters, ActionInterface actionInterface) {
        this.characters = characters;
        this.actionInterface = actionInterface;
        this.filteredList = this.characters;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character_layout,
                parent,false);
        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Character character = filteredList.get(position);
           if(character!=null){
               CharacterHolder characterHolder = (CharacterHolder) holder;
               characterHolder.name.setText(character.getName());
               Glide.with(holder.itemView.getContext())
                       .load(character.getImage()).into(characterHolder.icon);

               characterHolder.root.setOnClickListener(v -> {
                   if(actionInterface!=null){
                       actionInterface.OnActionSelected(SELECTED,character);
                   }
               });
           }

    }

    @Override
    public int getItemCount() {
        if( filteredList!=null){
            return filteredList.size();
        }else{
            return 0;
        }
    }

    public void addMore(List<Character> newcharacters){
        int currentPos = characters.size();
        characters.addAll(newcharacters);

        notifyItemInserted(currentPos + 1);

    }

    public void resetData(){
        filteredList = characters;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = characters;
                } else {
                    List<Character> chatFilter = new ArrayList<>();
                    for (Character row : characters) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            chatFilter.add(row);
                        }
                    }

                    filteredList = chatFilter;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<Character>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
