package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.ProductoController;
import vallegrande.edu.pe.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FrmProducto extends JFrame {

    private JTextField txtNombre;
    private JTextField txtDescripcion;

    private JComboBox<String> cboCategoria;

    private JRadioButton rbtNacional;
    private JRadioButton rbtImportado;

    private JCheckBox chkDisponible;

    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private JTable tabla;
    private DefaultTableModel modelo;

    private ProductoController controller;

    public FrmProducto() {

        controller = new ProductoController();

        setTitle("Registro de Productos");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // LABELS Y CAMPOS
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 20, 200, 25);
        add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 60, 100, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 60, 200, 25);
        add(txtDescripcion);

        // COMBOBOX
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 100, 100, 25);
        add(lblCategoria);

        cboCategoria = new JComboBox<>();
        cboCategoria.addItem("1");
        cboCategoria.addItem("2");
        cboCategoria.setBounds(120, 100, 200, 25);
        add(cboCategoria);

        // RADIOBUTTON
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(20, 140, 100, 25);
        add(lblTipo);

        rbtNacional = new JRadioButton("Nacional");
        rbtNacional.setBounds(120, 140, 100, 25);
        add(rbtNacional);

        rbtImportado = new JRadioButton("Importado");
        rbtImportado.setBounds(220, 140, 100, 25);
        add(rbtImportado);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbtNacional);
        grupo.add(rbtImportado);

        // CHECKBOX
        chkDisponible = new JCheckBox("Disponible");
        chkDisponible.setBounds(120, 170, 120, 25);
        add(chkDisponible);

        // BOTONES
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 210, 100, 25);
        add(btnGuardar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(130, 210, 100, 25);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 210, 100, 25);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(350, 210, 100, 25);
        add(btnLimpiar);

        // TABLA
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Tipo");
        modelo.addColumn("Disponible");
        modelo.addColumn("Categoría");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 260, 640, 180);
        add(scroll);

        // EVENTOS
        btnGuardar.addActionListener(e -> guardar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());

        cargarTabla();

        setVisible(true);
    }

    // GUARDAR
    private void guardar() {

        Producto p = new Producto();

        p.setNombre(txtNombre.getText());
        p.setDescripcion(txtDescripcion.getText());

        p.setTipo(rbtNacional.isSelected() ? "Nacional" : "Importado");
        p.setDisponible(chkDisponible.isSelected());
        p.setIdCategoria(Integer.parseInt(cboCategoria.getSelectedItem().toString()));

        controller.insertar(p);

        cargarTabla();
        limpiar();
    }

    // LISTAR
    private void cargarTabla() {

        modelo.setRowCount(0);

        ArrayList<Producto> lista = controller.listar();

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                    p.getIdProducto(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getTipo(),
                    p.isDisponible(),
                    p.getIdCategoria()
            });
        }
    }

    // ELIMINAR
    private void eliminar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
            return;
        }

        int id = (int) tabla.getValueAt(fila, 0);

        controller.eliminar(id);

        cargarTabla();
    }

    // MODIFICAR
    private void modificar() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
            return;
        }

        Producto p = new Producto();

        p.setIdProducto((int) tabla.getValueAt(fila, 0));
        p.setNombre(txtNombre.getText());
        p.setDescripcion(txtDescripcion.getText());

        p.setTipo(rbtNacional.isSelected() ? "Nacional" : "Importado");
        p.setDisponible(chkDisponible.isSelected());
        p.setIdCategoria(Integer.parseInt(cboCategoria.getSelectedItem().toString()));

        controller.actualizar(p);

        cargarTabla();
        limpiar();
    }

    // LIMPIAR
    private void limpiar() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        chkDisponible.setSelected(false);
        grupoTipoClear();
    }

    private void grupoTipoClear() {
        rbtNacional.setSelected(false);
        rbtImportado.setSelected(false);
    }
}