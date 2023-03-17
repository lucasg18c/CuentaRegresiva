package com.slavik.cuentaregresiva;

import org.junit.Test;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ExampleUnitTest {

    @Test
    public void restaFechas() {
        Calendar evento = Calendar.getInstance();
        evento.set(Calendar.YEAR, 2022);
        evento.set(Calendar.MONTH, Calendar.FEBRUARY);
        evento.set(Calendar.DAY_OF_MONTH, 20);

        // Fecha: 06/01/2022
        Calendar fechaActual = Calendar.getInstance();
        fechaActual.set(Calendar.YEAR, 2022);
        fechaActual.set(Calendar.MONTH, Calendar.JANUARY);
        fechaActual.set(Calendar.DAY_OF_MONTH, 6);

        long dias = Duration.between(fechaActual.toInstant(), evento.toInstant()).toDays();

        assertEquals(
                45,
                dias
        );

        // Fecha: 07/01/2022 : 00:00:01
        fechaActual.set(Calendar.YEAR, 2022);
        fechaActual.set(Calendar.DAY_OF_MONTH, 7);
        fechaActual.set(Calendar.HOUR_OF_DAY, 0);
        fechaActual.set(Calendar.MINUTE, 0);
        fechaActual.set(Calendar.SECOND, 1);

        dias = Duration.between(fechaActual.toInstant(), evento.toInstant()).toDays();

        assertEquals(
                44,
                dias
        );
    }
}