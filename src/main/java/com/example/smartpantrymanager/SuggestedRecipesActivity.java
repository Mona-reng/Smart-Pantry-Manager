package com.example.smartpantrymanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView txtRecipeResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        databaseHelper = new DatabaseHelper(this);

        txtRecipeResults = findViewById(R.id.txtRecipeResults);
        Button btnFindRecipes = findViewById(R.id.btnFindRecipes);
        Button btnBackHome = findViewById(R.id.btnBackHome);

        btnFindRecipes.setOnClickListener(v -> findRecipes());

        btnBackHome.setOnClickListener(v -> finish());
    }

    private void findRecipes() {
        List<String> ingredients = new ArrayList<>();

        Cursor cursor = databaseHelper.getAllIngredients();

        if (cursor != null) {
            int nameColumn = cursor.getColumnIndex("name");

            if (nameColumn != -1) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameColumn);

                    if (name != null) {
                        ingredients.add(
                                name.toLowerCase(Locale.ROOT).trim());
                    }
                }
            }

            cursor.close();
        }

        List<String> recipes = new ArrayList<>();

        if (ingredients.isEmpty()) {
            txtRecipeResults.setText(
                    "Your pantry is empty. Add ingredients first.");
            return;
        }

        if (ingredients.contains("tomato")
                || ingredients.contains("tomatoes")) {
            recipes.add("Tomato Salad - Use your tomatoes with a little "
                    + "oil, salt and pepper.");
        }

        if (ingredients.contains("egg")
                || ingredients.contains("eggs")) {
            recipes.add("Scrambled Eggs - Cook your eggs in a pan "
                    + "with a little oil or butter.");
        }

        if ((ingredients.contains("tomato")
                || ingredients.contains("tomatoes"))
                && (ingredients.contains("egg")
                || ingredients.contains("eggs"))) {
            recipes.add("Tomato and Egg Fry - Cook chopped tomatoes, "
                    + "add eggs and stir until cooked.");
        }

        if (ingredients.contains("potato")
                || ingredients.contains("potatoes")) {
            recipes.add("Potato Hash - Dice potatoes and fry "
                    + "until tender and golden.");
        }

        if (ingredients.contains("rice")) {
            recipes.add("Simple Rice Bowl - Cook your rice and "
                    + "serve with available vegetables.");
        }

        if (recipes.isEmpty()) {
            txtRecipeResults.setText(
                    "We found your pantry ingredients, but no matching "
                            + "recipes yet. Try adding tomatoes, eggs, potatoes "
                            + "or rice.");
        } else {
            StringBuilder result = new StringBuilder();

            result.append("Here are some ideas for you:\n\n");

            for (int i = 0; i < recipes.size(); i++) {
                result.append(i + 1)
                        .append(". ")
                        .append(recipes.get(i))
                        .append("\n\n");
            }

            txtRecipeResults.setText(result.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
