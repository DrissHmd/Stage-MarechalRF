import express from 'express';
import nodemailer from 'nodemailer';
import bodyParser from 'body-parser';
import cors from 'cors';
import { Sequelize, DataTypes } from 'sequelize';

// Initialisation de l'application Express
const app = express();
const port = 3000;

app.use(cors());
app.use(bodyParser.json());

// Configuration de Nodemailer
const transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'marechalretf@gmail.com',
    pass: 'Formation2024++!!'  // Utilisez un mot de passe d'application pour plus de sécurité
  }
});

// Configuration de Sequelize pour MySQL
const sequelize = new Sequelize('database_name', 'username', 'password', {
  host: 'localhost',
  dialect: 'mysql'
});

// Définition du modèle pour les données du formulaire
const FormData = sequelize.define('FormData', {
  name: {
    type: DataTypes.STRING,
    allowNull: false
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false
  },
  message: {
    type: DataTypes.TEXT,
    allowNull: false
  }
});

// Synchronisation du modèle avec la base de données
sequelize.sync();

// Route pour traiter les soumissions de formulaires
app.post('/send-email', async (req, res) => {
  const { name, email, message } = req.body;

  try {
    // Sauvegarde dans la base de données
    const newFormData = await FormData.create({ name, email, message });

    // Envoi de l'e-mail
    const mailOptions = {
      from: email,
      to: 'marechalretf@gmail.com',
      subject: `New contact form submission from ${name}`,
      text: message
    };

    transporter.sendMail(mailOptions, (error, info) => {
      if (error) {
        return res.status(500).send(error.toString());
      }
      res.status(200).send('Email sent: ' + info.response);
    });

  } catch (error) {
    res.status(500).send('Error saving data: ' + error.toString());
  }
});

// Démarrage du serveur
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
