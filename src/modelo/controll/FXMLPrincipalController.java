package modelo.controll;

import modelo.arquivo.In;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import modelo.algoritmos.GrafoFunc;
import modelo.model.Grafo;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private AnchorPane anchorPaneGrafo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Grafo();
    }

    public void Grafo() {
        In in = new In("Grafo3.txt");
        Grafo G = new Grafo(in);
        System.out.println(G);
        GrafoFunc grafoFunc = new GrafoFunc(anchorPaneGrafo, G);
        grafoFunc.desenharGrafo();
    }

}
