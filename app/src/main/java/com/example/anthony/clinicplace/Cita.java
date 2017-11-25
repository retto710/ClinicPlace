package com.example.anthony.clinicplace;

        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
        import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
/**
 * Created by Renato on 25/11/2017.
 */
@DynamoDBTable(tableName = "clinicplace-mobilehub-2110396219-cita")
public class Cita {
    private String _userId;
    private String _correoUsuario;
    private String _descripcion;
    private String _especialidad;
    private String _horaFin;
    private String _horaInicio;
    private String _nombreClinica;
    private String _sede;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "correo usuario")
    public String getCorreoUsuario() {
        return _correoUsuario;
    }

    public void setCorreoUsuario(final String _correoUsuario) {
        this._correoUsuario = _correoUsuario;
    }
    @DynamoDBAttribute(attributeName = "descripcion")
    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(final String _descripcion) {
        this._descripcion = _descripcion;
    }
    @DynamoDBAttribute(attributeName = "especialidad")
    public String getEspecialidad() {
        return _especialidad;
    }

    public void setEspecialidad(final String _especialidad) {
        this._especialidad = _especialidad;
    }
    @DynamoDBAttribute(attributeName = "hora fin")
    public String getHoraFin() {
        return _horaFin;
    }

    public void setHoraFin(final String _horaFin) {
        this._horaFin = _horaFin;
    }
    @DynamoDBAttribute(attributeName = "hora inicio")
    public String getHoraInicio() {
        return _horaInicio;
    }

    public void setHoraInicio(final String _horaInicio) {
        this._horaInicio = _horaInicio;
    }
    @DynamoDBAttribute(attributeName = "nombre clinica")
    public String getNombreClinica() {
        return _nombreClinica;
    }

    public void setNombreClinica(final String _nombreClinica) {
        this._nombreClinica = _nombreClinica;
    }
    @DynamoDBAttribute(attributeName = "sede")
    public String getSede() {
        return _sede;
    }

    public void setSede(final String _sede) {
        this._sede = _sede;
    }
}
