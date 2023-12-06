package com.teamtb.cookhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamtb.cookhelper.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText inputField;
    private LinearLayout layout;
    private ImageView imageView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        inputField = findViewById(R.id.editTextText);
//        layout = findViewById(R.id.linear);
        imageView = findViewById(R.id.imageView3);
        listView = findViewById(R.id.listView);

        CustomAdapter adapter = new CustomAdapter(this);
        listView.setAdapter(adapter);


        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String text = v.getText().toString();
                System.out.println("Введено всякое " + text);
                adapter.addElement(text);

                v.setText("");

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
//        listView = findViewById(R.id.listView);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.ingredient, R.id.ingredientName, ing_arr);
//        listView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    public void createButton(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Button btn = new Button(this);
        btn.setId(new Random().nextInt(1000000));
        final int id_ = btn.getId();
        btn.setText(text);
        btn.setBackgroundColor(Color.rgb(70, 80, 90));
        layout.addView(btn, params);
        Button btn1 = ((Button) findViewById(id_));
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                                "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}