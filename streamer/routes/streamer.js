var express = require('express');
var router = express.Router();
var fileSystem = require('fs'),
path = require('path');
const Eureka = require('eureka-js-client').Eureka;
var request = require('request');
var Pusher = require('pusher-js/node');//we need the node version
var PropertiesReader = require('properties-reader');
var properties = PropertiesReader('pusher.properties');
var CombinedStream = require('combined-stream2');
Pusher.logToConsole = true;

    var pusher = new Pusher(properties.get('token'), {
      cluster: 'eu',
      encrypted: true
    });
var io;
/*
var request = require('request-promise'); // suffisant si tes entrees et sorties sont des requetes http
var q = require('q');// check la doc de q
*/


/* a mettre apres dans app.js ou sur un autre fichier et utiliser 
module.exports */
const client = new Eureka({
  instance: {
    app: 'Streamer',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    statusPageUrl: 'http://localhost:3000',
    healthCheckUrl: 'http://localhost:3000/health',
    port: {
      '$': 3000,
      '@enabled': true,
    },
    vipAddress: 'Streamer',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: process.env.eurekaUrl ||'localhost',
    port: process.env.eurekaPort || 8761,
    servicePath: '/eureka/apps/',
},
});

client.start();


/* GET users listing. */
router.get('/', function(req, res) {


    var play;
    //we only sucribe and bind to a channel when streaming
    var channel = pusher.subscribe('my-channel');
    channel.bind('my-event', function(data) {
      console.log('========================================\nUPDATE EVENT');
      synchronize_request(data.zone, function(err,resp,body) {
          if (!err && resp.statusCode == 200) {
              var parsed_body = JSON.parse(body);
              play = parsed_body.playlist;
                  io.emit('synchronize',{ status : "ok"});
              console.log(play);
          }
      });
    });

	var musics = ["../music.mp3","../music1.mp3"]
	var position = 0;
	var time = 0;

	var zones = extractPort('zones');

  var synchro = extractPort('Synchro');

  request.get(
    "http://"+
    zones.hostname
    +':'+
    zones.port
    +'/zones?longitude='+req.query.longitude+'&latitude='+req.query.latitude,
    function (error,rest,bodyZ) {
      if (!error && rest.statusCode == 200) {


          //var play = JSON.parse(bodyP);
          synchronize_request(bodyZ, function (error, resp, body) {

              if (!error && resp.statusCode == 200) {
                  console.log(body)
                  body = JSON.parse(body);
                  play = body.playlist;
                  position = body.position;
                  time = body.time;
                res.set('Content-Type', 'audio/mpeg');

                  var combinedStream = CombinedStream.create();
                  var readStream = stream_music(play,position,time,res);
                  combinedStream.append(readStream);
                  // We replaced all the event handlers with a simple call to readStream.pipe()
                  append_streams(position,combinedStream,play,res,time)

                  combinedStream.pipe(res);



                
                }
            });
      }
    });

});

router.get("/genre/:genre",function(req,res){
    console.log('#############');
    console.log(io);
    io.on('connection',function(socket) {
      console.log('client listening on socket.');
  });
  //res.send("get genre "+req.params.genre);
      var play;
    //we only sucribe and bind to a channel when streaming
    var channel = pusher.subscribe('my-channel');
    channel.bind('my-event', function(data) {
      console.log('========================================\nUPDATE EVENT');
      synchronize_request(data.zone, function(err,resp,body) {
          if (!err && resp.statusCode == 200) {
              var parsed_body = JSON.parse(body);
              play = parsed_body.playlist;
              console.log(play);
          }
      });
    });

  var musics = ["../music.mp3","../music1.mp3"]
  var position = 0;
  var time = 0;

  var zones = extractPort('zones');

  var synchro = extractPort('Synchro');
  console.log("Requesting the zone....");
  console.log(    "http://"+
    zones.hostname
    +':'+
    zones.port
    +'/zones/'+req.params.genre)
  request.get(
    "http://"+
    zones.hostname
    +':'+
    zones.port
    +'/zones/'+req.params.genre,
    function (error,rest,bodyZ) {
      if (!error && rest.statusCode == 200) {
          console.log("done");

          //var play = JSON.parse(bodyP);
          synchronize_request(bodyZ, function (error, resp, body) {

              if (!error && resp.statusCode == 200) {
                  console.log(body)
                  body = JSON.parse(body);
                  play = body.playlist;
                  position = body.position;
                  time = body.time;
                res.set('Content-Type', 'audio/mpeg');

                  var combinedStream = CombinedStream.create();
                  console.log("trying to read the stream");
                  var readStream = stream_music(play,position,time,res);

                  combinedStream.append(readStream);
                  // We replaced all the event handlers with a simple call to readStream.pipe()
                  append_streams(position,combinedStream,play,res,0)

                  combinedStream.pipe(res);



                
                }
            });
      }
    });

});

function append_streams(index,combinedStream,play,res,time){
    index++;
      if(index === play.songs.length)
        return;
                    combinedStream.append(function(next){
                      console.log('append stream for time'+time);
                       next(stream_music(play,index,time,res));
                      
                      
                    });
                    append_streams(index,combinedStream,play,res,0);
}

function synchronize_request(body,callback) {
          var synchro = extractPort("Synchro");
            request.get(
          'http://'+
            synchro.hostname
            +':'+
            synchro.port
            +'/synchroZone/' + body,callback);
 

}







function stream_music(play,position,time,res) {
                
                  if(position >= play.songs.length)
                    position = 0;
                  console.log(play.songs[position].id);
                  var filePath = path.join(__dirname,'../' + play.songs[position].id);
                  var stat = fileSystem.statSync(filePath);

                  
                  console.log("Advance the file by "+128*256*time + " ( "+time+" ) ##timestamp: "+Math.floor(Date.now() / 1000))
                  var music_to_stream = fileSystem.createReadStream(filePath,{start: Math.floor(16*1000*time)});
                  return music_to_stream;
}





/*same file as client eureka */

function extractPort(ServiceName){
	const appInfo = client.getInstancesByAppId(ServiceName);
	console.log(appInfo)
	return {  port:appInfo[0].port['$'], hostname:appInfo[0].hostName }

}
/*
cette fonction va return file avant la fin de la boucle
vu que c'est non bloquant
function ma_fonc (musicName){
	for(blabla) {
		if(toto)
		file = fs.read;
	}
	return file;
}

astuce :

function ma_fonc (inex,musicName){
	if i== length
	return file;


		if(var === musicName) {
		file = fs.read;
		return file;
		}
	 ma_fonc(++idex,musicName); // ou avec un return ma_fonc
} */

module.exports = function(ios) {
    io = ios;
    return router;
}
