package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.ReservaProdutos;

public class ReservaProdutosDAO extends GenericDao<ReservaProdutos, Long> {
    public ReservaProdutosDAO() {
        super(ReservaProdutos.class);
    }
}
