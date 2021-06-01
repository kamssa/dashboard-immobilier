package ci.gstoreplus;

import java.util.Collections;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.password.PasswordEncoder;

import ci.gstoreplus.dao.dashboard.catalogue.ProduitRepository;
import ci.gstoreplus.dao.dashboard.personne.PersonneRepository;
import ci.gstoreplus.dao.dashboard.personne.RoleRepository;
import ci.gstoreplus.entity.catalogue.Document;
import ci.gstoreplus.entity.catalogue.Produit;
import ci.gstoreplus.entity.catalogue.Ville;
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
		//this.roleRepository.save(new Role(RoleName.ROLE_MEMBRE));
		//this.roleRepository.save(new Role(RoleName.ROLE_CLIENT));
		Role userRole = roleRepository.findByName(RoleName.ROLE_COMMERCIAL).get();
		Admin ad = new Admin("Kouadio", "Charles", "charle@gmail.com", passwordEncoder.encode("123456"));
		ad.setRoles(Collections.singleton(userRole));
         ad = personneRepository.save(ad);*/
		//this.produitRepository.deleteById(53L);
		/*Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
		Admin ad = new Admin("Traore", "Abdoulaye", "kamssa0@gmail.com", passwordEncoder.encode("Cancer01"));
		ad.setRoles(Collections.singleton(userRole));	
		ad = personneRepository.save(ad);*/
	}

}
