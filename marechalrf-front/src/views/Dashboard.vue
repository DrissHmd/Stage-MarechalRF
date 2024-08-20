<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="dashboard-page">
    <div class="dashboard-container">
      <h1>Dashboard</h1>

      <!-- Barre de recherche -->
      <div class="search-bar">
        <input v-model="searchQuery" placeholder="Rechercher un utilisateur par nom" />
        <button @click="searchUsers">Rechercher</button>
      </div>

      <!-- Liste des utilisateurs -->
      <div class="user-list">
        <h2>Liste des utilisateurs</h2>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Email</th>
              <th>Téléphone</th>
              <th>Nom d'utilisateur</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <!-- Cette ligne se répète pour chaque utilisateur filtré -->
            <tr v-for="user in filteredUsers" :key="user?.id">
              <td>{{ user?.id }}</td>
              <td>{{ user?.last_name }}</td>
              <td>{{ user?.first_name }}</td>
              <td>{{ user?.email }}</td>
              <td>{{ user?.phone }}</td>
              <td>{{ user?.username }}</td>
              <td>
                <router-link :to="{ name: 'UserEdit', params: { id: user.id } }">
                  Modifier
                </router-link>
              </td>
            </tr>
            <!-- Si la liste est vide, un message s'affiche -->
            <tr v-if="filteredUsers.length === 0">
              <td colspan="7">Aucun utilisateur trouvé</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>



<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: [],
      roles: [],
      searchQuery: '',
      filteredUsers: [],
      userForm: {
        id: null,
        first_name: '',
        last_name: '',
        email: '',
        phone: '',
        username: '',
        password: '',
        roleId: null
      },
      isEditing: false
    };
  },
  methods: {
    async searchUsers() {
      console.log("Requête en préparation");
      try {
        const response = await axios.get('http://localhost:8080/api/users/search', {
          params: { name: this.searchQuery }
        });
        console.log("Réponse de l'API:", response.data);

        this.filteredUsers = response.data;
      } catch (error) {
        console.error('Erreur lors de la recherche des utilisateurs', error);
        this.filteredUsers = [];
      }
      console.log("Fin de la fonction searchUsers");
    }
  }
};
</script>

<style scoped>
.dashboard-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.dashboard-container {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 1000px;
  width: 100%;
}

.search-bar input {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.user-list table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.user-list th, .user-list td {
  border: 1px solid #ddd;
  padding: 8px;
}

.user-list th {
  background-color: #f2f2f2;
}

.user-form {
  margin-top: 20px;
}

.user-form div {
  margin-bottom: 10px;
}

.user-form input, .user-form select {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-top: 5px;
}

.user-form button {
  padding: 10px 20px;
  background-color: #007bff;
  border: none;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

.user-form button:hover {
  background-color: #0056b3;
}
</style>
