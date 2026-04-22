// Toggle password visibility
document.getElementById('togglePassword').addEventListener('click', () => {
  const pwd = document.getElementById('password');
  pwd.type = pwd.type === 'password' ? 'text' : 'password';
});

document.getElementById('loginForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;
  const errorMsg = document.getElementById('errorMsg');
  errorMsg.classList.add('d-none');

  try {
    const response = await fetch('/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });

    if (!response.ok) {
      errorMsg.classList.remove('d-none');
      return;
    }

    const data = await response.json();
    const token = data.token;

    // Save token for future requests
    localStorage.setItem('jwt', token);
    localStorage.setItem('role', data.role);

    // Decode role from JWT payload (middle part, base64)
    const payload = JSON.parse(atob(token.split('.')[1]));
    const role = payload.role; // adjust if your claim name is different

    // Redirect based on role
    if (data.role === 'ROLE_ADMIN') {
      window.location.href = '/admin/catalogo';
    } else {
      window.location.href = '/user/catalogo';
    }

    // Get authorities from Spring Security — role is in the token subject or authorities
    // Since UserDetailsServiceImpl stores role as a GrantedAuthority, we need to check it
    // Let's ask the backend for the current user's role via a helper endpoint
    // OR we can decode it from the token if we include it as a claim

    // For now, redirect based on username as a fallback (we'll fix below)
    // redirectByRole(token);

  } catch (err) {
    errorMsg.classList.remove('d-none');
  }
});

function redirectByRole(token) {
  // Decode JWT payload
  const payload = JSON.parse(atob(token.split('.')[1]));
  // Spring Security puts authorities in "authorities" or you may need to add it as a claim
  // Check what's in the payload:
  console.log('JWT payload:', payload);
}
