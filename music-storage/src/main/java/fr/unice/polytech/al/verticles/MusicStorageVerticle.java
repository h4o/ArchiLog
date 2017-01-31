package fr.unice.polytech.al.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

/**
 * Created by ytijani on 28/12/2016.
 */
public class MusicStorageVerticle extends AbstractVerticle {


    @Override
    public void start(Future<Void> fut) {
        String fileToServe = "www/upload.html";
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

        router.get("/musicRequest/:id")
                .produces("application/json")
                .handler(MusicRequestHandler::requestMusicById);
        router.get("/musicStore/add/").handler(routingContext -> {
           routingContext.response().sendFile(fileToServe);
        });
        router.get("/musicRequest")
                .produces("application/json")
                .handler(MusicRequestHandler::requestAllMusic);
        router.post("/musicStore/add/")
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
        JsonObject config = new JsonObject();
        String uri = config.getString("mongo_uri");
        if (uri == null) {
            uri = "mongodb://mongo:27017";
        }
        String db = config.getString("mongo_db");
        if (db == null) {
            db = "test";
        }


        JsonObject mongoconfig = new JsonObject()
                .put("connection_string", uri)
                .put("db_name", db);
        //initialize the shared client, hope it wont fail because I'm not sure how to handle that
        MongoClient mongoClient = MongoClient.createShared(vertx, mongoconfig);

    }




}
