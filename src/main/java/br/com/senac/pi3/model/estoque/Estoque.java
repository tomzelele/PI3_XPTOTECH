package br.com.senac.pi3.model.estoque;

public class Estoque {
    // Esta classe é baseada na tabela de movimentação de estoque
    String ES; //Propriedade que define tipo de movimentação
    int Id;
    int Fk_produto; // Propriedade com código do produto
    int Fk_estoque; // Propriedade com código do estoque movimentado
    int Qt_movimento; // Propriedade com quantidade moviimentada de estoque

    public String getES() {
        return ES;
    }

    public void setES(String ES) {
        this.ES = ES;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public int getFk_produto() {
        return Fk_produto;
    }

    public void setFk_produto(int FK_Fk_produto) {
        this.Fk_produto = Fk_produto;
    }

    public int getFk_estoque() {
        return Fk_estoque;
    }

    public void setFk_estoque(int Fk_estoque) {
        this.Fk_estoque = Fk_estoque;
    }

    public int getQt_movimento() {
        return Qt_movimento;
    }

    public void setQt_movimento(int Qt_movimento) {
        this.Qt_movimento = Qt_movimento;
    }
    
}
