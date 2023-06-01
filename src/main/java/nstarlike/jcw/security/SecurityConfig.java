package nstarlike.jcw.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@PropertySource("classpath:application.properties")
public class SecurityConfig {
	private static final List<String> clients = Arrays.asList("google");
	private static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
	
	@Autowired
	private Environment env;
	
	@Bean("clientRegistrationRepository")
	public ClientRegistrationRepository clientRegistrationRepository() {
		List<ClientRegistration> registrations = clients.stream()
				.map(c -> clientRegistration(c))
				.filter(registration -> registration != null)
				.collect(Collectors.toList());
		return new InMemoryClientRegistrationRepository(registrations);
	}
	
	private ClientRegistration clientRegistration(String client) {
		String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");
		String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");
		
		if(clientId == null) {
			return null;
		}
		
		if(client.equals("google")) {
			return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(clientId).clientSecret(clientSecret).build();
		}
		
		return null;
	}
}
