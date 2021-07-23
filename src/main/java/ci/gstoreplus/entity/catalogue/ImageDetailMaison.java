package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;



@Entity
public class ImageDetailMaison extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_DetailMaison", nullable = false)
	private DetailMaison detailMaison;
	private String imageUrl;
	private String imageId;
	
	public ImageDetailMaison() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageDetailMaison(DetailMaison detailMaison, String imageUrl, String imageId) {
		super();
		this.detailMaison = detailMaison;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}

	public DetailMaison getDetailMaison() {
		return detailMaison;
	}

	public void setDetailMaison(DetailMaison detailMaison) {
		this.detailMaison = detailMaison;
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
