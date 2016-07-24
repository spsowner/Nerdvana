package com.sps;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sps.model.NerdvanaDao;
import com.sps.util.LogUtil;
import com.sps.util.SystemOutLogger;

/**
 * This class is an alternative to specifying <multipart-config> in web.xml.
 * @see http://spring.io/guides/gs/uploading-files/
 */
@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"classpath:/nerdvana.properties", "classpath:/override.properties"})
public class NerdvanaApplication implements ApplicationListener<ContextRefreshedEvent> {
	/*
	 * The property key whose value is used to decrypt encrypted passwords in properties files
	 * and which will be wiped out once the app has been started.
	 * This value is referenced in TomcatPropertyDecoder, app-scope-beans.xml, and daemon.sh.
	 */
	//private static final String ENC_SYS_PROPERTY_KEY = "propEncryptionKey";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent appEvent) {
		ApplicationContext cxt = appEvent.getApplicationContext(); 
		
		// ensure that we have the encryption key in memory
		//Preconditions.checkArgument(!Strings.isNullOrEmpty(ENC_SYS_PROPERTY_KEY));
		// wipe out the encryption key from memory;
		// HashTables cannot have null values
		//System.setProperty(ENC_SYS_PROPERTY_KEY, "");
		
		//LoggerFactory.getLogger(NerdvanaApplication.class).error("Restarted application.  Logging error to test SMTP server.");
		Thread.setDefaultUncaughtExceptionHandler(cxt.getBean(GlobalDefaultExceptionHandler.class));
		validate(cxt);
		// redirect System.out and System.err to our logger
		SystemOutLogger.redirect();
		LogUtil.runtimeStats();
	}
	
	private void validate(ApplicationContext cxt) {
    	validate(cxt, Arrays.asList(
			// make sure db is running and db parameters have been set
			NerdvanaDao.class
		));
    }
    
    private void validate(ApplicationContext cxt, Iterable<Class<? extends ConfigDependent>> configDependentClasses) {
		for (Class<? extends ConfigDependent> configDependentCls : configDependentClasses) {
			cxt.getBean(configDependentCls).validate();
		}
	}
}
