package fun.pplm.msc.framework.vertx;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.plugins.server.vertx.VertxRegistry;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;

import io.vertx.core.AbstractVerticle;

public abstract class ResteasyVerticle extends AbstractVerticle {
	
	public static final String CONFIG_KEY_PORT = "RESTEASY_VERTICLE_PORT";
	
	@SuppressWarnings("rawtypes")
	@Override
	public void start() throws Exception {
		VertxResteasyDeployment deployment = new VertxResteasyDeployment();
		
		List<Class<?>> classes = new ArrayList<>();
		registerProviders(classes);
		if (!classes.contains(ResteasyJackson2Provider.class)) {
			classes.add(ResteasyJackson2Provider.class);
		}
		
		deployment.setActualProviderClasses(new ArrayList<Class>(classes));
	    deployment.start();
	    VertxRegistry registry = deployment.getRegistry();
	    registerService(registry);

	    vertx.createHttpServer()
	        .requestHandler(new VertxRequestHandler(vertx, deployment))
	        .listen(Integer.parseInt(System.getProperty(CONFIG_KEY_PORT, "8080")), ar -> {
	        	System.out.println("Server started on port "+ ar.result().actualPort());
	        });
	}
	
	protected abstract void registerProviders(List<Class<?>> classes);
	
	protected abstract void registerService(VertxRegistry registry); 
	
}
