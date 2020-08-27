package space.sekirin.pikabutest.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import space.sekirin.pikabutest.databinding.FragmentViewPagerBinding;
import space.sekirin.pikabutest.adapters.SectionsPagerAdapter;

public class ViewPagerFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentViewPagerBinding binding = FragmentViewPagerBinding.inflate(inflater, container, false);

        final TabLayout tabLayout = binding.tabs;
        ViewPager2 viewPager = binding.viewPager;
        viewPager.setAdapter(new SectionsPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(getTabTitle(position));
            }
        }).attach();

        return binding.getRoot();
    }

    private String getTabTitle(int position){
        switch (position){
            case 0:
                return "Lenta";
            case 1:
                return "Favourite";
        }
        return null;
    }
}