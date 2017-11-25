package com.example.anthony.clinicplace;

/**
 * Created by Anthony on 04/11/2017.
 */
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
@DynamoDBTable(tableName = "clinicplace-mobilehub-2110396219-Usuarios")
public class Usuario {
    private String userid;
    private String correo;
    private String contraseña;
    private String username;
    private  String nombre;
    private String apellido;
    private String dni;
    private String distrito;
    private String tipo;

    @DynamoDBHashKey(attributeName = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    @DynamoDBRangeKey(attributeName = "correo")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @DynamoDBAttribute(attributeName = "contraseña")
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @DynamoDBAttribute(attributeName = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @DynamoDBAttribute(attributeName = "apellidos")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    @DynamoDBAttribute(attributeName = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    @DynamoDBAttribute(attributeName = "distrito")
    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
    @DynamoDBAttribute(attributeName = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
