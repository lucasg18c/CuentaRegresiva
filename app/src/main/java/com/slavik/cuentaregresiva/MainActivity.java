package com.slavik.cuentaregresiva;


import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static CanalNotificacion canalCataratas, canalCovid;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canalCataratas = new CanalNotificacion(
                getString(R.string.nombre_canal_cataratas),
                getString(R.string.descripcion_canal_cataratas),
                getString(R.string.id_canal_cataratas),
                NotificationManager.IMPORTANCE_HIGH);

        canalCovid = new CanalNotificacion(
                getString(R.string.nombre_canal_covid),
                getString(R.string.descripcion_canal_covid),
                getString(R.string.id_canal_covid),
                NotificationManager.IMPORTANCE_DEFAULT);

        Notificacion.crearCanal(this, canalCataratas);
        Notificacion.crearCanal(this, canalCovid);

        findViewById(R.id.tvNada).setOnClickListener(v -> {
            Notificacion.mostrarNotificacion(
                    this,
                    canalCovid,
                    "00:00",
                    "En 7 días se termina el aislamiento! 🤒🤧",
                    R.drawable.ic_coronavirus);
        });
    }
}