package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartpantrymanager.Database.DatabaseHelper;

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText edtName, edtQuantity, edtUnit, edtExpiry;
    private DatabaseHelper databaseHelper;
    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        databaseHelper = new DatabaseHelper(this);
        if (getIntent().hasExtra("ingredient_id")) {
            ingredientId = getIntent().getIntExtra("ingredient_id", -1);
        }

        edtName = findViewById(R.id.edtIngredientName);
        edtQuantity = findViewById(R.id.edtIngredientQuantity);
        edtUnit = findViewById(R.id.edtIngredientUnit);
        edtExpiry = findViewById(R.id.edtIngredientExpiry);

        Button btnSave = findViewById(R.id.btnSaveIngredient);
        if (ingredientId != -1) {
            edtName.setText(getIntent().getStringExtra("ingredient_name"));

            double quantity = getIntent().getDoubleExtra(
                    "ingredient_quantity", 0);

            edtQuantity.setText(String.valueOf(quantity));

            edtUnit.setText(getIntent().getStringExtra("ingredient_unit"));

            String expiry = getIntent().getStringExtra("ingredient_expiry");

            if (expiry != null) {
                edtExpiry.setText(expiry);
            }
        }

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String quantityText = edtQuantity.getText().toString().trim();
            String unit = edtUnit.getText().toString().trim();
            String expiry = edtExpiry.getText().toString().trim();

            if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
                Toast.makeText(this,
                        "Please enter the name, quantity and unit",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double quantity;

            try {
                quantity = Double.parseDouble(quantityText);
            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Please enter a valid quantity",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (quantity <= 0) {
                Toast.makeText(this,
                        "Quantity must be greater than zero",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success;

            if (ingredientId != -1) {
                success = databaseHelper.updateIngredient(
                        ingredientId, name, quantity, unit, expiry);
            } else {
                success = databaseHelper.addIngredient(
                        name, quantity, unit, expiry);
            }

            if (success) {
                Toast.makeText(this,
                        ingredientId != -1
                                ? "Ingredient updated successfully"
                                : "Ingredient saved successfully",
                        Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this,
                        "Could not save ingredient",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}

