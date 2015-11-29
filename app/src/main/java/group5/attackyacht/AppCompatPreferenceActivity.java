/*
********************************************************************************
*** AppCompatPreferenceActivity.java
*** Group 5
********************************************************************************
*** Purpose:
*** A {@link android.preference.PreferenceActivity} which implements and proxies
*** the necessary calls to be used with AppCompat.
********************************************************************************
*** Date:
*** 11/17/15
********************************************************************************
*** Change Log:
*** 11/17/15 - CS - Created 
*** 11/17/15 - CS - Created onCreate
*** 11/17/15 - CS - Created onPostCreate
*** 11/17/15 - CS - Created getSupportActionBar
*** 11/17/15 - CS - Created setSupportActionBar
*** 11/17/15 - CS - Created getMenuInflater
*** 11/17/15 - CS - Created setContentView(@LayoutRes int layoutResID)
*** 11/17/15 - CS - Created setContentView(View view)
*** 11/17/15 - CS - Created setContentView(View view, ViewGroup.LayoutParams
***                 params)
*** 11/17/15 - CS - Created addContentView
*** 11/17/15 - CS - Created onTitleChanged
*** 11/17/15 - CS - Created onConfigurationChanged
*** 11/17/15 - CS - Created onStop
*** 11/17/15 - CS - Created onDestroy
*** 11/17/15 - CS - Created invalidateOptionsMenu
*** 11/17/15 - CS - Created getDelegate
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported libraries
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AppCompatPreferenceActivity extends PreferenceActivity {

    private AppCompatDelegate mDelegate;

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
        getDelegate().installViewFactory();
        getDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

/*
********************************************************************************
*** onPostCreate
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getDelegate().onPostCreate(savedInstanceState);
    }

/*
********************************************************************************
*** getSupportActionBar
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** ActionBar getDelegate().getSupportActionBar()
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    public ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

/*
********************************************************************************
*** setSupportActionBar
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** @Nullable Toolbar toolbar
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().setSupportActionBar(toolbar);
    }

/*
********************************************************************************
*** getMenuInflater
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** MenuInflater getDelegate().getMenuInflater()
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public MenuInflater getMenuInflater() {
        return getDelegate().getMenuInflater();
    }

/*
********************************************************************************
*** setContentView
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** @LayoutRes int layoutResID
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getDelegate().setContentView(layoutResID);
    }

/*
********************************************************************************
*** setContentView
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** View view
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public void setContentView(View view) {
        getDelegate().setContentView(view);
    }

/*
********************************************************************************
*** setContentView
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** View view, ViewGroup.LayoutParams params
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().setContentView(view, params);
    }

/*
********************************************************************************
*** addContentView
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** View view, ViewGroup.LayoutParams params
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getDelegate().addContentView(view, params);
    }

/*
********************************************************************************
*** onPostResume
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    protected void onPostResume() {
        super.onPostResume();
        getDelegate().onPostResume();
    }

/*
********************************************************************************
*** onTitleChanged
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** CharSequence title, int color
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }

/*
********************************************************************************
*** onConfigurationChanged
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Configuration newConfig
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getDelegate().onConfigurationChanged(newConfig);
    }

/*
********************************************************************************
*** onStop
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

/*
********************************************************************************
*** onDestroy
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

/*
********************************************************************************
*** invalidateOptionsMenu
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

/*
********************************************************************************
*** getDelegate
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/17/15
********************************************************************************
*/
    private AppCompatDelegate getDelegate() {
        if (mDelegate == null) {
            mDelegate = AppCompatDelegate.create(this, null);
        }
        return mDelegate;
    }
}
