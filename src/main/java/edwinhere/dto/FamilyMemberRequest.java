/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.dto;

import edwinhere.type.Gender;
import edwinhere.type.MaritalStatus;
import edwinhere.type.OccupationType;
import java.util.Date;
import java.util.Optional;
import lombok.Value;

/**
 *
 * @author edwin
 */
@Value
public class FamilyMemberRequest {

	String name;
	Gender gender;
	MaritalStatus maritalStatus;
	Optional<Integer> spouseID;
	OccupationType occupationType;
	int annualIncome;
	Date dob;

}
