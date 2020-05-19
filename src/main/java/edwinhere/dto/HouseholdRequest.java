/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.dto;

import edwinhere.type.HousingType;
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
public class HouseholdRequest {
	HousingType type;
}
