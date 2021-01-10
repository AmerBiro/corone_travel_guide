package com.amer.coronetravelguide.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amer.coronetravelguide.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ContinentListAdapter extends RecyclerView.Adapter <ContinentListAdapter.ContinentListViewHolder> {

    private List<ContinentListModel> continentListModel;
    private OnContinentListItemClicked onContinentListItemClicked;

    public ContinentListAdapter(OnContinentListItemClicked onContinentListItemClicked) {
        this.onContinentListItemClicked = onContinentListItemClicked;
    }

    public void setContinentListsModels(List<ContinentListModel> continentListModel) {
        this.continentListModel = continentListModel;
    }

    @NonNull
    @Override
    public ContinentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.continent_single_item, parent, false);
        return new ContinentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContinentListViewHolder holder, int position) {
        holder.name.setText(continentListModel.get(position).getName());
        holder.number.setText( continentListModel.get(position).getNumber());
        holder.total_country_number.setText(continentListModel.get(position).getTotal_country_number());

        Glide
                .with(holder.itemView.getContext())
                .load(continentListModel.get(position).getImage_url())
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(continentListModel == null){
            return 0;
        } else {
            return continentListModel.size();
        }
    }


    public class ContinentListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, number, total_country_number;
        ImageView image;

        public ContinentListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.continent_name);
            number = itemView.findViewById(R.id.continent_number);
            total_country_number = itemView.findViewById(R.id.total_country_number);
            image = itemView.findViewById(R.id.continent_image);
            name.setEnabled(false);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onContinentListItemClicked.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnContinentListItemClicked{
        public void onItemClicked(int position);
    }
}
