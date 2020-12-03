package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Pays extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
    private String libele;
    private String description;
    
	public Pays() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pays(String libele, String description) {
		super();
		this.libele = libele;
		this.description = description;
	}

	public String getLibele() {
		return libele;
	}
	public void setLibele(String libele) {
		this.libele = libele;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
