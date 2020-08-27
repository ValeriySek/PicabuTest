package space.sekirin.pikabutest.adapters;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import space.sekirin.pikabutest.R;

public class BindingAdapters {

    @BindingAdapter("setImages")
    public static void setImages(LinearLayout view, List<String> imageUrls){
        if(imageUrls == null || imageUrls.size() < 1){
            return;
        }
        for(int i = 0; i < imageUrls.size(); i++){
            Log.i("Problems", "Bind " + imageUrls);
            if(imageUrls.get(i) != null && !imageUrls.get(i).isEmpty()) {
                ImageView imageView = new ImageView(view.getContext().getApplicationContext());
                Picasso.get()
                        .load(imageUrls.get(i))
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView);
                view.addView(imageView);
            }
        }
    }
    @BindingAdapter("setImage")
    public static void setImage(ImageView view, String url){
        if(url != null && !url.isEmpty()){
            Picasso.get().load(url).into(view);
        }

    }
}
