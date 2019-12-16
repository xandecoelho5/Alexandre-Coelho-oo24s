package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.ReservaProdutos;
import javax.persistence.Query;

public class ReservaProdutosDAO extends GenericDao<ReservaProdutos, Long> {
    public ReservaProdutosDAO() {
        super(ReservaProdutos.class);
    }
    
    public ReservaProdutos getRPbyProdId(Long idp, Long idr){
        Query query = em.createNativeQuery("select r.id from reservaprodutos r join produtos p on(r.produtos_id = p.id) "
                                         + "where (r.produtos_id = :idp) and (r.reserva_id = :idr)", ReservaProdutos.class);
        query.setParameter("idp", idp);
        query.setParameter("idr", idr);
        return (ReservaProdutos)query.getSingleResult();
    }
}
