package space.sekirin.pikabutest.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import space.sekirin.pikabutest.adapters.ContentAdapter;
import space.sekirin.pikabutest.data.models.PostContent;
import space.sekirin.pikabutest.databinding.FragmentPikabuBinding;
import space.sekirin.pikabutest.utils.InjectorUtils;
import space.sekirin.pikabutest.viewmodels.PikabuFragmentViewModel;

public class PikabuFragment extends Fragment {

    private ContentAdapter mContentAdapter;
    private PikabuFragmentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPikabuBinding binding = FragmentPikabuBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this,
                InjectorUtils.providePikabuFragmentViewModelFactory(getContext())).get(PikabuFragmentViewModel.class);

        mContentAdapter = new ContentAdapter(getContext());
        binding.rvContent.setAdapter(mContentAdapter);

        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getContent().observe(getViewLifecycleOwner(), new Observer<List<PostContent>>() {
            @Override
            public void onChanged(List<PostContent> postContents) {
                Log.i("Problem", "PikabuFragment" + postContents);
                mContentAdapter.setContents(postContents);
            }
        });
    }
}

















