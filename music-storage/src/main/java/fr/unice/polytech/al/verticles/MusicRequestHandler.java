package fr.unice.polytech.al.verticles;

import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.streams.Pump;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by ytijani on 29/12/2016.
 */
public class MusicRequestHandler {

    public static void handleGetMusic(RoutingContext rc) {

        String title = rc.request().getParam("title");
        rc.vertx().fileSystem().open("data/music/"+title+".mp3",new OpenOptions(), ares2 -> {
            AsyncFile file = ares2.result();
            rc.response().setChunked(true);
            System.out.println(rc.response());
            Pump pump = Pump.pump(file, rc.response());
            file.endHandler(v -> rc.response().end());
            pump.start();
        });
    }

    public static void handlePostMusic(RoutingContext rc) {

    }

}
