import { Request, Response, NextFunction } from 'express';
import jwt from 'jsonwebtoken';

// Étendre l'interface Request pour inclure la propriété user
declare module 'express-serve-static-core' {
  interface Request {
    user?: string | jwt.JwtPayload;
  }
}

const authenticateToken = (req: Request, res: Response, next: NextFunction) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];
  
  if (!token) {
    return res.sendStatus(401); // No token provided
  }

  jwt.verify(token, process.env.ACCESS_TOKEN_SECRET as string, (err, user) => {
    if (err) {
      return res.sendStatus(403); // Invalid token
    }
    req.user = user;
    next();
  });
};

export default authenticateToken;
