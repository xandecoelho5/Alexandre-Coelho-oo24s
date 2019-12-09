package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Endereco;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Query;

public class EnderecoDAO extends GenericDao<Endereco, Long>{
    public EnderecoDAO() {
        super(Endereco.class);
    }  
    
    public Long getByNome(String nome){
        Query query = em.createNativeQuery("SELECT id FROM Endereco WHERE NOME = :nome"); 
        query.setParameter("nome", nome);
        BigInteger b1 = (BigInteger)query.getResultList().get(0);        
        return b1.longValue();    
    }
    //List<Object[]> result = (List<Object[]>) query.getResultList();
    //    return Long.parseLong( result.get(0)[0].toString() );  
}
