package Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.get_a_ride.R;

import java.util.ArrayList;

import Model.LogStatus;

public class RecyclerAdStatus extends RecyclerView.Adapter<RecyclerAdStatus.ViewHolder> {
    ArrayList<LogStatus> list;
    Context context;

    public RecyclerAdStatus(ArrayList<LogStatus> l, Context c) {
        list = l;
        context = c;
    }

    @NonNull
    @Override
    public RecyclerAdStatus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardvstatus, parent, false);
        RecyclerAdStatus.ViewHolder viewHolder = new RecyclerAdStatus.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdStatus.ViewHolder holder, int position) {
        holder.dateandtime.setText(list.get(position).getCurrentDateandTime());
        holder.status.setText(list.get(position).getStatus());
        holder.userId.setText(list.get(position).getUserId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, dateandtime, userId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status);
            dateandtime = itemView.findViewById(R.id.dateandtime);
            userId = itemView.findViewById(R.id.userId);

        }
    }
}
