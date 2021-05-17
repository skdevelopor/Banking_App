package grip.example.bankappgrip;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ZtransactionAdapter extends RecyclerView.Adapter<ZtransactionAdapter.viewholder> {
    ArrayList<Zmodel> trans;
    ZtransactionAdapter.itemClicked activity;



  public interface itemClicked{
      Void itemOnClicked(int index);
  }




    public ZtransactionAdapter(ArrayList<Zmodel> trans, ZtransactionAdapter.itemClicked activity) {
        this.trans = trans;
        this.activity = activity;
    }






    public class viewholder extends RecyclerView.ViewHolder{
        TextView sname,rname,amt;
        ImageView arrow;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            sname = itemView.findViewById(R.id.sendersname);
            rname = itemView.findViewById(R.id.recieversname);
            amt =itemView.findViewById(R.id.amount);

            arrow = itemView.findViewById(R.id.arrow);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.itemOnClicked(trans.indexOf(v.getTag()));
                }
            });


        }
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ztranshis,parent,false);
        return new viewholder((v));

    }








    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.itemView.setTag(trans.get(position));
        holder.sname.setText(trans.get(position).getSendName());
        holder.rname.setText(trans.get(position).getRecName());
        holder.amt.setText(String.valueOf(trans.get(position).getAmount()));
        if(trans.get(position).getTransaction()==true){
            holder.arrow.setImageResource(R.drawable.rarrow);

        }
        else
        {
            holder.arrow.setImageResource(R.drawable.cancel);

        }


    }









    @Override
    public int getItemCount() {
        return trans.size();
    }
}
