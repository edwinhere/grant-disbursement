/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.repositories;

import edwinhere.models.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edwin
 */
@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {
	
}
