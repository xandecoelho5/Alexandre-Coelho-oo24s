package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Cliente;
import java.io.Serializable;

public class ClienteDAO extends GenericDao<Cliente, Long>{

    public ClienteDAO() {
        super(Cliente.class);
    }
}
