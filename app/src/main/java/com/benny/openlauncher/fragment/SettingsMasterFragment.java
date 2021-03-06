package com.benny.openlauncher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.Preference;

import com.benny.openlauncher.R;
import com.benny.openlauncher.activity.HomeActivity;
import com.benny.openlauncher.activity.MoreInfoActivity;
import com.benny.openlauncher.util.AppSettings;

import net.gsantner.opoc.util.ContextUtils;

import java.util.Locale;

import static com.benny.openlauncher.widget.AppDrawerController.DrawerMode.HORIZONTAL_PAGED;
import static com.benny.openlauncher.widget.AppDrawerController.DrawerMode.VERTICAL;

public class SettingsMasterFragment extends SettingsBaseFragment {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences_master);
    }

    @Override
    public void onResume() {
        super.onResume();

        Preference categoryDesktop = findPreference(getString(R.string.pref_key__cat_desktop));
        Preference categoryDock = findPreference(getString(R.string.pref_key__cat_dock));
        Preference categoryAppDrawer = findPreference(getString(R.string.pref_key__cat_app_drawer));
        Preference categoryAppearance = findPreference(getString(R.string.pref_key__cat_appearance));

        categoryDesktop.setSummary(String.format(Locale.ENGLISH, "%s: %d x %d", getString(R.string.pref_title__size), AppSettings.get().getDesktopColumnCount(), AppSettings.get().getDesktopRowCount()));
        categoryDock.setSummary(String.format(Locale.ENGLISH, "%s: %d", getString(R.string.pref_title__size), AppSettings.get().getDockSize()));
        categoryAppearance.setSummary(String.format(Locale.ENGLISH, "Icons: %ddp", AppSettings.get().getIconSize()));

        switch (AppSettings.get().getDrawerStyle()) {
            case HORIZONTAL_PAGED:
                categoryAppDrawer.setSummary(String.format("%s: %s", getString(R.string.pref_title__style), getString(R.string.horizontal_paged_drawer)));
                break;
            case VERTICAL:
                categoryAppDrawer.setSummary(String.format("%s: %s", getString(R.string.pref_title__style), getString(R.string.vertical_scroll_drawer)));
                break;
        }
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        super.onPreferenceTreeClick(preference);
        HomeActivity homeActivity = HomeActivity._launcher;
        int key = new ContextUtils(homeActivity).getResId(ContextUtils.ResType.STRING, preference.getKey());
        if (key == R.string.pref_key__about) {
            startActivity(new Intent(getActivity(), MoreInfoActivity.class));
            return true;
        }
        return false;
    }
}
