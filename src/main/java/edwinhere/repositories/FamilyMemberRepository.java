/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere.repositories;

import edwinhere.models.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edwin
 */
@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
	
}
