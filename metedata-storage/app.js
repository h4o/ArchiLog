var http = require('http'),
    fileSystem = require('fs'),
    path = require('path');

// Or, if you're not using a transpiler: 
const Eureka = require('eureka-js-client').Eureka;




/* a mettre apres dans app.js ou sur un autre fichier et utiliser 
module.exports */
const client = new Eureka({
  instance: {
    app: 'Metadata',
    hostName: 'al-discovery.herokuapp.com',
    //ipAddr: '127.0.0.1',
    statusPageUrl:  process.env.selfUrl || 'http://al-discovery.herokuapp.com',
    healthCheckUrl: process.env.selfUrl+'/health' || 'http://al-discovery.herokuapp.com/health',
    port: {
      '$': 3001,
      '@enabled': true,
    },
      vipAddress: 'Metadata',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: process.env.eurekaUrl || 'al-discovery.herokuapp.com',
    port: process.env.eurekaPort || 80,
    servicePath: process.env.eurekaPath || '/eureka/apps/',
  },
});

client.start();

var request = require('request');
// example configuration 
var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

var routes = require('./routes/index');
var metadata = require('./routes/metadata');

var app = express();
app.use(function(request, response, next) {
  response.header("Access-Control-Allow-Origin", "*");
  response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

var env = process.env.NODE_ENV || 'development';
app.locals.ENV = env;
app.locals.ENV_DEVELOPMENT = env == 'development';

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
app.use('/metadata', metadata);

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
