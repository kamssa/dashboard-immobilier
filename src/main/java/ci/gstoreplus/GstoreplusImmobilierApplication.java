package ci.gstoreplus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.crypto.password.PasswordEncoder;

import ci.gstoreplus.dao.dashboard.catalogue.ProduitRepository;
import ci.gstoreplus.dao.dashboard.personne.PersonneRepository;
import ci.gstoreplus.dao.dashboard.personne.RoleRepository;
import ci.gstoreplus.entity.dashboard.admin.Admin;
import ci.gstoreplus.entity.dashboard.shared.Role;
import ci.gstoreplus.entity.dashboard.shared.RoleName;

@SpringBootApplication
@EntityScan(basePackageClasses = { 
		GstoreplusImmobilierApplication.class,
		Jsr310JpaConverters.class 
})
public class GstoreplusImmobilierApplication implements CommandLineRunner {
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	private Map<String, Integer> progress = new HashMap<>();

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	public static void main(String[] args) {
		SpringApplication.run(GstoreplusImmobilierApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		/*this.roleRepository.save(new Role(RoleName.ROLE_COMMERCIAL));
		this.roleRepository.save(new Role(RoleName.ROLE_EXPLOITANT));
		this.roleRepository.save(new Role(RoleName.ROLE_ABONNE));
		this.roleRepository.save(new Role(RoleName.ROLE_MEMBRE));
		this.roleRepository.save(new Role(RoleName.ROLE_CLIENT));
		this.roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
		Admin ad = new Admin("Traore", "Abdoulaye", "kamssa0@gmail.com", passwordEncoder.encode("Cancer01"));
		ad.setRoles(Collections.singleton(userRole));	
		ad = personneRepository.save(ad);*/
	}
	@Bean
	public CommandLineRunner websocketDemo() {
		return (args) -> {
			while (true) {
				try {
					Thread.sleep(3*1000); // Each 3 sec.
					progress.put("num1", randomWithRange(0, 100));
					progress.put("num2", randomWithRange(0, 100));
					messagingTemplate.convertAndSend("/topic/progress", this.progress);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	/**
	 * Get a random integer value in a min / max range.
	 * @param min min range value
	 * @param max max range value
	 * @return A random integer value
	 */
	private int randomWithRange(int min, int max)
	{
		int range = Math.abs(max - min) + 1;
		return (int)(Math.random() * range) + (min <= max ? min : max);
	}

}
