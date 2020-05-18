/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.models;

import edwinhere.type.HousingType;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author edwin
 */
@Entity
@Table(name = "HOUSEHOLDS")
@NamedQueries({
	@NamedQuery(name = "Household.findAll", query = "SELECT h FROM Household h")})
public class Household implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Long id;
	@Basic(optional = false)
	@Column(nullable = false, length = 256)
	@Enumerated(EnumType.STRING)
	private HousingType type;
	@OneToMany(mappedBy = "household")
	private Set<FamilyMember> familyMembers;

	public Household() {
	}

	public Household(Long id) {
		this.id = id;
	}

	public Set<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Set<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

	public Household(Long id, HousingType type) {
		this.id = id;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HousingType getType() {
		return type;
	}

	public void setType(HousingType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Household)) {
			return false;
		}
		Household other = (Household) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "edwinhere.models.Household[ id=" + id + " ]";
	}

}
