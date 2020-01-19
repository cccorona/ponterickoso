package mx.com.baseapplication.model;

import java.io.Serializable;

import mx.com.baseapplication.utils.Enums;

public class GenericalError implements Serializable {


    private String descripcion;
    private Enums.ErrorType errorType;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Enums.ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(Enums.ErrorType errorType) {
        this.errorType = errorType;
    }
}
