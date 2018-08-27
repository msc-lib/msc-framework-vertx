package fun.pplm.msc.framework.vertx.utils;

import io.vertx.core.json.Json;

public final class PrintHelper {
	
	public static void jsonPretty(Object object) {
		System.out.println(Json.encodePrettily(object));
	}
	
}
