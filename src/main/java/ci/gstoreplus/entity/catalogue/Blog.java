package ci.gstoreplus.entity.catalogue;

import javax.persistence.Column;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Blog extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	private String libelle;
	@Column(columnDefinition="TEXT")
	private String description;
	private String path;
	private boolean im;
	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Blog(String libelle, String description, String path) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.path = path;
	}

	

	public Blog(String libelle, String description, String path, boolean im) {
		super();
		this.libelle = libelle;
		this.description = description;
		this.path = path;
		this.im = im;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	

	
	public boolean isIm() {
		return im;
	}

	public void setIm(boolean im) {
		this.im = im;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageAccueil [libelle=" + libelle + ", description=" + description + ", path=" + path + "]";
	}
	
	
}