package ci.gstoreplus.metier;

import java.util.List;

import ci.gstoreplus.exception.InvalideImmobilierException;


public interface Imetier <T,U>{
	
	public T creer(T entity) throws InvalideImmobilierException;
	
	public T modifier(T entity) throws InvalideImmobilierException;
	
	public List<T> findAll();
	
	public T findById(U id);

	public boolean supprimer(U id);
	
	public boolean supprimer(List<T> entites);
	
	public boolean existe(U id);
	
	Boolean existsByPseudo(String pseudo);

	Boolean existsByEmail(String email);
	

}
