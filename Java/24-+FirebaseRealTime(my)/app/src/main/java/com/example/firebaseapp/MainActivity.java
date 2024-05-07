package com.example.firebaseapp;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<User> usersFillUpDB;
    private UserAdapter userAdapter;
    private ArrayList<User> users;
    private RecyclerView recyclerView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView text = findViewById(R.id.TextView);
        TextView name = findViewById(R.id.name);
        TextView group = findViewById(R.id.group);
        users = new ArrayList<>();
        usersFillUpDB = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("Users");
        FillUpDb();
        usersRef.setValue(usersFillUpDB);

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        userAdapter = new UserAdapter(this, users);
        recyclerView.setAdapter(userAdapter);




        DatabaseReference ref = database.getReference("msg");
        DatabaseReference refObject = database.getReference("User");
        User user1 = new User("Jack", "456 235 678", "Owner");
        refObject.setValue(user1);
        ref.setValue("Hello");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                text.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        refObject.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User value = snapshot.getValue(User.class);
                name.setText(value.getName());
                group.setText(value.getGroup());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FirestoreActivity.class);
                startActivity(i);
            }
        });
    }

    private void FillUpDb() {
        User user1 = new User("Marko", "556 234 567", "Friends");
        User user2 = new User("Rafael", "567 945 234", "Friends");
        User user3 = new User("Tomas", "879 087 123", "Friends");
        User user4 = new User("Jack", "945 867 456", "Boss");
        usersFillUpDB.add(user1);
        usersFillUpDB.add(user2);
        usersFillUpDB.add(user3);
        usersFillUpDB.add(user4);
    }


}