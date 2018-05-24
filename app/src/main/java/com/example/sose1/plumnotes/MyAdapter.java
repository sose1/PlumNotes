package com.example.sose1.plumnotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private MyAdapter myAdapter;
    DBHelper dbHelper;


    public MyAdapter(Context context) {
        this.context = context;
        this.myAdapter = this;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dbHelper = new DBHelper(context);
        final Note note = Note.noteList(context).get(position);

        holder.noteTitle.setText(note.getTitle());
        holder.noteContent.setText(note.getContent());

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
        return Note.noteList(context).size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView noteTitle, noteContent;
        Button noteDeleteButton;


        ViewHolder(View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteContent = itemView.findViewById(R.id.note_content);
            noteDeleteButton = itemView.findViewById(R.id.note_delete_button);
        }
    }


}