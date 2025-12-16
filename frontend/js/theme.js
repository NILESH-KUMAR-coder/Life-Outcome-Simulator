function toggleTheme() {
  document.body.classList.toggle("light");

  localStorage.theme = document.body.classList.contains("light")
    ? "light"
    : "dark";
}

// ✅ Ensure body exists before applying theme
document.addEventListener("DOMContentLoaded", () => {
  if (localStorage.theme === "light") {
    document.body.classList.add("light");
  }
});
