package br.edu.utfpr.alexandre.coelho.oo24s.dao;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;

public class ProdutosDAO extends GenericDao<Produtos, Long>{
    public ProdutosDAO() {
        super(Produtos.class);
    }
}
