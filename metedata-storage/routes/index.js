var express = require('express');
var request = require('request');
var url = require('url');
var router = express.Router();

var track_search_API = "https://api.spotify.com/v1/search?q="
var SEARCH_TYPE = "track";
var auth = 'BQBm0Lx2gYBlq07NDCc4ybUBmdYZDxDO2oTvplXVlKeQwNv7PIcMCbV53Cd-qo6gfQLzsgSunobluQYOXD0WMwiSKFxWZfMSJUxqHXSLgfjzlFm16e6E9VyAA9pgUbfQhGHzHqmQwVvMwA'

var album_genre_API = "https://api.spotify.com/v1/albums/"


//http://localhost:3000/search?track=one
//https://developer.spotify.com/web-api/console/get-audio-features-track/
//curl -X GET "https://api.spotify.com/v1/audio-features/06AKEBrKUckW0KREUWRnvT" -H "Accept: application/json" -H "Authorization: Bearer BQBBzmPlzwWz0c54HxEzhEmTotuivLTOiRD6Ioz7MxiftvfUITLJ5GUNfa7Vlw2OLA96kUOywYV3UjP2Qe6aV-KYiiON_UsL_snWPt_Xo-4zcHOGCZxpx-sn_awL-XqCOkW_S95Gpw0l2Q"

/* GET home page. */

router.get('/test/:maman/:papa',function(req,res){
    res.send('dindjdjbdk');
});

router.get('/search', function(req, res) {
	var metadata_request_options = {
    url: 'https://api.spotify.com/v1/audio-features/',
    headers: {
        'User-Agent': 'request',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + auth
    }
};


var artist_request_options = {
    url: 'https://api.spotify.com/v1/artists/',
    headers: {
        'User-Agent': 'request',
        'Accept': 'application/json',
        // 'Authorization': 'Bearer ' + auth
    }
};


    var url_parts = url.parse(req.url, true);
    var query = url_parts.query;
    var track_name = query.track;
    var artist_name = query.artist;
  //  artist_name = "Metallica";

    // search for song by name and artist  
    request.get(track_search_API + track_name + "&type=" + SEARCH_TYPE, function(error, rest, body) {
        if (!error && rest.statusCode == 200) {
            var track_info = filter_tracks(JSON.parse(body), artist_name);

            // use track id to get metadata
            metadata_request_options.url = metadata_request_options.url + track_info.track_id;
            request(metadata_request_options, function(error, response, body) {
                if (!error && response.statusCode == 200) {
                    var result = {};
                    result.metadata = body;
                    artist_request_options.url = artist_request_options.url + track_info.artist_id;
                        console.log(artist_request_options.url)
                    request.get(artist_request_options,function(error,response,body){
                        // console.log(body)
                    	body = JSON.parse(body);
                    	result.genres = body.genres;
                    	console.log(result)
                    	res.send(result);	
                    }) 
                }
            });
        }
    });



    // request.get('https://api.spotify.com/v1/audio-features/06AKEBrKUckW0KREUWRnvT', {
    //   'auth': {
    //     'bearer': 'bearerToken'
    //   }
    // });
    // metadata_request_options.url =metadata_request_options.url+'06AKEBrKUckW0KREUWRnvT';

    // https://api.spotify.com/v1/search?q=one&type=track
    //track_search_API
    // res.render('index', { title: 'Express' });
});

function filter_tracks(body, artist_name) {
    var track_info={};
    var items = body.tracks.items;
    for (item in items) {
        if (has_artist(items[item].artists, artist_name)) {
            track_info.track_id = items[item].id;
            track_info.artist_id = items[item].artists[0].id;
            // console.log(track_info)
            return track_info;
        }
    }
    return null;

}

function has_artist(artists, artist_name) {
    for (artist in artists) {
        // console.log(artists[artist].name);
        if (artists[artist].name === artist_name) {
            return true;

        }
    }
    return false;
}

module.exports = router;