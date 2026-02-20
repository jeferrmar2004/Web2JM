package hn.uth.atm2uthgrup4;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 * Managed Bean para manejar la lógica de la pantalla de inicio de sesión (index.xhtml).
 */
@Named
@RequestScoped
public class loginBean {


    private String numeroCuenta;
    private String pin;
    private String mensaje;


    public void LoginBean() {
        this.mensaje = "Ingrese sus datos para acceder al sistema.";
    }

    /**
     * Procesa la solicitud de ingreso del usuario.
     * @return String con la regla de navegación (ej: "menu" si es exitoso).
     */
    public String ingresar() {

        if (numeroCuenta == null || pin == null || numeroCuenta.isEmpty() || pin.isEmpty()) {
            mensaje = "ERROR: Por favor ingrese la cuenta y el PIN.";
            return null;
        }
        if ("1001".equals(numeroCuenta) && "1234".equals(pin)) {
            mensaje = "¡Ingreso exitoso! Redirigiendo al menú...";
            return "retirar";
        } else {
            this.pin = null;
            mensaje = "ERROR: Número de cuenta o PIN incorrectos. Intente de nuevo.";
            return null;
        }
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}