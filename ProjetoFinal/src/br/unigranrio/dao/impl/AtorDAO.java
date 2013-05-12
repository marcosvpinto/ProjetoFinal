package br.unigranrio.dao.impl;

import java.util.List; 

import org.hibernate.Query;
import org.hibernate.Session;

import br.unigranrio.bean.requisito.Ator;
import br.unigranrio.dao.AbstractHibernateDAO;
import br.unigranrio.dao.HibernateUtil;

@SuppressWarnings("rawtypes")
public class AtorDAO extends AbstractHibernateDAO{
	
	private Session session = HibernateUtil.getSession();
	
	@SuppressWarnings("unchecked")
	public AtorDAO() {
		super(Ator.class);
	}

	public String retornaNomePorId(long id){
		return selecionaPorId(id).toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ator> retornaPorProjeto(long id){
		Query query = session.createSQLQuery("select * from ator where projeto_id=:id")
				.addEntity(Ator.class)
				.setParameter("id", id);
		return query.list();
	}

}