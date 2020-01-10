package br.edu.ifrs.canoas.richardburton.books;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.edu.ifrs.canoas.richardburton.util.BaseDAO;

@Stateless
public class AuthorDAO extends BaseDAO<Author, Long> {

    private static final long serialVersionUID = 1L;

    public Author retrieve(String name) {

        try {

            String queryString = "SELECT author FROM Author author WHERE author.name = :name";
            TypedQuery<Author> query = em.createQuery(queryString, Author.class);
            query.setParameter("name", name);
            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;
        }
    }

    @Override
    public Author create(Author author) {

        Author alreadyRegistered = retrieve(author.getName());
        return alreadyRegistered == null ? super.create(author) : alreadyRegistered;
    }

}