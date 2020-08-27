package space.sekirin.pikabutest.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import space.sekirin.pikabutest.adapters.ImageAdapter;
import space.sekirin.pikabutest.data.models.PostContent;
import space.sekirin.pikabutest.databinding.FragmentDetailBinding;
import space.sekirin.pikabutest.utils.InjectorUtils;
import space.sekirin.pikabutest.viewmodels.DetailFragmentViewModel;

public class DetailFragment extends Fragment {

    private DetailFragmentViewModel viewModel;
    private FragmentDetailBinding binding;
    private ImageAdapter mImageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int postId = DetailFragmentArgs.fromBundle(getArguments()).getPostId();
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this,
                InjectorUtils.provideDetailFragmentViewModelFactory(getContext(), postId))
                .get(DetailFragmentViewModel.class);

        mImageAdapter = new ImageAdapter();
        binding.rvImages.setAdapter(mImageAdapter);

        viewModel.getPost().observe(getViewLifecycleOwner(), new Observer<PostContent>() {
            @Override
            public void onChanged(PostContent postContent) {
                binding.setContent(postContent);
                mImageAdapter.setStrings(postContent.getImages());
            }
        });

        refresh();
        setCallback();

        return binding.getRoot();
    }

    private void refresh(){
        viewModel.isFavourite().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.setIsFavourite(aBoolean);
            }
        });
    }

    private void setCallback(){
        Callback callback = new Callback() {
            @Override
            public void addOrDelete(PostContent content, boolean isFavourite) {
                if(isFavourite){
                    viewModel.deleteFromFavourite();
                } else {
                    viewModel.addToFavourite(content);
                }
                refresh();
            }
        };
        binding.setCallback(callback);
    }

    public interface Callback{
        void addOrDelete(PostContent content, boolean isFavourite);
    }
}