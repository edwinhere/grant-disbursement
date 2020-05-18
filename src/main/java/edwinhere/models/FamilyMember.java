/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.models;

import edwinhere.type.Gender;
import edwinhere.type.MaritalStatus;
import edwinhere.type.OccupationType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author edwin
 */
@Entity
@Table(name = "FAMILY_MEMBERS", uniqueConstraints = {
	@UniqueConstraint(columnNames = {"SPOUSE_ID"})})
@NamedQueries({
	@NamedQuery(name = "FamilyMember.findAll", query = "SELECT f FROM FamilyMember f")})
public class FamilyMember implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(nullable = false)
	private Long id;
	@Basic(optional = false)
  @Column(nullable = false, length = 256)
	private String name;
	@Basic(optional = false)
  @Column(nullable = false, length = 256)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Basic(optional = false)
  @Column(name = "MARITAL_STATUS", nullable = false, length = 256)
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	@Basic(optional = false)
  @Column(name = "OCCUPATION_TYPE", nullable = false, length = 256)
	@Enumerated(EnumType.STRING)
	private OccupationType occupationType;
	@Column(name = "ANNUAL_INCOME")
	private Integer annualIncome;
	@Basic(optional = false)
  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
	private Date dob;
	@OneToOne(mappedBy = "spouse")
	private FamilyMember spouseReverse;
	@JoinColumn(name = "SPOUSE_ID", referencedColumnName = "ID")
  @OneToOne
	private FamilyMember spouse;
	@JoinColumn(name = "HOUSEHOLD_ID", referencedColumnName = "ID")
	@ManyToOne
	private Household household;

	public FamilyMember() {
	}

	public FamilyMember(Long id) {
		this.id = id;
	}

	public FamilyMember(Long id, String name, Gender gender, MaritalStatus maritalStatus, OccupationType occupationType, Date dob) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.occupationType = occupationType;
		this.dob = dob;
	}

	public Household getHousehold() {
		return household;
	}

	public void setHousehold(Household household) {
		this.household = household;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public OccupationType getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(OccupationType occupationType) {
		this.occupationType = occupationType;
	}

	public Integer getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Integer annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public FamilyMember getSpouseReverse() {
		return spouseReverse;
	}

	public void setSpouseReverse(FamilyMember spouseReverse) {
		this.spouseReverse = spouseReverse;
	}

	public FamilyMember getSpouse() {
		return spouse;
	}

	public void setSpouse(FamilyMember spouse) {
		this.spouse = spouse;
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
		if (!(object instanceof FamilyMember)) {
			return false;
		}
		FamilyMember other = (FamilyMember) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "edwinhere.models.FamilyMember[ id=" + id + " ]";
	}
	
}
