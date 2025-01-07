package com.example.mystudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class EtudiantListAdapter extends BaseAdapter {
    private Context context;
    private List<Etudiant> etudiantList;

    public EtudiantListAdapter(Context context, List<Etudiant> etudiantList) {
        this.context = context;
        this.etudiantList = etudiantList;
    }

    @Override
    public int getCount() {
        return etudiantList.size();
    }

    @Override
    public Object getItem(int position) {
        return etudiantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rawdisplay, parent, false);
        }


        TextView studentFirstName = convertView.findViewById(R.id.studentFirstName);
        TextView studentLastName = convertView.findViewById(R.id.studentLastName);
        TextView studentEmail = convertView.findViewById(R.id.studentEmail);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);


        Etudiant etudiant = etudiantList.get(position);


        studentFirstName.setText(etudiant.getNom());  // Assuming `getNom` returns the first name
        studentLastName.setText(etudiant.getPrenom());  // Assuming `getPrenom` returns the last name
        studentEmail.setText(etudiant.getEmail());


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Confirm deletion
                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete this student?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Remove the student from the list and update the view
                                etudiantList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


        return convertView;
    }
}
