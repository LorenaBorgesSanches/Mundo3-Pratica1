package cadastropoo;

import java.io.IOException;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.gerenciadores.PessoaFisicaRepo;
import model.gerenciadores.PessoaJuridicaRepo;

public class CadastroPOO {

    public static void main(String[] args) {

        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        PessoaFisica pessoa1 = new PessoaFisica(1, "Ana", "11111111111", 25);
        PessoaFisica pessoa2 = new PessoaFisica(2, "Carlos", "22222222222", 52);

        repo1.inserir(pessoa1);
        repo1.inserir(pessoa2);

        try {
            repo1.persistir("./repoPessoasFisicas.db");
        } catch (IOException ex) {
        }
        System.out.println("Dados de Pessoa Fisica Armazenados.");

        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
        try {
            repo2.recuperar("./repoPessoasFisicas.db");
        } catch (IOException | ClassNotFoundException ex) {
        }
        System.out.println("Dados de Pessoa Fisica Recuperados.");
        
        repo2.obterTodos();
        for (PessoaFisica pessoa : repo2.obterTodos()) {
            System.out.println(pessoa.exibir());
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        PessoaJuridica pessoa3 = new PessoaJuridica(3, "XPTO Sales", "3333333333333333");
        PessoaJuridica pessoa4 = new PessoaJuridica(4, "XPTO Solutions", "4444444444444444");

        repo3.inserir(pessoa3);
        repo3.inserir(pessoa4);

        try {
            repo3.persistir("./repoPessoasJuridicas.db");
        } catch (IOException ex) {
        }
        System.out.println("Dados de Pessoa Juridica Armazenados.");

        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
        try {
            repo4.recuperar("./repoPessoasJuridicas.db");
        } catch (IOException | ClassNotFoundException ex) {
        }
        System.out.println("Dados de Pessoa Juridica Recuperados.");

        repo4.obterTodos();
        for (PessoaJuridica pessoa : repo4.obterTodos()) {
            System.out.println(pessoa.exibir());
        }
    }

}
