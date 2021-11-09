package ci.gstoreplus.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                        .permitAll()
                    .antMatchers("/api/auth/**",
                    		"/api/demande/**",
                    		"/api/upload/**",
                    		"/api/uploadf/**",
                    		"/api/role/**",
                    		"/api/verificationToken/**",
                    		"/api/auth/prospect/**",
                    		"/api/produit/**","/api/detailTerrain/**",
                    		"/api/detailMaison/**","/stomp",
                    		"/api/imageDetail/**","/api/uploadImageAccueil/**",
                    		"/api/detailMaison/**", "/api/uploadImageAccueil/**",
                    		"/api/detailFlashMaison/**",
                    		"/api/imageFlashByIdFlash/**",
                    		"/api/uploadDetailFlashMaison/**",
                    		"/api/imageDetailFlashMaison/**",
                    		"/api/uploadBlog/**","/api/versement/**")
                        .permitAll()
                        .antMatchers(HttpMethod.GET, "/api/categorie/**",
                        		"/api/imageDetailMaison/**",
                         		"/api/ville/**",
                        		"/api/terrain/**", 
                        		"/api/maison/**", 
                        		"/api/uploadMaison/**",
                        		"/api/getMaisonByIdVille/**",
                        		"/api/flashTerrain/**",
                        		"/api/flashMaison/**",
                        		"/api/getTerrainByIdVille/**",
                        		"/api/getVilleByLibelle/**",
                        		"/api/getCategorieByNom/**",
                        		"/api/imageFlashTerrain/**",
                        		"/api/imageTerrain/**",
                        		"/api/getImageTerrain/**",
                        		"/api/getMembre/**",
                        		"/api/recherche/**",
                        		"/api/document/**",
                        		"/api/auth/getProspect/**",
                        		"/api/getTerrainAcheteByIdPersonne/**",
                        		"/api/role/**","/api/addRoleToEmploye/**",
                        		"/api/terrainAbidjan/**",
                        		"/api/detailTerrainByIdTerrain/**",
                        		"/api/detailTerrain/**","/api/image/**",
                        		"/api/auth/getClient/**",
                        		"/api/auth/client/**",
                        		"/api/detailMaisonByIdMaison/**",
                        		"/api/demandeNonLu/**","/api/imageDetailByIdDetail/**",
                        		"/api/imageFlashByIdFlash/**",
                        		"/api/imageDetailByIdDetailFlashMaison/**",
                        		"/api/imageAccueil/**",
                        		"/api/uploadImageAccueil/**",
                        		"/api/detailFlashMaison/**",
                        		"/api/detailFlashByIdflash/**",
                        		"/api/imageFlashByIdFlash/**",
                        		"/api/imageFlashByIdFlash/**",
                        		"/api/blog/**","/api/terrainVendu/**", 
                        		"/api/getTerrainVenduByIdPersonne/**",
                        		"/api/blogFalse/**","/api/blogTrue/**",
                        		"/api/auth/updatePassword/**","/api/versement/**").permitAll()
                                .anyRequest()
                                .authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}