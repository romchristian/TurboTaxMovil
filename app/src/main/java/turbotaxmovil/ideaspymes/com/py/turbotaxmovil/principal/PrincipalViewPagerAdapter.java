package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.principal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import turbotaxmovil.ideaspymes.com.py.turbotaxmovil.fragments.PieFragment;

/**
 * Created by christian.romero on 22/06/2017.
 */

public class PrincipalViewPagerAdapter extends FragmentPagerAdapter{

    public PrincipalViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment currentFragment = null;
        switch (position) {
            case 0:

                currentFragment = new PieFragment();
                break;
            case 1:

                currentFragment = new EgresosFragment();
                break;
        }

        return currentFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Ingresos";
            case 1:
                return "Egresos";

        }
        return null;
    }
}
