package com.adma.adma.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.adma.adma.Fragments.EventosFragment;
import com.adma.adma.Fragments.PerfilFragment;
import com.adma.adma.Fragments.SalasFragment;
import com.adma.adma.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bindUI();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment=null;
                switch (menuItem.getItemId()){
                    case R.id.eventos:
                        fragment=new EventosFragment();break;
                    case R.id.salas:
                        fragment= new SalasFragment();break;
                    case R.id.homemenu_perfil:
                        fragment=new PerfilFragment();break;
                }
                setFragment(fragment);
                return true;
            }
        });
    }
    private void bindUI(){
        bottomNav=(BottomNavigationView)findViewById(R.id.home_BottomNav);
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_home,fragment);
        fragmentTransaction.commit();
    }
}
