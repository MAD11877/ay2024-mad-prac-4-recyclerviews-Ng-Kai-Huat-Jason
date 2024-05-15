package sg.edu.np.mad.madpractical4;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnMessage = findViewById(R.id.btnMessage);

        // Get User from Serializable Intent & Set TextViews
        User ClickedUser = (User) getIntent().getSerializableExtra("ClickedUser");
        tvName.setText(ClickedUser.getName());
        tvDescription.setText(ClickedUser.getDescription());
        btnMessage.setText("Message");

        btnFollow.setText(ClickedUser.followed ? "Unfollow" : "Follow");

        btnFollow.setOnClickListener((view -> {
            //Toggle Follow State
            ClickedUser.setFollowed(!ClickedUser.getFollowed());

            if (ClickedUser.followed) {
                btnFollow.setText("Unfollow");
            } else {
                btnFollow.setText("Follow");
            }

            String toastMessage = ClickedUser.followed ? "Followed" : "Unfollowed";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }));

    }
}
