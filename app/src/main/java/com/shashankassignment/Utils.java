package com.shashankassignment;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {

    private static ProgressDialog sProgressDialog;

    public static void showProgressDialog(Context context) {
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setMessage("Loading...");
        sProgressDialog.setCanceledOnTouchOutside(true);
        sProgressDialog.show();
    }

    public static void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            sProgressDialog.dismiss();
        }
    }
}
