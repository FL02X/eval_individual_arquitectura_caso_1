package cajero;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaMenu extends JFrame {
    private JButton btnRetirar;

    public PantallaMenu(CajeroView vistaCajero) {
        setTitle("Menu Principal - Cajero");
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        this.btnRetirar = new JButton("Retirar");
        this.btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vistaCajero.setLocationRelativeTo(null);
                vistaCajero.setVisible(true);
                setVisible(false);
            }
        });

        panelCentro.add(this.btnRetirar);
        add(panelCentro, BorderLayout.CENTER);
    }
}
