package fun.pplm.msc.framework.vertx;

import java.util.function.Consumer;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VerticleRunner {

	public static void startup(Class<? extends ResteasyVerticle> clazz) {
		VertxOptions options = new VertxOptions();
		options.setClustered(false);
		
		Consumer<Vertx> runner = vertx -> {
			try {
				vertx.deployVerticle(clazz.getName());
			} catch (Throwable t) {
				t.printStackTrace();
			}
		};
		Vertx vertx = Vertx.vertx(options);
		runner.accept(vertx);
	}
}
