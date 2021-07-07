package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;



@Entity
public class ImageDetail extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	private Long idDetailTerrain;
	private String imageUrl;
	private String imageId;
	
	public ImageDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageDetail(Long idDetailTerrain, String imageUrl, String imageId) {
		super();
		this.idDetailTerrain = idDetailTerrain;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}

	
	public Long getIdDetailTerrain() {
		return idDetailTerrain;
	}

	public void setIdDetailTerrain(Long idDetailTerrain) {
		this.idDetailTerrain = idDetailTerrain;
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
