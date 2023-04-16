package com.unknown60.unknownclient.utils;

import android.content.Context;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarToast extends Toast {

    public MostrarToast(Context context) {
        super(context);
    }
        
        public static Toast makeText(Context context, String mensaje_toast, int tiempo)
        {
            Toast toast = new Toast(context);
            toast.setDuration(tiempo);
            View layout = LayoutInflater.from(context).inflate(R.layout.activity_toast, null, false);
            TextView tv_mensaje_toast = layout.findViewById(R.id.tv_mensaje_toast);
            tv_mensaje_toast.setText(mensaje_toast);
            toast.setView(layout);

            return toast;
        }



    
}
