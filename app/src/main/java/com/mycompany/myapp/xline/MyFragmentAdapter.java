package com.mycompany.myapp.xline;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Paul on 11/23/2017.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    private static int positio;
//   private List<Fragment> fragments;
    public MyFragmentAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
              return new Design();
            case 1:
                return new Calculation();
            case 2:
                return new Learn();

            case 6:
//                return new Second_inner_fragment();
        }
        return null;
    }
//  public void setItem(Fragment a, Fragment b , Fragment c){
//
//  }
    @Override
    public int getCount(){
     return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
switch (position){
    case 0: return "All";
    case 1: return "Answered";
    case 2: return "UnAnswered";
//    case 4: return "Interact";
//    case 5: return "Spicer";
//    case 6: return "Spicenext";
}
        return null;
    }

}
