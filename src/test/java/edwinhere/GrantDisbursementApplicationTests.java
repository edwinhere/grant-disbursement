package edwinhere;

import edwinhere.dto.HouseholdResponse;
import edwinhere.dto.FamilyMemberResponse;
import edwinhere.dto.FamilyMemberRequest;
import edwinhere.type.MaritalStatus;
import edwinhere.dto.HouseholdRequest;
import edwinhere.type.OccupationType;
import edwinhere.type.Gender;
import com.github.javafaker.Faker;
import edwinhere.type.HousingType;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GrantDisbursementApplicationTests extends AbstractTestNGSpringContextTests {

	@TestConfiguration
	static class TestConfig {

		@Bean
		public Faker faker() {
			return new Faker();
		}
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private Faker faker;

	private ResponseEntity<HouseholdResponse> householdResponse;
	private ResponseEntity<FamilyMemberResponse> familyMemberResponse;

	@Test(groups = "1")
	void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}

	@Test(groups = "2", dependsOnGroups = "1")
	void canCreateHousehold() {
		HouseholdRequest request = new HouseholdRequest(HousingType.HDB);
		ResponseEntity<HouseholdResponse> response = this.restTemplate
						.postForEntity(
										"/households",
										request,
										HouseholdResponse.class
						);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getType()).isEqualTo(request.getType());
		this.householdResponse = response;
	}

	@Test(groups = "3", dependsOnGroups = "2")
	void canAddFamilyMemberToHousehold() {
		assertThat(householdResponse).isNotNull();
		FamilyMemberRequest request = new FamilyMemberRequest(
						faker.name().fullName(),
						Gender.MALE,
						MaritalStatus.SINGLE,
						null,
						OccupationType.Unemployed,
						0,
						faker.date().birthday()
		);

		ResponseEntity<FamilyMemberResponse> response = this.restTemplate
						.postForEntity(UriComponentsBuilder
										.fromUriString("/households/{household-id}/family")
										.buildAndExpand(householdResponse.getBody().getId())
										.toUri(),
										request,
										FamilyMemberResponse.class
						);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody())
						.isEqualToIgnoringGivenFields(request, "id", "dob");

		this.familyMemberResponse = response;
	}

	@Test(groups = "4", dependsOnGroups = "3")
	void canListHouseholds() {
		assertThat(familyMemberResponse).isNotNull();
		ResponseEntity<HouseholdResponse[]> response = this.restTemplate
						.getForEntity(
										"/households",
										HouseholdResponse[].class
						);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()[0].getFamilyMembers()[0])
						.isEqualToIgnoringGivenFields(
										this.familyMemberResponse.getBody(), "dob"
						);
	}

	@Test(groups = "5", dependsOnGroups = "4")
	void canShowHousehold() {
		assertThat(householdResponse).isNotNull();
		assertThat(familyMemberResponse).isNotNull();
		ResponseEntity<HouseholdResponse> response = this.restTemplate
						.getForEntity(
										UriComponentsBuilder
														.fromUriString("/households/{household-id}")
														.buildAndExpand(householdResponse.getBody().getId())
														.toUri(),
										HouseholdResponse.class
						);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response
						.getBody()
						.getFamilyMembers()[0]
		).isEqualToIgnoringGivenFields(
						this.familyMemberResponse.getBody(), "dob"
		);
	}
}
