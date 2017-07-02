package pl.lukaszpelczar.p9_habittracker.data;

import android.provider.BaseColumns;

public class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */

    public static final class HabitEntry implements BaseColumns {

        /** Name of database table for habits */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME ="name";

        /**
         * Date and time of the habit.
         *
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE = "date";

        /**
         * Duration of the habit in minutes.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_DURATION = "duration";


    }

}
