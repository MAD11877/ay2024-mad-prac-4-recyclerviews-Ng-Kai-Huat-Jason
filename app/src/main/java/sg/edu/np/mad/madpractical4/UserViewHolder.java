package sg.edu.np.mad.madpractical4;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage;
    ImageView bigImage;
    TextView name;
    TextView description;
    TextView follow;

    public UserViewHolder(View itemView) {
        super(itemView);
        // Locations of image, name and description found in custom_activity_list.xml
        smallImage = itemView.findViewById(R.id.iv_Smallimage);
        name = itemView.findViewById(R.id.tvName);
        description = itemView.findViewById(R.id.tvDesc);
        bigImage = itemView.findViewById(R.id.ivBigImage);
        follow = itemView.findViewById(R.id.tvFollow);

    }
}
