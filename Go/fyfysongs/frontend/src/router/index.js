import Home from '../views/Home.vue'
import Artist from '../views/Artist.vue'
import Genres from '../views/Genres.vue'
import About from '../views/About.vue'

import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/artist',
            component: Artist
        },
        {
            path: '/genres',
            component: Genres
        },
        {
            path: '/about',
            component: About
        }
    ]
})

export default router