package businesslayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryproject.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList bookName, issuedOn, returnedOn, dueDate;

    public HistoryAdapter(Context context, ArrayList nameId, ArrayList emailId, ArrayList ageId, ArrayList dueDate) {
        this.context = context;
        this.bookName = nameId;
        this.issuedOn = emailId;
        this.returnedOn = ageId;
        this.dueDate= dueDate;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_history, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bookName.setText(String.valueOf(bookName.get(position)));
        holder.issuedOn.setText(String.valueOf(issuedOn.get(position)));
        holder.returnedOn.setText(String.valueOf(returnedOn.get(position)));
        holder.dueDate.setText(String.valueOf(dueDate.get(position)));
    }

    @Override
    public int getItemCount() {
        return bookName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookName, issuedOn, returnedOn, dueDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName=itemView.findViewById(R.id.cardTxtHistoryBook);
            issuedOn=itemView.findViewById(R.id.cardTxtHistoryIssuedOn);
            returnedOn=itemView.findViewById(R.id.cardTxtReturnedOn);
            dueDate=itemView.findViewById(R.id.cardTxtHistoryDueDate);
        }
    }

}
