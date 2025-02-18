package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdensCardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerRasFood();
        em.getTransaction().begin();
        CargaDeDadosUtil.cadastrarCategoria(em);
        CargaDeDadosUtil.cadastrarProdutosCardapio(em);
        CargaDeDadosUtil.cadastrarClientes(em);
        CargaDeDadosUtil.cadastrarOrdensClientes(em);

        ClienteDao clienteDao = new ClienteDao(em);
        System.out.println(clienteDao.consultarTodos());


        em.getTransaction().commit();
        em.close();
    }

}
