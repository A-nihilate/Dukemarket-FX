    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.dev.annihilate.dukemarket.javafx.controller;

import br.dev.annihilate.dukemarket.javafx.DAO.ProdutoDAO;
import br.dev.annihilate.dukemarket.javafx.model.Produto;
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
public class ScrProdutosController implements Initializable {
    
    @FXML
    TableView <Produto> tblProduto;
    
    @FXML
    TableColumn <Produto,Integer> tcoId;
    
    @FXML
    TableColumn <Produto,String> tcoCodBarras;
    
    @FXML
    TableColumn <Produto,String> tcoDescricao;
    
    @FXML
    TableColumn <Produto,Double> tcoQtd;
    
    @FXML
    TableColumn <Produto,Double> tcoValorCompra;
    
    @FXML
    TableColumn <Produto,Double> tcoValorVenda;
    
    @FXML
    TableColumn <Produto,Calendar> tcoDataCadastro;
    
    @FXML
    TextField txtId;
    
    @FXML
    TextField txtCodBarras;
    
    @FXML
    TextField txtDescricao;
    
    @FXML
    TextField txtQtd;
    
    @FXML
    TextField txtValorCompra;
    
    @FXML
    TextField txtValorVenda;
    
    @FXML
    DatePicker dtpDataCadastro;
    
    ProdutoDAO pDAO;
    Produto pClicked; //aparentemente isso vai amarzenar quem foi clicado intesante
    
    
    
    private void bindColumns() {
        tcoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcoId.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        tcoCodBarras.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        tcoQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        tcoQtd.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoValorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
        tcoValorCompra.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoValorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        tcoValorVenda.setStyle("-fx-alignment: CENTER_RIGHT;");
        
        tcoDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcoDataCadastro.setStyle("-fx-alignment: CENTER_RIGHT;");
        
    }
    
    
    
    public void carregaDados(){
            
        this.pDAO = new ProdutoDAO();
        
        this.tblProduto.getItems().setAll(pDAO.getResults());
    }
    
    public void btnNovoClick() {
        txtId.setText("auto");
        txtId.setEditable(false);
        
        txtCodBarras.setText("");
        txtCodBarras.requestFocus();
        
        txtDescricao.setText("");
        txtQtd.setText("");
        txtValorCompra.setText("");
        txtValorVenda.setText("");
        dtpDataCadastro.setValue(null);
        
    }
    
    public void tblProdutoOnMouseClicked(){
        
        
        
        
        this.pClicked = tblProduto.getSelectionModel().getSelectedItem();
        
        if (pClicked != null) {
            txtId.setText(String.valueOf(pClicked.getId()));
            txtId.setEditable(false);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
