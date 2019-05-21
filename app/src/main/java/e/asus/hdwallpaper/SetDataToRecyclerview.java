package e.asus.hdwallpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 21-May-19.
 */

public class SetDataToRecyclerview extends RecyclerView.Adapter<SetDataToRecyclerview.Vholder> {

    List<Example> hits;
    Context context;

    SetDataToRecyclerview(List<Example> hits){
        this.hits = hits;

    }

    @Override
    public Vholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_item,null);
        context = parent.getContext();
        return (new Vholder(v));
    }

    @Override
    public void onBindViewHolder(Vholder holder, int position) {
        holder.setdatatview(position);
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    class Vholder extends RecyclerView.ViewHolder{
        ImageView i1;
        TextView v1;
        int positions;
        public Vholder(View itemView) {
            super(itemView);

            i1 = itemView.findViewById(R.id.image);
            v1 = itemView.findViewById(R.id.tags);

//            itemView.setOnClickListener(this);
        }

        public void setdatatview(int position) {
            positions = position;

            v1.setText(hits.get(position).getTitle());

//            Picasso.with(i1.getContext()).load(hits.get(position).getWebformatURL()).into(i1);
        }

//        @Override
//        public void onClick(View view) {
//            Intent i = new Intent(context,Show_Full_Data.class);
//            i.putExtra("Tags",hits.get(positions).getTags());
//            i.putExtra("Comments",hits.get(positions).getComments());
//            i.putExtra("ImageUrl",hits.get(positions).getLargeImageURL());
//            context.startActivity(i);
//        }
    }
}
