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

public class CountryListAdapter extends RecyclerView.Adapter <CountryListAdapter.CountryListViewHolder> {

    private List<CountryListModel> countryListModel;
    private OnCountryListItemClicked onCountryListItemClicked;

    public CountryListAdapter(OnCountryListItemClicked onCountryListItemClicked) {
        this.onCountryListItemClicked = onCountryListItemClicked;
    }

    public void setCountryListsModels(List<CountryListModel> countryListModel) {
        this.countryListModel = countryListModel;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_single_item, parent, false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.name.setText(countryListModel.get(position).getName());
        holder.number.setText( countryListModel.get(position).getNumber());

        Glide
                .with(holder.itemView.getContext())
                .load(countryListModel.get(position).getImage_url())
                .centerCrop()
                .placeholder(R.drawable.placeholder_image)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(countryListModel == null){
            return 0;
        } else {
            return countryListModel.size();
        }
    }


    public class CountryListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, number, link;
        ImageView image;

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.country_name);
            number = itemView.findViewById(R.id.country_number);
            image = itemView.findViewById(R.id.country_image);

            name.setEnabled(false);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onCountryListItemClicked.onItemClicked(getAdapterPosition());
        }
    }

    public interface OnCountryListItemClicked{
        public void onItemClicked(int position);
    }
}
