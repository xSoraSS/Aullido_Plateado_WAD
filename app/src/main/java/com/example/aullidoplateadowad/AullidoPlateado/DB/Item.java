package com.example.aullidoplateadowad.AullidoPlateado.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "item")
    private String itemName;
    @ColumnInfo(name = "tipo")
    private String itemType;
    @ColumnInfo(name = "descripcion")
    private String description;
    //Se puede portar mas de uno?
    @ColumnInfo(name = "Almacenable")
    private boolean stockable;
    @ColumnInfo(name = "Cantidad")
    private int quantity;

    public Item(String itemName, String itemType, String description, boolean stockable, int quantity) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.description = description;
        this.stockable = stockable;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStockable() { return stockable; }

    public void setStockable(boolean stockable) { this.stockable = stockable; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        String almacenable;
        if(stockable = false) {almacenable = "No puedes almacenar o llevar mas de uno";}
        return  "Objeto: " + itemName +
                "\nTipo de Objeto: " + itemType +
                "\nDescripcion: " + description +
                "\nCantidad: " + quantity;
    }
}
