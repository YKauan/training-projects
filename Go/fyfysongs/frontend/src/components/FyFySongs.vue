<script setup>
import {ref} from 'vue'
import logoURL from '../assets/images/logo.png'

const isExpanded = ref(localStorage.getItem("isExpanded") === "true")

const ToggleMenu = () => {
	isExpanded.value = !isExpanded.value
	localStorage.setItem("isExpanded", isExpanded.value)
}

</script>

<template>
    <aside :class="`${isExpanded && 'is-expanded' }`">
      
      <div class="logo">
        <img :src="logoURL" alt="Vue" /> 
      </div>

      <div class="menu-toggle-wrap">
        <button class="menu-toggle" @click="ToggleMenu">
        <span class="material-icons">keyboard_double_arrow_right</span>
        </button>
      </div>

      <h3>Menu</h3>
      <div class="menu">

        <router-link to="/" class="button">
          <span class="material-icons">home</span>
          <span class="text">Home</span>
        </router-link>
        <router-link to="/artist" class="button">
          <span class="material-icons">library_music</span>
          <span class="text">Artist</span>
        </router-link>
        <router-link to="/genres" class="button">
          <span class="material-icons">music_note</span>
          <span class="text">Genres</span>
        </router-link>
        <router-link to="/about" class="button">
          <span class="material-icons">description</span>
          <span class="text">About</span>
        </router-link>

      </div>

      <div class="flex"></div>
		
      <div class="menu">
        <router-link to="/settings" class="button">
          <span class="material-icons">settings</span>
          <span class="text">Settings</span>
        </router-link>
      </div>

    </aside>

</template>

<style lang="scss" scoped>
  aside {
    display: flex;
    flex-direction: column;

    background-color: var(--dark);
    color: var(--light-buttuns);

    width: calc(2rem + 32px);
    overflow: hidden;
    min-height: 100vh;
    padding: 1rem;

    transition: 0.2s ease-in-out;

    .flex {
      flex: 1 1 0%;
    }

    .logo {
      margin-bottom: 1rem;

      img {
        width: 2rem;
      }
    }

    .menu-toggle-wrap {
      display: flex;
      justify-content: flex-end;
      margin-bottom: 1rem;

      position: relative;
      top: 0;
      transition: 0.2s ease-in-out;

      .menu-toggle {
        transition: 0.2s ease-in-out;
        .material-icons {
          font-size: 2rem;
          color: var(--light-buttuns);
          transition: 0.2s ease-out;
        }
        
        &:hover {
          .material-icons {
            color: var(--primary);
            transform: translateX(0.5rem);
          }
        }
      }
    }

    h3, .button .text {
      opacity: 0;
      transition: opacity 0.3s ease-in-out;
    }

    h3 {
      color: var(--grey);
      font-size: 0.875rem;
      margin-bottom: 0.5rem;
      text-transform: uppercase;
    }

    .menu {
      margin: 0 -1rem;

      .button {
        display: flex;
        align-items: center;
        text-decoration: none;

        transition: 0.2s ease-in-out;
        padding: 0.5rem 1rem;

        .material-icons {
          font-size: 2rem;
          color: var(--light-buttuns);
          transition: 0.2s ease-in-out;
        }
        .text {
          color: var(--light-buttuns);
          transition: 0.2s ease-in-out;
        }

        &:hover {
          background-color: var(--dark-alt);

          .material-icons, .text {
            color: var(--primary);
          }
        }

        &.router-link-exact-active {
          background-color: var(--dark-alt);
          border-right: 5px solid var(--primary);

          .material-icons, .text {
            color: var(--primary);
          }
        }
      }
    }

    .footer {
      opacity: 0;
      transition: opacity 0.3s ease-in-out;

      p {
        font-size: 0.875rem;
        color: var(--grey);
      }
    }

    .scrollable-menu {
      flex: 1;
      overflow-y: auto;
    }

    &.is-expanded {
      width: var(--sidebar-width);

      .menu-toggle-wrap {
        top: -3rem;
        
        .menu-toggle {
          transform: rotate(-180deg);
        }
      }

      h3, .button .text {
        opacity: 1;
      }

      .button {
        .material-icons {
          margin-right: 1rem;
        }
      }

      .footer {
        opacity: 0;
      }
    }

    @media (max-width: 1024px) {
      position: absolute;
      z-index: 99;
    }
  }
</style>
