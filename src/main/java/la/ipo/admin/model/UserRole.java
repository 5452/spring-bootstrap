package la.ipo.admin.model;

import org.joda.time.DateTime;
import org.springframework.core.style.ToStringCreator;

public class UserRole {

	private Long roleId = null;
	private Long userId = null;
	private DateTime createDateTime = null;
	private DateTime updateDateTime = null;

	public UserRole() {
		super();
	}

	public UserRole(Long roleId, Long userId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((updateDateTime == null) ? 0 : updateDateTime.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserRole other = (UserRole) obj;
		if (createDateTime == null) {
			if (other.createDateTime != null)
				return false;
		} else if (!createDateTime.equals(other.createDateTime))
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
		.append("userId", this.getUserId())
		.append("roleId", this.getRoleId())
		.append("createDateTime", this.getCreateDateTime())
		.append("updateDateTime", this.getUpdateDateTime())
		.toString();
	}

}