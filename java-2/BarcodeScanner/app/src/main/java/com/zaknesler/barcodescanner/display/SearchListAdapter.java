package com.zaknesler.barcodescanner.display;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zaknesler.barcodescanner.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>
{
    private Context context;
    private String[] items;

    public SearchListAdapter(Context context, String[] items)
    {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Create an inflater based on a single item layout.
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        // Set the text of the holder values.
        try {
            JSONObject object = new JSONObject(items[position]);

            DecimalFormat formatter = new DecimalFormat("0.00");

            holder.price.setText("$" + formatter.format(object.getDouble("salePrice")));
            holder.name.setText(object.getString("name"));
        } catch (JSONException exception) {}
    }

    @Override
    public int getItemCount()
    {
        return items.length;
    }

    // Create a view holder to house all of the text views.
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView price, name;

        public ViewHolder(View itemView)
        {
            super(itemView);

            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
        }
    }
}
