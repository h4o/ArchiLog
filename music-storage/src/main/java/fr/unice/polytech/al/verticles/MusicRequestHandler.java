package fr.unice.polytech.al.verticles;

import com.sun.org.apache.xpath.internal.SourceTree;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.streams.Pump;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;

import java.util.Iterator;
import java.util.Set;

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
    public static void requestAllMusic(RoutingContext rc){
        System.out.println("Request all music");
        JsonObject config = new JsonObject();
        //hope it's already configured
        MongoClient client = MongoClient.createShared(rc.vertx(),config);
        JsonObject query = new JsonObject();
        client.find("musics",query, listAsyncResult -> {
            System.out.println("something happened !!"+listAsyncResult);
            if(listAsyncResult.succeeded()){
                if(listAsyncResult.result().size() > 0) {

                    JsonArray array = new JsonArray(listAsyncResult.result());
                    JsonObject obj = new JsonObject();
                    obj.put("results",array);
                    System.out.println("found something");
                    rc.response().putHeader("ContentType","application/json");
                    rc.response().end(obj.encodePrettily());
                }
                else {
                    System.out.println("found nothing :(");
                    rc.response().end();
                }
            } else {
                rc.response().end("Failure");
            }

        });

    }

    public static void requestMusicById(RoutingContext rc){
        System.out.println("RequestMusicById");
        JsonObject config = new JsonObject();
        //hope it's already configured
        MongoClient client = MongoClient.createShared(rc.vertx(),config);
        JsonObject query = new JsonObject();
        System.out.println(rc.request().getParam("id"));
        query.put("_id",rc.request().getParam("id"));
        client.find("musics",query, listAsyncResult -> {
            System.out.println("something happened !!"+listAsyncResult);
            if(listAsyncResult.succeeded()){
                if(listAsyncResult.result().size() > 0) {
                    System.out.println("found something");
                    rc.response().end(listAsyncResult.result().get(0).encodePrettily());
                }
                else {
                    System.out.println("found nothing :(");
                    rc.response().end();
                }
            } else {
                rc.response().end("Failure");
            }

        });
    }


    public static void handlePostMusic(RoutingContext rc) {
        Set<FileUpload> fileUploadSet = rc.fileUploads();
        Iterator<FileUpload> fileUploadIterator = fileUploadSet.iterator();
       // while(fileUploadIterator.hasNext()){
        FileUpload fileUpload;
        if(fileUploadIterator.hasNext()) {
            fileUpload = fileUploadIterator.next();

        } else {
            rc.response().end("Failure :( no files");
            return;
        }
       // }
        JsonObject music = new JsonObject();
        music.put("name",rc.request().getParam("name"))
                .put("artist",rc.request().getParam("artist"))
                .put("length",rc.request().getParam("length"))
                .put("bitrate",rc.request().getParam("bitrate"))
                .put("genre",rc.request().getParam("genre"));

        MongoClient client = MongoClient.createShared(rc.vertx(),new JsonObject());
        client.insert("musics",music,result -> {
            System.out.println("wrote to db "+result.result());
            rc.vertx().fileSystem().move(fileUpload.uploadedFileName(), "data/music/"+result.result()+".mp3", rst -> {
                rc.response().end("Success !");
            });
        });

    }
    

}
