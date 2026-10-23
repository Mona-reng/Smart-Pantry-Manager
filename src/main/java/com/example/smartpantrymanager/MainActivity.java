
package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnMyPantry = findViewById(R.id.btnMyPantry);
        Button btnAddIngredient = findViewById(R.id.btnAddIngredient);
        Button btnSuggestedRecipes = findViewById(R.id.btnSuggestedRecipes);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnMyPantry.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyPantryActivity.class);
            startActivity(intent);
        });

        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        btnSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipesActivity.class);
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}

