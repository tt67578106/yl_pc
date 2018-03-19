package com.ylw.entity.wanlixing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ylw.entity.IdEntity;

/**
 * @author Nicolas.
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "act_wanlixing_project_guest")
public class ActWanlixingProjectGuest extends IdEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "ActWanlixingProjectGuest";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ACT_WANLIXING_PROJECT_ID = "actWanlixingProjectId";
	public static final String ALIAS_ACT_WANLIXING_GUEST_ID = "actWanlixingGuestId";
	
	//date formats
	

	//columns START
	private java.lang.Integer actWanlixingProjectId;
	private java.lang.Integer actWanlixingGuestId;
	//columns END


	public ActWanlixingProjectGuest(){
	}

	public ActWanlixingProjectGuest(
		java.lang.Integer id
	){
		this.id = id;
	}

	
	
	
	@Column(name = "act_wanlixing_project_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getActWanlixingProjectId() {
		return this.actWanlixingProjectId;
	}
	
	public void setActWanlixingProjectId(java.lang.Integer value) {
		this.actWanlixingProjectId = value;
	}
	
	@Column(name = "act_wanlixing_guest_id", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public java.lang.Integer getActWanlixingGuestId() {
		return this.actWanlixingGuestId;
	}
	
	public void setActWanlixingGuestId(java.lang.Integer value) {
		this.actWanlixingGuestId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("ActWanlixingProjectId",getActWanlixingProjectId())
			.append("ActWanlixingGuestId",getActWanlixingGuestId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ActWanlixingProjectGuest == false) return false;
		if(this == obj) return true;
		ActWanlixingProjectGuest other = (ActWanlixingProjectGuest)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

