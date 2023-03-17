package com.slavik.cuentaregresiva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import java.time.Duration;
import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class CuentaWidget extends AppWidgetProvider {

    private AlarmManager alarmManager;
    private static final String ACTUALIZAR_WIDGET = "ACTUALIZAR_WIDGET";
    private static String diasRestantes;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cuenta_widget);
        diasRestantes = calcularDiasRestantes();
        views.setTextViewText(R.id.lblDias, diasRestantes);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static String calcularDiasRestantes() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Calendar evento = Calendar.getInstance();
            evento.set(Calendar.YEAR, 2022);
            evento.set(Calendar.MONTH, Calendar.FEBRUARY);
            evento.set(Calendar.DAY_OF_MONTH, 20);

            Calendar fechaActual = Calendar.getInstance();
            return String.valueOf(Duration.between(fechaActual.toInstant(), evento.toInstant()).toDays());
        }
        return "";
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

        Intent i = new Intent(context, CuentaWidget.class);
        i.setAction(ACTUALIZAR_WIDGET);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i, 0);

        Calendar fecha = Calendar.getInstance();
        fecha.add(Calendar.DAY_OF_YEAR, 1);
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 1);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(
                AlarmManager.RTC,
                fecha.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                pendingIntent);

        onUpdate(context);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private void onUpdate(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        ComponentName thisAppWidgetComponentName = new ComponentName(context.getPackageName(),getClass().getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidgetComponentName);
        onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTUALIZAR_WIDGET.equals(intent.getAction())){
            onUpdate(context);
            Notificacion.mostrarNotificacion(
                    context,
                    MainActivity.canalCataratas,
                    "00:00",
                    "En " + diasRestantes + " días nos vamos a cataratas! 🛫🧳😎😍",
                    R.drawable.ic_airplane);
        }
        else{
            super.onReceive(context, intent);
        }
    }
}