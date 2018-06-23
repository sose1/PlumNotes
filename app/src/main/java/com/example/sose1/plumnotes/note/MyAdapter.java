package com.example.sose1.plumnotes.note;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sose1.plumnotes.database.DBHelper;
import com.example.sose1.plumnotes.R;
import com.example.sose1.plumnotes.activity.EditNoteActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private MyAdapter myAdapter;
    private ArrayList<Note> noteArrayList;
    DBHelper dbHelper;


    public MyAdapter(Context context, ArrayList<Note> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;
        this.myAdapter = this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        dbHelper = new DBHelper(context);
        final Note note = Note.noteArrayList(context).get(position);

        holder.cardView.setCardBackgroundColor(note.getColor());

        holder.noteTitle.setText(note.getTitle());
        holder.noteContent.setText(note.getContent());


        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, EditNoteActivity.class);

                String title = holder.noteTitle.getText().toString();
                String content = holder.noteContent.getText().toString();
                int ID = note.getID();

                intent.putExtra("ID", ID);
                intent.putExtra("title", title);
                intent.putExtra("content",content);
                context.startActivity(intent);
                return true;
            }
        });

        holder.noteDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle(context.getString(R.string.note_deletion));
                alertDialog.setMessage(context.getString(R.string.are_you_sure));
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.delNote(note.getID());
                        myAdapter.notifyDataSetChanged();
                    }
                });
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Note.noteArrayList(context).size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteContent;
        Button noteDeleteButton;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteContent = itemView.findViewById(R.id.note_content);
            noteDeleteButton = itemView.findViewById(R.id.note_delete_button);
            cardView = itemView.findViewById(R.id.card_view_note);
        }

    }

}