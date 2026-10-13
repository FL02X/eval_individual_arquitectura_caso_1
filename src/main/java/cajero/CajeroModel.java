package cajero;

import java.util.ArrayList;
import java.util.List;

public class CajeroModel {
    private List<ObservadorCajero> observadores;
    private Cuenta cuenta;
    private double montoSeleccionado;
    private double comision;
    private boolean retiroRealizado;
    private Recibo recibo;

    public CajeroModel(Cuenta cuenta) {
        this.cuenta = cuenta;
        this.observadores = new ArrayList<>();
        this.montoSeleccionado = 0.0;
        this.comision = 15.0;
        this.retiroRealizado = false;
        this.recibo = null;
    }

    public void agregarObservador(ObservadorCajero observador) {
        this.observadores.add(observador);
    }

    public void notificarObservadores() {
        for (int i = 0; i < this.observadores.size(); i++) {
            this.observadores.get(i).actualizar(this);
        }
    }

    public void seleccionarMonto(double monto) {
        this.montoSeleccionado = monto;
        this.retiroRealizado = false;
        notificarObservadores();
    }

    public void realizarRetiro() {
        if (this.montoSeleccionado > 0) {
            double totalADescontar = this.montoSeleccionado + this.comision;
            if (this.cuenta.getSaldoActual() >= totalADescontar) {
                double nuevoSaldo = this.cuenta.getSaldoActual() - totalADescontar;
                this.cuenta.setSaldoActual(nuevoSaldo);
                this.recibo = new Recibo(
                    this.cuenta.getCuatroUltimosDigitos(),
                    this.cuenta.getNombreTitular(),
                    this.montoSeleccionado,
                    this.comision
                );
                this.retiroRealizado = true;
                notificarObservadores();
            }
        }
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public double getMontoSeleccionado() {
        return montoSeleccionado;
    }

    public double getComision() {
        return comision;
    }

    public boolean isRetiroRealizado() {
        return retiroRealizado;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public double getMontoFinal() {
        return this.cuenta.getSaldoActual() - (this.montoSeleccionado + this.comision);
    }
}
