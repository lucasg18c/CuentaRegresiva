package com.slavik.cuentaregresiva;

public class CanalNotificacion {
    private String id, nombre, descripcion;
    private int importancia;

    public CanalNotificacion(String id, String nombre, String descripcion, int importancia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.importancia = importancia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }
}
