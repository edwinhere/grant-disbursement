/*
 * © 2020 Edwin Jose Palathinkal
 */
package edwinhere;

import edwinhere.dto.FamilyMemberRequest;
import edwinhere.dto.FamilyMemberResponse;
import edwinhere.dto.HouseholdRequest;
import edwinhere.dto.HouseholdResponse;
import edwinhere.models.FamilyMember;
import edwinhere.models.Household;
import edwinhere.repositories.FamilyMemberRepository;
import edwinhere.repositories.HouseholdRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author edwin
 */
@RestController
public class AllController {

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private HouseholdRepository householdRepository;

	@Autowired
	private FamilyMemberRepository familyMemberRepository;

	@PostMapping("/households")
	public ResponseEntity<HouseholdResponse> createHousehold(
					@RequestBody HouseholdRequest request
	) {
		Household household = mapper.map(request, Household.class);
		Household saved = householdRepository.save(household);
		HouseholdResponse response = mapper.map(saved, HouseholdResponse.class);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/households/{householdId}/family")
	public ResponseEntity<FamilyMemberResponse> addFamilyToHousehold(
					@PathVariable Long householdId,
					@RequestBody FamilyMemberRequest request
	) {
		Optional<Household> findById = householdRepository.findById(householdId);
		if (findById.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		FamilyMember e = mapper.map(request, FamilyMember.class);
		e.setHousehold(findById.get());
		FamilyMember saved = familyMemberRepository.save(e);
		FamilyMemberResponse response = mapper.map(saved, FamilyMemberResponse.class);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/households")
	public ResponseEntity<HouseholdResponse[]> listHouseholds() {
		List<Household> findAll = householdRepository.findAll();
		HouseholdResponse[] array = new HouseholdResponse[findAll.size()];
		List<HouseholdResponse> collect = findAll.stream().map((x) -> mapper.map(x, HouseholdResponse.class)).collect(Collectors.toList());
		array = collect.toArray(array);
		return new ResponseEntity<>(array, HttpStatus.OK);
	}

	@GetMapping("/households/{householdId}")
	public ResponseEntity<HouseholdResponse> showHousehold(
					@PathVariable Long householdId
	) {
		Optional<Household> findById = householdRepository.findById(householdId);
		if (findById.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		HouseholdResponse response = mapper.map(findById.get(), HouseholdResponse.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
