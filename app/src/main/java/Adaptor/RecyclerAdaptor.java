package Adaptor;

import android.content.Context;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.get_a_ride.R;

import java.util.ArrayList;

import Model.Driver;


public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder> {
   ArrayList<Driver> profiles;
    Context context;
    public RecyclerAdaptor(Context c,ArrayList<Driver> p) {
        context= c;
        profiles=p;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.email.setText(profiles.get(position).getdEmail());
           holder.name.setText(profiles.get(position).getdName());
           holder.nic.setText(profiles.get(position).getdNic());
           holder.btn.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       // ImageView imageView;
        TextView name,nic,email;
        Button btn;
       // EditText n2a,e2a,nic2a;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.emailCV);
                name=itemView.findViewById(R.id.nameCV);
                nic=itemView.findViewById(R.id.nicCV);
                btn=(Button)itemView.findViewById(R.id.checkedDetails);


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, " is clicked", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
