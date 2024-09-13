<template>
    <div class="upload-container">
      <h2>Upload your files</h2>
  
      <!-- Input for selecting multiple files -->
      <input type="file" id="file-input" multiple @change="onFileChange" />
      <label for="file-input">Choose files</label>
  
      <!-- Display preview of selected files -->
      <div v-if="selectedFiles.length" class="file-preview">
        <h3>Files Preview:</h3>
        <ul>
          <li v-for="(file, index) in selectedFiles" :key="index" class="file-item">
            <div v-if="file.type.startsWith('image/')">
              <img :src="file.preview" alt="Preview" class="preview-img" />
            </div>
            <div class="file-details">
              <p>{{ file.name }} ({{ formatFileSize(file.size) }})</p>
            </div>
            <button @click="removeFile(index)" class="remove-btn">
              <img src="/effacer.png" class="trash-icon">
            </button>
          </li>
        </ul>
      </div>
  
      <!-- Button to upload files -->
      <button @click="uploadFiles" :disabled="!selectedFiles.length" class="upload-btn">Upload</button>
  
      <!-- Message on successful upload or error -->
      <p v-if="message" :class="{ error: message.includes('Error') }">{{ message }}</p>
    </div>
</template>


<script>
import axios from 'axios';

export default {
    data() {
        return {
        selectedFiles: [],
        message: ""
        };
    },
    methods: {
        onFileChange(event) {
        const files = event.target.files;
        Array.from(files).forEach(file => {
            const reader = new FileReader();
            reader.onload = (e) => {
            this.selectedFiles.push({
                name: file.name,
                size: file.size,
                type: file.type,
                preview: e.target.result,
                file: file
            });
            };
            if (file.type.startsWith('image/')) {
            reader.readAsDataURL(file);
            } else {
            reader.readAsText(file);
            }
        });
        },

        removeFile(index) {
        this.selectedFiles.splice(index, 1);
        },

        uploadFiles() {
        const formData = new FormData();
        this.selectedFiles.forEach(file => {
            formData.append("files", file.file);
        });

        axios.post("/api/files/upload", formData, {
            headers: {
            'Content-Type': 'multipart/form-data',
            },
        })
            .then(response => {
            this.message = response.data;
            this.selectedFiles = [];
            })
            .catch(error => {
            this.message = "Error uploading file: " + error.response.data;
            });
        },

        formatFileSize(size) {
        if (size < 1024) return size + ' bytes';
        if (size < 1048576) return (size / 1024).toFixed(1) + ' KB';
        return (size / 1048576).toFixed(1) + ' MB';
        }
    },
};
</script>
  

<style scoped>
.upload-container {
  max-width: 600px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f0f2f5;
  border-radius: 10px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2, h3 {
  font-family: 'Arial', sans-serif;
  color: #343a40;
}

input[type="file"] {
  display: none;
}

input[type="file"] + label {
  display: inline-block;
  padding: 12px 30px;
  background-color: #007bff;
  color: white;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;
  font-size: 16px;
  font-weight: bold;
}

input[type="file"] + label:hover {
  background-color: #0056b3;
}

button.upload-btn {
  background-color: #28a745;
  color: white;
  padding: 12px 30px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
  font-size: 16px;
  font-weight: bold;
}

button.upload-btn:disabled {
  background-color: #b3b3b3;
  cursor: not-allowed;
}

button.upload-btn:hover:not(:disabled) {
  background-color: #218838;
}

.file-preview {
  margin-top: 30px;
  text-align: left;
}

ul {
  list-style: none;
  padding: 0;
}

li.file-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 15px;
  background-color: white;
  margin-bottom: 10px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: background-color 0.3s ease;
}

li.file-item:hover {
  background-color: #f8f9fa;
}

.preview-img {
  max-width: 60px;
  max-height: 60px;
  margin-right: 15px;
  border-radius: 8px;
}

.file-details {
  flex-grow: 1;
}

p {
  font-size: 15px;
  color: #495057;
}

/* Style du bouton de suppression */
button.remove-btn {
  background-color: transparent;
  border: none;
  cursor: pointer;
  margin-left: 15px;
}

button.remove-btn img.trash-icon {
  width: 24px;
  height: 24px;
  transition: transform 0.3s ease;
}

button.remove-btn:hover img.trash-icon {
  transform: scale(1.1);
}

p.error {
  color: #dc3545;
}
</style>
