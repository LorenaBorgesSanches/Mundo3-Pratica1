package cadastropoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.entidades.PessoaFisica;
import model.entidades.PessoaJuridica;
import model.gerenciadores.PessoaFisicaRepo;
import model.gerenciadores.PessoaJuridicaRepo;

public class CadastroPOO {

    static PessoaFisicaRepo repoPF;
    static PessoaJuridicaRepo repoPJ;
    static Scanner sc;

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        repoPF = new PessoaFisicaRepo();
        repoPJ = new PessoaJuridicaRepo();

        int opcao;

        do {
            System.out.println("==========================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persisttir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==========================================");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> incluir();
                case 2 -> alterar();
                case 3 -> excluir();
                case 4 -> obter();
                case 5 -> obterTodos();
                case 6 -> salvar();
                case 7 -> recuperar();
                case 0 -> System.out.println("Programa finalizado. ");
                default -> System.out.println("Opcao invalida. ");
            }

        } while (opcao != 0);

        sc.close();

    }

    public static void incluir() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Tipo de pessoa invalido");
            return;
        }

        System.out.println("Digite o id da pessoa: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o nome: ");
        String nome = sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            System.out.println("Digite o CPF: ");
            String cpf = sc.nextLine();
            System.out.println("Digite a idade: ");
            int idade = sc.nextInt();
            sc.nextLine();
            repoPF.inserir(new PessoaFisica(id, nome, cpf, idade));
        } else {
            System.out.println("Digite o CNPJ: ");
            String cnpj = sc.nextLine();
            repoPJ.inserir(new PessoaJuridica(id, nome, cnpj));
        }

        System.out.println("Pessoa incluida com sucesso! ");
    }

    public static void alterar() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Tipo de pessoa invalido");
            return;
        }
        System.out.println("Digite o id da pessoa: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            PessoaFisica pf = repoPF.obter(id);
            if (pf == null) {
                System.out.println("Pessoa não encontrada. ");
                return;
            }
            System.out.println(pf.exibir());

            System.out.println("Digite o nome: ");
            String nomePf = sc.nextLine();
            System.out.println("Digite o CPF: ");
            String cpfPf = sc.nextLine();
            System.out.println("Digite a idade: ");
            int idadePf = sc.nextInt();
            sc.nextLine();
            pf.setNome(nomePf);
            pf.setCpf(cpfPf);
            pf.setIdade(idadePf);
            repoPF.alterar(pf);
        } else {
            PessoaJuridica pj = repoPJ.obter(id);
            if (pj == null) {
                System.out.println("Pessoa não encontrada. ");
                return;
            }

            System.out.println(pj.exibir());
            System.out.println("Digite o nome: ");
            String nomePj = sc.nextLine();
            System.out.println("Digite o CNPJ. ");
            String cnpjPj = sc.nextLine();
            pj.setNome(nomePj);
            pj.setCnpj(cnpjPj);
            repoPJ.alterar(pj);
        }
        System.out.println("Alterado com sucesso");
    }

    public static void excluir() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Tipo de pessoa invalido");
            return;
        }
        System.out.println("Digite o id da pessoa: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            repoPF.excluir(id);
        } else {
            repoPJ.excluir(id);
        }
        System.out.println("Excluido com sucesso. ");
    }

    public static void obter() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Tipo de pessoa invalido");
            return;
        }
        System.out.println("Digite o id da pessoa: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            PessoaFisica pf = repoPF.obter(id);
            System.out.println(pf.exibir());
        } else {
            PessoaJuridica pj = repoPJ.obter(id);
            System.out.println(pj.exibir());
        }
    }

    public static void obterTodos() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica ");
        char tipoPessoa = sc.nextLine().charAt(0);
        if (tipoPessoa != 'F' && tipoPessoa != 'f' && tipoPessoa != 'J' && tipoPessoa != 'j') {
            System.out.println("Tipo de pessoa invalido");
            return;
        }

        if (tipoPessoa == 'F' || tipoPessoa == 'f') {
            ArrayList<PessoaFisica> pfs = repoPF.obterTodos();
            for (PessoaFisica pf : pfs) {
                System.out.println(pf.exibir());
                System.out.println("-----------------");
            }
        } else {
            ArrayList<PessoaJuridica> pjs = repoPJ.obterTodos();
            for (PessoaJuridica pj : pjs) {
                System.out.println(pj.exibir());
                System.out.println("-----------------");
            }
        }
    }

    public static void salvar() {
        System.out.println("Digite o nome do arquivo a ser salvo: ");
        String prefixo = sc.nextLine();

        try {
            repoPF.persistir(prefixo + "fisica.bin");
        } catch (IOException ex) {
            System.out.println("Não foi possivel salvar o repositorio de pessoa fisica.");
            return;
        }

        try {
            repoPJ.persistir(prefixo + "juridica.bin");
        } catch (IOException ex) {
            System.out.println("Não foi possivel salvar o repositorio de pessoa juridica.");
            return;
        }

        System.out.println("Arquivo salvo com sucesso! ");
    }

    public static void recuperar() {
        System.out.println("Digite o nome do arquivo a ser carregado: ");
        String prefixo = sc.nextLine();
        try {
            repoPF.recuperar(prefixo + "fisica.bin");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Não foi possivel ler o arquivo de pessoa fisica informado. ");
            return;
        }

        try {
            repoPJ.recuperar(prefixo + "juridica.bin");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Não foi possivel ler o arquivo de pessoa juridica informado. ");
            return;
        }

        System.out.println("Aquivo recuperado com sucesso! ");
    }
}
