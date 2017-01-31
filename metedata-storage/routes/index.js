var express = require('express');
var request = require('request');
var url = require('url');
var router = express.Router();

var track_search_API = "https://api.spotify.com/v1/search?q="
var SEARCH_TYPE = "track";
var auth = 'BQA9hSnOV9LzQwwJjmFhxUJcOyFEeoP-a81Q2o4xnNS2SudKoXROgbBsXk3ltPsDE_XqPRf3zx5amEHWj7nkWm8JPfAHDd-jch-v3g9nKBPd61AK6hUwyh8lv8fCjRPCv09dog'
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


var album_request_options = {
    url: 'https://api.spotify.com/v1/albums/',
    headers: {
        'User-Agent': 'request',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + auth
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
                    result.metadata = JSON.parse(body);
                    album_request_options.url = album_request_options.url + track_info.album_id + '?market=FR';

                    request.get(album_request_options,function(error,response,body){
                    	body = JSON.parse(body);
                    	result.genres = body.genres;
                    	// console.log(result)
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
            track_info.album_id = items[item].album.id;
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