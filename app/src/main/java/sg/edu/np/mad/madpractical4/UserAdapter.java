package sg.edu.np.mad.madpractical4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    // This ArrayList holds the list of User objects to be displayed in the RecyclerView
    private ArrayList<User> list_objects;

    // Constructor to initialize the adapter with the list of User objects
    public UserAdapter(ArrayList<User> list_objects) {
        this.list_objects = list_objects;
    }

    // This method is called by the RecyclerView layout manager to create new views for each user
    // in the list. It inflates the custom_activity_list.xml layout for each user item.
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(view); // Create a new UserViewHolder object
    }

    // This method is called by the RecyclerView to bind the data for each user (at a specific position)
    // to the corresponding view holder. It retrieves the User object at the given position from the list
    // and sets the user's name to the TextView in the view holder. It also sets an empty click listener
    // for the small image (you can customize this later).
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        User user = list_objects.get(position); // Get the User object at the current position

        holder.name.setText(user.getName()); // Set the user's name to the TextView in the view holder

        holder.description.setText(user.getDescription()); // Set the user's description to the// TextView in the view holder

        holder.follow.setText(user.getFollowed() ? "Following" : "Not Following");

        // Get the last character of the user's name
        String lastChar = user.getName().substring(user.getName().length() - 1);

        // Check if the last character is the string "7"
        if (lastChar.equals("7")) {
            // Show big image
            holder.smallImage.setVisibility(View.GONE);
            holder.bigImage.setVisibility(View.VISIBLE);
        } else {
            // Show small image
            holder.smallImage.setVisibility(View.VISIBLE);
            holder.bigImage.setVisibility(View.GONE);
        }

        // Set On Click Listeners for the images
        holder.smallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log a message (optional)
                Log.d("UserAdapter", "Small image clicked for user: " + user.getName());

                // Create and show alert dialog for small image
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile ( Small Image )");
                builder.setMessage("You clicked the small image for user: " + user.getName());

                int clickeduserid = user.getID(); // Get clicked user ID

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Get User object based on position in list
                        User ClickedUser = null;
                        for( User user : list_objects){
                            if(user.getID() == clickeduserid){
                                ClickedUser = user;
                                break;
                            }
                        }
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra("ClickedUser",ClickedUser);
                        v.getContext().startActivity(intent);
                    }
                });

                builder.setNegativeButton("Close", null); // Add negative button
                builder.create().show();
            }
        });

        holder.bigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log a message when small image is clicked
                Log.d("UserAdapter", "Big image clicked for user: " + user.getName());

                // Create and show alert dialog for small image
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile ( Big Image )");
                builder.setMessage("You clicked the big image for user: " + user.getName());
                builder.setPositiveButton("View", null); // Set a neutral button
                builder.setNegativeButton("Close", null); // Add negative button
                builder.create().show();
            }
        });
    }

    // This method returns the total number of user items in the list
    @Override
    public int getItemCount() {
        return list_objects.size();
    }
}
