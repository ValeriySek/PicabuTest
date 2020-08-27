package space.sekirin.pikabutest.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.sekirin.pikabutest.R;
import space.sekirin.pikabutest.databinding.ImageItemBinding;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> mStrings = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.image_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mStrings != null && mStrings.size() > 0){
            holder.bind(mStrings.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mStrings != null ? mStrings.size() : 0;
    }

    public void setStrings(List<String> strings) {
        Log.i("Problem", "ImageAdapter " + strings);
        mStrings = strings;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageItemBinding mBinding;

        public ViewHolder(ImageItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(String url){
            Log.i("Problem", "ImageAdapter " + url);
            mBinding.setImageUrl(url);
            mBinding.executePendingBindings();
        }
    }
}
