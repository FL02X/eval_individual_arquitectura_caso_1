package cajero;

public class Cuenta {
    private String numeroCuenta;
    private String nombreTitular;
    private double saldoActual;

    public Cuenta(String numeroCuenta, String nombreTitular, double saldoActual) {
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.saldoActual = saldoActual;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getCuatroUltimosDigitos() {
        if (numeroCuenta != null && numeroCuenta.length() >= 4) {
            return numeroCuenta.substring(numeroCuenta.length() - 4);
        }
        return numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }
}
