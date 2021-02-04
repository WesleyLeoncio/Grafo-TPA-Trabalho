package modelo.algoritmos;

import modelo.arquivo.Fila;
import modelo.arquivo.Pilha;
import modelo.model.Aresta;
import modelo.model.Grafo;

public class AlgoritmoCicloEuleriano {

    public Pilha<Integer> ciclo = new Pilha<Integer>();

    public AlgoritmoCicloEuleriano(Grafo G) {
        // deve ter pelo menos uma aresta
        if (G.A() == 0) {
            return;
        }
        // condição necessária: todos os vértices tem o mesmo grau
        // (este teste é necessário ou pode encontrar um caminho Euleriano em vez de ciclo)
        for (int v = 0; v < G.V(); v++) {
            if (G.grau(v) % 2 != 0) {
                return;
            }
        }

        // criar visão local das listas de adjacências, para iterar um vértice de cada vez
        // o tipo de dados auxiliar Aresta é usado para evitar a exploração de ambas as cópias de uma Aresta v-w
        Fila<Aresta>[] adj = (Fila<Aresta>[]) new Fila[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = new Fila<Aresta>();
        }

        for (int v = 0; v < G.V(); v++) {
            int autoLacos = 0;
            for (Aresta a : G.adj(v)) {
                int w = a.getV2();
                // cuidado com auto laços
                if (v == w) {
                    if (autoLacos % 2 == 0) {
                        Aresta a1 = new Aresta(v, w);
                        adj[v].enfileira(a1);
                        adj[w].enfileira(a1);
                    }
                    autoLacos++;
                } else if (v < w) {
                    Aresta a2 = new Aresta(v, w);
                    adj[v].enfileira(a2);
                    adj[w].enfileira(a2);
                }
            }
        }
        // initialize pilha with any non-isolated vertex
        int s = verticeNaoIsolado(G);
        Pilha<Integer> pilha = new Pilha<Integer>();
        pilha.empilha(s);
        
          // pesquisar avidamente pelas arestas em estilo DFS iterativo
        ciclo = new Pilha<Integer>();
        while (!pilha.isEmpty()) {
            int v = pilha.desempilha();
            while (!adj[v].isEmpty()) {
                Aresta aresta = adj[v].desenfileira();
                if (aresta.getEhUsada()) {
                    continue;
                }
                aresta.setEhUsada(true);
                pilha.empilha(v);
                v = aresta.outroVertice(v);
            }
            // adicionar vértice sem deixar mais arestas para ciclo
            ciclo.empilha(v);
        }
        // verifica se todas as arestas são usadas
        if (ciclo.tamanho() != G.A() + 1) {
            ciclo = null;
        }
    }

    // retorna algum vértice não isolado; -1 se não houver tal vértice
    private static int verticeNaoIsolado(Grafo G) {
        for (int v = 0; v < G.V(); v++) {
            if (G.grau(v) > 0) {
                return v;
            }
        }
        return -1;
    }
    
    /**
     * Retorna a sequência de vértices no ciclo Euleriano.
     *
     * @return a sequência de vértices no ciclo Euleriano; ou null, se não
     * houver tal ciclo
     */
    public Iterable<Integer> ciclo() {
        return ciclo;
    }
     /**
     * Retorna true se o grafo tem um ciclo Euleriano.
     *
     * @return true se o grafo tem um ciclo Euleriano; false, caso contrário
     */
    public boolean temCicloEuleriano() {
        return ciclo.tamanho() != 0;
    }
    
    
    
}
