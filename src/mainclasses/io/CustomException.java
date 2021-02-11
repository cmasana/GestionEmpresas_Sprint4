package mainclasses.io;

public class CustomException extends Exception {
    // Código de error
    private final int errorId;

    // Constructor sobrecargado
    public CustomException(int errorId) {
        super();
        this.errorId = errorId;
    }

    @Override
    public String getMessage() {
        String alertMessage = "";

        switch (errorId) {
            case 1111:
                alertMessage = "Error: Por favor, rellene todos los campos";
                break;
            case 1112:
                alertMessage = "Error: El DNI que ha introducido es incorrecto";
                break;
            case 1113:
                alertMessage = "Error: El NSS que ha introducido es incorrecto";
                break;
            case 1114:
                alertMessage = "Error: No hay ninguna fila seleccionada";
                break;
            case 1115:
                alertMessage = "Error: No hay ningún empleado creado";
                break;
            case 1116:
                alertMessage = "Error: No hay ninguna propuesta creada";
                break;
        }
        return alertMessage;
    }
}
