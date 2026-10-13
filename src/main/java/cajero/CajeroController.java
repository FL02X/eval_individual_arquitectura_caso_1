package cajero;

public class CajeroController {
    private CajeroModel modelo;

    public CajeroController(CajeroModel modelo) {
        this.modelo = modelo;
    }

    public void seleccionarMonto(double monto) {
        this.modelo.seleccionarMonto(monto);
    }

    public void realizarRetiro() {
        this.modelo.realizarRetiro();
    }
}
