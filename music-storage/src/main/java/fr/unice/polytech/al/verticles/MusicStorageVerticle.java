package fr.unice.polytech.al.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

/**
 * Created by ytijani on 28/12/2016.
 */
public class MusicStorageVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> fut) {
        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowedHeader("Access-Control-Request-Method")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("Content-Type"));


        router.route().handler(BodyHandler.create());

        router.get("/musicStore/:title")
                .produces("application/mpeg")
                .handler(MusicRequestHandler::handleGetMusic);

        router.post("/musicStore/")
                .consumes("application/json")
                .produces("application/json")
                .handler(MusicRequestHandler::handlePostMusic);
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );

    }

}
