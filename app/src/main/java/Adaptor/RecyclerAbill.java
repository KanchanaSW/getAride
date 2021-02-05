package Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.get_a_ride.R;

import java.util.ArrayList;

import Model.RideStatus;

public class RecyclerAbill extends RecyclerView.Adapter<RecyclerAbill.ViewHolder> {
    ArrayList<RideStatus> list;
    Context context;

    public RecyclerAbill(ArrayList<RideStatus> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAbill.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cardviewbill,parent,false);
        RecyclerAbill.ViewHolder viewHolder=new RecyclerAbill.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAbill.ViewHolder holder, int position) {
// holder.dateandtime.setText(list.get(position).getCurrentDateandTime());
        holder.name.setText(list.get(position).getCustomerName());
        holder.mileage.setText(list.get(position).getMileage());
        holder.phone.setText(list.get(position).getPhone());
        holder.price.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView phone, mileage, price, name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.email);
            mileage = itemView.findViewById(R.id.mileage);
            price = itemView.findViewById(R.id.price);
        }
    }
}
