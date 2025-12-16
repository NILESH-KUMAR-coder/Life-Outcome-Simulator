const canvas = document.getElementById("chart");
const ctx = canvas.getContext("2d");

let animationFrame = 0;
let data;
let maxValue;

function scaleY(value) {
  return canvas.height - (value / maxValue) * (canvas.height - 60) - 40;
}

function scaleX(index, total) {
  return 60 + index * ((canvas.width - 100) / (total - 1));
}

function drawAxes() {
  ctx.strokeStyle = "#666";
  ctx.lineWidth = 1;

  ctx.beginPath();
  ctx.moveTo(50, 20);
  ctx.lineTo(50, canvas.height - 40);
  ctx.lineTo(canvas.width - 20, canvas.height - 40);
  ctx.stroke();
}

function drawLine(series, color, progress) {
  ctx.strokeStyle = color;
  ctx.lineWidth = 2;
  ctx.beginPath();

  series.forEach((v, i) => {
    if (i / series.length > progress) return;
    const x = scaleX(i, series.length);
    const y = scaleY(v);
    if (i === 0) ctx.moveTo(x, y);
    else ctx.lineTo(x, y);
  });

  ctx.stroke();
}

function drawFan(percentiles, progress) {
  ctx.fillStyle = "rgba(124,156,255,0.15)";
  ctx.beginPath();

  percentiles.forEach((p, i) => {
    if (i / percentiles.length > progress) return;
    ctx.lineTo(scaleX(i, percentiles.length), scaleY(p.p90));
  });

  for (let i = percentiles.length - 1; i >= 0; i--) {
    if (i / percentiles.length > progress) continue;
    const p = percentiles[i];
    ctx.lineTo(scaleX(i, percentiles.length), scaleY(p.p10));
  }

  ctx.closePath();
  ctx.fill();
}

function render(progress) {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  drawAxes();

  drawFan(data.yearlyPercentiles, progress);

  if (toggleWorst.checked)
    drawLine(data.worst, "#ff6b6b", progress);

  if (toggleExpected.checked)
    drawLine(data.expected, "#7c9cff", progress);

  if (toggleBest.checked)
    drawLine(data.best, "#2ecc71", progress);
}

function animate() {
  animationFrame += 0.01;
  render(Math.min(animationFrame, 1));
  if (animationFrame < 1) requestAnimationFrame(animate);
}

document.getElementById("replay").onclick = () => {
  animationFrame = 0;
  animate();
};

async function loadSimulation() {
  const id = localStorage.simulationId;
  data = await api(`/simulation/results/${id}`);
  if (!data) {
    alert("Failed to load simulation results");
    return;
  }
  maxValue = Math.max(...data.best) * 1.1;
  animationFrame = 0;
  animate();
}

// Define the function for Apply & Re-run
async function applyDecision() {
  const career = document.getElementById("career").value;
  const savingsRate = document.getElementById("savings").value;
  const investmentStrategy = document.getElementById("investment").value;
  const lifestyleCost = document.getElementById("lifestyle").value;
  const category = document.getElementById("category").value;
  const description = document.getElementById("description").value;

  // Send updated data to backend API
  await api("/simulation/decision", "POST", {
    simulationId: localStorage.simulationId,
    career,
    savingsRate: +savingsRate,
    investmentStrategy,
    lifestyleCost: +lifestyleCost,
    category,
    description,
    impactFactor: 1.0
  });

  // Reload simulation with updated parameters
  await loadSimulation();
}

// Initialize simulation
loadSimulation();
