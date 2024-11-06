package com.example.carla_delafuentebernardino_hibernate1n.controllers;

import com.example.carla_delafuentebernardino_hibernate1n.CRUD.MultasCRUD;
import com.example.carla_delafuentebernardino_hibernate1n.MultasApplication;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Multas;
import com.example.carla_delafuentebernardino_hibernate1n.util.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultasController implements Initializable {

    @FXML
    private Button bt_actualizar;

    @FXML
    private Button bt_eliminar;

    @FXML
    private Button bt_insertar;

    @FXML
    private Button bt_limpiar;

    @FXML
    private Button bt_volver;

    @FXML
    private DatePicker dt_fecha;

    @FXML
    private TableColumn<Multas, String> tc_fecha;

    @FXML
    private TableColumn<Multas, String> tc_idmulta;

    @FXML
    private TableColumn<Multas, String> tc_precio;

    @FXML
    private TableView<Multas> tv_multas;

    @FXML
    private TextField txt_idmulta;

    @FXML
    private TextField txt_matricula;

    @FXML
    private TextField txt_precio;

    private Coche coche;

    private List<Multas> multas;

    private MultasCRUD multasCRUD;

    private Multas multa_seleccionada;

    public void obtenerCoche(Coche coche_selecionado) {
        this.coche = coche_selecionado;
        if (this.coche != null) {
            txt_matricula.setText(coche.getMatricula());
            cargarMultas(this.coche);
        } else {
            System.out.println("Usuario no disponible");
        }
    }

    @FXML
    void OnActualizarClick(ActionEvent event) {
        if (multa_seleccionada == null) {
            Alerta.mensajeError("Seleccione una multa de la tabla para poder modificarla.");
        } else {
            Multas multa_original = new Multas(multa_seleccionada);
            multa_seleccionada.setPrecio(Double.parseDouble(txt_precio.getText()));
            multa_seleccionada.setFecha(dt_fecha.getValue());
            multa_seleccionada.setCoche(coche);

            if(multa_original.equals(multa_seleccionada)) {
                Alerta.mensajeError("Debe modificar al menos un campo para poder actualizar la multa.");
            } else {
                if(multasCRUD.actualizarMulta(multa_seleccionada)) {
                    cargarMultas(multa_seleccionada.getCoche());
                    Alerta.mensajeInfo("ÉXITO", "Multa actualizada correctamente.");
                } else {
                    Alerta.mensajeError("No se pudo actualizar la multa.");
                }
            }
            limpiarCampos();
            multa_seleccionada = null;
        }
    }

    @FXML
    void OnMultaClick(MouseEvent event) {
        multa_seleccionada = tv_multas.getSelectionModel().getSelectedItem();
        if (multa_seleccionada != null) {
            txt_idmulta.setText(String.valueOf(multa_seleccionada.getId_multa()));
            txt_precio.setText(String.valueOf(multa_seleccionada.getPrecio()));
            dt_fecha.setValue(multa_seleccionada.getFecha());
        }
    }

    @FXML
    void OnEliminarClick(ActionEvent event) {
        if (multa_seleccionada == null) {
            Alerta.mensajeError("Seleccione una multa de la tabla para poder eliminarla.");
        } else {
            multasCRUD.eliminarMulta(multa_seleccionada);
            cargarMultas(coche);
            Alerta.mensajeInfo("ÉXITO", "Multa eliminada correctamente.");
            limpiarCampos();
        }
    }

    @FXML
    void OnInsertarClick(ActionEvent event) {
        if(txt_precio.getText().isEmpty() || dt_fecha.getValue() == null){
            Alerta.mensajeError("Complete todos los campos, por favor.");
        } else {
            if(multa_seleccionada != null) {
                Alerta.mensajeError("No seleccione una multa de la tabla, debe crear una nueva");
            } else {
                Multas multa_nueva = new Multas(Double.parseDouble(txt_precio.getText()), dt_fecha.getValue());
                multa_nueva.setCoche(coche);
                if(multasCRUD.insertarMulta(multa_nueva)) {
                    cargarMultas(coche);
                    txt_idmulta.setText(String.valueOf(multa_nueva.getId_multa()));
                    Alerta.mensajeInfo("ÉXITO", "Multa insertada correctamente.");
                } else {
                    Alerta.mensajeError("No se pudo insertar la multa.");
                }
            }
            limpiarCampos();
        }
    }

    @FXML
    void OnLimpiarClick(ActionEvent event) {
        limpiarCampos();
    }

    public void OnVolverClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MultasApplication.class.getResource("coches.fxml"));
            Parent root = fxmlLoader.load();
            CochesController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_volver.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        multasCRUD = new MultasCRUD();

        tc_idmulta.setCellValueFactory(new PropertyValueFactory<>("id_multa"));
        tc_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tc_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tv_multas.setOnMouseClicked(this::OnMultaClick);
    }

    public void cargarMultas(Coche coche) {
        multas = multasCRUD.obtenerMultasCoche(coche);
        tv_multas.getItems().setAll(multas);
        if(multas.isEmpty()){
            Alerta.mensajeInfo("INFO","Este coche no tiene ninguna multa.");
        }
    }

    public void limpiarCampos() {
        txt_idmulta.clear();
        txt_precio.clear();
        dt_fecha.setValue(null);
    }
}