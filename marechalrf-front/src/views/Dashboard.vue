<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="dashboard-page">
    <div class="dashboard-container">
      <h1>Dashboard</h1>

      <!-- Barre de recherche -->
      <div class="search-bar">
        <input v-model="searchQuery" placeholder="Rechercher un utilisateur par nom" />
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
            <tr v-for="user in filteredUsers" :key="user.id">
              <td>{{ user.id }}</td>
              <td>{{ user.last_name }}</td>
              <td>{{ user.first_name }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.phone }}</td>
              <td>{{ user.username }}</td>
              <td>
                <button @click="editUser(user)">Modifier</button>
                <button @click="deleteUser(user.id)">Supprimer</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Formulaire d'ajout/modification d'utilisateur -->
      <div class="user-form">
        <h2>{{ isEditing ? 'Modifier' : 'Ajouter' }} un utilisateur</h2>
        <form @submit.prevent="isEditing ? updateUser() : createUser()">
          <div>
            <label for="first_name">Prénom</label>
            <input v-model="userForm.first_name" type="text" id="first_name" required />
          </div>
          <div>
            <label for="last_name">Nom</label>
            <input v-model="userForm.last_name" type="text" id="last_name" required />
          </div>
          <div>
            <label for="email">Email</label>
            <input v-model="userForm.email" type="email" id="email" required />
          </div>
          <div>
            <label for="phone">Téléphone</label>
            <input v-model="userForm.phone" type="tel" id="phone" required />
          </div>
          <div>
            <label for="username">Nom d'utilisateur</label>
            <input v-model="userForm.username" type="text" id="username" required />
          </div>
          <div>
            <label for="password">Mot de passe</label>
            <input v-model="userForm.password" type="password" id="password" required />
          </div>
          <div>
            <label for="roleId">Rôle</label>
            <select v-model="userForm.roleId" id="roleId" required>
              <option v-for="role in roles" :key="role.id" :value="role.id">{{ role.name }}</option>
            </select>
          </div>
          <button type="submit">{{ isEditing ? 'Mettre à jour' : 'Ajouter' }}</button>
        </form>
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
  computed: {
    filteredUsers() {
  return Array.isArray(this.users) 
    ? this.users.filter(user => 
        user.first_name.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        user.last_name.toLowerCase().includes(this.searchQuery.toLowerCase())
      )
    : [];
    }
  },
  methods: {
    async fetchUsers() {
  try {
    const response = await axios.get('/api/users');
    console.log('Full API Response for Users:', response);
    if (response.headers['content-type'].includes('application/json')) {
      this.users = response.data;
    } else {
      console.error('La réponse de l\'API utilisateurs n\'est pas du JSON:', response.data);
    }
  } catch (error) {
    console.error('Erreur lors de la récupération des utilisateurs', error);
  }
    },

    async fetchRoles() {
      try {
        const response = await axios.get('/api/roles');
        console.log('API Response for Roles:', response.data);
        if (Array.isArray(response.data)) {
          this.roles = response.data;
        } else {
          console.error('La réponse de l\'API rôles n\'est pas un tableau.', response.data);
        }
      } catch (error) {
        console.error('Erreur lors de la récupération des rôles', error);
      }
    },
    editUser(user) {
      this.isEditing = true;
      this.userForm = { ...user, password: '', roleId: user.roleId };
    },
    async createUser() {
      try {
        const response = await axios.post('/api/users', this.userForm);
        this.users.push(response.data);
        this.resetForm();
      } catch (error) {
        console.error('Erreur lors de la création de l’utilisateur', error);
      }
    },
    async updateUser() {
      try {
        const response = await axios.put(`/api/users/${this.userForm.id}`, this.userForm);
        const index = this.users.findIndex(user => user.id === this.userForm.id);
        this.$set(this.users, index, response.data);
        this.resetForm();
      } catch (error) {
        console.error('Erreur lors de la mise à jour de l’utilisateur', error);
      }
    },
    async deleteUser(userId) {
      try {
        await axios.delete(`/api/users/${userId}`);
        this.users = this.users.filter(user => user.id !== userId);
      } catch (error) {
        console.error('Erreur lors de la suppression de l’utilisateur', error);
      }
    },
    resetForm() {
      this.isEditing = false;
      this.userForm = {
        id: null,
        first_name: '',
        last_name: '',
        email: '',
        phone: '',
        username: '',
        password: '',
        roleId: null
      };
    }
  },
  async created() {
    await this.fetchUsers();
    await this.fetchRoles();
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
