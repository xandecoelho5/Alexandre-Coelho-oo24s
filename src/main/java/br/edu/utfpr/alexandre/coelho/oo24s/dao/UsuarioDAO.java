package br.edu.utfpr.alexandre.coelho.oo24s.dao;

import br.edu.utfpr.alexandre.coelho.oo24s.model.Usuario;
import javax.persistence.Query;

public class UsuarioDAO extends GenericDao<Usuario, Long> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByEmailAndSenhaNamedQuery(String email,
            String senha) {
        Query query = em.createNamedQuery(
                Usuario.FIND_BY_EMAIL_AND_SENHA);
        query.setParameter("email", email);
        query.setParameter("senha", senha);

        return (Usuario) query.getSingleResult();
    }
}
