package space.sekirin.pikabutest.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import space.sekirin.pikabutest.ui.FavouriteFragment;
import space.sekirin.pikabutest.ui.PikabuFragment;

public class SectionsPagerAdapter extends FragmentStateAdapter {


    public SectionsPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PikabuFragment();
            case 1:
                return new FavouriteFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}