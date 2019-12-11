package br.edu.utfpr.alexandre.coelho.oo24s.dao;
import br.edu.utfpr.alexandre.coelho.oo24s.model.Produtos;
import java.util.List;
import javax.persistence.Query;

public class ProdutosDAO extends GenericDao<Produtos, Long>{
    public ProdutosDAO() {
        super(Produtos.class);
    }
    
    public List<Produtos> getByReservaId(Long Id){
        Query query = em.createNativeQuery("SELECT p.* FROM produtos p JOIN reserva_produtos r on (r.produtos_id = p.id) WHERE r.reservas_id = :rId", Produtos.class);
        query.setParameter("rId", Id);          
        return query.getResultList();    
    }
}
