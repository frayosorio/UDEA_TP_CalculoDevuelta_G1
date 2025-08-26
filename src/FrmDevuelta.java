import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmDevuelta extends JFrame {

    // variables globales
    private int[] denominacion = new int[] { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    private int[] existencia = new int[denominacion.length];
    private String[] encabezados = new String[] { "Cantidad", "Presentación", "Denominación" };
    private JComboBox cmbDenominacion;
    private JTextField txtExistencia, txtDevuelta;
    private JTable tblDevuelta;

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

        // Agregar elementos para leer el valor a devolver
        JLabel lblDevuelta = new JLabel("Valor a devolver:");
        lblDevuelta.setBounds(10, 70, 100, 25);
        getContentPane().add(lblDevuelta);

        txtDevuelta = new JTextField();
        txtDevuelta.setBounds(150, 70, 100, 25);
        getContentPane().add(txtDevuelta);

        JButton btnDevuelta = new JButton("Calcular");
        btnDevuelta.setBounds(260, 70, 100, 25);
        getContentPane().add(btnDevuelta);

        // Agregar rejilla de datos
        tblDevuelta = new JTable();
        JScrollPane spDevuelta = new JScrollPane(tblDevuelta);
        spDevuelta.setBounds(10, 100, 450, 200);
        getContentPane().add(spDevuelta);

        // Asignar el modelo de datos de la rejilla
        DefaultTableModel dtm = new DefaultTableModel(null, encabezados);
        tblDevuelta.setModel(dtm);

        // Agregar el evento para calcular la devuelta
        btnDevuelta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcularDevuelta();
            }

        });

    }

    private void consultarExistencia() {
        int existenciaActual = existencia[cmbDenominacion.getSelectedIndex()];
        txtExistencia.setText(String.valueOf(existenciaActual));
    }

    private void actualizarExistencia() {
        int existenciaActual = Integer.parseInt(txtExistencia.getText());
        existencia[cmbDenominacion.getSelectedIndex()] = existenciaActual;
    }

    private void calcularDevuelta() {

        int[] devuelta = new int[denominacion.length];

        int valorDevuelta = Integer.parseInt(txtDevuelta.getText());
        int i = 0;
        int totalFilas = 0;
        while (valorDevuelta > 0 && i < denominacion.length) {
            if (valorDevuelta > denominacion[i]) {
                int cantidadNecesaria = (int) (valorDevuelta / denominacion[i]);
                devuelta[i] = existencia[i] >= cantidadNecesaria ? cantidadNecesaria : existencia[i];
                // if (existencia[i] >= cantidadNecesaria) {
                // devuelta[i] = cantidadNecesaria;
                // } else {
                // devuelta[i] = existencia[i];
                // }
                if (devuelta[i] > 0) {
                    valorDevuelta -= denominacion[i] * devuelta[i];
                    totalFilas++;
                }
            }
            i++;
        }

        String[][] datos = new String[totalFilas][encabezados.length];
        totalFilas = 0;
        for (i = 0; i < devuelta.length; i++) {
            if (devuelta[i] > 0) {
                datos[totalFilas][0] = String.valueOf(devuelta[i]);
                datos[totalFilas][1] = denominacion[i] <= 1000 ? "moneda" : "billete";
                datos[totalFilas][2] =  String.valueOf(denominacion[i]);
                totalFilas++;
            }
        }

         // Asignar el modelo de datos de la rejilla
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblDevuelta.setModel(dtm);

        if(valorDevuelta>0){
            JOptionPane.showMessageDialog(null, "Queda pendiente $ "+valorDevuelta + " por devolver");
        }

    }

}
