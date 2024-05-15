package sg.edu.np.mad.madpractical4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Create 20 Random Users
        ArrayList<User> users = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int id = i + 1;
            String name = "Name" + generateRandomInteger();
            String description = "Description" + generateRandomInteger();
            boolean followed = random.nextBoolean();

            users.add(new User(name, description, id, followed));

            // Log each user information
            Log.d("ListActivity", "User " + (i + 1) + ": " +
                    "Name: " + name + ", " +
                    "Description: " + description + ", " +
                    "ID: " + id + ", " +
                    "Followed: " + followed);
        }
        //RecyclerView
        UserAdapter userAdapter = new UserAdapter(users);
        RecyclerView recyclerView = findViewById(R.id.rvUsers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

    }

    // Methods need to be outside the OnCreate

    // Method to generate random Integer;
    private int generateRandomInteger() {
        Random random = new Random();
        // return random.nextInt();
        return random.nextInt(900000) + 100000; // Generate a random integer between 100000 and 999999, to see layout
    }

}
