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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((detailMaison == null) ? 0 : detailMaison.hashCode());
		result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
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
		ImageDetailMaison other = (ImageDetailMaison) obj;
		if (detailMaison == null) {
			if (other.detailMaison != null)
				return false;
		} else if (!detailMaison.equals(other.detailMaison))
			return false;
		if (imageId == null) {
			if (other.imageId != null)
				return false;
		} else if (!imageId.equals(other.imageId))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageDetailMaison [detailMaison=" + detailMaison + ", imageUrl=" + imageUrl + ", imageId=" + imageId
				+ "]";
	}

	
}