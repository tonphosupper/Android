package com.uni.officecriminal;

import android.app.Application;

import com.uni.officecriminal.dao.CriminalOpenHelper;
import com.uni.officecriminal.model.DaoMaster;
import com.uni.officecriminal.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class CriminalApplication extends Application {
    public DaoSession daoSession;
    static CriminalApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        CriminalOpenHelper helper = new CriminalOpenHelper(this, "criminal");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();


    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static CriminalApplication getInstance() {
        return instance;
    }
}
