<template>
    <div class="user-edit-page">
      <div class="user-edit-container">
        <h1>Modifier l'utilisateur</h1>
        <form @submit.prevent="updateUser" class="user-form">
          <div>
            <label for="first_name">Prénom:</label>
            <input type="text" v-model="userForm.first_name" id="first_name" required />
          </div>
  
          <div>
            <label for="last_name">Nom:</label>
            <input type="text" v-model="userForm.last_name" id="last_name" required />
          </div>
  
          <div>
            <label for="email">Email:</label>
            <input type="email" v-model="userForm.email" id="email" required />
          </div>
  
          <div>
            <label for="phone">Téléphone:</label>
            <input type="text" v-model="userForm.phone" id="phone" required />
          </div>
  
          <div>
            <label for="username">Nom d'utilisateur:</label>
            <input type="text" v-model="userForm.username" id="username" required />
          </div>
  
          <div>
            <label for="role">Rôle:</label>
            <select v-model="userForm.roleId" id="role" required>
              <option v-for="role in roles" :key="role.id" :value="role.id">{{ role.name }}</option>
            </select>
          </div>
  
          <div class="buttons">
            <button type="submit">Valider les modifications</button>
            <button type="button" @click="deleteUser" class="delete-button">Supprimer l'utilisateur</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { useRoute, useRouter } from 'vue-router';
  
  export default {
    data() {
      return {
        userForm: {
          first_name: '',
          last_name: '',
          email: '',
          phone: '',
          username: '',
          roleId: null,
        },
        roles: [],
      };
    },
    setup() {
      const route = useRoute();
      const router = useRouter();
  
      return { route, router };
    },
    created() {
      console.log('Route Params:', this.route.params);
      this.fetchRoles();
      this.fetchUser();
    },
    methods: {
      async fetchRoles() {
        try {
            const response = await axios.get('http://localhost:8080/api/roles/all');
            console.log("Response Data : ", response.data.data.map(role => role.name));
            if (response && response.data) {
                this.roles = response.data.data;
            } else {
                console.error('La réponse de l\'API est vide ou non définie');
            }
            } catch (error) {
            if (error.response) {
                console.error('Erreur de réponse du serveur', error.response.status);
            } else if (error.request) {
                console.error('Aucune réponse reçue', error.request);
            } else {
                console.error('Erreur lors de la configuration de la requête', error.message);
            }
        }
      },
      async fetchUser() {
        const userId = this.route.params.id;
        console.log('Fetched User ID:', userId);
        if (!userId) {
          console.error('Invalid User ID:', userId);
          return;
        }
        try {
          const response = await axios.get(`http://localhost:8080/api/users/${userId}`);
          console.log('Response.data : ', response.data)
          if (response && response.data) {
            this.userForm = response.data.data;
            console.log("UserForm : ", this.userForm);
          } else {
            console.error('No data returned for user ID:', userId);
          }
        } catch (error) {
          console.error('Erreur lors du chargement de l’utilisateur', error);
        }
      },
      async updateUser() {
        const userId = this.route.params.id;
        try {
          await axios.put(`http://localhost:8080/api/users/${userId}`, this.userForm);
          this.$router.push('/');
        } catch (error) {
          console.error('Erreur lors de la mise à jour de l’utilisateur', error);
        }
      },
      async deleteUser() {
        const userId = this.route.params.id;
        if (confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) {
          try {
            await axios.delete(`http://localhost:8080/api/users/${userId}`);
            this.$router.push('/');
          } catch (error) {
            console.error('Erreur lors de la suppression de l’utilisateur', error);
          }
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .user-edit-page {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f5f5f5;
  }
  
  .user-edit-container {
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 600px;
    width: 100%;
  }
  
  h1 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .user-form div {
    margin-bottom: 15px;
  }
  
  .user-form label {
    display: block;
    margin-bottom: 5px;
  }
  
  .user-form input,
  .user-form select {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
  
  .buttons {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
  }
  
  button {
    padding: 10px 20px;
    background-color: #007bff;
    border: none;
    color: white;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .delete-button {
    background-color: #dc3545;
  }
  
  .delete-button:hover {
    background-color: #c82333;
  }
  </style>
  