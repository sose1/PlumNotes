package com.example.sose1.plumnotes.note;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sose1.plumnotes.activity.EditNoteActivity;
import com.example.sose1.plumnotes.database.DBHelper;
import com.example.sose1.plumnotes.R;


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


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.poupup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.one:
                                Toast.makeText(context,"Edytowanie",Toast.LENGTH_SHORT).show();
                                actionWhenClickEdit(holder, note);
                                break;

                            case R.id.two:
                                Toast.makeText(context,"Usuwanie", Toast.LENGTH_SHORT).show();
                                actionWhenClickDelete(note);
                                break;
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Note.noteArrayList(context).size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteContent;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteContent = itemView.findViewById(R.id.note_content);
            cardView = itemView.findViewById(R.id.card_view_note);
        }

    }


    public void actionWhenClickDelete(final Note note){
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

    public void actionWhenClickEdit(ViewHolder holder, Note note){
        Intent intent = new Intent(context, EditNoteActivity.class);

        String title = holder.noteTitle.getText().toString();
        String content = holder.noteContent.getText().toString();
        int ID = note.getID();

        intent.putExtra("ID", ID);
        intent.putExtra("title", title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
}