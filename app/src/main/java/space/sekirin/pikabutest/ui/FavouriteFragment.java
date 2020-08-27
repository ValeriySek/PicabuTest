package space.sekirin.pikabutest.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import space.sekirin.pikabutest.R;
import space.sekirin.pikabutest.adapters.ContentAdapter;
import space.sekirin.pikabutest.data.models.FavouritePostContent;
import space.sekirin.pikabutest.databinding.FragmentFavouriteBinding;
import space.sekirin.pikabutest.utils.InjectorUtils;
import space.sekirin.pikabutest.viewmodels.FavouriteFragmentViewModel;

public class FavouriteFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFavouriteBinding binding = FragmentFavouriteBinding.inflate(inflater, container, false);

        FavouriteFragmentViewModel viewModel = new ViewModelProvider(this,
                InjectorUtils.provideFavouriteFragmentViewModelFactory(getContext()))
                .get(FavouriteFragmentViewModel.class);

        final ContentAdapter contentAdapter = new ContentAdapter(getContext());

        binding.rvFavourite.setAdapter(contentAdapter);

        viewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<List<FavouritePostContent>>() {
            @Override
            public void onChanged(List<FavouritePostContent> favouritePostContents) {
                Log.i("Problem", "FavouriteFragment " + favouritePostContents);
                contentAdapter.setContents(favouritePostContents);
            }
        });

        return binding.getRoot();
    }
}