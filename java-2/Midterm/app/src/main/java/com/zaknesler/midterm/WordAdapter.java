package com.zaknesler.midterm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>
{
    private Context context;
    private String[] items;

    public WordAdapter(Context context, String[] items)
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

            holder.word.setText(object.getString("item"));
            holder.type.setText(object.getString("pos"));
            holder.weight.setText(object.getString("weight"));
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
        public TextView word, type, weight;

        public ViewHolder(View itemView)
        {
            super(itemView);

            word = itemView.findViewById(R.id.word);
            type = itemView.findViewById(R.id.type);
            weight = itemView.findViewById(R.id.weight);
        }
    }
}
