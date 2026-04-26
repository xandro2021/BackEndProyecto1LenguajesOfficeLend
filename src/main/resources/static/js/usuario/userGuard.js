// userGuard.js
console.log('token:', localStorage.getItem('jwt'));
console.log('role:', localStorage.getItem('role'));

const token = localStorage.getItem('jwt');
const role = localStorage.getItem('role');

// ✅ Correct — allow both USER and ADMIN
if (!token || (role !== 'ROLE_USER' && role !== 'ROLE_ADMIN')) {
  console.log('Redirecting to login — no valid user session');
  window.location.href = '/';
}
