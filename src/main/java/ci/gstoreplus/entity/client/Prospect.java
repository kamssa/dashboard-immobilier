package ci.gstoreplus.entity.client;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.dashboard.shared.Personne;

@Entity
@DiscriminatorValue("PR")
public class Prospect extends Personne{

	
	private static final long serialVersionUID = 1L;
	@Column(name = "satisfait")
	private boolean satisfait;
	private String preocupation;
	
	public boolean isSatisfait() {
		return satisfait;
	}
	public void setSatisfait(boolean satisfait) {
		this.satisfait = satisfait;
	}
	public String getPreocupation() {
		return preocupation;
	}
	public void setPreocupation(String preocupation) {
		this.preocupation = preocupation;
	}
	
}
