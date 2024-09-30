package com.example.cronoswim.main;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.cronoswim.R;
import com.example.cronoswim.fragmentos.HomeFragment;
import com.example.cronoswim.fragmentos.consultas.ConsultarTiemposFragment;
import com.example.cronoswim.fragmentos.cronometro.CronoFragment;
import com.example.cronoswim.fragmentos.registrar.RegisterViewFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int[] TAB_ICONS = new int[]{
            R.drawable.iconhome,
            R.drawable.iconcrono,
            R.drawable.iconadd,
    };

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CronoFragment();
            case 2:
                return new RegisterViewFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null; // No necesitas títulos de texto, ya que estás usando iconos
    }

    @Override
    public int getCount() {
        return TAB_ICONS.length;
    }

    public int getTabIcon(int position) {
        return TAB_ICONS[position];
    }
}

