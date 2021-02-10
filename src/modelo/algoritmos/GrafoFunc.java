package modelo.algoritmos;

import java.util.List;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import modelo.model.Aresta;
import modelo.model.Grafo;
import modelo.model.Vertice;

public class GrafoFunc {

    private AnchorPane anchorPaneGrafo;
    private Grafo G;

    public GrafoFunc(AnchorPane anchorPaneGrafo, Grafo G) {
        this.anchorPaneGrafo = anchorPaneGrafo;
        this.G = G;
    }

    public void desenharGrafo() {
        List<Vertice> vertices = G.vertices();
        Group componentes = new Group();
        //Desenhando as arestas
        for (Aresta a : G.arestas()) {
            Line line = new Line();
            line.setStartX(vertices.get(a.getV1()).getX());
            line.setStartY(vertices.get(a.getV1()).getY());
            line.setEndX(vertices.get(a.getV2()).getX());
            line.setEndY(vertices.get(a.getV2()).getY());
            line.setStroke(colorir());
            componentes.getChildren().add(line);
        }

        //Desenhando os vértices
        for (int v = 0; v < G.V(); v++) {
            Circle circle = new Circle();
            circle.setCenterX(vertices.get(v).getX());
            circle.setCenterY(vertices.get(v).getY());
            circle.setRadius(15.0f);
            circle.setStroke(colorir());
            circle.setFill(colorir());
            Text text = new Text(circle.getCenterX() - 4, circle.getCenterY() + 4, String.valueOf(v));
            componentes.getChildren().add(circle);
            componentes.getChildren().add(text);
        }
        Platform.runLater(() -> anchorPaneGrafo.getChildren().addAll(componentes));
    }
    
    public Color colorir(){
        AlgoritmoCicloEuleriano algoEuleriano = new AlgoritmoCicloEuleriano(G);
        boolean temCaminho = algoEuleriano.temCicloEuleriano();
        if(temCaminho){
            return Color.GREEN;
        }
        return Color.RED;
    }
}
