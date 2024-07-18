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
                <button class="btn" @click="fetchGenres">Genres</button>
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
        margin-bottom: 90px;
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
    }
    .slider::-webkit-scrollbar {
        display: none;
    }
    .slider {
        -ms-overflow-style: none;
        scrollbar-width: none;
    }

</style>