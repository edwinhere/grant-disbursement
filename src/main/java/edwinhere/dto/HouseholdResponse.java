/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.dto;

import edwinhere.type.HousingType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author edwin
 */
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HouseholdResponse {
	Long id;
	HousingType type;
	FamilyMemberResponse[] familyMembers;
}
