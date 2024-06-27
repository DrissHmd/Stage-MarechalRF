import express, { Request, Response } from 'express';
import authenticateToken from './authenticate'; // Supposons que c'est le nom du middleware d'authentification

const app = express();
const PORT = 8080;

app.use(express.json());

// Exemple de route sécurisée nécessitant une authentification
app.get('/dashboard', authenticateToken, (req: Request, res: Response) => {
  res.json({ message: 'Welcome to the dashboard!' });
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
