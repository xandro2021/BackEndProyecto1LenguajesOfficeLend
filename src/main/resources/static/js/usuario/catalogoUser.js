// Redirecciona al login if no esta debidamente autenticado o esta en role equivocado
const token = localStorage.getItem('jwt');
const role = localStorage.getItem('role');
if (!token || (role !== 'ROLE_USER' && role !== 'ROLE_ADMIN')) {
  window.location.href = '/';
}
