package la.ipo.admin.model;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

public class BaseEntity {

	private Long id = null;
	private DateTime createDateTime = null;
	private DateTime updateDateTime = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(DateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public DateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(DateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((updateDateTime == null) ? 0 : updateDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (updateDateTime == null) {
			if (other.updateDateTime != null)
				return false;
		} else if (!updateDateTime.equals(other.updateDateTime))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
        .append("id", this.getId())
        .append("new", this.isNew())
        .append("createDateTime", this.getCreateDateTime())
        .append("updateDateTime", this.getUpdateDateTime())
        .toString();
	}
}
