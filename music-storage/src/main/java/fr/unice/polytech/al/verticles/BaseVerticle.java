package fr.unice.polytech.al.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/**
 * Created by user on 28/01/17.
 */
public class BaseVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> fut) {
        vertx.deployVerticle(MusicStorageVerticle.class.getName());
        vertx.deployVerticle(EurekaWorkerVerticle.class.getName(), new DeploymentOptions().setWorker(true));

    }
}
