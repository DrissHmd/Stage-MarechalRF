const USER_STORAGE_KEY = 'user';

export const setUser = (user: any) => {
  localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user));
};

export const clearUser = () => {
  localStorage.removeItem(USER_STORAGE_KEY);
};

export const getUser = () => {
  const user = localStorage.getItem(USER_STORAGE_KEY);
  return user ? JSON.parse(user) : null;
};

export const isAuthenticated = () => {
  return !!getUser();
};
