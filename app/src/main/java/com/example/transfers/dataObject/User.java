package com.example.transfers.dataObject;

import java.io.Serializable;

public class User  implements Serializable {

    private int id;
    private String nombrePersonaJuridica;
    private String codEmpresa;
    private String tipoId;
    private String direccion;
    private String telefono;
    private String apartadoAereo;
    private String estado;
    private String primerNombre;
    private String segundoNombre;
    private String priemrApellido;
    private String segundoApellido;
    private String correo;
    private String digitoVerificacion;
    private String tipoNit;
    private String tipoContribuyente;
    private String tipoTercero;
    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePersonaJuridica() {
        return nombrePersonaJuridica;
    }

    public void setNombrePersonaJuridica(String nombrePersonaJuridica) {
        this.nombrePersonaJuridica = nombrePersonaJuridica;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApartadoAereo() {
        return apartadoAereo;
    }

    public void setApartadoAereo(String apartadoAereo) {
        this.apartadoAereo = apartadoAereo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPriemrApellido() {
        return priemrApellido;
    }

    public void setPriemrApellido(String priemrApellido) {
        this.priemrApellido = priemrApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDigitoVerificacion() {
        return digitoVerificacion;
    }

    public void setDigitoVerificacion(String digitoVerificacion) {
        this.digitoVerificacion = digitoVerificacion;
    }

    public String getTipoNit() {
        return tipoNit;
    }

    public void setTipoNit(String tipoNit) {
        this.tipoNit = tipoNit;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(String tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    public String getTipoTercero() {
        return tipoTercero;
    }

    public void setTipoTercero(String tipoTercero) {
        this.tipoTercero = tipoTercero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
