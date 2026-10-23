package com.example.smartpantrymanager.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import com.example.smartpantrymanager.AddEditIngredientActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.Database.DatabaseHelper;
import com.example.smartpantrymanager.R;

import android.database.Cursor;

public class Ingredientadapter extends RecyclerView.Adapter<Ingredientadapter.IngredientViewHolder> {

    private final Context context;
    private Cursor cursor;
    private final DatabaseHelper databaseHelper;

    public Ingredientadapter(Context context, Cursor cursor,
                             DatabaseHelper databaseHelper) {
        this.context = context;
        this.cursor = cursor;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.item_ingredient, parent, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull IngredientViewHolder holder, int position) {

        if (!cursor.moveToPosition(position)) {
            return;
        }

        int id = cursor.getInt(
                cursor.getColumnIndexOrThrow(DatabaseHelper.COL_ID));

        String name = cursor.getString(
                cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NAME));

        double quantity = cursor.getDouble(
                cursor.getColumnIndexOrThrow(DatabaseHelper.COL_QUANTITY));

        String unit = cursor.getString(
                cursor.getColumnIndexOrThrow(DatabaseHelper.COL_UNIT));

        String expiry = cursor.getString(
                cursor.getColumnIndexOrThrow(DatabaseHelper.COL_EXPIRY));

        holder.txtName.setText(name);
        holder.txtQuantity.setText("Quantity: " + quantity + " " + unit);

        if (expiry == null || expiry.trim().isEmpty()) {
            holder.txtExpiry.setText("Expiry: Not specified");
        } else {
            holder.txtExpiry.setText("Expiry: " + expiry);
        }

        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditIngredientActivity.class);

            intent.putExtra("ingredient_id", id);
            intent.putExtra("ingredient_name", name);
            intent.putExtra("ingredient_quantity", quantity);
            intent.putExtra("ingredient_unit", unit);
            intent.putExtra("ingredient_expiry", expiry);

            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete ingredient")
                    .setMessage("Delete " + name + " from your pantry?")
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .setPositiveButton("Delete", (dialog, which) -> {
                        boolean deleted = databaseHelper.deleteIngredient(id);

                        if (deleted) {
                            Toast.makeText(context,
                                    "Ingredient deleted",
                                    Toast.LENGTH_SHORT).show();

                            // Refresh the list after deletion.
                            Cursor newCursor = databaseHelper.getAllIngredients();
                            swapCursor(newCursor);
                        } else {
                            Toast.makeText(context,
                                    "Could not delete ingredient",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor == newCursor) {
            return;
        }

        Cursor oldCursor = cursor;
        cursor = newCursor;

        notifyDataSetChanged();

        if (oldCursor != null) {
            oldCursor.close();
        }
    }


    static class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtQuantity, txtExpiry;
        Button btnEdit, btnDelete;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtIngredientName);
            txtQuantity = itemView.findViewById(R.id.txtIngredientQuantity);
            txtExpiry = itemView.findViewById(R.id.txtIngredientExpiry);

            btnEdit = itemView.findViewById(R.id.btnEditIngredient);
            btnDelete = itemView.findViewById(R.id.btnDeleteIngredient);
        }
    }
}
