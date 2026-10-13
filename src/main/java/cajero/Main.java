package cajero;

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("1234567890", "HIDDEKEL YOCUPICIO RUIZ", 5000.0);
        CajeroModel modelo = new CajeroModel(cuenta);
        CajeroController controlador = new CajeroController(modelo);
        CajeroView vistaCajero = new CajeroView(controlador);

        modelo.agregarObservador(vistaCajero);

        PantallaMenu menu = new PantallaMenu(vistaCajero);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
