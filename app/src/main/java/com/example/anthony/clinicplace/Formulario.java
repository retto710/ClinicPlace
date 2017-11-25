package com.example.anthony.clinicplace;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.net.CacheRequest;
import java.text.Normalizer;

/**
 * Created by Anthony on 25/11/2017.
 */
@DynamoDBTable(tableName = "clinicplace-mobilehub-2110396219-formularios")
public class Formulario {
    private Number idFormulario;
    private String clinica;
    private String correo;
    private String cargo;
    private String dni;
    private String mensaje;

    public Formulario(){}

    public Formulario(Number idFormulario,String clinica,String correo,String dni, String mensaje,String cargo){
        this.setClinica(clinica);
        this.setCorreo(correo);
        this.setDni(dni);
        this.setMensaje(mensaje);
        this.setIdFormulario(idFormulario);
        this.setCargo(cargo);
    }

    @DynamoDBHashKey(attributeName = "idFormulario")
    public Number getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Number idFormulario) {
        this.idFormulario = idFormulario;
    }
    @DynamoDBRangeKey(attributeName = "clinica")
    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }
    @DynamoDBAttribute(attributeName = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @DynamoDBAttribute(attributeName = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    @DynamoDBAttribute(attributeName = "mensaje")
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    @DynamoDBAttribute(attributeName = "cargo")
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
