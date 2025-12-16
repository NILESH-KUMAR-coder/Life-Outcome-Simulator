const svg = document.getElementById("tree");

function node(x, y, text) {
  svg.innerHTML += `
    <circle cx="${x}" cy="${y}" r="25" fill="#7c9cff"/>
    <text x="${x}" y="${y}" fill="white" text-anchor="middle" dy=".3em">${text}</text>
  `;
}

node(400, 50, "Now");
node(200, 200, "Low Risk");
node(600, 200, "High Risk");
