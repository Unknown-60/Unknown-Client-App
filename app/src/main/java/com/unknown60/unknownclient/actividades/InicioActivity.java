package com.unknown60.unknownclient.actividades;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.appcompat.R;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import com.unknown60.unknownclient.UnknownClientService;
import com.unknown60.unknownclient.utils.MostrarToast;
import java.util.ArrayList;
import java.util.List;

public class InicioActivity extends AppCompatActivity implements OnItemSelectedListener {
    Button btn_atacar;
    Button btn_desactivar_servicio;
    EditText et_mensaje;
    TextView tv_estado_actual;
    
    private RadioGroup radioGroup_uc;
	private RadioButton rb_ninguno, rb_estilo_negrita, rb_estilo_italica, rb_estilo_monoespaciado, rb_estilo_tachado, rb_estilo_vacio, rb_estilo_invertir;
    
    Spinner spItems;
    ArrayAdapter arrayAdapter;
    List<String>listData=new ArrayList<String>();
    
    public static final String preferencias = "EstadoServicio";
    public static final String cantidadMensajes = "CantidadMensajes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_atacar = (Button) findViewById(R.id.btn_atacar);       
        et_mensaje = (EditText) findViewById(R.id.et_mensaje);
		tv_estado_actual = (TextView) findViewById(R.id.tv_estado_actual);
        btn_desactivar_servicio = (Button) findViewById(R.id.btn_estado_servicio);
        
        listData.add("1");
        listData.add("5");
        listData.add("10");
        listData.add("15");
        listData.add("20");
        listData.add("30");
        listData.add("40");
        listData.add("60");
        listData.add("70");
        listData.add("80");
        listData.add("100");
        listData.add("200");
        listData.add("400");
        listData.add("500");
        listData.add("800");
        listData.add("1000");
        listData.add("1500");
        listData.add("2000");
        listData.add("3000");
        listData.add("5000");
        listData.add("8000");
        listData.add("10000");

        spItems = (Spinner) findViewById(R.id.spItems);
        
        arrayAdapter = new ArrayAdapter(InicioActivity.this,R.layout.row_spinner, listData);
        spItems.setAdapter(arrayAdapter);
        spItems.setOnItemSelectedListener(this);       

        SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
        editor.putString("estadoServicio", "desactivado");
        editor.apply();
        
        boolean servicio_activado = isAccessibilityServiceEnabled(InicioActivity.this, com.unknown60.unknownclient.UnknownClientService.class);
        
        if(servicio_activado){
            tv_estado_actual.setTextColor(Color.parseColor("#ff00ff00"));
            tv_estado_actual.setText(getString(R.string.tv_activado));
        } else {
            tv_estado_actual.setTextColor(Color.parseColor("#ffff0000"));
            tv_estado_actual.setText(getString(R.string.tv_desactivado));
        }
        
