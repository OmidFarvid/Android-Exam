package com.example.omid.firstexam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omid.firstexam.POJO.Country;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private List<Country> lstCountries;
    private Context context;

    public CountriesAdapter(List<Country> listItems, Context context) {
        this.lstCountries = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country listItem = lstCountries.get(position);
        holder.txtIndex.setText(String.valueOf(position + 1));
        holder.txtIso.setText(listItem.getIso());
        holder.txtName.setText(listItem.getName());
        holder.txtPhone.setText(listItem.getPhone());
    }

    @Override
    public int getItemCount() {
        return lstCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIndex;
        public TextView txtIso;
        public TextView txtName;
        public TextView txtPhone;

        public ViewHolder(View itemView) {
            super(itemView);

            txtIndex = (TextView) itemView.findViewById(R.id.txtIndex);
            txtIso = (TextView) itemView.findViewById(R.id.txtIso);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPhone = (TextView) itemView.findViewById(R.id.txtPhone);

        }
    }
}
