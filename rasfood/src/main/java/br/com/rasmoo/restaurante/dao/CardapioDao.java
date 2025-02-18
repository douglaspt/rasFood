package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class CardapioDao {

    private EntityManager em;

    public CardapioDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(final Cardapio cardapio){
        this.em.persist(cardapio);
        System.out.println("Entidade Cadastrada: "+ cardapio);
    }

    public Cardapio consultarPorId(final Integer id){
        return this.em.find(Cardapio.class,id);
    }

    public List<Cardapio> consultaPorValor(final BigDecimal filtro){
        try {
            String jpql = "SELECT c FROM Cardapio c WHERE c.valor = :valor";
            return this.em.createQuery(jpql,Cardapio.class).setParameter("valor", filtro).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Cardapio consultaPorNome(final String filtro){
        try {
            String jpql = "SELECT c FROM Cardapio c WHERE UPPER(c.nome) = UPPER(:nome)";
            return this.em.createQuery(jpql,Cardapio.class).setParameter("nome", filtro).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cardapio> consultarTodos(){
        try {
            String jpql = "SELECT c FROM Cardapio c";
            return this.em.createQuery(jpql,Cardapio.class).getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }

    }

    public void atualizar(final Cardapio cardapio){
        this.em.merge(cardapio);
    }

    public void excluir(final Cardapio cardapio){
        this.em.remove(cardapio);
    }
}
