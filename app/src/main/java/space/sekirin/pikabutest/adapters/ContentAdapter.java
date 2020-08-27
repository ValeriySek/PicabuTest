package space.sekirin.pikabutest.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import space.sekirin.pikabutest.R;
import space.sekirin.pikabutest.data.FavouriteContentRepository;
import space.sekirin.pikabutest.data.models.PostContent;
import space.sekirin.pikabutest.databinding.ContentItemBinding;
import space.sekirin.pikabutest.ui.ViewPagerFragmentDirections;
import space.sekirin.pikabutest.utils.InjectorUtils;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<? extends PostContent> mContents = new ArrayList<>();
    private FavouriteContentRepository mRepository;


    public ContentAdapter(Context context) {
        mRepository = InjectorUtils.getFavouriteContentRepository(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.content_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostContent content = mContents.get(position);
        holder.bind(content);
        holder.bindFavouritePost(mRepository.isFavourite(content.getId()));

    }

    @Override
    public int getItemCount() {
        return mContents != null ? mContents.size() : 0;
    }

    public void setContents(List<? extends PostContent> contents) {
        Log.i("Problem", "ContentAdapter" + contents);
        mContents.clear();
        mContents = contents;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ContentItemBinding mBinding;

        public ViewHolder(final ContentItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

            Callback callback = new Callback() {
                @Override
                public void addOrDelete(PostContent content, boolean isFavourite) {
                    if(isFavourite){
                        mRepository.deleteFavouriteMovie(mRepository.getFavouritePostById(content.getId()));
                    } else {
                        mRepository.addToFavourite(content);
                    }
                }
            };
            binding.setCallback(callback);

            binding.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavDirections directions = ViewPagerFragmentDirections
                            .actionViewPagerFragmentToDetailFragment(binding.getContent().getId());
                    Navigation.findNavController(view).navigate(directions);
                }
            });
        }

        void bind(PostContent content){
            mBinding.setContent(content);
            Log.i("Problem", "ContentAdapter" + content);
            mBinding.executePendingBindings();
        }

        void bindFavouritePost(boolean isFavourite){
            mBinding.setIsFavourite(isFavourite);
            mBinding.executePendingBindings();
        }

    }

    public interface Callback{
        void addOrDelete(PostContent content, boolean isFavourite);
    }
}
