package com.example.carla_delafuentebernardino_hibernate1n.controllers;

import com.example.carla_delafuentebernardino_hibernate1n.CRUD.CocheCRUD;
import com.example.carla_delafuentebernardino_hibernate1n.MultasApplication;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.util.Alerta;
import com.example.carla_delafuentebernardino_hibernate1n.util.Validar;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class CochesController implements Initializable {

    @FXML
    private Button bt_actualizar;

    @FXML
    private Button bt_eliminar;

    @FXML
    private Button bt_insertar;

    @FXML
    private Button bt_limpiar;

    @FXML
    private Button bt_multas;

    @FXML
    private ComboBox<String> cb_tipo;

    @FXML
    private TableColumn<Coche, String> tc_marca;

    @FXML
    private TableColumn<Coche, String> tc_matricula;

    @FXML
    private TableColumn<Coche, String> tc_modelo;

    @FXML
    private TableColumn<Coche, String> tc_tipo;

    @FXML
    private TableView<Coche> tv_coches;

    @FXML
    private TextField txt_marca;

    @FXML
    private TextField txt_matricula;

    @FXML
    private TextField txt_modelo;

    private List<Coche> coches;

    private CocheCRUD cocheCRUD;

    private Coche coche_seleccionado;

    @FXML
    void OnActualizarClick(ActionEvent event) {
        if (coche_seleccionado == null) {
            Alerta.mensajeError("Seleccione un coche de la tabla para poder modificarlo.");
        } else {
            Coche coche_original = new Coche(coche_seleccionado);
            coche_seleccionado.setMarca(txt_marca.getText());
            coche_seleccionado.setModelo(txt_modelo.getText());
            coche_seleccionado.setTipo(cb_tipo.getValue());

            if(coche_original.equals(coche_seleccionado)){
                Alerta.mensajeError("Debe modificar al menos un campo para poder actualizar el coche.");
            } else {
                if(cocheCRUD.actualizarCoche(coche_seleccionado)){
                    cargarCoches();
                    Alerta.mensajeInfo("ÉXITO", "Coche actualizado correctamente.");
                } else {
                    Alerta.mensajeError("No se pudo actualizar el coche.");
                }
            }
            limpiarCampos();
            coche_seleccionado = null;
        }
    }

    @FXML
    void OnCocheClick(MouseEvent event) {
        coche_seleccionado = tv_coches.getSelectionModel().getSelectedItem();
        if (coche_seleccionado != null) {
            txt_matricula.setText(coche_seleccionado.getMatricula());
            txt_marca.setText(coche_seleccionado.getMarca());
            txt_modelo.setText(coche_seleccionado.getModelo());
            cb_tipo.setValue(coche_seleccionado.getTipo());
        }
    }

    @FXML
    void OnEliminarClick(ActionEvent event) {
        if (coche_seleccionado == null) {
            Alerta.mensajeError("Seleccione un coche de la tabla para poder eliminarlo.");
        } else {
            if(cocheCRUD.eliminarCoche(coche_seleccionado)){
                cargarCoches();
                Alerta.mensajeInfo("ÉXITO", "Coche eliminado correctamente.");
                limpiarCampos();
            } else {
                Alerta.mensajeError("No se pudo eliminar el coche.");
            }
        }
    }

    @FXML
    void OnInsertarClick(ActionEvent event) {
        if(txt_matricula.getText().isEmpty() || txt_marca.getText().isEmpty() || txt_modelo.getText().isEmpty() || cb_tipo.getValue() == null){
            Alerta.mensajeError("Complete todos los campos, por favor.");
        } else {
            if(Validar.validarMatricula(txt_matricula.getText())) {
                if(cocheCRUD.existeCoche(txt_matricula.getText())) {
                    Alerta.mensajeError("Ya existe un coche con esa matricula.");
                    limpiarCampos();
                } else {
                    Coche coche_nuevo = new Coche(txt_matricula.getText(), txt_marca.getText(), txt_modelo.getText(), cb_tipo.getValue());
                    if (cocheCRUD.insertarCoche(coche_nuevo)){
                        cargarCoches();
                        Alerta.mensajeInfo("ÉXITO", "Coche insertado correctamente.");
                        limpiarCampos();
                    } else {
                        Alerta.mensajeError("No se pudo insertar el coche.");
                    }
                }
            } else {
                Alerta.mensajeError("El formato de la matricula introducida es incorrecto, tiene que ser del tipo '0000AAA'.");
                txt_matricula.clear();
            }
        }
    }

    @FXML
    void OnLimpiarClick(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void OnVerMultasClick(ActionEvent event) {
        if(coche_seleccionado == null) {
            Alerta.mensajeError("Debe seleccionar un coche para poder consultar sus multas.");
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MultasApplication.class.getResource("multas.fxml"));
                Parent root = fxmlLoader.load();
                MultasController controller = fxmlLoader.getController();
                controller.obtenerCoche(coche_seleccionado);

                Scene scene = new Scene(root);
                Stage stage = (Stage) bt_multas.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Alerta.mensajeError(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cocheCRUD = new CocheCRUD();

        String[] tipos = new String[]{"Familiar", "Monovolumen", "Deportivo", "SUV"};
        cb_tipo.setItems(FXCollections.observableArrayList(tipos));

        tc_matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tc_marca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        tc_modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tc_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        cargarCoches();

        tv_coches.setOnMouseClicked(this::OnCocheClick);
    }

    public void cargarCoches(){
        coches = cocheCRUD.obtenerCoches();
        tv_coches.getItems().setAll(coches);
    }

    public void limpiarCampos() {
        txt_matricula.clear();
        txt_marca.clear();
        txt_modelo.clear();
        cb_tipo.setValue(null);
    }
}