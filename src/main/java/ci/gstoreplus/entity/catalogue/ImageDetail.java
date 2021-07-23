package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;



@Entity
public class ImageDetail extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_DetailTerrain", nullable = false)
	private DetailTerrain detailTerrain;
	private String imageUrl;
	private String imageId;
	
	public ImageDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageDetail(DetailTerrain detailTerrain, String imageUrl, String imageId) {
		super();
		this.detailTerrain = detailTerrain;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}

	public DetailTerrain getDetailTerrain() {
		return detailTerrain;
	}

	public void setDetailTerrain(DetailTerrain detailTerrain) {
		this.detailTerrain = detailTerrain;
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

	@Override
	public String toString() {
		return "ImageDetail [detailTerrain=" + detailTerrain + ", imageUrl=" + imageUrl + ", imageId=" + imageId + "]";
	}

	
	}
