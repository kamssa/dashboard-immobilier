package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Image extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	private Long idProduit;
	private String imageUrl;
	private String imageId;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(Long idProduit, String imageUrl, String imageId) {
		super();
		this.idProduit = idProduit;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Image [idProduit=" + idProduit + ", imageUrl=" + imageUrl + ", imageId=" + imageId + "]";
	}

}
