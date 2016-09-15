package dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;

import entities.Projeto;

public class ProjetoDAO {

	private static Session session;
    private static Transaction tx;
    
    public ProjetoDAO(){}
    
    public static void criarProjeto(Projeto novo){
        session = DAO.getSession();
    	tx = session.beginTransaction();
    	session.save(novo);
    	tx.commit();
    }
    
    public static void deletaProjeto(String nome){
        session = DAO.getSession();
    	tx = session.beginTransaction();
        Projeto p = (Projeto) session.get(Projeto.class, nome);
        session.delete(p);
        tx.commit();
    }
    
    public static void updateProjeto(Projeto p){
        session = DAO.getSession();
    	tx = session.beginTransaction();
        session.update(p);
        tx.commit();
    }
    
    public static Projeto getProjetoByNome(String nome){
        session = DAO.getSession();
    	tx = session.beginTransaction();
        Projeto p = (Projeto) session.get(Projeto.class, nome);
        tx.commit();
        return p;
    }
    
    public static List<Projeto> getAllProjetos(){
        session = DAO.getSession();
    	tx = session.beginTransaction();
        List<Projeto> lista = session.createCriteria(Projeto.class).list();
        tx.commit();
        return lista;
    }
    
    public static boolean existeProjeto(String nome){
        session = DAO.getSession();
    	tx = session.beginTransaction();
        Projeto p = (Projeto)session.get(Projeto.class, nome);
        tx.commit();
        return p != null;
    }
//	public void Salvar(Projeto p) {
//		// obtem uma sessao
//		session = DAO.getInstance();
//		Transaction tx = null; // permite transacao com o BD
//
//		try {
//			tx = session.beginTransaction();
//			session.save(p);
//			tx.commit();// faz a transacao
//		} catch (Exception e) {
//			e.printStackTrace();
//			// cancela a transcao em caso de falha
//			tx.rollback();
//		} finally {
//			session.close();
//		}
//	}

}