        btn_desactivar_servicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
					Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                }
            });
            
       radioGroup_uc = (RadioGroup) findViewById(R.id.radiogroup_uc);
       radioGroup_uc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
 
                }
            });
        
        rb_ninguno = (RadioButton) findViewById(R.id.rb_ninguno);
        rb_estilo_negrita = (RadioButton) findViewById(R.id.rb_estilo_negrita);
        rb_estilo_italica = (RadioButton) findViewById(R.id.rb_estilo_italica);
	    rb_estilo_monoespaciado = (RadioButton) findViewById(R.id.rb_estilo_monoespaciado);
        rb_estilo_tachado = (RadioButton) findViewById(R.id.rb_estilo_tachado);
        rb_estilo_vacio = (RadioButton) findViewById(R.id.rb_estilo_vacio);
	    rb_estilo_invertir = (RadioButton) findViewById(R.id.rb_estilo_invertir);
        
        btn_atacar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //si el mensaje está vacío
                    if (et_mensaje.getText().toString().equals("")) {
                        MostrarToast.makeText(InicioActivity.this, getString(R.string.toast_entrada_vacia), MostrarToast.LENGTH_LONG).show();
                    }
                    else {              
                        int idSeleccionado = radioGroup_uc.getCheckedRadioButtonId();
                                        
                        SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
                        editor.putString("estadoServicio", "activado");
                        editor.apply();
                        
                        String mensaje;
                        
                        if(idSeleccionado == rb_ninguno.getId()) {
                            mensaje = et_mensaje.getText().toString();
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensaje);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } else if(idSeleccionado == rb_estilo_negrita.getId()) {
                            mensaje = "*" + et_mensaje.getText().toString() + "*";
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensaje);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } else if(idSeleccionado == rb_estilo_italica.getId()) {
							mensaje = "_" + et_mensaje.getText().toString() + "_";
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensaje);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } else if(idSeleccionado == rb_estilo_monoespaciado.getId()) {
							mensaje = "```" + et_mensaje.getText().toString() + "```";
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensaje);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } else if(idSeleccionado == rb_estilo_tachado.getId()) {
							mensaje = "~" + et_mensaje.getText().toString() + "~";
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensaje);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } else if(idSeleccionado == rb_estilo_vacio.getId()) {
							mensaje = "‌"; //adentro de esta String hay un texto
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensaje);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                        } else if(idSeleccionado == rb_estilo_invertir.getId()) {
                            mensaje = et_mensaje.getText().toString();
                            String mensajeInvertido = new StringBuffer(mensaje).reverse().toString();
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", mensajeInvertido);
                            clipboard.setPrimaryClip(clip);
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, mensajeInvertido);
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage("com.whatsapp");
                            startActivity(sendIntent);
                                               
                        } 
                    }
                }
            });
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

    public void MostrarAcerca(){
        final AlertDialog dialogoAcerca = new AlertDialog.Builder(this).create();

        View inflar = getLayoutInflater().inflate(R.layout.dialogo_acerca,null);
        dialogoAcerca.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btn_cerrar = (Button) inflar.findViewById(R.id.btn_cerrar);
        TextView tv_github = (TextView) inflar.findViewById(R.id.tv_github);

        btn_cerrar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    dialogoAcerca.dismiss();
                }
            });

        tv_github.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){                 
                    Intent abrir_url = new Intent(Intent.ACTION_VIEW);
                    abrir_url.setData(Uri.parse("https://www.github.com/Unknown-60/Unknown-Client-App"));
                    startActivity(abrir_url);
                }
            });
        dialogoAcerca.setView(inflar);
        dialogoAcerca.setCancelable(false);
        dialogoAcerca.show();

    }
    
    @Override
    public void onResume(){
        super.onResume();
        
        SharedPreferences.Editor editor = getSharedPreferences(preferencias, MODE_PRIVATE).edit();
        editor.putString("estadoServicio", "desactivado");
        editor.apply();
        
        boolean servicio_activado = isAccessibilityServiceEnabled(InicioActivity.this, com.unknown60.unknownclient.UnknownClientService.class);
       
        if (servicio_activado){
            tv_estado_actual.setTextColor(Color.parseColor("#ff00ff00"));
            tv_estado_actual.setText(getString(R.string.tv_activado));
            btn_desactivar_servicio.setText(getString(R.string.btn_desactivar));
            
            btn_atacar.setEnabled(true);
         	btn_atacar.setTextColor(Color.parseColor("#ff00ff00"));
            
            btn_desactivar_servicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        startActivity(intent);
                    }
                });
        } else {
            tv_estado_actual.setTextColor(Color.parseColor("#ffff0000"));
            tv_estado_actual.setText(getString(R.string.tv_desactivado));
            btn_desactivar_servicio.setText(getString(R.string.btn_activar));

			btn_atacar.setEnabled(false);
         	btn_atacar.setTextColor(Color.parseColor("#ff575757"));
            
            btn_desactivar_servicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        startActivity(intent);
                    }
                });
        }
        
        stopService(new Intent(this, UnknownClientService.class));
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id)
    {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spItems)
        {                   
            String item = parent.getItemAtPosition(position).toString();
            SharedPreferences.Editor editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
            switch (item){
                case "1":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "1");
                    editor_cantidad.apply();
                    break;
                case "5":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "5");
                    editor_cantidad.apply();
                    break;
                case "10":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "10");
                    editor_cantidad.apply();
                    break;
                case "15":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "15");
                    editor_cantidad.apply();
                    break;
                case "20":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "20");
                    editor_cantidad.apply();
                    break;
                case "30":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "30");
                    editor_cantidad.apply();
                    break;
                case "40":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "40");
                    editor_cantidad.apply();
                    break;
                case "60":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "60");
                    editor_cantidad.apply();
                    break;
                case "70":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "70");
                    editor_cantidad.apply();
                    break;
                case "80":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "80");
                    editor_cantidad.apply();
                    break;
                case "100":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "100");
                    editor_cantidad.apply();
                    break;
                case "200":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "200");
                    editor_cantidad.apply();
                    break;
                case "400":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "400");
                    editor_cantidad.apply();
                    break;
                case "500":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "500");
                    editor_cantidad.apply();
                    break;
                case "800":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "800");
                    editor_cantidad.apply();
                    break;
                case "1000":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "1000");
                    editor_cantidad.apply();
                    break;
                case "1500":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "1500");
                    editor_cantidad.apply();
                    break;
                case "2000":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "2000");
                    editor_cantidad.apply();
                    break;
                case "3000":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "3000");
                    editor_cantidad.apply();
                    break;
                case "5000":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "5000");
                    editor_cantidad.apply();
                    break;
                case "8000":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "8000");
                    editor_cantidad.apply();
                    break;
                case "10000":
                    editor_cantidad = getSharedPreferences(cantidadMensajes, MODE_PRIVATE).edit();
                    editor_cantidad.putString("CantidadMensajes", "10000");
                    editor_cantidad.apply();
                    break;

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
 
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.menu_restablecer:
                et_mensaje.setText("");
                spItems.setSelection(0);
                rb_ninguno.setChecked(true);
                break;
            case R.id.menu_acerca:
                MostrarAcerca();
                break;
        }
        return super.onOptionsItemSelected(item);
    } 
}
