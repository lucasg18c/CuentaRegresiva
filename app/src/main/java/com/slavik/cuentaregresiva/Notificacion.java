package com.slavik.cuentaregresiva;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notificacion {

    public static void crearCanal(Context context, CanalNotificacion canal) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(canal.getId(), canal.getNombre(), importance);
            channel.setDescription(canal.getDescripcion());
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void mostrarNotificacion(Context context, CanalNotificacion canal, String titulo, String contenido, int icono) {
        NotificationCompat.Builder buider =
                new NotificationCompat.Builder(context, canal.getId())
                        .setSmallIcon(icono)
                        .setContentTitle(titulo)
                        .setContentText(contenido)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setVibrate(new long[] {100, 250, 100, 500})
                        .setAutoCancel(true);

        NotificationManagerCompat.from(context).notify(777, buider.build());
    }

}
