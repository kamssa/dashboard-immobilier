package ci.gstoreplus.dashboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.dashboard.metier.CloudinaryService;
import ci.gstoreplus.dashboard.metier.catalogue.BlogMetier;
import ci.gstoreplus.dashboard.metier.catalogue.ImageAccueilMetier;
import ci.gstoreplus.entity.catalogue.Blog;
import ci.gstoreplus.entity.catalogue.ImageAccueil;
import ci.gstoreplus.exception.InvalideImmobilierException;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BlogController {
	@Autowired
	private BlogMetier blogMetier;
	@Autowired
	CloudinaryService cloudinaryService;
   @Autowired
	private ObjectMapper jsonMapper;
	@Value("${dir.images}")
	private String storeImage;
	
	// recuper Categorie par identifiant
		private Reponse<Blog> getBlogById(Long id) {
			Blog blog = null;

			try {
				blog = blogMetier.findById(id);
				if (blog == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("Flash terrain n'existe pas", id));
					new Reponse<Blog>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<Blog>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<Blog>(0, null, blog);
		}

	//////////////////////////////////////////////////////////////////////////////////////////////
	////////////////// enregistrer un terrain  dans la base de donnee
	////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

		@PostMapping("/blog")
		public String creer(@RequestBody Blog blog) throws JsonProcessingException {
			System.out.println("voir blog"+ blog);
			Reponse<Blog> reponse;
			
			try {
                 
				Blog t = blogMetier.creer(blog);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", t.getId()));
				reponse = new Reponse<Blog>(0, messages, t);

			} catch (InvalideImmobilierException e) {

				reponse = new Reponse<Blog>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}
		// recherche les terrains par id
			@GetMapping("/blog/{id}")
			public String getblogById(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Blog> reponse;

				try {

					Blog t = blogMetier.findById(id);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<Blog>(0, messages, t);

				} catch (Exception e) {

					reponse = new Reponse<Blog>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// supprimer un  terrain
					@DeleteMapping("/blog/{id}")
					public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

						Reponse<Boolean> reponse = null;

						try {

							reponse = new Reponse<Boolean>(0, null, blogMetier.supprimer(id));

						} catch (RuntimeException e1) {
							reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
						}

						return jsonMapper.writeValueAsString(reponse);
					}

		// get all Terrains
			@GetMapping("/blog")
			public String findAll() throws JsonProcessingException {
				Reponse<List<Blog>> reponse;
				try {
					List<Blog> terrains = blogMetier.findAll();
					if (!terrains.isEmpty()) {
						reponse = new Reponse<List<Blog>>(0, null, terrains);
					} else {
						List<String> messages = new ArrayList<>();
						messages.add("Pas de terrain enregistrés");
						reponse = new Reponse<List<Blog>>(1, messages, new ArrayList<>());
					}

				} catch (Exception e) {
					reponse = new Reponse<>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);

			}
			// solution alterntive cloudinary//////////////////////////
			@PostMapping("/uploadBlog")
			public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
					@RequestParam Long id) throws IOException, InvalideImmobilierException{
				Map result = cloudinaryService.upload(multipartFile);
				Blog ft = blogMetier.findById(id);
				ft.setPath((String) result.get("url"));
				 blogMetier.modifier(ft);
				
				return new ResponseEntity(result, HttpStatus.OK);
			}
		
}
