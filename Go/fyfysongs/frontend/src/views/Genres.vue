<template>
    <main class="genres-page">
        <h1>Genres</h1>
        <p>Welcome to the Genres page.</p>

        <div class="slider">
            <div v-if="data.genres.length === 0" class="item">No genres found.</div>
            <div v-else>
                <div v-for="genre in data.genres" :key="genre" class="genre-item">
                    <p>{{ genre }}</p>
                </div>
            </div>
        </div>

        <div class="content">
            <div id="result" class="result">{{ data.resultText }}</div>
            <div id="input" class="input-box">
                <button class="glow-on-hover" @click="fetchGenres">Genres</button>
            </div>
         </div>
    </main>
</template>

<script setup>

    import {reactive} from 'vue'
    import {Genres} from '../../wailsjs/go/main/App'

    const data = reactive({
        name: "",
        genres: [],
        resultText: "",
    })


    function fetchGenres() {
        Genres(data.name).then(genres => {
            if (genres && genres.length > 0) {
                data.genres = genres
                data.resultText = "Genres fetched successfully."
            } else {
                data.genres = []
                data.resultText = "No genres found."
            }
        }).catch(err => {
            console.error(err)
            data.resultText = "Error fetching genres."
        })
    }

</script>

<style lang="scss" scoped>
    body{
        min-height: 100vh;
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        font-family: monospace;
    }
    .slider{
        margin-top: 50px;
        margin-bottom: 60px;
        position: relative;
        width: 100%;
        height: 400px;
        min-height: 400px;
        display: flex;
        gap: 10px;
        padding-bottom: 20px;
        overflow-x: auto;
    }
    .genre-item {
        background-color: var(--dark);
        border-radius: 40px;
        padding: 10px 20px;
        margin-bottom: 10px;
        margin-right: 3px;
        display: inline-block;
        align-items: center;
        justify-content: center;
        margin-bottom: 10px;
        min-width: 200px;
        color: var(--primary);
    }
    .slider::-webkit-scrollbar {
        display: none;
    }
    .slider {
        -ms-overflow-style: none;
        scrollbar-width: none;
    }
    .glow-on-hover {
        width: 220px;
        height: 50px;
        border: none;
        outline: none;
        color: var(--primary);
        background: #111;
        cursor: pointer;
        position: relative;
        z-index: 0;
        border-radius: 10px;
        margin-top: 10px;
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
        color: #000
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
        background: #111;
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