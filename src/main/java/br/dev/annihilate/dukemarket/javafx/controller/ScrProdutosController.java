package br.dev.annihilate.dukemarket.javafx.controller;

import br.dev.annihilate.dukemarket.javafx.DAO.ProdutoDAO;
import br.dev.annihilate.dukemarket.javafx.model.Produto;
import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    TableView<Produto> tblProduto;

    @FXML
    TableColumn<Produto, Integer> tcoId;

    @FXML
    TableColumn<Produto, String> tcoCodBarras;

    @FXML
    TableColumn<Produto, String> tcoDescricao;

    @FXML
    TableColumn<Produto, Double> tcoQtd;

    @FXML
    TableColumn<Produto, Double> tcoValorCompra;

    @FXML
    TableColumn<Produto, Double> tcoValorVenda;

    @FXML
    TableColumn<Produto, Calendar> tcoDataCadastro;

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

    boolean isProdutoNovo = false;

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

    public void carregaDados() {

        this.pDAO = new ProdutoDAO();

        this.tblProduto.getItems().setAll(pDAO.getResults());
    }
    
    @FXML

    private void btnNovoClick() {
        txtId.setText("auto");
        txtId.setEditable(false);
        

        txtCodBarras.setText("");
        txtCodBarras.requestFocus();

        txtDescricao.setText("");
        txtQtd.setText("");
        txtValorCompra.setText("");
        txtValorVenda.setText("");
        dtpDataCadastro.setValue(null);

        isProdutoNovo = true;
    }

    @FXML
    public void tblProdutoOnMouseClicked() {

        this.pClicked = tblProduto.getSelectionModel().getSelectedItem();

        if (pClicked != null) {
            txtId.setText(String.valueOf(pClicked.getId()));
            txtId.setEditable(false);
        }
        isProdutoNovo = false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bindColumns();
        carregaDados();
    }

    @FXML
    public void btnSalvarClick() {

        // Montar um objeto Produto
        Produto p = new Produto();

        // Carrega os dados do objeto produto com os dados da interface
        
//        if (!isProdutoNovo) {         // TENTATIVA 1
//            p = pDAO.getResultById(this.pClicked.getId());
//        }
         
        p.setCodBarras(txtCodBarras.getText());
        p.setDescricao(txtDescricao.getText());
        p.setQtd(Double.parseDouble(txtQtd.getText()));
        p.setValorCompra(Double.parseDouble(txtValorCompra.getText()));
        p.setValorVenda(Double.parseDouble(txtValorVenda.getText())); 

        if (!isProdutoNovo) {
            p.setId(Integer.parseInt(txtId.getId())); // TENTATIVA 2 ()
            pDAO.update(p);
        } else {
            pDAO.create(p);
        }
        
        this.carregaDados();
        

    }
    
    public void btnExcluirClick() {
        
        if (this.pClicked != null) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirma Exclusão de Produto?");
            alert.setHeaderText(pClicked.getDescricao());
            alert.setContentText("tem certeza que vai excluir ?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.pDAO.delete(this.pClicked.getId());
                
                this.tblProduto.getItems().remove(this.pClicked);
                
            }
            this.tblProduto.getSelectionModel().clearSelection();
            pClicked = null;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ops");
            alert.setHeaderText("Atenção");
            alert.setContentText("você tem que selecionar um registro antes de excluí-lo");
            
            alert.showAndWait();
        }
            
            
            
            
    }

}
