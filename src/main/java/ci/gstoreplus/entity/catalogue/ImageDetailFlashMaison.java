package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;



@Entity
public class ImageDetailFlashMaison extends AbstractEntity{
    
	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_DetailFashMaison", nullable = false)
	private DetailFlashMaison detailFashMaison;
	private String imageUrl;
	private String imageId;
	public ImageDetailFlashMaison() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImageDetailFlashMaison(DetailFlashMaison detailFashMaison, String imageUrl, String imageId) {
		super();
		this.detailFashMaison = detailFashMaison;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}
	public DetailFlashMaison getDetailFashMaison() {
		return detailFashMaison;
	}
	public void setDetailFashMaison(DetailFlashMaison detailFashMaison) {
		this.detailFashMaison = detailFashMaison;
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
		result = prime * result + ((detailFashMaison == null) ? 0 : detailFashMaison.hashCode());
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
		ImageDetailFlashMaison other = (ImageDetailFlashMaison) obj;
		if (detailFashMaison == null) {
			if (other.detailFashMaison != null)
				return false;
		} else if (!detailFashMaison.equals(other.detailFashMaison))
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
		return "ImageDetailFlashMaison [detailFashMaison=" + detailFashMaison + ", imageUrl=" + imageUrl + ", imageId="
				+ imageId + "]";
	}
	
}
