package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.ClienteId;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(final Cliente cliente){
        this.em.persist(cliente);
        System.out.println("Entidade Cadastrada: "+ cliente);
    }

    public Cliente consultar(final ClienteId id){
        return this.em.find(Cliente.class,id);
    }

    public List<Cliente> consultarTodos(){
        String sql = "SELECT c FROM Cliente c";
        return this.em.createQuery(sql,Cliente.class).getResultList();
    }

    public List<Cliente> consultarPorNome(String nome){
        String sql = "SELECT c FROM Cliente c WHERE UPPER(c.nome) like UPPER(:nome)";
        return this.em.createQuery(sql,Cliente.class).setParameter("nome","%"+nome+"%")
                .getResultList();
    }

    public void atualizar(final Cliente cliente){
        this.em.merge(cliente);
    }

    public void excluir(final Cliente cliente){
        this.em.remove(cliente);
    }
}
