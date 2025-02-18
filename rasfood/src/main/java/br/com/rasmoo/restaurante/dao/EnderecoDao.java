package br.com.rasmoo.restaurante.dao;


import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class EnderecoDao {

    private EntityManager em;

    public EnderecoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(final Endereco endereco){
        this.em.persist(endereco);
        System.out.println("Entidade Cadastrada: "+ endereco);
    }

    public Endereco consultar(final Integer id){
        return this.em.find(Endereco.class,id);
    }

    public List<Endereco> consultarTodos(){
        String sql = "SELECT e FROM Endereco e";
        return this.em.createQuery(sql,Endereco.class).getResultList();
    }

    public List<ClienteVo> consultarClientes(String estado, String cidade, String logradouro){
        String jpql = "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.clienteId.cpf, e.cliente.nome) "+
                "FROM Endereco e WHERE 1 = 1 ";
        if (Objects.nonNull(estado)){
            jpql = jpql.concat("AND UPPER(e.estado) = UPPER(:estado) ");
        }
        if (Objects.nonNull(cidade)){
            jpql = jpql.concat("AND UPPER(e.cidade) = UPPER(:cidade) ");
        }
        if (Objects.nonNull(logradouro)){
            jpql = jpql.concat("AND UPPER(e.logradouro) = UPPER(:logradouro) ");
        }

        TypedQuery<ClienteVo> typedQuery = this.em.createQuery(jpql,ClienteVo.class);
        if (Objects.nonNull(estado)){
            typedQuery.setParameter("estado",estado);
        }
        if (Objects.nonNull(cidade)){
            typedQuery.setParameter("cidade",cidade);
        }
        if (Objects.nonNull(logradouro)){
            typedQuery.setParameter("logradouro",logradouro);
        }
        return typedQuery.getResultList();
    }

    public List<ClienteVo> consultarClientesCriteria(String estado, String cidade, String logradouro){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ClienteVo> criteriaQuery = builder.createQuery(ClienteVo.class);
        Root<Endereco> root = criteriaQuery.from(Endereco.class);
        criteriaQuery.multiselect(root.get("cliente").get("clienteId").get("cpf"), root.get("cliente").get("nome"));
        Predicate predicate = builder.and();
        if (Objects.nonNull(estado)){
            predicate = builder.and(predicate,builder.equal(builder.upper(root.get("estado")),estado.toUpperCase()));
        }
        if (Objects.nonNull(cidade)){
            predicate = builder.and(predicate,builder.equal(root.get("cidade"),cidade.toUpperCase()));
        }
        if (Objects.nonNull(logradouro)){
            predicate = builder.and(predicate,builder.equal(builder.upper(root.get("logradouro")),logradouro.toUpperCase()));
        }

        criteriaQuery.where(predicate);

        return em.createQuery(criteriaQuery).getResultList();

    }

    public void atualizar(final Endereco endereco){
        this.em.merge(endereco);
    }

    public void excluir(final Endereco endereco){
        this.em.remove(endereco);
    }
}
