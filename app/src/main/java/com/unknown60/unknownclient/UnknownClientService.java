package com.unknown60.unknownclient;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import java.util.List;

public class UnknownClientService extends AccessibilityService {

    public static final String preferencias = "EstadoServicio";
    public static final String cantidadMensajes = "CantidadMensajes";
    
    @Override
    public void onInterrupt() {
 
    }

    @Override
    public void onAccessibilityEvent (AccessibilityEvent event) {
        if (getRootInActiveWindow () == null) {
            return;
        }

        AccessibilityNodeInfoCompat rootInActiveWindow = AccessibilityNodeInfoCompat.wrap (getRootInActiveWindow ());

        // Id del EditText de WhatsApp
        List<AccessibilityNodeInfoCompat> messageNodeList = rootInActiveWindow.findAccessibilityNodeInfosByViewId ("com.whatsapp:id/entry");
        if (messageNodeList == null || messageNodeList.isEmpty ()) {
            return;
        }

        AccessibilityNodeInfoCompat messageField = messageNodeList.get (0);
        if (messageField.getText () == null || messageField.getText ().length() == 0) { // So your service doesn't process any message, but the ones ending your apps suffix
            return;
        }

        // Id boton de WhatsApp
        List<AccessibilityNodeInfoCompat> sendMessageNodeInfoList = rootInActiveWindow.findAccessibilityNodeInfosByViewId ("com.whatsapp:id/send");
        if (sendMessageNodeInfoList == null || sendMessageNodeInfoList.isEmpty ()) {
            return;
        }

        AccessibilityNodeInfoCompat sendMessageButton = sendMessageNodeInfoList.get (0);
        if (!sendMessageButton.isVisibleToUser ()) {
            return;
        }
               
        //disparar Unknown Client
        SharedPreferences prefs = getSharedPreferences(preferencias, MODE_PRIVATE); 
        String nombre = prefs.getString("estadoServicio", "");
        if (nombre.equals("desactivado")){
            
        } else if (nombre.equals("activado")){

       SharedPreferences prefs_mensajes = getSharedPreferences(cantidadMensajes, MODE_PRIVATE); 
       String cantidad = prefs_mensajes.getString("CantidadMensajes", "");
           
            int i = 0;

            if (cantidad.equals("1")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);         
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500);
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    
            } else if (cantidad.equals("5")){
                // Es necesario dispararlo 1 vez antes de inicie el bucle
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 4; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 4){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500);
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }
            }
            else if (cantidad.equals("10")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 9; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 9){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }
                
            } else if (cantidad.equals("15")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 14; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 14){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("20")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 19; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 19){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("30")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 29; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 29){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("40")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 39; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 39){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("60")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 59; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 59){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("70")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 69; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 69){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("80")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 79; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 79){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("100")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 99; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 99){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("200")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 199; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 199){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("400")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 399; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 399){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("500")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 499; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 499){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("800")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 799; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 799){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("1000")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 999; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 999){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("1500")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 1499; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 1499){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("2000")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 1999; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 1999){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("3000")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 2999; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 2999){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("5000")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 4999; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 4999){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("8000")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 7999; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 7999){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }

            } else if (cantidad.equals("10000")){
                sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);
                for (i = 1; i <= 9999; i++){

                    messageField.performAction(AccessibilityNodeInfo.ACTION_PASTE);
                    sendMessageButton.performAction (AccessibilityNodeInfo.ACTION_CLICK);

                    if (i >= 9999){
                        try {
                            SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                            editor.putString("estadoServicio", "desactivado");
                            editor.apply();
                            Thread.sleep (500); // hack for certain devices in which the immediate back click is too fast to handle
                            performGlobalAction (GLOBAL_ACTION_BACK);
                            Thread.sleep (500);  // same hack as above
                        } catch (InterruptedException ignored) {}
                        performGlobalAction (GLOBAL_ACTION_BACK);
                    }
                }
            }
        }     
   	 }   
}
