package br.com.fameg.cfom.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.fameg.cfom.util.HibernateUtil;

	// Conforme video aula Sérgio [Programação Web com Java] com o Prof. Sérgio Roberto Delfino
	// Para facilitar a vida, pode ser criado o GenericDAO, a fim de ter as implementações 
	// basicas de DAO
public class GenericDAO<Entity> {

	private Class<Entity> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void save(Entity Entity) {
		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;

		try {
			transaction = factory.beginTransaction();
			factory.save(Entity);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			factory.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entity> getAll() {
		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = factory.createCriteria(classe);
			List<Entity> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			factory.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Entity getOn(Long codigo) {
		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = factory.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entity resultado = (Entity) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			factory.close();
		}
	}

	public void delete(Entity Entity) {
		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;

		try {
			transaction = factory.beginTransaction();
			factory.delete(Entity);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			factory.close();
		}
	}

	public void update(Entity Entity) {
		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;

		try {
			transaction = factory.beginTransaction();
			factory.update(Entity);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			factory.close();
		}
	}

	// Verificar para subtituir este merge, colocando dentro do save
	@SuppressWarnings("unchecked")
	public Entity merge(Entity Entity) {
		Session factory = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transaction = null;

		try {
			transaction = factory.beginTransaction();
			Entity retorno = (Entity) factory.merge(Entity);
			transaction.commit();
			return retorno;
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			factory.close();
		}
	}
}