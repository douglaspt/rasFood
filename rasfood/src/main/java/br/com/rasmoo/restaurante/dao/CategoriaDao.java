package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(final Categoria categoria){
        this.em.persist(categoria);
        System.out.println("Entidade Cadastrada: "+ categoria);
    }

    public Categoria consultar(final Integer id){
        return this.em.find(Categoria.class,id);
    }

    public List<Categoria> consultarTodos(){
        String sql = "SELECT c FROM Categoria c";
        return this.em.createQuery(sql,Categoria.class).getResultList();
    }

    public void atualizar(final Categoria categoria){
        this.em.merge(categoria);
    }

    public void excluir(final Categoria categoria){
        this.em.remove(categoria);
    }
}
