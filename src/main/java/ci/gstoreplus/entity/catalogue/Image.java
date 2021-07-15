package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Image extends AbstractEntity{
    private static final long serialVersionUID = 1L;
	private Long idTerrain;
	private String nom;
	private String imageUrl;
	private String imageId;
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Image(Long idTerrain, String imageUrl) {
		super();
		this.idTerrain = idTerrain;
		this.imageUrl = imageUrl;
	}
	
	public Image(Long idTerrain, String nom, String imageUrl, String imageId) {
		super();
		this.idTerrain = idTerrain;
		this.nom = nom;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}

	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public Long getIdTerrain() {
		return idTerrain;
	}
	public void setIdTerrain(Long idTerrain) {
		this.idTerrain = idTerrain;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Image [idTerrain=" + idTerrain + ", imageUrl=" + imageUrl + "]";
	}
	
    
}
