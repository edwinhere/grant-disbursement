package edwinhere;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edwinhere.dto.FamilyMemberRequest;
import edwinhere.dto.FamilyMemberResponse;
import edwinhere.dto.HouseholdRequest;
import edwinhere.dto.HouseholdResponse;
import edwinhere.models.FamilyMember;
import edwinhere.models.Household;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrantDisbursementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrantDisbursementApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// enable toString method of enums to return the value to be mapped
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		return mapper;
	}

	@Bean
	public BeanMappingBuilder beanMappingBuilder() {
		return new BeanMappingBuilder() {
			@Override
			protected void configure() {
				mapping(Household.class, HouseholdRequest.class);
				mapping(Household.class, HouseholdResponse.class);
				mapping(FamilyMember.class, FamilyMemberRequest.class);
				mapping(FamilyMember.class, FamilyMemberResponse.class);
			}
		};
	}

	@Bean
	public DozerBeanMapper beanMapper() {
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.addMapping(beanMappingBuilder());
		return dozerBeanMapper;
	}
}
