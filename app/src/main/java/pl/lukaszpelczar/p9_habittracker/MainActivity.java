package pl.lukaszpelczar.p9_habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import pl.lukaszpelczar.p9_habittracker.data.HabitContract.HabitEntry;
import pl.lukaszpelczar.p9_habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertHabit("Walking the dog", 60, "20-06-2017 16:34:35");
        insertHabit("Practicing the violin", 90, "21-06-2017 17:37:45");

        readHabit();
    }

    private void insertHabit(String name, int duration, String date){

        // Create database helper
        mDbHelper = new HabitDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_DURATION, duration);
        values.put(HabitEntry.COLUMN_HABIT_DATE, date);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

    }

    private Cursor readHabit() {

        // Create StringBuilder to append the values to it
        StringBuilder sb = new StringBuilder();

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_DURATION,
                HabitEntry.COLUMN_HABIT_DATE };

        // Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,              // The columns to return
                null,                    // The columns for the WHERE clause
                null,                    // The values for the WHERE clause
                null,                    // Don't group the rows
                null,                    // Don't filter by row groups
                null);                   // The sort order

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);

                sb.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDuration + " - " +
                        currentDate ));

            }
        } finally {
            //Display a Toast with all database columns
            Toast.makeText(this, sb, Toast.LENGTH_LONG).show();
        }

        return cursor;

    }


}
