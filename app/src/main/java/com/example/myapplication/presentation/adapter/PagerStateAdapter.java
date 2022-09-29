package com.example.myapplication.presentation.adapter;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagerStateAdapter extends FragmentStateAdapter {
    private final Map<Integer, Fragment> fragmentMap;
    private final List<Class<? extends Fragment>> fragments;
    private final List<Bundle> bundles;
    public final List<String> titles;
    private FragmentActivity fragmentActivity;

    public PagerStateAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
        this.fragmentMap = new HashMap();
        this.fragments = new ArrayList();
        this.bundles = new ArrayList();
        this.titles = new ArrayList();
    }

    public void addFragment(Class<? extends Fragment> clazz) {
        this.fragments.add(clazz);
    }

    public void addFragment(Class<? extends Fragment> clazz, Bundle bundle) {
        this.fragments.add(clazz);
        this.bundles.add(bundle);
    }

    public void addFragment(Class<? extends Fragment> clazz, String title) {
        this.fragments.add(clazz);
        this.titles.add(title);
    }

    public void addFragment(Class<? extends Fragment> clazz, String title, Bundle bundle) {
        this.fragments.add(clazz);
        this.titles.add(title);
        this.bundles.add(bundle);
    }

    public void changeTitle(int position, String title) {
        this.titles.set(position, title);
        this.notifyDataSetChanged();
    }

    public void removeFragment(int position) {
        if (!this.fragments.isEmpty() && this.fragments.size() > position) {
            this.fragments.remove(position);
            this.fragmentMap.remove(position);
            this.notifyDataSetChanged();
        }

    }

    public Fragment fetchFragment(int position) {
        return this.fragmentMap.containsKey(position) ? (Fragment)this.fragmentMap.get(position) : null;
    }

    @NonNull
    public Fragment createFragment(int position) {
        Fragment fragment = null;

        try {
            Method m = ((Class)this.fragments.get(position)).getDeclaredMethod("newInstance");
            fragment = (Fragment)m.invoke((Object)null);
            if (this.bundles.size() > position) {
                assert fragment != null;

                fragment.setArguments((Bundle)this.bundles.get(position));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException var4) {
            var4.printStackTrace();
        }

        assert fragment != null;

        this.fragmentMap.put(position, fragment);
        return fragment;
    }

    public int getItemCount() {
        return this.fragments.size();
    }
}
