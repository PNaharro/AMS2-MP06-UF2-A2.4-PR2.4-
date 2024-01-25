package com.project;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Manager implements Serializable {

    private static SessionFactory factory;

    public static void createSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }

    public static void close () {
        factory.close();
    }
    public static Llibre addLlibre(String string, String editorial) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Llibre llibre = null;

        try {
            transaction = session.beginTransaction();

            // Crear una instancia de la entidad Llibre
            llibre = new Llibre();
            llibre.setNom(string);
            llibre.setEditorial(editorial);

            // Guardar el llibre en la base de datos
            session.save(llibre);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return llibre;
    }

    public static Biblioteca addBiblioteca(String nom, String ciutat) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Biblioteca biblioteca = null;

        try {
            transaction = session.beginTransaction();

            // Crear una instancia de la entidad Biblioteca
            biblioteca = new Biblioteca();
            biblioteca.setNom(nom);
            biblioteca.setCiutat(ciutat);

            // Guardar la biblioteca en la base de datos
            session.save(biblioteca);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return biblioteca;
    }

    public static void updateBiblioteca(Long bibliotecaId, String nom, String ciutat, Set<Llibre> llibres) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Obtener la biblioteca existente desde la base de datos
            Biblioteca biblioteca = (Biblioteca) session.get(Biblioteca.class, bibliotecaId);

            if (biblioteca != null) {
                // Actualizar los atributos de la biblioteca
                biblioteca.setNom(nom);
                biblioteca.setCiutat(ciutat);

                // Actualizar la colección de llibres asociada a la biblioteca
                biblioteca.setLlibres(llibres);

                // Guardar los cambios en la base de datos
                session.update(biblioteca);

                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Persona addPersona(String dni, String nom, String telefon) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Persona persona = null;

        try {
            transaction = session.beginTransaction();

            // Crear una instancia de la entidad Persona
            persona = new Persona();
            persona.setDni(dni);
            persona.setNom(nom);
            persona.setTelefon(telefon);

            // Guardar la persona en la base de datos
            session.save(persona);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return persona;
    }

    public static void updatePersona(Long personaId, String dni, String nom, String telefon, Set<Llibre> llibres) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Obtener la persona existente desde la base de datos
            Persona persona = (Persona) session.get(Persona.class, personaId);

            if (persona != null) {
                // Actualizar los atributos de la persona
                persona.setDni(dni);
                persona.setNom(nom);
                persona.setTelefon(telefon);

                // Actualizar la colección de llibres asociada a la persona
                persona.setLlibres(llibres);

                // Guardar los cambios en la base de datos
                session.update(persona);

                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Autor addAutor(String nom) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Autor autor = null;

        try {
            transaction = session.beginTransaction();

            // Crear una instancia de la entidad Autor
            autor = new Autor();
            autor.setNom(nom);

            // Guardar el autor en la base de datos
            session.save(autor);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return autor;
    }

    public static void updateAutor(Long autorId, String nom, Set<Llibre> llibres) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Obtener el autor existente desde la base de datos
            Autor autor = (Autor) session.get(Autor.class, autorId);

            if (autor != null) {
                // Actualizar los atributos del autor
                autor.setNom(nom);

                // Actualizar la colección de llibres asociada al autor
                autor.setLlibres(llibres);

                // Guardar los cambios en la base de datos
                session.update(autor);

                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static Collection<?> listCollection(Class<?> clazz) {
        return listCollection(clazz, "");
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz, String where) {
        Session session = factory.openSession();
        Transaction transaction = null;
        Collection<?> result = null;

        try {
            transaction = session.beginTransaction();
            if (where.length() == 0) {
                result = session.createQuery("FROM " + clazz.getName()).list();
            } else {
                result = session.createQuery("FROM " + clazz.getName() + " WHERE " + where).list();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public static <T> String collectionToString(Class<? extends T> clazz, Collection<?> collection) {
        StringBuilder result = new StringBuilder();

        result.append("=== ").append(clazz.getSimpleName()).append(" ===\n");

        for (Object entity : collection) {
            if (entity != null) {
                result.append(entity).append("\n");
            }
        }

        result.append("====================\n");

        return result.toString();
    }

    public static void queryUpdate(String queryString) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryString);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Object[]> queryTable(String queryString) {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Object[]> result = null;

        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryString);
            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.list();
            result = rows;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public static String tableToString(List<Object[]> rows) {
        StringBuilder txt = new StringBuilder();
        for (Object[] row : rows) {
            for (Object cell : row) {
                txt.append(cell.toString()).append(", ");
            }
            if (txt.length() >= 2 && txt.substring(txt.length() - 2).compareTo(", ") == 0) {
                txt = new StringBuilder(txt.substring(0, txt.length() - 2));
            }
            txt.append("\n");
        }
        if (txt.length() >= 2) {
            txt = new StringBuilder(txt.substring(0, txt.length() - 2));
        }
        return txt.toString();
    }
}
