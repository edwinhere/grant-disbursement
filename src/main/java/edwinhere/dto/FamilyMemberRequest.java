/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edwinhere.type.Gender;
import edwinhere.type.MaritalStatus;
import edwinhere.type.OccupationType;
import java.util.Date;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 *
 * @author edwin
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FamilyMemberRequest {

	String name;
	Gender gender;
	MaritalStatus maritalStatus;
	Optional<Integer> spouseID;
	OccupationType occupationType;
	int annualIncome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date dob;

}
