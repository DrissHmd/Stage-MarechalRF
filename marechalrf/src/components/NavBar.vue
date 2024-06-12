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
      <li class="navbar-item">
        <a class="not-allowed">
          <img src="/account-logo.png" alt="Account Logo" class="nav-logo">
          Compte
        </a>
      </li>
    </ul>
  </nav>
</template>


<script>
export default {
  name: 'NavBar',
  data() {
    return {
      isMenuOpen: false
    };
  },
  methods: {
    toggleMenu() {
      this.isMenuOpen = !this.isMenuOpen;
    }
  }
};
</script>


<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background: rgba(255, 255, 255, 0.6); 
}

.navbar-menu {
  list-style: none;
  display: flex;
  margin: 0;
  padding: 0;
  transition: max-height 0.3s ease-in-out; /* Smooth transition for menu opening/closing */
  overflow: hidden; /* Hide overflowing content when menu is closed */
}

.navbar-menu.show {
  max-height: 400px; /* Adjust based on the number of menu items */
}

.navbar-item {
  display: flex;
  align-items: center;
  margin-left: 1.5rem;
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

.navbar-item a img, .navbar-item router-link img {
  margin-right: 0.5rem;
  width: 30px;
  height: auto;
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
  transition: all 0.3s ease-in-out; /* Smooth transition for the hamburger icon */
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
    background: rgba(255, 255, 255, 0.5); 
  }

  .navbar-menu {
    display: block;
    flex-direction: column;
    width: 100%; 
    text-align: center;
    max-height: 0; /* Initially hidden */
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
</style>
