import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrmDevuelta extends JFrame {

    // variables globales
    private int[] denominacion = new int[] { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    private int[] existencia = new int[denominacion.length];
    private JComboBox cmbDenominacion;
    private JTextField txtExistencia;

    // metodo constructor
    public FrmDevuelta() {
        setTitle("Calculo de devueltas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Agregar una etiqueta
        JLabel lblDenominacion = new JLabel("Denominación:");
        lblDenominacion.setBounds(100, 10, 100, 25);
        getContentPane().add(lblDenominacion);

        // Agregar una lista desplegable
        cmbDenominacion = new JComboBox();
        cmbDenominacion.setBounds(200, 10, 100, 25);
        getContentPane().add(cmbDenominacion);

        // Crear un modelo de datos para la lista desplegable
        String[] strDenominacion = new String[denominacion.length];
        for (int i = 0; i < denominacion.length; i++) {
            strDenominacion[i] = String.valueOf(denominacion[i]);
        }
        cmbDenominacion.setModel(new DefaultComboBoxModel(strDenominacion));

        // Agregar un boton
        JButton btnActualizarExistencia = new JButton("Actualizar Existencia");
        btnActualizarExistencia.setBounds(10, 40, 180, 25);
        getContentPane().add(btnActualizarExistencia);

        // Agregar una caja de texto
        txtExistencia = new JTextField();
        txtExistencia.setBounds(200, 40, 100, 25);
        getContentPane().add(txtExistencia);

        // Agregar los eventos para actualizar las EXISTENCIAS
        cmbDenominacion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                consultarExistencia();
            }
        });

        btnActualizarExistencia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarExistencia();
            }

        });

    }

    private void consultarExistencia() {
        int existenciaActual = existencia[cmbDenominacion.getSelectedIndex()];
        txtExistencia.setText(String.valueOf(existenciaActual));
    }

    private void actualizarExistencia() {

    }

}
