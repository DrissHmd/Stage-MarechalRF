import express, { Request, Response } from 'express';
import authenticateToken from './authentificate';
import nodemailer from 'nodemailer';

const app = express();
const PORT = 8080;

app.use(express.json());

// Exemple de route sécurisée nécessitant une authentification
app.get('/dashboard', authenticateToken, (req: Request, res: Response) => {
  res.json({ message: 'Welcome to the dashboard!' });
});

app.post('/contact', async (req: Request, res: Response) => {
  const { nom, prenom, entreprise, fonction, email, telephone, message, consent } = req.body;

  if (!nom || !prenom || !entreprise || !fonction || !email || !consent) {
    return res.status(400).json({ error: 'Please fill out all required fields.' });
  }

  // Configuration de Nodemailer
  const transporter = nodemailer.createTransport({
    host: "smtp.example.com", // Remplacez par votre hôte SMTP
    port: 587,
    secure: false, // true pour le port 465, false pour les autres ports
    auth: {
      user: "your_email@example.com", // votre email SMTP
      pass: "your_password", // votre mot de passe SMTP
    },
  }); 

  // Options de l'email
  const mailOptions = {
    from: `"Contact Form" (${email})`, // adresse de l'expéditeur
    to: "marechalretf@gmail.com", // adresse du destinataire
    subject: "New Test Contact Form Submission",
    text: `You have received a new message from ${nom} ${prenom} (${email}):
Entreprise: ${entreprise}
Fonction: ${fonction}
Téléphone: ${telephone}
Message: ${message}
Consent: ${consent}`,
  };

  try {
    // Envoyer l'email
    const info = await transporter.sendMail(mailOptions);
    console.log('Message sent: %s', info.messageId);
    res.status(200).json({ message: 'Message sent successfully!' });
  } catch (error) {
    console.error('Error sending email:', error);
    res.status(500).json({ error: 'Failed to send message.' });
  }
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
