var supertest = require("supertest");
var should = require("should");



var server = supertest.agent("https://al-zones.herokuapp.com");



describe("Zones Test",function(){
  // this.timeout(5000);
  // #1 should return home page
  it("should return airbnb propositions",function(done){

    // calling home page api
    server
      .get("/genres")
      .expect("Content-type",/json/)
      .expect(400) // THis is HTTP response
      .end(function(err,res){
        console.log(res.body); 
        res.body[0].should.equal('METAL');
        done();  
      });

  });

});