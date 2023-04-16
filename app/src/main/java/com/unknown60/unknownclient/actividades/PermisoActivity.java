package com.unknown60.unknownclient.actividades;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.R;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.Locale;
import android.widget.Toast;

public class PermisoActivity extends AppCompatActivity {
    Button btn_accesibilidad, btn_continuar, btn_bateria;
    TextView tv_bateria, tv_opcional;
    
    public static final String mostrarDialogoCambios = "EstadoDialogo";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     	setContentView(R.layout.activity_permisos);
        
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbarPermisos);
		setSupportActionBar(toolbar);
        
        btn_accesibilidad = (Button) findViewById(R.id.btn_accesibilidad);     
       	btn_continuar = (Button) findViewById(R.id.btn_continuar);
        btn_bateria = (Button) findViewById(R.id.btn_bateria);
        tv_bateria = (TextView) findViewById(R.id.tv_bateria);
        tv_opcional = (TextView) findViewById(R.id.tv_opcional);

        btn_continuar.setEnabled(false);
        
        SharedPreferences prefDialogo = getSharedPreferences(mostrarDialogoCambios, MODE_PRIVATE); 
        String estado = prefDialogo.getString("EstadoDialogo", "");
        if (!estado.equals("visto")){
			MostrarCambios();
        }

        boolean servicio_activado = isAccessibilityServiceEnabled(PermisoActivity.this, com.unknown60.unknownclient.UnknownClientService.class);
        
        if (servicio_activado){        
            btn_accesibilidad.setEnabled(false);
            btn_continuar.setEnabled(true);

            Intent iniciar_uc = new Intent(PermisoActivity.this, InicioActivity.class);
            startActivity(iniciar_uc);
            finish();

            btn_continuar.setTextColor(Color.parseColor("#ff00ff00"));
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent iniciar = new Intent(PermisoActivity.this, InicioActivity.class);
                        startActivity(iniciar);
                        finish();
                    }
                }); 
        } else {
            btn_accesibilidad.setEnabled(true);
            btn_continuar.setEnabled(false);
            btn_continuar.setTextColor(Color.parseColor("#ff575757"));
            btn_accesibilidad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        startActivity(intent);
                    }
                }); 
        }
        // en caso de que la version de android sea mayo o igual.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){        
            
            final String paquete = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
			// en caso de no haber desactivado la optimización
            if (!pm.isIgnoringBatteryOptimizations(paquete)) {
               btn_bateria.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
							Intent intent = new Intent();
                            intent.setAction(android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                            startActivity(intent);                
                            
                        }
                    });
            // en caso de haber desactivado la optimización   
            }else {
                btn_bateria.setEnabled(false); 
            }
        // en caso de que la version de android sea menor
        } else {
            btn_bateria.setVisibility(View.GONE);
            tv_bateria.setVisibility(View.GONE);
            tv_opcional.setVisibility(View.GONE);
        }
    }
    
    @Override
    public void onResume(){
        super.onResume();
        btn_continuar.setEnabled(false);

        boolean servicio_activado = isAccessibilityServiceEnabled(PermisoActivity.this, com.unknown60.unknownclient.UnknownClientService.class);

        if (servicio_activado){
            btn_accesibilidad.setEnabled(false);
            btn_continuar.setEnabled(true);
            btn_continuar.setTextColor(Color.parseColor("#ff00ff00"));
            btn_continuar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent iniciar = new Intent(PermisoActivity.this, InicioActivity.class);
                        startActivity(iniciar);
                        finish();
                    }
                }); 
        } else {
            btn_accesibilidad.setEnabled(true);
            btn_continuar.setEnabled(false);
         	btn_continuar.setTextColor(Color.parseColor("#ff575757"));   
        }
        
        // optimizacion de batería
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){        

            final String paquete = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            // en caso de no haber desactivado la optimización
            if (pm.isIgnoringBatteryOptimizations(paquete)) {
             	btn_bateria.setEnabled(false); 
                // en caso de haber desactivado la optimización   
            }else {
                btn_bateria.setEnabled(true); 
            }
            // en caso de que la version de android sea menor
        } else {
            btn_bateria.setVisibility(View.GONE);
            tv_bateria.setVisibility(View.GONE);
            tv_opcional.setVisibility(View.GONE);
        }
    }

    public static boolean isAccessibilityServiceEnabled(Context context, Class<? extends AccessibilityService> service) {
    AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
    List<AccessibilityServiceInfo> enabledServices = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK);

    for (AccessibilityServiceInfo enabledService : enabledServices) {
        ServiceInfo enabledServiceInfo = enabledService.getResolveInfo().serviceInfo;
        if (enabledServiceInfo.packageName.equals(context.getPackageName()) && enabledServiceInfo.name.equals(service.getName()))
            return true;
    }

    return false;
	}
    
    public void MostrarCambios(){
        String lenguaje = Locale.getDefault().toString();
        if (lenguaje.startsWith("es_")){
        
        final AlertDialog dialogoCambios = new AlertDialog.Builder(this).create();

        View inflar = getLayoutInflater().inflate(R.layout.dialogo_cambios,null);
        dialogoCambios.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        WebView historialCambios = (WebView) inflar.findViewById(R.id.wb_cambios);
        historialCambios.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });
        WebSettings webSettings = historialCambios.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        historialCambios.loadUrl("file:///android_asset/www/historialcambios.html");

        Button btn_aceptar = (Button) inflar.findViewById(R.id.btn_historial_cambios);   

        btn_aceptar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    SharedPreferences.Editor editor_dialogo = getSharedPreferences(mostrarDialogoCambios, MODE_PRIVATE).edit();
                    editor_dialogo.putString("EstadoDialogo", "visto");
                    editor_dialogo.apply();
                    dialogoCambios.dismiss();
                }
            });

        dialogoCambios.setView(inflar);
        dialogoCambios.setCancelable(false);
        dialogoCambios.show();

     } else {
         final AlertDialog dialogoCambios = new AlertDialog.Builder(this).create();

         View inflar = getLayoutInflater().inflate(R.layout.dialogo_cambios,null);
         dialogoCambios.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

         WebView historialCambios = (WebView) inflar.findViewById(R.id.wb_cambios);
         historialCambios.setWebViewClient(new WebViewClient() {
                 @Override
                 public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     view.loadUrl(url);
                     return false;
                 }
             });
         WebSettings webSettings = historialCambios.getSettings();
         webSettings.setJavaScriptEnabled(true);
         webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
         webSettings.setAllowFileAccessFromFileURLs(true);
         webSettings.setAllowUniversalAccessFromFileURLs(true);

         historialCambios.loadUrl("file:///android_asset/www/historialcambios-en.html");

         Button btn_aceptar = (Button) inflar.findViewById(R.id.btn_historial_cambios);   

         btn_aceptar.setOnClickListener(new View.OnClickListener(){
                 public void onClick(View v){
                     SharedPreferences.Editor editor_dialogo = getSharedPreferences(mostrarDialogoCambios, MODE_PRIVATE).edit();
                     editor_dialogo.putString("EstadoDialogo", "visto");
                     editor_dialogo.apply();
                     dialogoCambios.dismiss();
                 }
             });

         dialogoCambios.setView(inflar);
         dialogoCambios.setCancelable(false);
         dialogoCambios.show();
     }
    }
}
