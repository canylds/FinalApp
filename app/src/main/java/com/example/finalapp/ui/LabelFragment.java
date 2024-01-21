package com.example.finalapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalapp.R;
import com.example.finalapp.models.LabelModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LabelFragment extends Fragment {
    EditText input_label, description_label;
    Button add_label;
    LinearLayout layout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_label, container, false);
        input_label = root.findViewById(R.id.et_input_label);
        description_label = root.findViewById(R.id.et_description_label);
        add_label = root.findViewById(R.id.btn_add_label);
        layout = root.findViewById(R.id.layout_label);

        add_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Send(view);
            }
        });
        Get_Layout();
        return  root;
    }

    public void Send(View v) {
        String str_input_label = input_label.getText().toString();
        String str_description_label = description_label.getText().toString();

        if (!str_input_label.isEmpty() && !str_description_label.isEmpty())
        {
            LabelModel labelModel = new LabelModel(str_input_label, str_description_label);
            db.collection("LabelModel").add(labelModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getContext(), "Label Başarıyla eklendi.", Toast.LENGTH_SHORT).show();
                    Get_Layout();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Bir hata ile karşılaşıldı.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(getContext(), "Boş alan girilemez.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Get_Layout()
    {
        layout.removeAllViews();
        db.collection("LabelModel").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        LinearLayout child = new LinearLayout(requireContext());
                        TextView label = new TextView(requireContext());
                        ImageView label_image = new ImageView(requireContext());
                        label_image.setImageResource(R.drawable.baseline_adb_24);
                        label.setText(queryDocumentSnapshot.getString("label"));
                        label.setTextSize(20);
                        child.addView(label);
                        child.addView(label_image);
                        layout.addView(child);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Labellar getirilemedi.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}