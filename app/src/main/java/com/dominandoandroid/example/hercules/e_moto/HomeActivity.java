package com.dominandoandroid.example.hercules.e_moto;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.activity_home);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        // apareceu as 3 barrinhas para abrir o menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // eventos de click
        NavigationView navigationView = findViewById(R.id.nav_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // revolveu o problema de quando clicar nas 3 barrinhas
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_concluidas) {
            Toast.makeText(getApplicationContext(), "concluida", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_canceladas) {
            Toast.makeText(getApplicationContext(), "canceladas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_lista) {
            Toast.makeText(getApplicationContext(), "lista", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_configuracoes) {
            Toast.makeText(getApplicationContext(), "configuracoes", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_sair) {
            Toast.makeText(getApplicationContext(), "sair", Toast.LENGTH_SHORT).show();
        }

        // fechar o navigation quando clicar
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // quando clicar no botao de voltar
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

}
