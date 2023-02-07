package edu.northeastern.numad23sp_neharamachandra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerLinkAdapter extends RecyclerView.Adapter<RecyclerLinkAdapter.ViewHolder> {
    Context context;
    ArrayList<LinkModel> arrLink;
    public RecyclerLinkAdapter(Context context, ArrayList<LinkModel> arrLink){
        this.context = context;
        this.arrLink = arrLink;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_link_adapter,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(arrLink.get(position).name);
        holder.textLink.setText(arrLink.get(position).link);
    }

    @Override
    public int getItemCount() {
        return arrLink.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textName, textLink;
        public ViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textLink = itemView.findViewById(R.id.textLink);
        }
    }

}
