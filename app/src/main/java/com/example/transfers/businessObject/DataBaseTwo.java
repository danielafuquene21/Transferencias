package com.example.transfers.businessObject;

import android.os.Environment;

import java.io.File;

public class DataBaseTwo {

    public static File DirApp() {
        File SDCardRoot = Environment.getExternalStorageDirectory(); // Environment.getDataDirectory(); //
        File dirApp = new File(SDCardRoot.getPath() + "/" + "Transfers");
        // File dirApp = new File(SDCardRoot + "/data/" + "co.com.SuperRicas/" +
        // Const.nameDirApp);
        if (!dirApp.isDirectory()) dirApp.mkdirs();
        return dirApp;
    }



}
