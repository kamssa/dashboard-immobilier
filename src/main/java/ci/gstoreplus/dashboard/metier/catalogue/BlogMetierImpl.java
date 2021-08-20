package ci.gstoreplus.dashboard.metier.catalogue;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.dashboard.catalogue.BlogRepository;
import ci.gstoreplus.entity.catalogue.Blog;
import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.exception.InvalideImmobilierException;

@Service
public class BlogMetierImpl implements BlogMetier {
@Autowired
private BlogRepository blogRepository;
	@Override
	public Blog creer(Blog entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return blogRepository.save(entity);
	}

	@Override
	public Blog modifier(Blog entity) throws InvalideImmobilierException {
		// TODO Auto-generated method stub
		return blogRepository.save(entity);
	}

	@Override
	public List<Blog> findAll() {
		// TODO Auto-generated method stub
		return blogRepository.findAll();
	}

	@Override
	public Blog findById(Long id) {
		// TODO Auto-generated method stub
		return blogRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		blogRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Blog> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Blog> getBlocsFalse() {
		List<Blog> b = null;
		List<Blog> blogs = blogRepository.findAll();
	    b = blogs.stream().filter(t -> t.isIm()==false).collect(Collectors.toList());
	    return b;
	}

	@Override
	public List<Blog> getBlocsTrue() {
		List<Blog> b = null;
		List<Blog> blogs = blogRepository.findAll();
	    b = blogs.stream().filter(t -> t.isIm()==true).collect(Collectors.toList());
	    return b;
	}

}
