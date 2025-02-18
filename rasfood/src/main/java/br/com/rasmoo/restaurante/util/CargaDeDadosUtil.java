package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CargaDeDadosUtil {

    public CargaDeDadosUtil() {

    }

    public static void cadastrarCategoria(EntityManager em){
        Categoria entrada = new Categoria("Entrada");
        Categoria salada = new Categoria("Salada");
        Categoria pratoPrincipal = new Categoria("Prato Principal");

        CategoriaDao categoriaDao = new CategoriaDao(em);

        categoriaDao.cadastrar(entrada);
        em.flush();
        categoriaDao.cadastrar(salada);
        em.flush();
        categoriaDao.cadastrar(pratoPrincipal);
        em.flush();
        em.clear();

    }

    public static void cadastrarProdutosCardapio(EntityManager em){
        CardapioDao cardapioDao = new CardapioDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        List<Categoria> categorias = categoriaDao.consultarTodos();

        Cardapio risoto = new Cardapio("Risoto de Frutos do Mar",
                "Risoto de Frutos do Mar co Lula, camarao, polvo e mariscos",true,
                BigDecimal.valueOf(99.90), categorias.get(2));

        Cardapio salmao = new Cardapio("Salmão ao molho",
                "Salmão Grelhado ao molho de maracuja",true,
                BigDecimal.valueOf(99.90), categorias.get(2));

        Cardapio burrata = new Cardapio("Burrata",
                "Tomates queimados, rucula e torradas",true,
                BigDecimal.valueOf(20.0), categorias.get(0));

        Cardapio ceasar = new Cardapio("Ceasar",
                "Salada de frango com molho ceasar",true,
                BigDecimal.valueOf(35.0), categorias.get(0));

        cardapioDao.cadastrar(risoto);
        cardapioDao.cadastrar(salmao);
        cardapioDao.cadastrar(burrata);
        cardapioDao.cadastrar(ceasar);
        em.flush();
        em.clear();

    }

    public static void cadastrarClientes(EntityManager em){
        ClienteDao clienteDao = new ClienteDao(em);
        Cliente c1 = new Cliente("11122233344","douglas@email.com","Douglas Pimentel");
        c1.addEndereco(new Endereco("09811333","Rua ABC", 100, "BL 5", "SBC", "SP" ));
        clienteDao.cadastrar(c1);

        Cliente c2 = new Cliente("22233344455","joseca@email.com","Jose Carlos");
        c2.addEndereco(new Endereco("09811000","Rua 25 de Março", 200, "Sala 1", "São Paulo", "SP" ));
        clienteDao.cadastrar(c2);

        Cliente c3 = new Cliente("33344455566","maria@email.com","Maria Madalena");
        c3.addEndereco(new Endereco("09811000","Rua 15 de Outubro", 300, "Sala 2", "São Paulo", "SP" ));
        clienteDao.cadastrar(c3);

        Cliente c4 = new Cliente("44455566677","joseant@email.com","Jose Antonio");
        c4.addEndereco(new Endereco("09811000","Rua Kennedy", 123, "", "Rio de Janeiro", "RJ" ));
        clienteDao.cadastrar(c4);

        em.flush();
        em.clear();
    }

    public static void cadastrarOrdensClientes(EntityManager em){
        ClienteDao clienteDao = new ClienteDao(em);
        OrdemDao ordemDao = new OrdemDao(em);
        CardapioDao cardapioDao = new CardapioDao(em);

        Ordem ordem1 = new Ordem(clienteDao.consultar(new ClienteId("11122233344","douglas@email.com")));
        ordem1.AddOrdensCardapio(new OrdensCardapio(ordem1,cardapioDao.consultarPorId(1),2));
        ordem1.AddOrdensCardapio(new OrdensCardapio(ordem1,cardapioDao.consultarPorId(2),3));
        ordemDao.cadastrar(ordem1);

        Ordem ordem2 = new Ordem(clienteDao.consultar(new ClienteId("11122233344","douglas@email.com")));
        ordem2.AddOrdensCardapio(new OrdensCardapio(ordem2,cardapioDao.consultarPorId(3),3));
        ordem2.AddOrdensCardapio(new OrdensCardapio(ordem2,cardapioDao.consultarPorId(4),4));
        ordemDao.cadastrar(ordem2);

        Ordem ordem3 = new Ordem(clienteDao.consultar(new ClienteId("11122233344","douglas@email.com")));
        ordem3.AddOrdensCardapio(new OrdensCardapio(ordem3,cardapioDao.consultarPorId(1),1));
        ordem3.AddOrdensCardapio(new OrdensCardapio(ordem3,cardapioDao.consultarPorId(4),2));
        ordemDao.cadastrar(ordem3);

        Ordem ordem4 = new Ordem(clienteDao.consultar(new ClienteId("11122233344","douglas@email.com")));
        ordem4.AddOrdensCardapio(new OrdensCardapio(ordem4,cardapioDao.consultarPorId(3),4));
        ordem4.AddOrdensCardapio(new OrdensCardapio(ordem4,cardapioDao.consultarPorId(1),2));
        ordemDao.cadastrar(ordem4);

        em.flush();
        em.clear();
    }


}
