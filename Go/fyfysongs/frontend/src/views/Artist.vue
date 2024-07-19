<template>
    <main class="artist-page">
        <h1>Artist</h1>
        <p>Welcome to the Artist page.</p>

        <div class="card">
            <div v-if="!data.artistName.trim()" class="item">No Artist found.</div>
            <div v-else>
                <div class="artist-item">
                    <div class="artist-image">
                        <img :src="data.image.url" :height="data.image.height" :width="data.image.width" alt="Artist" />
                    </div>
                    <p>{{ data.artistName }}</p>
                    <p>Followers: {{ data.followers }}</p>
                    <p>Genres: {{ data.genres.join(", ") }}</p>
                    <p>Popularity: {{ data.popularity }}</p>
                    <p>Type: {{ data.type }}</p>
                </div>
            </div>
        </div>

        <div id="result" class="result">{{ data.resultText }}</div>
        <div id="input" class="input-box">
            <input v-model="data.id" type="text" class="input-item" placeholder="Artist ID" />
            <button class="glow-on-hover" @click="fetchArtist">Get Artist</button>
        </div>
    </main>
</template>

<script setup>

    import {reactive} from 'vue'
    import {Artist} from '../../wailsjs/go/main/App'

    const data = reactive({
        id: "",
        artistName: "",
        resultText: "",
        followers: 0,
        genres: [],
        image: {
            url: "",
            height: 0,
            width: 0
        },
        popularity: 0,
        type: ""
    })

    function fetchArtist() {
        Artist(data.id).then(artist => {
            if (artist != null) {
                data.artistName   = artist.name
                data.followers    = artist.followers.total
                data.genres       = artist.genres
                data.image.url    = artist.images[0].url
                data.image.height = artist.images[0].height
                data.image.width  = artist.images[0].width
                data.popularity   = artist.popularity
                data.type         = artist.type
                data.resultText   = "Artist fetched successfully."
            } else {
                data.artistName = ""
                data.resultText = "No Artist found."
            }
        }).catch(err => {
            console.error(err)
            data.resultText = "Error fetching Artist."
        })
    }

</script>

<style lang="scss" scoped>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
        min-height: 100vh;
        padding: 1rem;
        transition: 0.2s ease-in-out;
    }
    p {
        margin-bottom: 5px;
    }
    .card {
        position: relative;
        width: 100%;
        height: 370px;
        overflow: hidden;
        margin-bottom: 50px;
    }
    .artist-item {
        background-color: var(--dark);
        border-radius: 40px;
        padding: 10px 20px;
        margin-right: 3px;
        display: inline-block;
        align-items: center;
        justify-content: center;
        margin-top: 50px;
        margin-bottom: 50px;
        width: 200px;
        height: 320px;
        color: var(--primary);
    }
    .artist-image {
        display: flex;
        width: 100px;
        height: 100px;
        overflow: hidden;
        border-radius: 50px;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 15px;
        margin-top: 10px;
    }
    .artist-image img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 50px;
    }
    .card::-webkit-scrollbar {
        display: none;
    }
    .card {
        -ms-overflow-style: none;
        scrollbar-width: none;
    }
    .input-box {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin-top: 20px;
    }
    .input-item {
        width: 200px;
        height: 30px;
        border: none;
        outline: none;
        background: var(--dark);
        color: var(--primary);
        border-radius: 10px;
        padding: 0 10px;
        margin-bottom: 10px;
    }
    .glow-on-hover {
        width: 220px;
        height: 50px;
        border: none;
        outline: none;
        color: var(--primary);
        background: var(--dark);
        cursor: pointer;
        position: relative;
        z-index: 0;
        border-radius: 10px;
        margin-top: 20px;
    }

    .glow-on-hover:before {
        content: '';
        background: linear-gradient(45deg, #c8c3da, #76c1df, #565fdb, #4cf10a, #ed26ff, #6903f0, #4400ff, #7700ff, #ff0000);
        position: absolute;
        top: -2px;
        left:-2px;
        background-size: 400%;
        z-index: -1;
        filter: blur(5px);
        width: calc(100% + 4px);
        height: calc(100% + 4px);
        animation: glowing 20s linear infinite;
        opacity: 0;
        transition: opacity .3s ease-in-out;
        border-radius: 10px;
    }

    .glow-on-hover:active {
        color: var(--dark)
    }

    .glow-on-hover:active:after {
        background: transparent;
    }

    .glow-on-hover:hover:before {
        opacity: 1;
    }

    .glow-on-hover:after {
        z-index: -1;
        content: '';
        position: absolute;
        width: 100%;
        height: 100%;
        background: var(--dark);
        left: 0;
        top: 0;
        border-radius: 10px;
    }

    @keyframes glowing {
        0% { background-position: 0 0; }
        50% { background-position: 400% 0; }
        100% { background-position: 0 0; }
    }

</style>