package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;



@Entity
public class ImageDetailMaison extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	private Long  idDetailMaison;
	private String imageUrl;
	private String imageId;
	
	public ImageDetailMaison() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdDetailMaison() {
		return idDetailMaison;
	}

	public void setIdDetailMaison(Long idDetailMaison) {
		this.idDetailMaison = idDetailMaison;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	
}
