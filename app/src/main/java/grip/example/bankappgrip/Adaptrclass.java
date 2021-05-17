package grip.example.bankappgrip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptrclass extends RecyclerView.Adapter<Adaptrclass.ViewHolder>{
    ArrayList<ModelCustomer>customerz;
    itemClicked activity;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.onerowlayout,parent,false);
        return new ViewHolder((v));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(customerz.get(position));
        holder.id.setText(String.valueOf(position+1));
        holder.customerName.setText(customerz.get(position).getName());
        holder.bal.setText(String.valueOf(customerz.get(position).getBal()));
    }

    @Override
    public int getItemCount() {
        return customerz.size();
    }

    public interface itemClicked{
        Void itemOnClicked(int index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,customerName,bal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName=(TextView)itemView.findViewById(R.id.name);
            bal=(TextView)itemView.findViewById(R.id.bal);
            id=(TextView)itemView.findViewById(R.id.number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.itemOnClicked(customerz.indexOf(v.getTag()));

                }
            });
        }
    }


    public Adaptrclass(ArrayList<ModelCustomer> customers, itemClicked activity) {
        this.customerz = customers;
        this.activity = activity;
    }




}
