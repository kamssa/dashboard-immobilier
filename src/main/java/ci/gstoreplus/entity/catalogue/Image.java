package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.AbstractEntity;

@Entity
public class Image extends AbstractEntity{
    private static final long serialVersionUID = 1L;
	private Long idTerrain;
    private String path;
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Image(Long idTerrain, String path) {
		super();
		this.idTerrain = idTerrain;
		this.path = path;
	}
	public Long getIdTerrain() {
		return idTerrain;
	}
	public void setIdTerrain(Long idTerrain) {
		this.idTerrain = idTerrain;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Image [idTerrain=" + idTerrain + ", path=" + path + "]";
	}
    
}
