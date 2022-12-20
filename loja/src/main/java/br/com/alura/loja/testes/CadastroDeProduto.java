package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastraProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);

		Produto p = produtoDAO.buscarPorId(1l);
		System.out.println(p.getPreco());

		List<Produto> todos = produtoDAO.buscaTodos();
		todos.forEach(p2 -> System.out.println(p.getNome()));

		List<Produto> buscaNome = produtoDAO.buscaPorNome("Xiaomi Redmi");
		buscaNome.forEach(p3 -> System.out.println(p.getNome()));

		List<Produto> buscaPorNomeDaCategoria = produtoDAO.buscaPorNomeDaCategoria("CELULARES");
		buscaPorNomeDaCategoria.forEach(p4 -> System.out.println(p.getNome()));
		
		BigDecimal buscaPreco= 
				produtoDAO.buscaPrecoDoProdutoPorNome("Xiaomi Redmi");
		System.out.println("Preço do Produto: " + buscaPreco);
	}

	private static void cadastraProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito Legal", new BigDecimal("800"), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);

		em.getTransaction().begin();
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);

		em.getTransaction().commit();
		em.close();
	}

}
