package com.proyect.services.utils;

public enum EnumStatusResponse {

    OK(200, "Solicitud aceptada; la respuesta contiene el resultado."),
    OK_DELETE(200, "Solicitud aceptada; se ha eliminado correctamente el registro."),
    CREATED(201, "Se ha creado un recurso de forma satisfactoria."),
    CREATED_UPDATE(201, "Se ha actualizado un recurso de forma satisfactoria."),
    NO_CONTENT(204, "Se ha aceptado la solicitud, pero no había datos para devolver."),
    BAD_REQUEST(400, "La solicitud no fue válida."),
    BAD_REQUEST_CLIENT(400, "La solicitud con el cliente no fue válida."),
    UNAUTHORIZED(401, "Seguridad habilitada falta la información de autorización en la solicitud."),
    FORBIDDEN(403, "El cliente ha intentado acceder a un recurso al que no tiene acceso."),
    NOT_FOUND(404, "El recurso de destino no existe."),
    METHOD_NOT_ALLOWED(405, "El recurso de destino no admite el método HTTP solicitado."),
    NOT_ACCEPTABLE(406, "El recurso de destino no admite el formato de datos solicitado en la cabecera de Accept o el parámetro accept."),
    CONFLICT(409, "Se ha detectado un cambio conflictivo durante un intento de modificación de un recurso."),
    CONFLICT_DELETE(409, "Se ha detectado un cambio conflictivo durante un intento de modificación de un recurso."),
    UNSUPPORTED_MEDIA_TYPE(415, "El recurso de destino no admite el formato de datos del cuerpo de la solicitud especificado en la cabecera de Content-Type."),
    INTERNAL_SERVER_ERROR(500, "Se ha producido un error interno en el servidor.");

    private final int code;
    private final String message;

    EnumStatusResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}