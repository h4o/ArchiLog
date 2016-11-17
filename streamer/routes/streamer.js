var express = require('express');
var router = express.Router();
var fileSystem = require('fs'),
path = require('path');
const Eureka = require('eureka-js-client').Eureka;
var request = require('request');
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
    host: 'al-discovery.herokuapp.com',
    port: 80,
    servicePath: '/eureka/apps/',
  },
});

client.start();


/* GET users listing. */
router.get('/', function(req, res) {
	var musics = ["../music.mp3","../music1.mp3"]
	var position = 0;
	var time = 0;

	var zones = extractPort('zones');

  var synchro = extractPort('Synchro');

  var playlists = extractPort('Playlists');
  console.log("http://"+zones.hostname+':'+zones.port+'/zones?longitude='+req.query.longitude+'&latitude='+req.query.latitude);
  console.log("connecting to "+zones.ip+" with port"+zones.port);
  request.get(
    "http://"+zones.hostname+':'+zones.port+'/zones?longitude='+req.query.longitude+'&latitude='+req.query.latitude,
    function (error,rest,bodyZ) {
      if (!error && rest.statusCode == 200) {

          console.log("Zone id: "+bodyZ);
          console.log("url :"+'http://'+playlists.hostname+':'+playlists.port+'/zone/'+bodyZ+'/playlist');
          request.get('http://'+playlists.hostname+':'+playlists.port+'/zone/'+bodyZ+'/playlist', function(err,resp,bodyP){
          console.log('playlist: '+bodyP);
          console.log('http://'+synchro.hostname+':'+synchro.port+'/synchro');
          var play = JSON.parse(bodyP);
          request.post(
          'http://'+synchro.hostname+':'+synchro.port+'/synchro',
          { json: play },
          function (error, resp, body) {
              if (!error && resp.statusCode == 200) {

                  position = body.position
                  time = body.time
                
                  if(position >= musics.length)
                    position = 0;
                  console.log(play.songs[position].id);
                  var filePath = path.join(__dirname,'../' + play.songs[position].id);
                  var stat = fileSystem.statSync(filePath);

                  res.set('Content-Type', 'audio/mpeg');
                  console.log("Advance the file by "+128*256*time + " ( "+time+" ) ##timestamp: "+Math.floor(Date.now() / 1000))
                  var readStream = fileSystem.createReadStream(filePath,{start: Math.floor(16*1000*time)});
                  // We replaced all the event handlers with a simple call to readStream.pipe()
                  readStream.pipe(res);

                
                }
            }
          );});
      }
    });
  /*request.post(
  'http://al-synchro.herokuapp.com/synchro',
  { json: {id:"3",songs:[{id:"Barbiegirl.mp3",length:237},{id:"Ma gueule.mp3",length:348}]} },
  function (error, resp, body) {
      if (!error && resp.statusCode == 200) {
          	position = body.position
          	time = body.time
      		
      			if(position >= musics.length)
      				position = 0;
          	    var filePath = path.join(__dirname, musics[position]);
			    var stat = fileSystem.statSync(filePath);

				  res.set('Content-Type', 'audio/mpeg');
			    console.log("Advance the file by "+128*256*time + " ( "+time+" ) ##timestamp: "+Math.floor(Date.now() / 1000))
			    var readStream = fileSystem.createReadStream(filePath,{start: Math.floor(16*1000*time)});
			    // We replaced all the event handlers with a simple call to readStream.pipe()
			    readStream.pipe(res);

				
        }
    }
	);*/
});

/*same file as client eureka */

function extractPort(ServiceName){
	const appInfo = client.getInstancesByAppId(ServiceName);
	//console.log(appInfo)
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

module.exports = router;
