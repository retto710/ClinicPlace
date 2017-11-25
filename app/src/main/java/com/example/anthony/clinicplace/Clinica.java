package com.example.anthony.clinicplace;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by Anthony on 04/11/2017.
 */
@DynamoDBTable(tableName = "clinicplace-mobilehub-2110396219-Clinica")
public class Clinica {

    private String imageUrl="https://s3.amazonaws.com/clinicplace/logo.png";
    private String userid;
    private String nombre;
    private String sede;
    private String descripcion;
    private String numeroTelefonico;
    public Clinica() {

    }
    public Clinica(String Nombre, String Sede){
        this.setNombre(Nombre);
        this.setSede(Sede);

    }
    public Clinica(String Nombre, String Sede, String imageUrl) {
        this.setNombre(Nombre);
        this.setSede(Sede);
        this.setImageUrl(imageUrl);
    }
    @DynamoDBHashKey(attributeName = "userId")
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    @DynamoDBRangeKey(attributeName = "Nombre")
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @DynamoDBAttribute(attributeName = "Sede")
    public String getSede() {
        return sede;
    }
    public void setSede(String sede) {
        this.sede = sede;
    }
    public boolean hasImage(){
        return imageUrl!="1";
    }
    @DynamoDBAttribute(attributeName = "Imagen")
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @DynamoDBAttribute(attributeName = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @DynamoDBAttribute(attributeName = "numeroTelefonico")
    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }
}
