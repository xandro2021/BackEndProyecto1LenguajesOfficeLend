console.log('token:', localStorage.getItem('jwt'));
console.log('role:', localStorage.getItem('role'));

const token = localStorage.getItem('jwt');
const role = localStorage.getItem('role');

// Only ADMIN can access this page
if (!token || role !== 'ROLE_ADMIN') {
  console.log('Redirecting to login — no valid admin session');
  window.location.href = '/';
}
