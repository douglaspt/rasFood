package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.vo.ItensPrincipaisVo;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdemDao {

    private EntityManager em;

    public OrdemDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(final Ordem ordem){
        this.em.persist(ordem);
        System.out.println("Entidade Cadastrada: "+ ordem);
    }

    public Ordem consultar(final Integer id){
        return this.em.find(Ordem.class,id);
    }

    public List<Ordem> consultarTodos(){
        String sql = "SELECT o FROM Ordem o";
        return this.em.createQuery(sql,Ordem.class).getResultList();
    }

    public List<ItensPrincipaisVo> consultarItensMaisVendidos(){
        String sql = "SELECT new br.com.rasmoo.restaurante.vo.ItensPrincipaisVo("+
        "c.nome, SUM(oc.quantidade)) FROM Ordem o " +
                "JOIN OrdensCardapio oc ON o.id = oc.cardapio.id " +
                "JOIN oc.cardapio c " +
                "GROUP BY c.nome "+
                "ORDER BY SUM(oc.quantidade) DESC";
        return this.em.createQuery(sql,ItensPrincipaisVo.class).getResultList();
    }

    public void atualizar(final Ordem ordem){
        this.em.merge(ordem);
    }

    public void excluir(final Ordem ordem){
        this.em.remove(ordem);
    }
}
