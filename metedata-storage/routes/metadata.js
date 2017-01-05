var express = require('express');
var router = express.Router();

router.get('/',function(req,res) {
	var query = req.query.search;
	res.send(requestSpotify(query));
});

const Eureka = require('eureka-js-client').Eureka;



/* a mettre apres dans app.js ou sur un autre fichier et utiliser 
module.exports */
const client = new Eureka({
  instance: {
    app: 'Metadata',
    hostName: 'localhost',
    ipAddr: '127.0.0.1',
    statusPageUrl:  process.env.selfUrl || 'http://localhost:3000',
    healthCheckUrl: process.env.selfUrl+'/health' || 'http://localhost:3000/health',
    port: {
      '$': 3000,
      '@enabled': true,
    },
    vipAddress: 'Metadata',
    dataCenterInfo: {
      '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
      name: 'MyOwn',
    },
  },
  eureka: {
    host: process.env.eurekaUrl || 'localhost',
    port: process.env.eurekaPort || 8761,
    servicePath: process.env.eurekaPath || '/eureka/apps/',
  },
});




function requestSpotify(query) {
	return {
  "tracks" : {
    "href" : "https://api.spotify.com/v1/search?query=narcos+-+tuyo&offset=0&limit=20&type=track&market=FR",
    "items" : [ {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/5SiBQxijnMwUGUYG31gZnS"
          },
          "href" : "https://api.spotify.com/v1/artists/5SiBQxijnMwUGUYG31gZnS",
          "id" : "5SiBQxijnMwUGUYG31gZnS",
          "name" : "Pedro Bromfman",
          "type" : "artist",
          "uri" : "spotify:artist:5SiBQxijnMwUGUYG31gZnS"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/49Qb3S79ev6Xno2TeJCsNg"
        },
        "href" : "https://api.spotify.com/v1/albums/49Qb3S79ev6Xno2TeJCsNg",
        "id" : "49Qb3S79ev6Xno2TeJCsNg",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/8ed3fc7a75c88aacb97af537b44364326033383b",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/77db5589eed30bbf975f2b977a81b849ec0cad38",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/7eaa1bfcbbf414b08e425c714f606936d3b35aa3",
          "width" : 64
        } ],
        "name" : "Narcos (A Netflix Original Series Soundtrack)",
        "type" : "album",
        "uri" : "spotify:album:49Qb3S79ev6Xno2TeJCsNg"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0UOrkpzPED604dKzxgfJqg"
        },
        "href" : "https://api.spotify.com/v1/artists/0UOrkpzPED604dKzxgfJqg",
        "id" : "0UOrkpzPED604dKzxgfJqg",
        "name" : "Rodrigo Amarante",
        "type" : "artist",
        "uri" : "spotify:artist:0UOrkpzPED604dKzxgfJqg"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 89293,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USLS51551202"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/4hC7fmtx9LmjruoIieWT0m"
      },
      "href" : "https://api.spotify.com/v1/tracks/4hC7fmtx9LmjruoIieWT0m",
      "id" : "4hC7fmtx9LmjruoIieWT0m",
      "name" : "Tuyo",
      "popularity" : 66,
      "preview_url" : "https://p.scdn.co/mp3-preview/256f72f058371c26b9a5d61a2c51e309fc44b614?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 2,
      "type" : "track",
      "uri" : "spotify:track:4hC7fmtx9LmjruoIieWT0m"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/0UOrkpzPED604dKzxgfJqg"
          },
          "href" : "https://api.spotify.com/v1/artists/0UOrkpzPED604dKzxgfJqg",
          "id" : "0UOrkpzPED604dKzxgfJqg",
          "name" : "Rodrigo Amarante",
          "type" : "artist",
          "uri" : "spotify:artist:0UOrkpzPED604dKzxgfJqg"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/3B2t98eiSQyDmRR8oI8hf4"
        },
        "href" : "https://api.spotify.com/v1/albums/3B2t98eiSQyDmRR8oI8hf4",
        "id" : "3B2t98eiSQyDmRR8oI8hf4",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/c4a11f81715bea214b03b13bafc9b5219fb43e27",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/e652127d9aa1943c99682283f44596a642fe514d",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/0ae3d454b27ec9434db7fffed9c324a3b7c8346a",
          "width" : 64
        } ],
        "name" : "Tuyo - Narcos Theme (A Netflix Original Series Soundtrack)",
        "type" : "album",
        "uri" : "spotify:album:3B2t98eiSQyDmRR8oI8hf4"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0UOrkpzPED604dKzxgfJqg"
        },
        "href" : "https://api.spotify.com/v1/artists/0UOrkpzPED604dKzxgfJqg",
        "id" : "0UOrkpzPED604dKzxgfJqg",
        "name" : "Rodrigo Amarante",
        "type" : "artist",
        "uri" : "spotify:artist:0UOrkpzPED604dKzxgfJqg"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 89293,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USLS51551202"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/1ue7BookWlm8Ia3QQPkmfG"
      },
      "href" : "https://api.spotify.com/v1/tracks/1ue7BookWlm8Ia3QQPkmfG",
      "id" : "1ue7BookWlm8Ia3QQPkmfG",
      "name" : "Tuyo - Narcos Theme (A Netflix Original Series Soundtrack)",
      "popularity" : 64,
      "preview_url" : "https://p.scdn.co/mp3-preview/256f72f058371c26b9a5d61a2c51e309fc44b614?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:1ue7BookWlm8Ia3QQPkmfG"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/5SiBQxijnMwUGUYG31gZnS"
          },
          "href" : "https://api.spotify.com/v1/artists/5SiBQxijnMwUGUYG31gZnS",
          "id" : "5SiBQxijnMwUGUYG31gZnS",
          "name" : "Pedro Bromfman",
          "type" : "artist",
          "uri" : "spotify:artist:5SiBQxijnMwUGUYG31gZnS"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/27Y8E2446JliIWR2PBCgpw"
        },
        "href" : "https://api.spotify.com/v1/albums/27Y8E2446JliIWR2PBCgpw",
        "id" : "27Y8E2446JliIWR2PBCgpw",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/e7ead4c020a666216c0b5d6a90431545b844670c",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/377591256d8ba16eca0d704b7b09c90c0915df06",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/18a8666af1e373a32b8438f5fbbd048f8e03597f",
          "width" : 64
        } ],
        "name" : "Narcos - Deluxe Edition (A Netflix Original Series Soundtrack)",
        "type" : "album",
        "uri" : "spotify:album:27Y8E2446JliIWR2PBCgpw"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0UOrkpzPED604dKzxgfJqg"
        },
        "href" : "https://api.spotify.com/v1/artists/0UOrkpzPED604dKzxgfJqg",
        "id" : "0UOrkpzPED604dKzxgfJqg",
        "name" : "Rodrigo Amarante",
        "type" : "artist",
        "uri" : "spotify:artist:0UOrkpzPED604dKzxgfJqg"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 89293,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USLS51551202"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/2DhxAA9IzMUacV7QNrNNXk"
      },
      "href" : "https://api.spotify.com/v1/tracks/2DhxAA9IzMUacV7QNrNNXk",
      "id" : "2DhxAA9IzMUacV7QNrNNXk",
      "name" : "Tuyo",
      "popularity" : 36,
      "preview_url" : "https://p.scdn.co/mp3-preview/256f72f058371c26b9a5d61a2c51e309fc44b614?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 2,
      "type" : "track",
      "uri" : "spotify:track:2DhxAA9IzMUacV7QNrNNXk"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/5SiBQxijnMwUGUYG31gZnS"
          },
          "href" : "https://api.spotify.com/v1/artists/5SiBQxijnMwUGUYG31gZnS",
          "id" : "5SiBQxijnMwUGUYG31gZnS",
          "name" : "Pedro Bromfman",
          "type" : "artist",
          "uri" : "spotify:artist:5SiBQxijnMwUGUYG31gZnS"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/69Ne0hPtLHOTk0PU9RZC2p"
        },
        "href" : "https://api.spotify.com/v1/albums/69Ne0hPtLHOTk0PU9RZC2p",
        "id" : "69Ne0hPtLHOTk0PU9RZC2p",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/3d45d751d08c0fab5ba934f69b717080b65371a0",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/3d9f502cdb9c081c3fe383b3a310440b358941a8",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/4631192f640b5a82a1766025a28b46f7f792d888",
          "width" : 64
        } ],
        "name" : "Narcos, Season 2 (A Netflix Original Series Soundtrack)",
        "type" : "album",
        "uri" : "spotify:album:69Ne0hPtLHOTk0PU9RZC2p"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0UOrkpzPED604dKzxgfJqg"
        },
        "href" : "https://api.spotify.com/v1/artists/0UOrkpzPED604dKzxgfJqg",
        "id" : "0UOrkpzPED604dKzxgfJqg",
        "name" : "Rodrigo Amarante",
        "type" : "artist",
        "uri" : "spotify:artist:0UOrkpzPED604dKzxgfJqg"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 91973,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USLS51689820"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/0dckHY8V9G37mR4dLOVHl7"
      },
      "href" : "https://api.spotify.com/v1/tracks/0dckHY8V9G37mR4dLOVHl7",
      "id" : "0dckHY8V9G37mR4dLOVHl7",
      "name" : "Tuyo",
      "popularity" : 31,
      "preview_url" : "https://p.scdn.co/mp3-preview/9c6f9c37cd9b808f9be8e54af7bc42e5095f6c77?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 20,
      "type" : "track",
      "uri" : "spotify:track:0dckHY8V9G37mR4dLOVHl7"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/4eK8tOP1y12SKI2WpWbo6N"
          },
          "href" : "https://api.spotify.com/v1/artists/4eK8tOP1y12SKI2WpWbo6N",
          "id" : "4eK8tOP1y12SKI2WpWbo6N",
          "name" : "El beso del escorpión",
          "type" : "artist",
          "uri" : "spotify:artist:4eK8tOP1y12SKI2WpWbo6N"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/6icSVvx5tUmYd1uLSt8Sgz"
        },
        "href" : "https://api.spotify.com/v1/albums/6icSVvx5tUmYd1uLSt8Sgz",
        "id" : "6icSVvx5tUmYd1uLSt8Sgz",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/daad925e7afa31e496a4a15dacd7000b8c52fcaa",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/a93338b2b6d4a1d1f181f9cb3823697e628c610b",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/25f0d075a96904b636be218c3bfa4890f45da12e",
          "width" : 64
        } ],
        "name" : "Tuyo (Narcos Intro) [Versión]",
        "type" : "album",
        "uri" : "spotify:album:6icSVvx5tUmYd1uLSt8Sgz"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4eK8tOP1y12SKI2WpWbo6N"
        },
        "href" : "https://api.spotify.com/v1/artists/4eK8tOP1y12SKI2WpWbo6N",
        "id" : "4eK8tOP1y12SKI2WpWbo6N",
        "name" : "El beso del escorpión",
        "type" : "artist",
        "uri" : "spotify:artist:4eK8tOP1y12SKI2WpWbo6N"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 91754,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "ESA011627409"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/50TMLg8RflgPP9u6UUH5e4"
      },
      "href" : "https://api.spotify.com/v1/tracks/50TMLg8RflgPP9u6UUH5e4",
      "id" : "50TMLg8RflgPP9u6UUH5e4",
      "name" : "Tuyo (Narcos Intro) - Versión",
      "popularity" : 22,
      "preview_url" : "https://p.scdn.co/mp3-preview/34b9ecd4978f34ecd8598f552c4d80dace219d2e?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:50TMLg8RflgPP9u6UUH5e4"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/3pJy7ivp2DTdbTrigH4oeQ"
          },
          "href" : "https://api.spotify.com/v1/artists/3pJy7ivp2DTdbTrigH4oeQ",
          "id" : "3pJy7ivp2DTdbTrigH4oeQ",
          "name" : "Farralone",
          "type" : "artist",
          "uri" : "spotify:artist:3pJy7ivp2DTdbTrigH4oeQ"
        }, {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/4xrhbPoIZf67Cmcm8bBHvP"
          },
          "href" : "https://api.spotify.com/v1/artists/4xrhbPoIZf67Cmcm8bBHvP",
          "id" : "4xrhbPoIZf67Cmcm8bBHvP",
          "name" : "James Kennedy",
          "type" : "artist",
          "uri" : "spotify:artist:4xrhbPoIZf67Cmcm8bBHvP"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/390iZ2cnKufvwM5Lq2N40M"
        },
        "href" : "https://api.spotify.com/v1/albums/390iZ2cnKufvwM5Lq2N40M",
        "id" : "390iZ2cnKufvwM5Lq2N40M",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/78124f826af5047f59754a62c4c5bf60b2783bef",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/50cc52256d802efea50516982643a007aa91f3fb",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/c69dd17d58899ac1eaa318231bbd4f57f4d26114",
          "width" : 64
        } ],
        "name" : "Narcos Theme (Tuyo)",
        "type" : "album",
        "uri" : "spotify:album:390iZ2cnKufvwM5Lq2N40M"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/3pJy7ivp2DTdbTrigH4oeQ"
        },
        "href" : "https://api.spotify.com/v1/artists/3pJy7ivp2DTdbTrigH4oeQ",
        "id" : "3pJy7ivp2DTdbTrigH4oeQ",
        "name" : "Farralone",
        "type" : "artist",
        "uri" : "spotify:artist:3pJy7ivp2DTdbTrigH4oeQ"
      }, {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4xrhbPoIZf67Cmcm8bBHvP"
        },
        "href" : "https://api.spotify.com/v1/artists/4xrhbPoIZf67Cmcm8bBHvP",
        "id" : "4xrhbPoIZf67Cmcm8bBHvP",
        "name" : "James Kennedy",
        "type" : "artist",
        "uri" : "spotify:artist:4xrhbPoIZf67Cmcm8bBHvP"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 244000,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "QM6P41585913"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/5T8szCMIgtOs9eiG2iqfXq"
      },
      "href" : "https://api.spotify.com/v1/tracks/5T8szCMIgtOs9eiG2iqfXq",
      "id" : "5T8szCMIgtOs9eiG2iqfXq",
      "name" : "Narcos Theme (Tuyo)",
      "popularity" : 10,
      "preview_url" : "https://p.scdn.co/mp3-preview/55fd469b30709c16a386726113e6e6fcf94bf1b9?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:5T8szCMIgtOs9eiG2iqfXq"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/6a3QgiFnR96MM8nkJfp2Ev"
          },
          "href" : "https://api.spotify.com/v1/artists/6a3QgiFnR96MM8nkJfp2Ev",
          "id" : "6a3QgiFnR96MM8nkJfp2Ev",
          "name" : "Iker Plan",
          "type" : "artist",
          "uri" : "spotify:artist:6a3QgiFnR96MM8nkJfp2Ev"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/5jqJoY7EFtGqMrDvXTkoRG"
        },
        "href" : "https://api.spotify.com/v1/albums/5jqJoY7EFtGqMrDvXTkoRG",
        "id" : "5jqJoY7EFtGqMrDvXTkoRG",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/86e41f71ed38c3fe3a9a0b855d31cec20a9e72af",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/0efd7f051b6f582265ffa7c4978cf629987d49e0",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/d42b2ebce1f84ae78cfe565451aa5193b87824c7",
          "width" : 64
        } ],
        "name" : "Tuyo",
        "type" : "album",
        "uri" : "spotify:album:5jqJoY7EFtGqMrDvXTkoRG"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/6a3QgiFnR96MM8nkJfp2Ev"
        },
        "href" : "https://api.spotify.com/v1/artists/6a3QgiFnR96MM8nkJfp2Ev",
        "id" : "6a3QgiFnR96MM8nkJfp2Ev",
        "name" : "Iker Plan",
        "type" : "artist",
        "uri" : "spotify:artist:6a3QgiFnR96MM8nkJfp2Ev"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 98181,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "GBLPF0100730"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/63KkEb3NO8px8Ktgzji9Qf"
      },
      "href" : "https://api.spotify.com/v1/tracks/63KkEb3NO8px8Ktgzji9Qf",
      "id" : "63KkEb3NO8px8Ktgzji9Qf",
      "name" : "Tuyo - NARCOS THEME",
      "popularity" : 12,
      "preview_url" : "https://p.scdn.co/mp3-preview/4a566c4b242548d2f5d1a6061765d6b621b38ba4?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:63KkEb3NO8px8Ktgzji9Qf"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
          },
          "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
          "id" : "4m1i53DaEliWzSkSFwqv5W",
          "name" : "L'Orchestra Cinematique",
          "type" : "artist",
          "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/344nM3DXHleZKwVEYqNXoQ"
        },
        "href" : "https://api.spotify.com/v1/albums/344nM3DXHleZKwVEYqNXoQ",
        "id" : "344nM3DXHleZKwVEYqNXoQ",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/b0882e8f5d1faf3c6d33b9ceb04f9b97e79b4aa9",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/d397c3d041d8ed068ff10889f3c95196938a51b1",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/19a6fcecfa1cff55a3eaa40c9b402b504ba7ea04",
          "width" : 64
        } ],
        "name" : "Narcos Main Theme - Tuyo (Netflix Series)",
        "type" : "album",
        "uri" : "spotify:album:344nM3DXHleZKwVEYqNXoQ"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
        },
        "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
        "id" : "4m1i53DaEliWzSkSFwqv5W",
        "name" : "L'Orchestra Cinematique",
        "type" : "artist",
        "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 92658,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "CB2CY0939196"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/20zWoMv5TSGstOlF22746u"
      },
      "href" : "https://api.spotify.com/v1/tracks/20zWoMv5TSGstOlF22746u",
      "id" : "20zWoMv5TSGstOlF22746u",
      "name" : "Narcos Main Theme - Tuyo (Netflix Series)",
      "popularity" : 24,
      "preview_url" : "https://p.scdn.co/mp3-preview/a966df336845c9d53cba68858689c86d0ab77945?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:20zWoMv5TSGstOlF22746u"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/4qvqt4vEWaYEzLkV9nVkws"
          },
          "href" : "https://api.spotify.com/v1/artists/4qvqt4vEWaYEzLkV9nVkws",
          "id" : "4qvqt4vEWaYEzLkV9nVkws",
          "name" : "Fandom",
          "type" : "artist",
          "uri" : "spotify:artist:4qvqt4vEWaYEzLkV9nVkws"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/4KO0KAQixzElF8a4Bf399Z"
        },
        "href" : "https://api.spotify.com/v1/albums/4KO0KAQixzElF8a4Bf399Z",
        "id" : "4KO0KAQixzElF8a4Bf399Z",
        "images" : [ {
          "height" : 600,
          "url" : "https://i.scdn.co/image/290c9a6e46675ba993e0c300af514ccadfca77c7",
          "width" : 600
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/622092a669166b95f5ed87d877473a1341a12f53",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/bd8ce63b282f8bd1f81ba5ec8af51d504fa99db0",
          "width" : 64
        } ],
        "name" : "Tuyo - Narcos TV Theme Song",
        "type" : "album",
        "uri" : "spotify:album:4KO0KAQixzElF8a4Bf399Z"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4qvqt4vEWaYEzLkV9nVkws"
        },
        "href" : "https://api.spotify.com/v1/artists/4qvqt4vEWaYEzLkV9nVkws",
        "id" : "4qvqt4vEWaYEzLkV9nVkws",
        "name" : "Fandom",
        "type" : "artist",
        "uri" : "spotify:artist:4qvqt4vEWaYEzLkV9nVkws"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 89312,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "QMVRR1630046"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/2TtgNeTiljGtBYwfBsDQMG"
      },
      "href" : "https://api.spotify.com/v1/tracks/2TtgNeTiljGtBYwfBsDQMG",
      "id" : "2TtgNeTiljGtBYwfBsDQMG",
      "name" : "Tuyo (From \"Narcos\")",
      "popularity" : 23,
      "preview_url" : "https://p.scdn.co/mp3-preview/f8a9d280854877ab69494970e44db7127afa5cbc?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:2TtgNeTiljGtBYwfBsDQMG"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/6akDfyocmsREgR5eUXZt3I"
          },
          "href" : "https://api.spotify.com/v1/artists/6akDfyocmsREgR5eUXZt3I",
          "id" : "6akDfyocmsREgR5eUXZt3I",
          "name" : "Bely Basarte",
          "type" : "artist",
          "uri" : "spotify:artist:6akDfyocmsREgR5eUXZt3I"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/4lpVREsriy4llarOAiM8vL"
        },
        "href" : "https://api.spotify.com/v1/albums/4lpVREsriy4llarOAiM8vL",
        "id" : "4lpVREsriy4llarOAiM8vL",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/a4b178d623faf830872742c79b91aaa9c9dcf465",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/c5b122f4ac798188fb34e67a443484a9b6bfeedf",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/b1375545318f662484445bd58424488a318a3d0f",
          "width" : 64
        } ],
        "name" : "Tuyo (Narcos Theme)",
        "type" : "album",
        "uri" : "spotify:album:4lpVREsriy4llarOAiM8vL"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/6akDfyocmsREgR5eUXZt3I"
        },
        "href" : "https://api.spotify.com/v1/artists/6akDfyocmsREgR5eUXZt3I",
        "id" : "6akDfyocmsREgR5eUXZt3I",
        "name" : "Bely Basarte",
        "type" : "artist",
        "uri" : "spotify:artist:6akDfyocmsREgR5eUXZt3I"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 76636,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "ES7431610200"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/3rQfOY9evwCS8paQp4bnNN"
      },
      "href" : "https://api.spotify.com/v1/tracks/3rQfOY9evwCS8paQp4bnNN",
      "id" : "3rQfOY9evwCS8paQp4bnNN",
      "name" : "Tuyo - Narcos Theme originally performed by Rodrigo Amarante",
      "popularity" : 34,
      "preview_url" : "https://p.scdn.co/mp3-preview/623101cc8fab74771d73b29fbfb7264902cb62df?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:3rQfOY9evwCS8paQp4bnNN"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
          },
          "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
          "id" : "4m1i53DaEliWzSkSFwqv5W",
          "name" : "L'Orchestra Cinematique",
          "type" : "artist",
          "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/4TAmqb9jh2r0lhAXQIBb4E"
        },
        "href" : "https://api.spotify.com/v1/albums/4TAmqb9jh2r0lhAXQIBb4E",
        "id" : "4TAmqb9jh2r0lhAXQIBb4E",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/7cc88153b4a088cbd560332ef576b73b6b54db21",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/d9cfdb5a996d8b0c0e70475e998e7e39132b20bb",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/2362c63659ddeb50b619d81075f18674a76018b7",
          "width" : 64
        } ],
        "name" : "The Golden Age Of Television 2015",
        "type" : "album",
        "uri" : "spotify:album:4TAmqb9jh2r0lhAXQIBb4E"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
        },
        "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
        "id" : "4m1i53DaEliWzSkSFwqv5W",
        "name" : "L'Orchestra Cinematique",
        "type" : "artist",
        "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 92653,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "CB2CY0942672"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/3sxFDWEQEVczEqgs0NIyjR"
      },
      "href" : "https://api.spotify.com/v1/tracks/3sxFDWEQEVczEqgs0NIyjR",
      "id" : "3sxFDWEQEVczEqgs0NIyjR",
      "name" : "Narcos Main Theme - Tuyo (Netflix Series)",
      "popularity" : 5,
      "preview_url" : "https://p.scdn.co/mp3-preview/4b1c9ff7cd5632a296ba2fcb1780c46e278b78ac?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 6,
      "type" : "track",
      "uri" : "spotify:track:3sxFDWEQEVczEqgs0NIyjR"
    }, {
      "album" : {
        "album_type" : "single",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/0cf25EACpDMSXZam6GgQe5"
          },
          "href" : "https://api.spotify.com/v1/artists/0cf25EACpDMSXZam6GgQe5",
          "id" : "0cf25EACpDMSXZam6GgQe5",
          "name" : "Music Legends",
          "type" : "artist",
          "uri" : "spotify:artist:0cf25EACpDMSXZam6GgQe5"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/5v99jQDAwG8TB3OCJd6rRi"
        },
        "href" : "https://api.spotify.com/v1/albums/5v99jQDAwG8TB3OCJd6rRi",
        "id" : "5v99jQDAwG8TB3OCJd6rRi",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/813850c5036e268455f8e4c5f5492bc11761231c",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/68af649a4937493f9c91c1bdb47b846bb0d98125",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/df1b9cc0223641d64ac93df65a0d4697f6f5aaad",
          "width" : 64
        } ],
        "name" : "Main Theme / Tuyo (From \"Narcos\")",
        "type" : "album",
        "uri" : "spotify:album:5v99jQDAwG8TB3OCJd6rRi"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0cf25EACpDMSXZam6GgQe5"
        },
        "href" : "https://api.spotify.com/v1/artists/0cf25EACpDMSXZam6GgQe5",
        "id" : "0cf25EACpDMSXZam6GgQe5",
        "name" : "Music Legends",
        "type" : "artist",
        "uri" : "spotify:artist:0cf25EACpDMSXZam6GgQe5"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 177646,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USE831593934"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/4us2RckH06pSNeWv9JRZr5"
      },
      "href" : "https://api.spotify.com/v1/tracks/4us2RckH06pSNeWv9JRZr5",
      "id" : "4us2RckH06pSNeWv9JRZr5",
      "name" : "Main Theme / Tuyo (From \"Narcos\")",
      "popularity" : 3,
      "preview_url" : "https://p.scdn.co/mp3-preview/b132863e3ef09cae898c8c09eb332c4f67cf1ed7?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 1,
      "type" : "track",
      "uri" : "spotify:track:4us2RckH06pSNeWv9JRZr5"
    }, {
      "album" : {
        "album_type" : "compilation",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/0LyfQWJT6nXafLPZqxe9Of"
          },
          "href" : "https://api.spotify.com/v1/artists/0LyfQWJT6nXafLPZqxe9Of",
          "id" : "0LyfQWJT6nXafLPZqxe9Of",
          "name" : "Various Artists",
          "type" : "artist",
          "uri" : "spotify:artist:0LyfQWJT6nXafLPZqxe9Of"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/0eDXot1QEQgsh6vzTNNhMN"
        },
        "href" : "https://api.spotify.com/v1/albums/0eDXot1QEQgsh6vzTNNhMN",
        "id" : "0eDXot1QEQgsh6vzTNNhMN",
        "images" : [ {
          "height" : 600,
          "url" : "https://i.scdn.co/image/62091d485a7b9541cc7d35e3f6767a04554ce11a",
          "width" : 600
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/6eda043a92a7d7fa31d3f01946c1a0d8c36ed383",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/cbbc677614429b495b526bf6a383412bfcdb620f",
          "width" : 64
        } ],
        "name" : "A Tribute to the 2016 Golden Globe Nominees",
        "type" : "album",
        "uri" : "spotify:album:0eDXot1QEQgsh6vzTNNhMN"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4qvqt4vEWaYEzLkV9nVkws"
        },
        "href" : "https://api.spotify.com/v1/artists/4qvqt4vEWaYEzLkV9nVkws",
        "id" : "4qvqt4vEWaYEzLkV9nVkws",
        "name" : "Fandom",
        "type" : "artist",
        "uri" : "spotify:artist:4qvqt4vEWaYEzLkV9nVkws"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 89312,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "QMVRR1630046"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/7rVZ0iSpYIQt73GNSExrDT"
      },
      "href" : "https://api.spotify.com/v1/tracks/7rVZ0iSpYIQt73GNSExrDT",
      "id" : "7rVZ0iSpYIQt73GNSExrDT",
      "name" : "Tuyo (From \"Narcos\")",
      "popularity" : 0,
      "preview_url" : "https://p.scdn.co/mp3-preview/f8a9d280854877ab69494970e44db7127afa5cbc?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 21,
      "type" : "track",
      "uri" : "spotify:track:7rVZ0iSpYIQt73GNSExrDT"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/47G5hK3XB0uqQTAGOLSHIV"
          },
          "href" : "https://api.spotify.com/v1/artists/47G5hK3XB0uqQTAGOLSHIV",
          "id" : "47G5hK3XB0uqQTAGOLSHIV",
          "name" : "TV Sounds Unlimited",
          "type" : "artist",
          "uri" : "spotify:artist:47G5hK3XB0uqQTAGOLSHIV"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/0pdCFKaHSPT4zLtNSsvfGw"
        },
        "href" : "https://api.spotify.com/v1/albums/0pdCFKaHSPT4zLtNSsvfGw",
        "id" : "0pdCFKaHSPT4zLtNSsvfGw",
        "images" : [ {
          "height" : 600,
          "url" : "https://i.scdn.co/image/5f55928f1476f876964a7b27af451044b17cd915",
          "width" : 600
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/cf6e981b8bfd13246c948019b0ba662f660076c5",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/44fc7142055b8cd4e993b3b320103387497bc635",
          "width" : 64
        } ],
        "name" : "The Essential Television Soundtrack Library: American TV, Vol. 1",
        "type" : "album",
        "uri" : "spotify:album:0pdCFKaHSPT4zLtNSsvfGw"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/18yNPBvd7fpTcjtBjdlRlh"
        },
        "href" : "https://api.spotify.com/v1/artists/18yNPBvd7fpTcjtBjdlRlh",
        "id" : "18yNPBvd7fpTcjtBjdlRlh",
        "name" : "Tito Flores",
        "type" : "artist",
        "uri" : "spotify:artist:18yNPBvd7fpTcjtBjdlRlh"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 89312,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "QMVRR1631504"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/0JE3IduJ9YBbzJKSmwMsgq"
      },
      "href" : "https://api.spotify.com/v1/tracks/0JE3IduJ9YBbzJKSmwMsgq",
      "id" : "0JE3IduJ9YBbzJKSmwMsgq",
      "name" : "Tuyo - From \"Narcos\"",
      "popularity" : 3,
      "preview_url" : "https://p.scdn.co/mp3-preview/f8a9d280854877ab69494970e44db7127afa5cbc?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 8,
      "type" : "track",
      "uri" : "spotify:track:0JE3IduJ9YBbzJKSmwMsgq"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
          },
          "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
          "id" : "4m1i53DaEliWzSkSFwqv5W",
          "name" : "L'Orchestra Cinematique",
          "type" : "artist",
          "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/7n5YAT9W5c9356LFKlYDpy"
        },
        "href" : "https://api.spotify.com/v1/albums/7n5YAT9W5c9356LFKlYDpy",
        "id" : "7n5YAT9W5c9356LFKlYDpy",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/e4fdf7f3268c213842a6957fc798e3e96f5b34e4",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/4da40f42851f803b51e34567cbeee82c20f2d7c0",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/ecea260b87a78dc727f6fa161e68b1c2e021c537",
          "width" : 64
        } ],
        "name" : "Tracks from the Golden Globes 2016 Nominees",
        "type" : "album",
        "uri" : "spotify:album:7n5YAT9W5c9356LFKlYDpy"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
        },
        "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
        "id" : "4m1i53DaEliWzSkSFwqv5W",
        "name" : "L'Orchestra Cinematique",
        "type" : "artist",
        "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 94653,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "CB2CY0942363"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/7CCU1bsEgJsXsm81C9cQnt"
      },
      "href" : "https://api.spotify.com/v1/tracks/7CCU1bsEgJsXsm81C9cQnt",
      "id" : "7CCU1bsEgJsXsm81C9cQnt",
      "name" : "Narcos Main Theme - Tuyo - Cover Version",
      "popularity" : 2,
      "preview_url" : "https://p.scdn.co/mp3-preview/a11d24d8a21357b897d91a3e2113606616d0988b?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 18,
      "type" : "track",
      "uri" : "spotify:track:7CCU1bsEgJsXsm81C9cQnt"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/75pZvrrW04VyYu7WlFFVZ4"
          },
          "href" : "https://api.spotify.com/v1/artists/75pZvrrW04VyYu7WlFFVZ4",
          "id" : "75pZvrrW04VyYu7WlFFVZ4",
          "name" : "L'Orcestra Cinematique",
          "type" : "artist",
          "uri" : "spotify:artist:75pZvrrW04VyYu7WlFFVZ4"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/086vAZTFz5tfbc8HQRIab9"
        },
        "href" : "https://api.spotify.com/v1/albums/086vAZTFz5tfbc8HQRIab9",
        "id" : "086vAZTFz5tfbc8HQRIab9",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/79bfb336bdbfd6354fab263a55d4331ee38f59e7",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/3f35a365bde66c89e30d488c59e33b254a35179c",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/9acd578e09b7f7ebcf1989c74251b097c7194f32",
          "width" : 64
        } ],
        "name" : "The Very Best Of TV Crime Dramas 2015",
        "type" : "album",
        "uri" : "spotify:album:086vAZTFz5tfbc8HQRIab9"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/4m1i53DaEliWzSkSFwqv5W"
        },
        "href" : "https://api.spotify.com/v1/artists/4m1i53DaEliWzSkSFwqv5W",
        "id" : "4m1i53DaEliWzSkSFwqv5W",
        "name" : "L'Orchestra Cinematique",
        "type" : "artist",
        "uri" : "spotify:artist:4m1i53DaEliWzSkSFwqv5W"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 92653,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "CB2CY0942716"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/1u4NkGRSC6l8YMlX0I1YMv"
      },
      "href" : "https://api.spotify.com/v1/tracks/1u4NkGRSC6l8YMlX0I1YMv",
      "id" : "1u4NkGRSC6l8YMlX0I1YMv",
      "name" : "Narcos Main Theme - Tuyo (Netflix Series)",
      "popularity" : 3,
      "preview_url" : "https://p.scdn.co/mp3-preview/4b1c9ff7cd5632a296ba2fcb1780c46e278b78ac?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 5,
      "type" : "track",
      "uri" : "spotify:track:1u4NkGRSC6l8YMlX0I1YMv"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/0cf25EACpDMSXZam6GgQe5"
          },
          "href" : "https://api.spotify.com/v1/artists/0cf25EACpDMSXZam6GgQe5",
          "id" : "0cf25EACpDMSXZam6GgQe5",
          "name" : "Music Legends",
          "type" : "artist",
          "uri" : "spotify:artist:0cf25EACpDMSXZam6GgQe5"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/3HL4JTOYlIVi4gJrSU7O4j"
        },
        "href" : "https://api.spotify.com/v1/albums/3HL4JTOYlIVi4gJrSU7O4j",
        "id" : "3HL4JTOYlIVi4gJrSU7O4j",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/3306297cf9c51cc20bfccbf50f4abcee6ea3e3e0",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/040946ad051ec28e0c8b8b38f4f8cf6f08f7b49f",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/77ee62c87ad6dad7b60291882715130b28ffa3dc",
          "width" : 64
        } ],
        "name" : "Serenity: An Instrumental Collection",
        "type" : "album",
        "uri" : "spotify:album:3HL4JTOYlIVi4gJrSU7O4j"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0cf25EACpDMSXZam6GgQe5"
        },
        "href" : "https://api.spotify.com/v1/artists/0cf25EACpDMSXZam6GgQe5",
        "id" : "0cf25EACpDMSXZam6GgQe5",
        "name" : "Music Legends",
        "type" : "artist",
        "uri" : "spotify:artist:0cf25EACpDMSXZam6GgQe5"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 177646,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USY281606325"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/6brbsoufPO6PAunilxtOOT"
      },
      "href" : "https://api.spotify.com/v1/tracks/6brbsoufPO6PAunilxtOOT",
      "id" : "6brbsoufPO6PAunilxtOOT",
      "name" : "Main Theme / Tuyo (Acoustic Version) [From \"Narcos\"]",
      "popularity" : 1,
      "preview_url" : "https://p.scdn.co/mp3-preview/cacd0d7e3a9debfb3edbbc0bc014997ef6005170?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 2,
      "type" : "track",
      "uri" : "spotify:track:6brbsoufPO6PAunilxtOOT"
    }, {
      "album" : {
        "album_type" : "album",
        "artists" : [ {
          "external_urls" : {
            "spotify" : "https://open.spotify.com/artist/0cf25EACpDMSXZam6GgQe5"
          },
          "href" : "https://api.spotify.com/v1/artists/0cf25EACpDMSXZam6GgQe5",
          "id" : "0cf25EACpDMSXZam6GgQe5",
          "name" : "Music Legends",
          "type" : "artist",
          "uri" : "spotify:artist:0cf25EACpDMSXZam6GgQe5"
        } ],
        "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
        "external_urls" : {
          "spotify" : "https://open.spotify.com/album/3HL4JTOYlIVi4gJrSU7O4j"
        },
        "href" : "https://api.spotify.com/v1/albums/3HL4JTOYlIVi4gJrSU7O4j",
        "id" : "3HL4JTOYlIVi4gJrSU7O4j",
        "images" : [ {
          "height" : 640,
          "url" : "https://i.scdn.co/image/3306297cf9c51cc20bfccbf50f4abcee6ea3e3e0",
          "width" : 640
        }, {
          "height" : 300,
          "url" : "https://i.scdn.co/image/040946ad051ec28e0c8b8b38f4f8cf6f08f7b49f",
          "width" : 300
        }, {
          "height" : 64,
          "url" : "https://i.scdn.co/image/77ee62c87ad6dad7b60291882715130b28ffa3dc",
          "width" : 64
        } ],
        "name" : "Serenity: An Instrumental Collection",
        "type" : "album",
        "uri" : "spotify:album:3HL4JTOYlIVi4gJrSU7O4j"
      },
      "artists" : [ {
        "external_urls" : {
          "spotify" : "https://open.spotify.com/artist/0cf25EACpDMSXZam6GgQe5"
        },
        "href" : "https://api.spotify.com/v1/artists/0cf25EACpDMSXZam6GgQe5",
        "id" : "0cf25EACpDMSXZam6GgQe5",
        "name" : "Music Legends",
        "type" : "artist",
        "uri" : "spotify:artist:0cf25EACpDMSXZam6GgQe5"
      } ],
      "available_markets" : [ "AD", "AR", "AT", "AU", "BE", "BG", "BO", "BR", "CA", "CH", "CL", "CO", "CR", "CY", "CZ", "DE", "DK", "DO", "EC", "EE", "ES", "FI", "FR", "GB", "GR", "GT", "HK", "HN", "HU", "ID", "IE", "IS", "IT", "JP", "LI", "LT", "LU", "LV", "MC", "MT", "MX", "MY", "NI", "NL", "NO", "NZ", "PA", "PE", "PH", "PL", "PT", "PY", "SE", "SG", "SK", "SV", "TR", "TW", "US", "UY" ],
      "disc_number" : 1,
      "duration_ms" : 179519,
      "explicit" : false,
      "external_ids" : {
        "isrc" : "USY281606329"
      },
      "external_urls" : {
        "spotify" : "https://open.spotify.com/track/4T5xt2iEpMOBIHWBBmwiXm"
      },
      "href" : "https://api.spotify.com/v1/tracks/4T5xt2iEpMOBIHWBBmwiXm",
      "id" : "4T5xt2iEpMOBIHWBBmwiXm",
      "name" : "Main Theme / Tuyo (Music Box Version) [From \"Narcos\"]",
      "popularity" : 0,
      "preview_url" : "https://p.scdn.co/mp3-preview/ea39e0a9b280c964895cfdabbeec5c369a3f415c?cid=8897482848704f2a8f8d7c79726a70d4",
      "track_number" : 6,
      "type" : "track",
      "uri" : "spotify:track:4T5xt2iEpMOBIHWBBmwiXm"
    } ],
    "limit" : 20,
    "next" : null,
    "offset" : 0,
    "previous" : null,
    "total" : 18
  }
}
}

module.exports = router;