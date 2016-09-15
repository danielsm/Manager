package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Pessoa;

public class PessoaDAO {
    private static Session session;
    private static Transaction tx;
    
    public PessoaDAO(){}
    
    public static void criaPessoa(Pessoa novo){
        session = DAO.getSession();
        tx = session.beginTransaction();
        session.save(novo);
        tx.commit();
    }
    
    public static void deletaPessoa(long id){
        session = DAO.getSession();
        tx = session.beginTransaction();
        Pessoa m = (Pessoa)session.get(Pessoa.class, id);
        session.delete(m);
        tx.commit();
    }
    
    public static void updatePessoa(Pessoa m){
        session = DAO.getSession();
        tx = session.beginTransaction();
        session.update(m);
        tx.commit();
    }
    
    public static Pessoa getPessoaById(long id){
        session = DAO.getSession();
        tx = session.beginTransaction();
        Pessoa m = (Pessoa)session.get(Pessoa.class, id);
        tx.commit();
        return m;
    }
    
    public static List<Pessoa> getMateriaisByIdTarefa(long id_tarefa){
        session = DAO.getSession();
        tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Pessoa.class);
        crit.add(Restrictions.eq("id_tarefa", id_tarefa));
        List<Pessoa> lista = crit.list();
        tx.commit();
        return lista;
    }
}

