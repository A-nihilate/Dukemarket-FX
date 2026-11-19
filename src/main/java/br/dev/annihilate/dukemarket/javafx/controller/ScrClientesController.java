/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.dev.annihilate.dukemarket.javafx.controller;

import br.dev.annihilate.dukemarket.javafx.DAO.ClientesDAO;
import br.dev.annihilate.dukemarket.javafx.model.Clientes;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sesi2dia
 */
public class ScrClientesController implements Initializable {

    @FXML
    TableView<Clientes> tblClientes;

    @FXML
    TableColumn<Clientes, Integer> tcoId;

    @FXML
    TableColumn<Clientes, String> tcoCPF;

    @FXML
    TableColumn<Clientes, String> tcoNome;

    @FXML
    TableColumn<Clientes, String> tcoEndereco;

    @FXML
    TableColumn<Clientes, String> tcoCidade;

    @FXML
    TableColumn<Clientes, String> tcoUF;

    @FXML
    TableColumn<Clientes, Calendar> tcoDataCadastro;

    @FXML
    TextField txtId;

    @FXML
    TextField txtCPF;

    @FXML
    TextField txtNome;

    @FXML
    TextField txtEndereco;

    @FXML
    TextField txtCidade;

    @FXML
    TextField txtUF;

    @FXML
    DatePicker dtpDataCadastro;

    ClientesDAO cDAO;
    Clientes cClicked;

    boolean isClientesNovo = false;

    private void bindColumns() {
        tcoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcoId.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoCPF.setCellValueFactory(new PropertyValueFactory<>("cPF"));
        tcoCPF.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        tcoCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tcoCidade.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoUF.setCellValueFactory(new PropertyValueFactory<>("uF"));
        tcoUF.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tcoEndereco.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcoDataCadastro.setStyle("-fx-alignment: CENTER_RIGHT;");

    }

    public void carregaDados() {

        this.cDAO = new ClientesDAO();

        this.tblClientes.getItems().setAll(cDAO.getResults());
    }

    @FXML

    private void btnNovoClick() {
        txtId.setText("auto");
        txtId.setEditable(false);

        txtNome.setText("");
        txtNome.requestFocus();

        txtCPF.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtUF.setText("");
        dtpDataCadastro.setValue(null);

        isClientesNovo = true;
    }

    @FXML
    public void tblProdutoOnMouseClicked() {

        this.cClicked = tblClientes.getSelectionModel().getSelectedItem();

        if (cClicked != null) {
            txtId.setText(String.valueOf(cClicked.getId()));
            txtId.setEditable(false);
        }
        isClientesNovo = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindColumns();

        carregaDados();
    }

    @FXML
    public void btnSalvarClick() {

        // Montar um objeto Produto
        Clientes c = new Clientes();

        // Carrega os dados do objeto produto com os dados da interface
        c.setNome(txtNome.getText());
        c.setCPF(txtCPF.getText());
        c.setEndereco(txtEndereco.getText());
        c.setCidade(txtCidade.getText());
        c.setuF(txtUF.getText());

        if (!isClientesNovo) {
            c.setId(Integer.parseInt(txtId.getText())); // TENTATIVA 2 ()
            cDAO.update(c);
        } else {
            cDAO.create(c);
        }

        this.carregaDados();

    }

    @FXML
    public void btnSairClick() {
        System.out.println("Sair clickado. Implementar.");
    }

}
