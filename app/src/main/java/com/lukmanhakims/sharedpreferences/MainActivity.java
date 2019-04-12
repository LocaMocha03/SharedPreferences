package com.lukmanhakims.sharedpreferences;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lukmanhakims.sharedpreferences.fragments.LoginFragment;
import com.lukmanhakims.sharedpreferences.fragments.NoteFragment;
import com.lukmanhakims.sharedpreferences.fragments.SettingFragment;
import com.lukmanhakims.sharedpreferences.models.User;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentListener, NoteFragment.OnNoteFragmentListener {
    Settings settings;
    Session session;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        settings = new Settings ( this );
        session = new Session ( settings );

        addFragment ( );
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {

        getMenuInflater ( ).inflate ( R.menu.menu_main , menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem item ) {
        int id = item.getItemId ( );

        if (id == R.id.action_settings) {
            createSettingFragment();
            return true;
        }

        return super.onOptionsItemSelected ( item );
    }

    private void addFragment () {
        Fragment fragment = null;
        if (session.isLogin ( )) {
            fragment = new NoteFragment ( );
            ((NoteFragment) fragment).setListener ( this );
        } else {
            fragment = new LoginFragment ( );
            ((LoginFragment) fragment).setListener ( this );
        }
        getSupportFragmentManager ( ).beginTransaction ( )
                .replace ( R.id.fragment_container , fragment )
                .commit ( );
    }

    @Override
    public void onLoginClicked ( View view , String username , String password ) {
        User user = session.doLogin ( username , password );
        String message = "Authentication failed";
        if (user != null) {
            message = "Welcome " + username;
            session.setUser ( username );
        }
        Snackbar.make ( view , message , Snackbar.LENGTH_SHORT ).show ( );
        addFragment ( );
    }

    @Override
    public void onLogoutClick () {
        session.doLogout ( );
        addFragment ( );
    }

    private void createSettingFragment() {
        Fragment settingsFragment = new SettingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, settingsFragment)
                .addToBackStack(null)
                .commit();
    }
}
