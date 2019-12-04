package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Endereco;
import java.util.List;
import javax.persistence.Query;

public class EnderecoDAO extends GenericDao<Endereco, Long>{
    public EnderecoDAO() {
        super(Endereco.class);
    }  
    
    public List<Endereco> getByNome(String nome){
        Query query = em.createQuery("SELECT e.id "
                + "FROM Endereco e "
                + "WHERE UPPER(e.nome) LIKE :nome");
        query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        return query.getResultList();
    }
}
