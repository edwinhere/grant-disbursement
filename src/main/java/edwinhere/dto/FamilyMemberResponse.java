/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edwinhere.type.Gender;
import edwinhere.type.MaritalStatus;
import edwinhere.type.OccupationType;
import java.util.Date;
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
public class FamilyMemberResponse {
	
	Long id;
	String name;
	Gender gender;
	MaritalStatus maritalStatus;
	Long spouseID;
	OccupationType occupationType;
	int annualIncome;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date dob;
}
