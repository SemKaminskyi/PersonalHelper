package com.gmail.kaminskysem.PersnalHelper.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetails;

import java.util.ArrayList;
import java.util.List;

class UserTasksDataBaseHelper extends SQLiteOpenHelper implements IUserPlanerProvider {
    private static final String DB_NAME = "plan date base";

    private static final String TABLE_PLANS = "plans";

    private static final String KEY_TASK_ID = "task_id";

    private static final String KEY_TASK = "USER_TASK";
    private static final Boolean KEY_CHECK_PERFORMANCE_TASK = false;

    private static final String LOG_TAG =UserTasksDataBaseHelper.class.getSimpleName();


    public UserTasksDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POST_TABLE = "CREATE TABLE" + TABLE_PLANS + "(" +
                KEY_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TASK + " TEXT " +
                KEY_CHECK_PERFORMANCE_TASK + "TEXT" +
                ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public PlanerDetails getTaskById(long id) {
        try (SQLiteDatabase db = getReadableDatabase()){
            PlanerDetails planerDetails = null;
            String query = "SELECT * FROM " + TABLE_PLANS + " WHERE "+ KEY_TASK_ID + " = ?";

            Cursor cursor =db.rawQuery(query, new String[]{String.valueOf(id)});
            if(cursor.moveToFirst()){
                planerDetails = getPlanerDetailsFromCursor(cursor);
            }
            cursor.close();
            return planerDetails;
        }catch (Exception e){
            Log.d(LOG_TAG, "Failed to get task: "+ e+" with id"+id);
            e.printStackTrace();
        }
        return null;
    }

    public List<PlanerDetails> getPlanerList (){
        ArrayList<PlanerDetails> planerList = new ArrayList<>();

        try (SQLiteDatabase db = getReadableDatabase()){
            PlanerDetails planerDetails = null;
            String query = "SELECT * FROM " + TABLE_PLANS;
            Cursor cursor =db.rawQuery(query, new String[]{});
            if(cursor.moveToFirst()){
                do{
                planerDetails = getPlanerDetailsFromCursor(cursor);
                    planerList.add(planerDetails);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return planerList;
        }catch (Exception e){
            Log.d(LOG_TAG, "Failed to get plane (all tasks): "+ e);
            e.printStackTrace();
        }
        return null;
    }

    private PlanerDetails getPlanerDetailsFromCursor( Cursor cursor) {
        PlanerDetails planerDetails;
        long taskID =cursor.getLong(cursor.getColumnIndex(KEY_TASK_ID));
        String task =cursor.getString(cursor.getColumnIndex(KEY_TASK));
        String checkTask =cursor.getString(cursor.getColumnIndex(KEY_CHECK_PERFORMANCE_TASK.toString()));
        planerDetails = new PlanerDetails(taskID, task, Boolean.parseBoolean(checkTask));
        return planerDetails;
    }

    public void updateTaskWithID (long taskId, PlanerDetails task){
        ContentValues cv = getContentValuesForTask(task);
        try (SQLiteDatabase db = getWritableDatabase()){
            db.update(TABLE_PLANS, cv, KEY_TASK_ID + "=?", new String[]{String.valueOf(taskId)});
        } catch (Exception e) {
            Log.e(LOG_TAG, "FAile to update task" + e);
            e.printStackTrace();
        }
    }

    public long addNewTask(PlanerDetails task) {
        ContentValues cv = getContentValuesForTask(task);

        try (SQLiteDatabase db = getWritableDatabase()) {
            return (int) db.insertOrThrow(TABLE_PLANS, null, cv);
        
        } catch (Exception e) {
            Log.e(LOG_TAG, "FAile to save new task" + e);
            e.printStackTrace();
            return -1;
        }


    }
    private PlanerDetails getTaskDetailsFromCursor (Cursor cursor){
        PlanerDetails planerDetails;
        long taskID = cursor.getLong(cursor.getColumnIndex(KEY_TASK_ID));
        String taskFromUser = cursor.getString(cursor.getColumnIndex(KEY_TASK));
        String checkTask = cursor.getString(cursor.getColumnIndex(KEY_CHECK_PERFORMANCE_TASK.toString()));

        planerDetails = new PlanerDetails(taskID,taskFromUser,false);

        return planerDetails;
    }

    private ContentValues getContentValuesForTask(PlanerDetails task) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_TASK, task.getTask());
        cv.put(String.valueOf(KEY_CHECK_PERFORMANCE_TASK), task.getCheckTask());
        return cv;
    }
}
