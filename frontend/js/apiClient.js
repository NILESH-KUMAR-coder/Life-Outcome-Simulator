async function api(path, method = "GET", body) {
  const token = localStorage.getItem("token");
  const isAuthEndpoint = path.startsWith("/api/auth/");

  console.log("Sending request to:", path);
  console.log("Token from localStorage:", token);

  const headers = {
    "Content-Type": "application/json",
  };

  // ✅ Only attach token for NON-auth endpoints
  if (!isAuthEndpoint && token) {
    headers["Authorization"] = "Bearer " + token;
  }

  // ❌ If protected endpoint & no token → block
  if (!isAuthEndpoint && !token) {
    throw new Error("You must be logged in.");
  }

  const res = await fetch("http://localhost:8080" + path, {
    method,
    headers,
    body: body ? JSON.stringify(body) : null,
  });

  if (!res.ok) {
    const errorMessage = await res.text();
    throw new Error(errorMessage || "Request failed");
  }

  // Handle empty responses
  if (res.status === 204) return null;

  try {
    return await res.json();
  } catch {
    return null;
  }
}
