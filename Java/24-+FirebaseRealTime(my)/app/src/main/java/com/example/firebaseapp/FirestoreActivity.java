package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirestoreActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText phoneNumberET;
    private EditText groupET;
    private Button saveBtn;
    private Button readBtn;
    private Button updateBtn;
    private Button deleteBtn;
    TextView textView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db.collection("Users").document("jdlCETyhhOxqmOILhrjv");
    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore);

        nameET = findViewById(R.id.Name);
        phoneNumberET = findViewById(R.id.PhoneNumber);
        groupET = findViewById(R.id.Group);
        saveBtn = findViewById(R.id.Save);
        readBtn = findViewById(R.id.Read);
        updateBtn = findViewById(R.id.Update);
        deleteBtn = findViewById(R.id.Delete);
        textView = findViewById(R.id.textView);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDataToNewDocument();
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllDocumentsInCollection();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSpecificDocument();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteAll();
            }
        });


    }



    private void SaveDataToNewDocument(){
        String name = nameET.getText().toString();
        String phoneNumber = phoneNumberET.getText().toString();
        String group = groupET.getText().toString();

        User user = new User(name, phoneNumber, group);
        collectionReference.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                textView.setText("Save test");
                nameET.setText("");
                phoneNumberET.setText("");
                groupET.setText("");
            }
        });
    }

    private void GetAllDocumentsInCollection(){
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String data = "";

                // This code is executed when data retrieval is successful
                // the queryDocumentSnapshot contains the documents in the collection
                // Each queryDocumentSnapshot ---> represents a document in the collection
                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots){

                    // Transforming snapshots into objects
                    User user = snapshot.toObject(User.class);

                    data += "Name: "+user.getName() + " Phone Number: "+user.getPhoneNumber() + "Group: "+user.getGroup() + "\n";
                }
                textView.setText(data);
            }
        });
    }

    private void UpdateSpecificDocument(){
        String name = nameET.getText().toString();
        String phoneNumber = phoneNumberET.getText().toString();
        String group = groupET.getText().toString();

        userRef.update("name" , name).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                nameET.setText("");
            }
        });
        userRef.update("phoneNumber" , phoneNumber);
        userRef.update("group", group);
        phoneNumberET.setText("");
        groupET.setText("");

    }

    private void DeleteAll(){
        userRef.delete();
    }
}