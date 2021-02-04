package modelo.controll;

import modelo.arquivo.In;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelo.algoritmos.AlgoritmoCicloEuleriano;
import modelo.algoritmos.GrafoFunc;
import modelo.model.CaminhoEuleriano;
import modelo.model.Grafo;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPaneGrafo;
    @FXML
    private TableView<CaminhoEuleriano> tableView;
    @FXML
    private TableColumn<CaminhoEuleriano, Boolean> column_tem_ciclo;
    @FXML
    private TableColumn<CaminhoEuleriano, Integer> column_tamanho;
    @FXML
    private TableColumn<CaminhoEuleriano, String> column_trajeto;

    private ObservableList<CaminhoEuleriano> observableCaminhoEuleriano;
    @FXML
    private TextField txt_nomeGrafo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Grafo(txt_nomeGrafo.getText());
    }

    @FXML
    public void buttonEventCarregar() {
        Platform.runLater(() -> anchorPaneGrafo.getChildren().clear());
        Grafo(txt_nomeGrafo.getText());
    }

    public void Grafo(String nomeArquivo) {
        In in = new In(nomeArquivo);
        Grafo G = new Grafo(in);
        System.out.println(G);
        GrafoFunc grafoFunc = new GrafoFunc(anchorPaneGrafo, G);
        grafoFunc.desenharGrafo();
        carregarTableView(G);

    }

    public void carregarTableView(Grafo G) {
        AlgoritmoCicloEuleriano algoEuleriano = new AlgoritmoCicloEuleriano(G);
        boolean temCaminho = algoEuleriano.temCicloEuleriano();
        int tamanho = 0;
        String caminho = "";
        if (algoEuleriano.temCicloEuleriano()) {
            tamanho = algoEuleriano.ciclo.tamanho();
            for (int v : algoEuleriano.ciclo()) {
                caminho += v + " ";
            }
        }
        CaminhoEuleriano caminhoEuleriano = new CaminhoEuleriano(temCaminho, tamanho, caminho);
        column_tem_ciclo.setCellValueFactory(new PropertyValueFactory<>("temCaminho"));
        column_tamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        column_trajeto.setCellValueFactory(new PropertyValueFactory<>("caminho"));
        observableCaminhoEuleriano = FXCollections.observableArrayList(caminhoEuleriano);
        Platform.runLater(() -> tableView.setItems(observableCaminhoEuleriano));
    }

}
