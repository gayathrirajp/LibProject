package adapterlayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryproject.R;

import java.util.ArrayList;

public class FindBookAdapter extends RecyclerView.Adapter<FindBookAdapter.MyViewHolder> {
    private Context context;
    private ArrayList bookName, author, copiesAvailable;

    public FindBookAdapter(Context context, ArrayList bookName, ArrayList author, ArrayList copiesAvailable) {
        this.context = context;
        this.bookName = bookName;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_find_book, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bookName.setText(String.valueOf(bookName.get(position)));
        holder.author.setText(String.valueOf(author.get(position)));
        holder.copiesAvailable.setText(String.valueOf(copiesAvailable.get(position)));
    }

    @Override
    public int getItemCount() {
        return bookName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookName, author, copiesAvailable;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName=itemView.findViewById(R.id.cardTxtBookName);
            author=itemView.findViewById(R.id.cardTxtAuthor);
            copiesAvailable=itemView.findViewById(R.id.cardTxtCopiesAvailable);
        }
    }

}

