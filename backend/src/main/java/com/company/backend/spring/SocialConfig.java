package com.company.backend.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import com.company.backend.persistence.dao.UserRepository;
import com.company.backend.security.AuthenticationUserIdExtractor;
import com.company.backend.social.AppConnectionSignUp;


@Configuration
@EnableSocial
@ComponentScan(basePackages = {"com.company.backend.social"})
public class SocialConfig implements SocialConfigurer{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private AppConnectionSignUp connectionSignUp;
	
	
	public SocialConfig() {
        super();
        
    }
   

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfc, Environment env) {
       
        GoogleConnectionFactory gcf = new GoogleConnectionFactory(
        		env.getProperty("google.api.key"),
				env.getProperty("google.api.secret"));
       // gcf.setScope("email");
        cfc.addConnectionFactory(gcf);
    }
	

	@Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationUserIdExtractor();
    }
	
	 @Override
	    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator cfl) {
	        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, cfl, Encryptors.noOpText());
	        repository.setConnectionSignUp(connectionSignUp);
	        return repository;
	    }
	 
	/* @Bean
		public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
			ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		//	connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
		//	connectController.addInterceptor(new TweetAfterConnectInterceptor());
			return connectController;
		}
		
	//	@Bean
	//	public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository, Environment environment) {
	//		return new DisconnectController(usersConnectionRepository, environment.getProperty("facebook.appSecret"));
	//	}

		@Bean
		public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
			return new ReconnectFilter(usersConnectionRepository, userIdSource);
		}
*/
	 @Bean
	    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
	        return new ConnectController(connectionFactoryLocator, connectionRepository);
	    }
	 
	 @Bean
		@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
		public Google google(ConnectionRepository repository) {
			Connection<Google> connection = repository.findPrimaryConnection(Google.class);
			return connection != null ? connection.getApi() : null;
	}
	
	
	

}
