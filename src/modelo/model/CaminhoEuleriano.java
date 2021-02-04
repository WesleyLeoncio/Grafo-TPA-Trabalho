
package modelo.model;


public class CaminhoEuleriano {
    private boolean temCaminho = false;
    private int tamanho = 0;
    private String caminho = "";

    public CaminhoEuleriano() {
    }
    
   public CaminhoEuleriano(boolean temCaminho, int tamanho, String caminho) {
       this.temCaminho = temCaminho;
       this.tamanho = tamanho;
       this.caminho = caminho;
    }
    
    public boolean isTemCaminho() {
        return temCaminho;
    }

    public void setTemCaminho(boolean temCaminho) {
        this.temCaminho = temCaminho;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
    
}
