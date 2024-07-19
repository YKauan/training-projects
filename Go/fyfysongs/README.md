
# FyFySongs

FyFySongs is a mini project that leverages the Spotify APIs to provide dynamic and modern features.


## 🛠 Technologies used


![Badge](https://img.shields.io/static/v1?label=go&message=Golang&color=blue&style=for-the-badge&logo=GO)

![Badge](https://img.shields.io/static/v1?label=vue&message=framework&color=green&style=for-the-badge&logo=VUE.JS)

![Badge](https://img.shields.io/static/v1?label=wails&message=framework&color=red&style=for-the-badge&logo=wails)

## Features

- Artist Search: Search and view detailed information about your favorite artists.
- Music Genres: Explore the different music genres available on Spotify.
- Spotify Integration: Use the Spotify API to get real-time data about songs, albums and playlists.
- User-Friendly Interface: A modern and intuitive interface built with Vue.js.

## Spotify API Used

#### Get Artist By ID

```https
  GET /artists/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `access_token` | `string` | **Required**. Your API key |
| `artis_id` | `string` | **Required**. Spotify Artist ID |

#### Response Sample
```json
{
  "genres": ["alternative", "samba"]
}
```


#### Get Genres

```https
  GET /recommendations/available-genre-seeds
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `access_token` | `string` | **Required**. Your API key |

#### Response Sample
```json
{
  "external_urls": {
    "spotify": "string"
  },
  "followers": {
    "href": "string",
    "total": 0
  },
  "genres": ["Prog rock", "Grunge"],
  "href": "string",
  "id": "string",
  "images": [
    {
      "url": "https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228",
      "height": 300,
      "width": 300
    }
  ],
  "name": "string",
  "popularity": 0,
  "type": "artist",
  "uri": "string"
}
```




## 🔗 References And Documentation Used

[![Static Badge](https://img.shields.io/badge/SPOTIFY-green?style=for-the-badge&logo=spotify&labelColor=green&color=GREEN)
](https://developer.spotify.com)

[![Static Badge](https://img.shields.io/badge/BRASIL--CODE-green?style=for-the-badge&labelColor=green&color=blue)
](https://www.brasilcode.com.br/35-botoes-css-com-animacao/)

[![Static Badge](https://img.shields.io/badge/Tyler--Potts-green?style=for-the-badge&logo=youtube&labelColor=red&color=red)
](https://www.youtube.com/watch?v=Ul4KOXNQJSk)

[![Static Badge](https://img.shields.io/badge/Package--Main-green?style=for-the-badge&logo=youtube&labelColor=red&color=red)
](https://www.youtube.com/watch?v=Dg9rUXxNV-c)
