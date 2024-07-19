<template>
    <main class="about-page">
        <h1>About</h1>
        <p class="sub">Welcome to the About page.</p>

        <br><br>
        <div class="about">
            <p>FyFySongs is a mini project that leverages the Spotify APIs to provide dynamic and modern features.</p>
            
            <br>
            <h2>Technologies used</h2>
            <div class="tech">
                <p class="devicon-go-original-wordmark"> Go</p> 
                <p class="devicon-vuejs-plain"> Vue</p>
                <p class="fa fa-spotify spotify-icon"> Spotify API</p>
            </div>
            
            <br>
            <h2>Features</h2>
            <p>Artist Search: Search and view detailed information about your favorite artists.</p>
            <p>Music Genres: Explore the different music genres available on Spotify.</p>
            <p>Spotify Integration: Use the Spotify API to get real-time data about songs, albums and playlists.</p>
            <p>User-Friendly Interface: A modern and intuitive interface built with Vue.js.</p>

            <br><br>
            <h3>Created by: Kauan</h3>

            <a href="https://github.com/YKauan/training-projects/tree/main/Go/fyfysongs">
                <button class="glow-on-hover">GitHub Repository</button>
            </a>

        </div>

    </main>
</template>

<style lang="scss" scoped>
    
    p:not(.sub) {
        font-size: 1.2em;
        text-align: justify;
    }

    .tech {
        margin-top: 20px;
        display: flex;
        align-items: center;
        justify-content: flex-start;
        gap: 20px;
    }

    h2, h3 {
        text-align: justify;
        color: var(--primary);
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