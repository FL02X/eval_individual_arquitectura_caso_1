package cajero;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CajeroView extends JFrame implements ObservadorCajero {
    private CajeroController controlador;

    private JList<String> listaMontos;
    private JLabel lblNombreCliente;
    private JLabel lblSaldoActual;
    private JLabel lblMontoElegido;
    private JLabel lblComision;
    private JLabel lblSaldoFinal;
    private JButton btnRetirarEfectivo;

    private JLabel lblReciboTitulo;
    private JLabel lblReciboCuenta;
    private JLabel lblReciboNombre;
    private JLabel lblReciboMonto;
    private JLabel lblReciboComision;

    private JPanel panelEstadoCuenta;
    private JPanel panelRecibo;

    public CajeroView(CajeroController controlador) {
        this.controlador = controlador;

        setTitle("Cajero Automatico - Retiro de Efectivo");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        String[] montos = {"200.00", "400.00", "600.00", "800.00", "1000.00", "1200.00"};
        this.listaMontos = new JList<>(montos);
        this.listaMontos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String valor = listaMontos.getSelectedValue();
                    if (valor != null) {
                        double monto = Double.parseDouble(valor);
                        controlador.seleccionarMonto(monto);
                    }
                }
            }
        });

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(new JLabel("Cantidades disponibles (multiplos de 200):", JLabel.CENTER), BorderLayout.NORTH);
        panelIzquierdo.add(new JScrollPane(this.listaMontos), BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());

        this.panelEstadoCuenta = new JPanel(new GridLayout(6, 1));
        this.lblNombreCliente = new JLabel("Cliente: HIDDEKEL YOCUPICIO RUIZ");
        this.lblSaldoActual = new JLabel("Saldo Actual: $5000.00");
        this.lblMontoElegido = new JLabel("Cantidad a Retirar: $0.00");
        this.lblComision = new JLabel("Comision por retiro: $15.00");
        this.lblSaldoFinal = new JLabel("Monto final si retira: $5000.00");
        this.btnRetirarEfectivo = new JButton("Retirar Efectivo");
        this.btnRetirarEfectivo.setEnabled(false);

        this.btnRetirarEfectivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.realizarRetiro();
            }
        });

        this.panelEstadoCuenta.add(this.lblNombreCliente);
        this.panelEstadoCuenta.add(this.lblSaldoActual);
        this.panelEstadoCuenta.add(this.lblMontoElegido);
        this.panelEstadoCuenta.add(this.lblComision);
        this.panelEstadoCuenta.add(this.lblSaldoFinal);
        this.panelEstadoCuenta.add(this.btnRetirarEfectivo);

        this.panelRecibo = new JPanel(new GridLayout(5, 1));
        this.lblReciboTitulo = new JLabel("--- RECIBO DE RETIRO ---", JLabel.CENTER);
        this.lblReciboCuenta = new JLabel("Cuenta: **** ----");
        this.lblReciboNombre = new JLabel("Nombre: ----");
        this.lblReciboMonto = new JLabel("Cantidad Retirada: $0.00");
        this.lblReciboComision = new JLabel("Cobro por comision: $0.00");

        this.panelRecibo.add(this.lblReciboTitulo);
        this.panelRecibo.add(this.lblReciboCuenta);
        this.panelRecibo.add(this.lblReciboNombre);
        this.panelRecibo.add(this.lblReciboMonto);
        this.panelRecibo.add(this.lblReciboComision);
        this.panelRecibo.setVisible(false);

        panelDerecho.add(this.panelEstadoCuenta, BorderLayout.NORTH);
        panelDerecho.add(this.panelRecibo, BorderLayout.CENTER);

        add(panelIzquierdo);
        add(panelDerecho);
    }

    public void actualizar(CajeroModel modelo) {
        if (modelo.isRetiroRealizado()) {
            this.panelEstadoCuenta.setVisible(false);
            this.panelRecibo.setVisible(true);

            Recibo r = modelo.getRecibo();
            this.lblReciboCuenta.setText("Cuenta: **** " + r.getUltimosDigitos());
            this.lblReciboNombre.setText("Nombre: " + r.getNombreTitular());
            this.lblReciboMonto.setText("Cantidad Retirada: $" + r.getCantidadRetirada());
            this.lblReciboComision.setText("Cobro por comision: $" + r.getCobroComision());
        } else {
            this.panelEstadoCuenta.setVisible(true);
            this.panelRecibo.setVisible(false);

            this.lblNombreCliente.setText("Cliente: " + modelo.getCuenta().getNombreTitular());
            this.lblSaldoActual.setText("Saldo Actual: $" + modelo.getCuenta().getSaldoActual());
            this.lblMontoElegido.setText("Cantidad a Retirar: $" + modelo.getMontoSeleccionado());
            this.lblComision.setText("Comision por retiro: $" + modelo.getComision());
            this.lblSaldoFinal.setText("Monto final si retira: $" + modelo.getMontoFinal());

            if (modelo.getMontoSeleccionado() > 0) {
                this.btnRetirarEfectivo.setEnabled(true);
            } else {
                this.btnRetirarEfectivo.setEnabled(false);
            }
        }
    }
}
