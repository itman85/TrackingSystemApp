package phannguyen.com.trackingsystem.ui;

import android.support.v4.app.Fragment;

public interface NavigationHost {
    //https://medium.com/@bherbst/managing-the-fragment-back-stack-373e87e4ff62
    void navigateTo(Fragment fragment, boolean addToBackstack);
}
