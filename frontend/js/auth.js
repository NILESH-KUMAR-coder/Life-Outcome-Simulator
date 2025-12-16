async function login() {
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value;

  // Clear any stale token before auth
  localStorage.removeItem("token");

  try {
    // 1️⃣ Try LOGIN
    const res = await api("/api/auth/login", "POST", {
      email,
      password,
    });

    localStorage.setItem("token", res.token);
    window.location = "dashboard.html";

  } catch (loginError) {
    console.warn("Login failed, attempting register...");

    try {
      // 2️⃣ Fallback to REGISTER
      const res = await api("/api/auth/register", "POST", {
        email,
        password,
      });

      localStorage.setItem("token", res.token);
      window.location = "dashboard.html";

    } catch (registerError) {
      alert("Login/Register failed: " + registerError.message);
    }
  }
}
