<template>
  <nav class="navbar">
    <div style="display: flex; justify-content: space-between;">
      <div class="navbar-item company">
        <router-link to="/">
          <img src="/company-logo.png" alt="Company Logo" class="company-logo">
          <span class="company-name">MARECHAL Recrutement et Formation</span>
        </router-link>
      </div>
      <div class="navbar-toggle" @click="toggleMenu">
        <span :class="{ 'open': isMenuOpen }"></span>
        <span :class="{ 'open': isMenuOpen }"></span>
        <span :class="{ 'open': isMenuOpen }"></span>
      </div>
    </div>
    
    <ul :class="{'navbar-menu': true, 'show': isMenuOpen}">
      <li class="navbar-item">
        <router-link to="/">
          <img src="/home-logo.png" alt="Home Logo" class="nav-logo">
          Accueil
        </router-link>
      </li>
      <li class="navbar-item">
        <router-link to="/about">
          <img src="/about-logo.png" alt="About Logo" class="nav-logo">
          À propos
        </router-link>
      </li>
      <li class="navbar-item">
        <router-link to="/contact">
          <img src="/contact-logo.png" alt="Contact Logo" class="nav-logo">
          Contact
        </router-link>
      </li>
      <li v-if="!isLoggedIn" class="navbar-item">
        <router-link to="/login">
          <img src="/account-logo.png" alt="Account Logo" class="nav-logo">
          Compte
        </router-link>
      </li>
      <li v-if="isLoggedIn" class="navbar-item" @click="toggleDropdown">
        <img src="/account-logo.png" alt="Account Logo" class="nav-logo">
        <ul v-if="isDropdownOpen" class="dropdown-menu">
          <router-link v-if="isAdmin" class="dashboard-link" to="/dashboard">Dashboard</router-link>
          <li @click="logout">Déconnexion</li>
        </ul>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from 'vue';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'NavBar',
  setup() {
    const isMenuOpen = ref(false);
    const isDropdownOpen = ref(false);
    const router = useRouter();

    const toggleMenu = () => {
      isMenuOpen.value = !isMenuOpen.value;
    };

    const toggleDropdown = () => {
      isDropdownOpen.value = !isDropdownOpen.value;
    };

    const logout = () => {
      localStorage.removeItem('token');
      router.push('/');
      setTimeout(() => {
        location.reload();
      }, 100);
    };

    const isLoggedIn = computed(() => !!localStorage.getItem('token'));
    const isAdmin = computed(() => localStorage.getItem('roleId') === "2");

    return {
      isMenuOpen,
      isDropdownOpen,
      toggleMenu,
      toggleDropdown,
      logout,
      isLoggedIn,
      isAdmin
    };
  }
});
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background: rgba(229, 230, 255, 0.8); 
}

.navbar-menu {
  list-style: none;
  width: 100%;
  display: flex;
  margin: 0;
  padding: 0;
  transition: max-height 0.3s ease-in-out;
  overflow: hidden;
}

.navbar-menu.show {
  max-height: 400px;
}

ul.navbar-menu {
  justify-content: right;
}

.navbar-item {
  display: flex;
  align-items: center;
  margin-left: 1.3rem;
}

.company {
  margin-left: 0%;
}

.navbar-item a, .navbar-item router-link {
  display: flex;
  align-items: center;
  color: black;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s;
}

.not-allowed {
  cursor: not-allowed;
}

.navbar-item a img, .navbar-item router-link img, .navbar-item img {
  margin-right: 0.5rem;
  width: 30px;
  height: auto;
}

.company-logo {
  width: auto;
  height: 50px; /* Ajustez la hauteur selon vos besoins */
}

.company-name {
  font-size: 1.2rem;
  font-weight: bold;
  margin-left: 0.5rem;
}

.navbar-toggle {
  display: none;
  flex-direction: column;
  cursor: pointer;
}

.navbar-toggle span {
  height: 2px;
  width: 25px;
  background: black;
  margin: 4px;
  display: block;
  transition: all 0.3s ease-in-out;
}

.navbar-toggle span.open:nth-child(1) {
  transform: rotate(45deg) translate(35%, 5px);
}

.navbar-toggle span.open:nth-child(2) {
  opacity: 0;
}

.navbar-toggle span.open:nth-child(3) {
  transform: rotate(-45deg) translate(35%, -5px);
}

@media (max-width: 768px) {
  .navbar {
    flex-direction: column;
    padding: 1rem;
    background: rgba(229, 230, 255, 0.8); 
  }

  .navbar-menu {
    display: block;
    flex-direction: column;
    width: 100%; 
    text-align: center;
    max-height: 0;
    transition: max-height 0.3s ease-in-out;
  }

  .navbar-toggle {
    display: flex; 
    justify-content: flex-end; 
    margin-bottom: 0.5rem; 
  }

  .navbar-menu.show {
    max-height: 400px;
  }
}

.dropdown-menu {
  position: absolute;
  top: 80px;
  right: 10px;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  list-style: none;
  padding: 0;
  margin: 0;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.dropdown-menu li, .dropdown-menu .dashboard-link {
  padding: 10px;
  cursor: pointer;
}

.dropdown-menu li:hover, .dropdown-menu .dashboard-link:hover {
  background-color: #f0f0f0;
}
</style>
