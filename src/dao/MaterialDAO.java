package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entities.Material;

public class MaterialDAO {
    private static Session session;
    private static Transaction tx;
    
    public MaterialDAO(){}
    
    public static void criaMaterial(Material novo){
        session = DAO.getSession();
        tx = session.beginTransaction();
        session.save(novo);
        tx.commit();
    }
    
    public static void deletaMaterial(long id){
        session = DAO.getSession();
        tx = session.beginTransaction();
        Material m = (Material)session.get(Material.class, id);
        session.delete(m);
        tx.commit();
    }
    
    public static void updateMaterial(Material m){
        session = DAO.getSession();
        tx = session.beginTransaction();
        session.update(m);
        tx.commit();
    }
    
    public static Material getMaterialById(long id){
        session = DAO.getSession();
        tx = session.beginTransaction();
        Material m = (Material)session.get(Material.class, id);
        tx.commit();
        return m;
    }
    
    public static List<Material> getMateriaisByIdTarefa(long id_tarefa){
        session = DAO.getSession();
        tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Material.class);
        crit.add(Restrictions.eq("id_tarefa", id_tarefa));
        List<Material> lista = crit.list();
        tx.commit();
        return lista;
    }
}

