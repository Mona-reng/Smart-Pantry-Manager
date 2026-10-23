
package com.example.smartpantrymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.Adapter.Ingredientadapter;
import com.example.smartpantrymanager.Database.DatabaseHelper;

public class MyPantryActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Ingredientadapter ingredientAdapter;
    private RecyclerView recyclerPantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pantry);

        databaseHelper = new DatabaseHelper(this);

        recyclerPantry = findViewById(R.id.recyclerPantry);
        recyclerPantry.setLayoutManager(
                new LinearLayoutManager(this));

        loadPantryIngredients();

        Button btnBackHome = findViewById(R.id.btnBackHome);
        btnBackHome.setOnClickListener(v -> finish());
    }

    private void loadPantryIngredients() {
        Cursor cursor = databaseHelper.getAllIngredients();

        if (ingredientAdapter == null) {
            ingredientAdapter = new Ingredientadapter(
                    this, cursor, databaseHelper);
            recyclerPantry.setAdapter(ingredientAdapter);
        } else {
            ingredientAdapter.swapCursor(cursor);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (databaseHelper != null && recyclerPantry != null) {
            loadPantryIngredients();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (ingredientAdapter != null) {
            ingredientAdapter.swapCursor(null);
        }

        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}

