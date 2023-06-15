package model.gerenciadores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import model.entidades.PessoaJuridica;

public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> repo;

    public PessoaJuridicaRepo() {
        repo = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoa) {
        repo.add(pessoa);
    }
    
     public void alterar(PessoaJuridica pessoa) {
        for (int i = 0; i < repo.size(); i++) {
            if (pessoa.getId() == repo.get(i).getId()) {
                repo.set(i, pessoa);
                break;
            }
        }
    }

    public void excluir(int Id) {
        Iterator<PessoaJuridica> iterator = repo.iterator();
        while (iterator.hasNext()) {
            PessoaJuridica pessoa = iterator.next();
            if (pessoa.getId() == Id) {
                iterator.remove();
                break;
            }
        }
    }

    public PessoaJuridica obter(int Id) {
        for (PessoaJuridica pessoa : repo) {
            if (pessoa.getId() == Id) {
                return pessoa;
            }
        }
        return null;
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return repo;
    }

    public void persistir(String caminho) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(caminho);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(repo);
    }

    public void recuperar(String caminho) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fout = new FileInputStream(caminho);
        ObjectInputStream oos = new ObjectInputStream(fout);
        repo = (ArrayList<PessoaJuridica>) oos.readObject();
    }
}
