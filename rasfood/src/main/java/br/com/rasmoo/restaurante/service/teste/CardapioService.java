package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CardapioService {
    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManagerRasFood();
        em.getTransaction().begin();
        CargaDeDadosUtil.cadastrarCategoria(em);
        CargaDeDadosUtil.cadastrarProdutosCardapio(em);
        CardapioDao cardapioDao = new CardapioDao(em);
        System.out.println("O Produto pesquisado foi: "+cardapioDao.consultaPorNome("burrata"));

        em.close();
    }

}
