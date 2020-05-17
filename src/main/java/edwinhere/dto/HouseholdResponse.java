/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.dto;

import edwinhere.type.HousingType;
import lombok.Value;

/**
 *
 * @author edwin
 */
@Value
public class HouseholdResponse {
	int id;
	HousingType type;
	FamilyMemberResponse[] familyMembers;
}
