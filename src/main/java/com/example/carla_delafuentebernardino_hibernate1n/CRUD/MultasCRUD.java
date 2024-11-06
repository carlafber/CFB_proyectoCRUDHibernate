package com.example.carla_delafuentebernardino_hibernate1n.CRUD;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.classes.Multas;
import com.example.carla_delafuentebernardino_hibernate1n.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class MultasCRUD implements CRUDMultas{
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public boolean insertarMulta(Multas multa) {
        Transaction transaction = null;
        boolean cambios = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(multa);
            cambios = true;
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return cambios;
    }

    @Override
    public boolean actualizarMulta(Multas multa) {
        Transaction transaction = null;
        boolean cambios = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(multa);
            transaction.commit();
            cambios = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return cambios;
    }

    @Override
    public boolean eliminarMulta(Multas multa) {
        Transaction transaction = null;
        boolean cambios = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(multa);
            cambios = true;
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return cambios;
    }

    @Override
    public List<Multas> obtenerMultasCoche(Coche coche) {
        Transaction transaction = null;
        List<Multas> multas = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            multas = session.createQuery("from Multas WHERE coche.matricula = :matricula", Multas.class)
                    .setParameter("matricula", coche.getMatricula())
                    .list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return multas;
    }
}
