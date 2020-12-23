package com.uni.officecriminal.dao;

import android.content.Context;
import android.util.Log;

import com.uni.officecriminal.CriminalApplication;
import com.uni.officecriminal.model.DaoMaster;

import org.apache.commons.io.IOUtils;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CriminalOpenHelper extends DatabaseOpenHelper {
    private static final int SCHEMA_VERSION = 2;
    public CriminalOpenHelper(Context context, String name){
        super(context, name, SCHEMA_VERSION );
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
        Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
        DaoMaster.createAllTables(db, false);

        addBootstrapData(db);

    }

    private void addBootstrapData(Database db) {
        List<String> lines = new ArrayList<>();
        try {
        IOUtils.readLines(CriminalApplication.getInstance().getAssets().open("CRIMINAL.sql"));
        } catch (IOException e) {
            Log.e("CriminalOpenHelper","addBootstrapData" + e.toString());
        }
        for (String line:lines){
            db.execSQL(line);
        }
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
