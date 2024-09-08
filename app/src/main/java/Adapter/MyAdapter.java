package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.MyDatabaseHelper;
import com.example.contacts.R;

import java.util.List;

import Model.ListItem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<ListItem> listItems;
    MyDatabaseHelper MyDB;
    public MyAdapter(Context context, List listItem) {
        this.context=context;
        this.listItems=listItem;
        this.MyDB=new MyDatabaseHelper(context);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        ListItem item=listItems.get(position);
        holder.name.setText(item.getName());
        holder.number.setText(item.getNumber());
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition= holder.getAdapterPosition();
                MyDB.deleteData(item.getName());
                listItems.remove(currentPosition);
                notifyItemRemoved(currentPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView number;
        private ImageButton delete_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.contact_name);
            number=itemView.findViewById(R.id.contact_number);
            delete_btn=itemView.findViewById(R.id.delete_btn);
        }

        @Override
        public void onClick(View view) {
        int position=getAdapterPosition();
        ListItem item=listItems.get(position);
        Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+item.getNumber()));
        context.startActivity(intent);
        }
    }

}
