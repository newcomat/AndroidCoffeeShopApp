package alexnewcomer.cs360.com.DatabaseItems;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import alexnewcomer.cs360.com.Model.Order;
//SQLite database to hold older history on local database


public class DatabaseItems extends SQLiteAssetHelper {
    private static final String DB_NAME = "LCSIncDB.db";
    private static final int DB_VER = 1;
    public DatabaseItems(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID","ProductId","ProductName","Quantity","Price","Discount"};
        String sqlTable = "OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()) {
            do{
                result.add(new Order(
                        c.getInt(c.getColumnIndex("ID")),
                        c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))
                        ));
            }while(c.moveToNext());
        }
        return result;
    }

    //add items to cart
    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }


    //empty the cart after the order has been placed
    public void cleanCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }


    //get item quantity for items in cart
    public int getCountCart() {
        int count = 0;

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT COUNT(*) FROM OrderDetail");
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do{
                count = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return count;

    }


    //update cart to set new quantity in real time with the edit quantity button in the cart
    public void updateCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("UPDATE OrderDetail SET Quantity= %s WHERE ID = %d",order.getQuantity(), order.getID());
        db.execSQL(query);

    }

    //delete items from the cart
    public void removeFromCart(String productId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail WHERE ProductId='%s'", productId);
        db.execSQL(query);

    }
}
