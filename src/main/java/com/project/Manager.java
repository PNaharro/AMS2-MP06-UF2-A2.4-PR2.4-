package com.project;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Collection;

public class Manager {

    private static SessionFactory factory;

    public static void createSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void close() {
        factory.close();
    }

    public static Ciutat addCiutat(String nom, String pais, int codiPostal) {
        Session session = factory.openSession();
        Transaction tx = null;
        Ciutat result = null;
        try {
            tx = session.beginTransaction();
            result = new Ciutat(nom, pais, codiPostal);
            session.save(result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }

    public static Ciutada addCiutada(String nom, String cognom, int edat, long ciutatId) {
        Session session = factory.openSession();
        Transaction tx = null;
        Ciutada result = null;
        try {
            tx = session.beginTransaction();
            result = new Ciutada(nom, cognom, edat);
            session.save(result);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            result = null;
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> T getById(Class<? extends T> clazz, long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        T obj = null;
        try {
            tx = session.beginTransaction();
            obj = clazz.cast(session.get(clazz, id));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return obj;
    }

    public static void updateCiutada(long ciutadaId, String nom, String cognom, int edat) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Ciutada obj = (Ciutada) session.get(Ciutada.class, ciutadaId);
            obj.setNom(nom);
            obj.setCognom(cognom);
            obj.setEdat(edat);
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteCiutada(long ciutadaId) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Ciutada obj = (Ciutada) session.get(Ciutada.class, ciutadaId);
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz) {
        return listCollection(clazz, "");
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz, String where) {
        Session session = factory.openSession();
        Transaction tx = null;
        Collection<?> result = null;
        try {
            tx = session.beginTransaction();
            Query query;
            if (where.length() == 0) {
                query = session.createQuery("FROM " + clazz.getName());
            } else {
                query = session.createQuery("FROM " + clazz.getName() + " WHERE " + where);
            }
            result = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> String collectionToString(Class<? extends T> clazz, Collection<?> collection) {
        StringBuilder txt = new StringBuilder();
        for (Object obj : collection) {
            T cObj = clazz.cast(obj);
            txt.append("\n").append(cObj.toString());
        }
        if (txt.length() > 0 && txt.substring(0, 1).compareTo("\n") == 0) {
            txt = new StringBuilder(txt.substring(1));
        }
        return txt.toString();
    }

    // Resto del código...

}
