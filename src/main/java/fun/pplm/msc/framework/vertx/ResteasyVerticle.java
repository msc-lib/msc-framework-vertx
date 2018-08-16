package fun.pplm.msc.framework.vertx;

import org.jboss.resteasy.plugins.server.vertx.VertxRegistry;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;

import io.vertx.core.AbstractVerticle;

public abstract class ResteasyVerticle extends AbstractVerticle {
	
	public static final String CONFIG_KEY_PORT = "RESTEASY_VERTICLE_PORT";
	
	@Override
	public void start() throws Exception {
		VertxResteasyDeployment deployment = new VertxResteasyDeployment();
		deploy(deployment);
	    deployment.start();
	    VertxRegistry registry = deployment.getRegistry();
	    register(registry);

	    vertx.createHttpServer()
	        .requestHandler(new VertxRequestHandler(vertx, deployment))
	        .listen(Integer.parseInt(System.getProperty(CONFIG_KEY_PORT, "8080")), ar -> {
	          System.out.println("Server started on port "+ ar.result().actualPort());
	        });
	}
	
	protected abstract void deploy(VertxResteasyDeployment deployment);
	
	protected abstract void register(VertxRegistry registry); 
	
}
