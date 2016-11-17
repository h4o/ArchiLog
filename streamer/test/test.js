var supertest = require("supertest");
var should = require("should");
var eureka = require("../routes/streamer");

// This agent refers to PORT where program is runninng.
var host = "http://localhost:"+ 3000;
var server = supertest.agent(host);

// UNIT test begin

describe("streaming test",function(){

  // #1 should return home page

  it("should return a stream",function(done){

    server
    .get("/streamer?latitude=1&longitude=1")
    .expect("Content-type",'audio/blabla')
    .expect(200) // THis is HTTP response
    .end(function(err,res){
      done();
    });
  });

});