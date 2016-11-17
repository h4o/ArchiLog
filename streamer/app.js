<<<<<<< HEAD
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

var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes/index');
var streamer = require('./routes/streamer');

var app = express();
app.use(function(request, response, next) {
  response.header("Access-Control-Allow-Origin", "*");
  response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

var env = process.env.NODE_ENV || 'development';
app.locals.ENV = env;
app.locals.ENV_DEVELOPMENT = env == 'development';

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


app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

// app.use(favicon(__dirname + '/public/img/favicon.ico'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: true
}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);
app.use('/streamer', streamer);

/// catch 404 and forward to error handler
app.use(function(req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

/// error handlers

// development error handler
// will print stacktrace

if (app.get('env') === 'development') {
    app.use(function(err, req, res, next) {
        res.status(err.status || 500);
        res.render('error', {
            message: err.message,
            error: err,
            title: 'error'
        });
    });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
        message: err.message,
        error: {},
        title: 'error'
    });
});


module.exports = app;
