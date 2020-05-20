package com.example.pfe_test;



        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentTransaction;

        import android.os.Bundle;
        import android.view.MenuItem;
        import androidx.appcompat.widget.Toolbar;

        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar  toolbar;
    DrawerLayout nDrawerLayout;
    ActionBarDrawerToggle nDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar= findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        nDrawerLayout=findViewById(R.id.drawerLayout);
        nDrawerToggle= new ActionBarDrawerToggle(this, nDrawerLayout, toolbar,  R.string.open, R.string.close);
        nDrawerLayout.addDrawerListener(nDrawerToggle);
        nDrawerToggle.syncState();

        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        showFragments(new fragmentAccueil());

        BottomNavigationView bottomNav = findViewById(R.id.activity_main_bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navlistner);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragmentselecter =null;

                    switch (item.getItemId()){

                        case R.id.accueil:
                            fragmentselecter= new fragmentAccueil();
                            break;
                        case R.id.compte:
                            fragmentselecter= new fragmentCompte();
                            break;
                        case R.id.favorie:
                            fragmentselecter= new fragmentFavorie();
                            break;
                        case R.id.notif:
                            fragmentselecter= new fragmentNotification();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout,fragmentselecter).commit();
                    return true;

                }
            };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if (id == R.id.nav_home){
            showFragments(new fragmentAccueil());
        }
        nDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showFragments(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_frame_layout, fragment);
        ft.commit();
    }
    @Override
    public void onBackPressed() {
        if (nDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            nDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


}
