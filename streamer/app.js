var http = require('http'),
    fileSystem = require('fs'),
    path = require('path');

// Or, if you're not using a transpiler: 
const Eureka = require('eureka-js-client').Eureka;
var request = require('request');
// example configuration 
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

http.createServer(function(req, response) {
	var musics = ["music.mp3","music1.mp3"]
	var position = 0;
	var time = 0;

	info = extractPort('Playlists');
	console.log(info);


	info = extractPort('Synchro');
    request.post(
    'http://al-synchro.herokuapp.com/synchro/',
    { json: {id:"3",songs:[{id:"Barbiegirl.mp3",length:237},{id:"Ma gueule.mp3",length:348}]} },
    function (error, resp, body) {
        if (!error && resp.statusCode == 200) {
            	position = body.position
            	time = body.time
        		
        			if(position >= musics.length)
        				position = 0;
            	    var filePath = path.join(__dirname, musics[position]);
				    var stat = fileSystem.statSync(filePath);

				    response.writeHead(200, {
				        'Content-Type': 'audio/mpeg'
				    });
				    console.log("Advance the file by "+128*256*time + " ( "+time+" ) ##timestamp: "+Math.floor(Date.now() / 1000))
				    var readStream = fileSystem.createReadStream(filePath,{start: Math.floor(16*1000*time)});
				    // We replaced all the event handlers with a simple call to readStream.pipe()
				    readStream.pipe(response);

				
        }
    }
	);


})
.listen(3000);


function extractPort(ServiceName){
	const appInfo = client.getInstancesByAppId(ServiceName);
	//console.log(appInfo)
	return {  port:appInfo[0].port['$'], ip:appInfo[0].ipAddr }

}
