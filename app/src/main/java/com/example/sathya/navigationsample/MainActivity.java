package com.example.sathya.navigationsample;

import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    TextView textView;
    DrawerLayout drawerLayout;
    String welcomeText = "Hello, India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            welcomeText = savedInstanceState.getString("t1");
        }

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.menubar);

        drawerLayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        textView.setText(welcomeText);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_account:
                        textView.setText(menuItem.getTitle());
                        break;
                    case R.id.nav_gallery:
                        textView.setText(menuItem.getTitle());
                        break;
                    case R.id.nav_slideshow:
                        textView.setText(menuItem.getTitle());
                        break;
                }
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("t1", textView.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
