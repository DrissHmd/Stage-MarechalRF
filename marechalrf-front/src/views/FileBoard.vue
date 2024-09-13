<template>
    <div class="existing-files-container">
      <h2>Your Files</h2>
      <div v-if="files.length">
        <ul>
          <li v-for="file in files" :key="file.id" class="file-item">
            <span>{{ file.name }} ({{ formatFileSize(file.size) }})</span>
            <a :href="`/api/files/download/${file.name}`" class="download-btn">Download</a>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>No files found.</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        files: [],
      };
    },
    methods: {
      fetchFiles() {
        // Replace '1' with the actual user ID
        axios.get('/api/files/user/1')
          .then(response => {
            this.files = response.data;
          })
          .catch(error => {
            console.error("Error fetching files:", error);
          });
      },
      formatFileSize(size) {
        if (size < 1024) return size + ' bytes';
        if (size < 1048576) return (size / 1024).toFixed(1) + ' KB';
        return (size / 1048576).toFixed(1) + ' MB';
      }
    },
    created() {
      this.fetchFiles();
    }
  };
  </script>
  
  <style scoped>
  .existing-files-container {
    max-width: 600px;
    margin: 50px auto;
    padding: 20px;
    background-color: #f0f2f5;
    border-radius: 10px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
  
  h2 {
    font-family: 'Arial', sans-serif;
    color: #343a40;
  }
  
  ul {
    list-style: none;
    padding: 0;
  }
  
  li.file-item {
    display: flex;
    justify-content: space-between;
    padding: 10px;
    background-color: white;
    margin-bottom: 10px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  }
  
  .download-btn {
    color: #007bff;
    text-decoration: none;
    font-weight: bold;
  }
  
  .download-btn:hover {
    text-decoration: underline;
  }
  
  p {
    font-size: 15px;
    color: #495057;
  }
  </style>
  