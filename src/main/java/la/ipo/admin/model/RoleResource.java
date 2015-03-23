package la.ipo.admin.model;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

public class RoleResource {

	private Long roleId = null;
	private Long resourceId = null;
	private DateTime createDateTime = null;
	private DateTime updateDateTime = null;

	public RoleResource() {
		super();
	}

	public RoleResource(Long roleId, Long resourceId) {
		super();
		this.roleId = roleId;
		this.resourceId = resourceId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		RoleResource other = (RoleResource) obj;
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
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
		.append("roleId", this.getRoleId())
		.append("resourceId", this.getResourceId())
		.append("createDateTime", this.getCreateDateTime())
		.append("updateDateTime", this.getUpdateDateTime())
		.toString();
	}

}