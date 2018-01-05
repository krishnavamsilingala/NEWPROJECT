package com.company.backend.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.company.backend.security.CustomAuthenticationProvider;
import com.company.backend.security.RESTAuthenticationEntryPoint;
import com.company.backend.security.RESTAuthenticationFailureHandler;
import com.company.backend.security.RESTAuthenticationSuccessHandler;
import com.company.backend.security.RestLoginConfigurer;
import com.company.backend.social.SimpleSocialUsersDetailService;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@ComponentScan(basePackages = { "com.company.backend.security" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
				
	
		@Autowired
	    private UserDetailsService userDetailsService;
		
		 @Autowired
		 private ConfigurableApplicationContext context;
	 
	 	@Autowired
		private RESTAuthenticationEntryPoint authenticationEntryPoint;
	 	
		@Autowired
		private RESTAuthenticationFailureHandler authenticationFailureHandler;
		
		@Autowired
		private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
		
		
		public SecurityConfig() {
	        super();
	        //logger.info("loading SecurityConfig ................................................ ");
	    }
	    @Override
	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authProvider());
	    }

	    @Override
	    public void configure(final WebSecurity web) throws Exception {
	        web.ignoring()
	            .antMatchers("/resources/**");
	    }

	    @Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        // @formatter:off
	        http
	        	.authorizeRequests()
	            	.antMatchers("/rest/hello/secured/all").hasAuthority("WRITE_PRIVILEGE")
	                .antMatchers("/rest/**").authenticated()
	                .anyRequest().permitAll()
	                .and()
	                .apply(new RestLoginConfigurer<HttpSecurity>())
	                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler)
	                .loginProcessingUrl("/login")
	                .and()
	            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
	                .and()
	         /*   .formLogin()
	            	
	            	.successHandler(authenticationSuccessHandler)
	    			.failureHandler(authenticationFailureHandler)
	    			.authenticationDetailsSource(authenticationDetailsSource)
	                .and()       */
	            .sessionManagement()
	                .invalidSessionUrl("/invalidSession.html")
	                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
	                .sessionFixation().none()
	            .and()
	            .logout()
	            	.logoutSuccessUrl("/")
	                .invalidateHttpSession(false)
	                .deleteCookies("JSESSIONID")
	                .permitAll()
	             .and()
	             .apply(
		 	 				new SpringSocialConfigurer()
		 	 				.postLoginUrl("/")
		 	                .defaultFailureUrl("/signup")
		 	                .alwaysUsePostLoginUrl(true))
	             .and()
	             
	         //   .addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
	             //	.and()
	             .csrf().disable();
	            
	            
	 	             
	                
	                
	           //     .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	            
	    // @formatter:on
	    }

	    // beans

	    @Bean
	    public CustomAuthenticationProvider authProvider() {
	        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(encoder());
	        return authProvider;
	    }

	    @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder(11);
	    }

	    @Bean
	    public SessionRegistry sessionRegistry() {
	        return new SessionRegistryImpl();
	    }
	    
	    
	    @Bean
	    public SocialUserDetailsService socialUsersDetailService() {
	        return new SimpleSocialUsersDetailService();
	    }
	    
	    
	 
	    @Bean
		public TextEncryptor textEncryptor() {
			return Encryptors.noOpText();
	}
		
		



}
