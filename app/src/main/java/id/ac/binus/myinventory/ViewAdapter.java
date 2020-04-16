package id.ac.binus.myinventory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {


    public ViewAdapter(ArrayList<Item> objItemArrayList) {
        this.objItemArrayList = objItemArrayList;
    }

    ArrayList<Item> objItemArrayList;
    @NonNull
    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.view_adapter,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewAdapter.MyViewHolder holder, int position) {
        final Item item = objItemArrayList.get(position);


        holder.tvName.setText(item.getName());
        holder.tvQuantity.setText("Quantity : "+item.getQuantity());
        String description="";
        if(item.getDescription().length()>100)
        {
            description=item.getDescription();
            holder.tvDescription.setText(description.substring(0,100)+"...");
        }
        else
        {
            description=item.getDescription();
            holder.tvDescription.setText(description);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddActivity.class);
                Bundle extras = new Bundle();
                extras.putLong("id",objItemArrayList.get(holder.getAdapterPosition()).getId());
                extras.putString("name",objItemArrayList.get(holder.getAdapterPosition()).getName());
                extras.putString("description",objItemArrayList.get(holder.getAdapterPosition()).getDescription());
                extras.putString("quantity",objItemArrayList.get(holder.getAdapterPosition()).getQuantity());
                intent.putExtras(extras);
//                Toast.makeText(holder.itemView.getContext(),item.getName().length(),Toast.LENGTH_SHORT).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objItemArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvQuantity,tvDescription;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvName= itemView.findViewById(R.id.name);
            tvDescription = itemView.findViewById(R.id.description);
            tvQuantity = itemView.findViewById(R.id.quantity);
        }
    }
}
