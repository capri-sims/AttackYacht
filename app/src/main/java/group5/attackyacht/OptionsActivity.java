/*
********************************************************************************
*** OptionsActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** A {@link PreferenceActivity} that presents a set of application settings. On
*** handset devices, settings are presented as a single list. On tablets,
*** settings are split by category, with category headers shown to the left of
*** the list of settings.
********************************************************************************
*** Date:
*** 11/17/15
********************************************************************************
*** Change Log:
*** 11/17/15 - CS - Created onCreate
*** 11/17/15 - CS - Created setupActionBar
*** 11/17/15 - CS - Created onIsMultiPane
*** 11/17/15 - CS - Created isXLargeTablet
*** 11/17/15 - CS - Created onBuildHeaders
*** 11/17/15 - CS - Created sBindPreferenceSummaryToValueListener
*** 11/17/15 - CS - Created bindPreferenceSummaryToValue
*** 11/17/15 - CS - Created isValidFragment
*** 11/xx/15 - xx -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported libraries
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.MenuItem;

import java.util.List;

/**
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class OptionsActivity extends AppCompatPreferenceActivity {
    /*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    /*
********************************************************************************
*** setupActionBar
*** Group 5
********************************************************************************
*** Purpose:
*** Set up the {@link android.app.ActionBar}, if the API is available.
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*
********************************************************************************
*** onIsMultiPane
*** Group 5
********************************************************************************
*** Purpose:
*** {@inheritDoc}
*** Inputs:
*** n/a
*** Outputs:
*** isXLargeTablet
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /*
********************************************************************************
*** isXLargeTablet
*** Group 5
********************************************************************************
*** Purpose:
*** Helper method to determine if the device has an extra-large screen. For
*** example, 10" tablets are extra-large.
*** Inputs:
*** Context context
*** Outputs:
*** (context.getResources().getConfiguration().screenLayout &
*** Configuration.SCREENLAYOUT_SIZE_MASK) >=
*** Configuration.SCREENLAYOUT_SIZE_XLARGE;
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /*
********************************************************************************
*** onBuildHeaders
*** Group 5
********************************************************************************
*** Purpose:
*** {@inheritDoc}
*** Inputs:
*** List<Header> target
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /*
********************************************************************************
*** sBindPreferenceSummaryToValueListener
*** Group 5
********************************************************************************
*** Purpose:
*** A preference value change listener that updates the preference's summary
*** to reflect its new value.
*** Inputs:
***
*** Outputs:
*** Boolean true
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /*
********************************************************************************
*** bindPreferenceSummaryToValue
*** Group 5
********************************************************************************
*** Purpose:
*** Binds a preference's summary to its value. More specifically, when the
*** preference's value is changed, its summary (line of text below the
*** preference title) is updated to reflect the value. The summary is also
*** immediately updated upon calling this method. The exact display format is
*** dependent on the type of preference.
*** @see #sBindPreferenceSummaryToValueListener
*** Inputs:
*** Preference preference
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    /*
********************************************************************************
*** isValidFragment
*** Group 5
********************************************************************************
*** Purpose:
*** This method stops fragment injection in malicious applications.
*** Make sure to deny any unknown fragments here.
*** Inputs:
*** String fragmentName
*** Outputs:
*** PreferenceFragment.class.getName().equals(fragmentName),
*** GeneralPreferenceFragment.class.getName().equals(fragmentName),
*** DataSyncPreferenceFragment.class.getName().equals(fragmentName),
*** NotificationPreferenceFragment.class.getName().equals(fragmentName)
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || NotificationPreferenceFragment.class.getName().equals(fragmentName);
    }


    /*
********************************************************************************
*** GeneralPreferenceFragment
*** Group 5
********************************************************************************
*** Purpose:
*** This fragment shows general preferences only. It is used when the
*** activity is showing a two-pane settings UI.
********************************************************************************
*** Date:
*** 11/17/15
********************************************************************************
*** Change Log:
*** 11/17/15 - CS - Created onCreate
*** 11/xx/15 - xx -
***
********************************************************************************
*/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {
        /*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("example_text"));
            bindPreferenceSummaryToValue(findPreference("example_list"));
        }
    }

    /*
********************************************************************************
*** NotificationPreferenceFragment
*** Group 5
********************************************************************************
*** Purpose:
*** This fragment shows notification preferences only. It is used when the
*** activity is showing a two-pane settings UI.
********************************************************************************
*** Date:
*** 11/17/15
********************************************************************************
*** Change Log:
*** 11/17/15 - CS - Created onCreate
*** 11/17/15 - CS - Created onOptionsItemSelected
*** 11/xx/15 - xx -
***
********************************************************************************
*/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class NotificationPreferenceFragment extends PreferenceFragment {
        /*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_notification);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
        }

        /*
********************************************************************************
*** onOptionsItemSelected
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** MenuItem item
*** Outputs:
*** Boolean true, Boolean super.onOptionsItemSelected(item)
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), OptionsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    /*
********************************************************************************
*** DataSyncPreferenceFragment
*** Group 5
********************************************************************************
*** Purpose:
*** This fragment shows data and sync preferences only. It is used when the
*** activity is showing a two-pane settings UI.
********************************************************************************
*** Date:
*** 11/17/15
********************************************************************************
*** Change Log:
*** 11/17/15 - CS - Created onCreate
*** 11/17/15 - CS - Created onOptionsItemSelected
*** 11/xx/15 - xx -
***
********************************************************************************
*/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        /*
********************************************************************************
*** onCreate
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Bundle savedInstanceState
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }

        /*
********************************************************************************
*** onOptionsItemSelected
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** MenuItem item
*** Outputs:
*** Boolean true, Boolean super.onOptionsItemSelected(item)
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), OptionsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
