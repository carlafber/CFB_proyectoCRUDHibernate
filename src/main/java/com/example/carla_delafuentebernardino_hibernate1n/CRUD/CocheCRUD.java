package com.example.carla_delafuentebernardino_hibernate1n.CRUD;

import com.example.carla_delafuentebernardino_hibernate1n.classes.Coche;
import com.example.carla_delafuentebernardino_hibernate1n.util.HibernateUtil;
import com.example.carla_delafuentebernardino_hibernate1n.util.Validar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CocheCRUD implements CRUDCoche{
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public boolean insertarCoche(Coche coche) {
        Transaction transaction = null;
        boolean cambios = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(coche);
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
    public boolean actualizarCoche(Coche coche) {
        Transaction transaction = null;
        boolean cambios = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.update(coche);
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
    public boolean eliminarCoche(Coche coche) {
        Transaction transaction = null;
        boolean cambios = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            int multas_eliminadas = session.createQuery("delete from Multas where coche.matricula = :matricula")
                    .setParameter("matricula", coche.getMatricula()).executeUpdate();
            int coches_eliminados = session.createQuery("delete from Coche where matricula = :matricula")
                    .setParameter("matricula", coche.getMatricula()).executeUpdate();
            transaction.commit();

            if (multas_eliminadas > 0 || coches_eliminados > 0) {
                cambios = true;
            }

        } catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
        }
        return cambios;
    }

    @Override
    public List<Coche> obtenerCoches() {
        Transaction transaction = null;
        List<Coche> coches = null;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            coches = session.createQuery("from Coche", Coche.class).list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return coches;
    }

    public boolean existeCoche(String matricula) {
        Transaction transaction = null;
        boolean coche = false;
        try(Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            coche = !session.createQuery("from Coche where matricula = :matricula")
                    .setParameter("matricula", matricula).list().isEmpty();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return coche;
    }
}

//dar id a la multa automaticamente