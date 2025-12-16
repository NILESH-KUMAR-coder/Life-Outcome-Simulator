const canvas = document.getElementById("scenarioChart");
const ctx = canvas.getContext("2d");

(async function () {
  const id = localStorage.simulationId;
  const scenarios = await api(`/simulation/${id}/scenarios`);

  draw(scenarios);
})();

function draw(scenarios) {
  const max = Math.max(
    ...scenarios.flatMap(s => s.values)
  );

  scenarios.forEach((s, idx) => {
    ctx.beginPath();
    ctx.strokeStyle = ["#aaa", "#2ecc71", "#ff6b6b", "#7c9cff"][idx];
    ctx.lineWidth = 2;

    s.values.forEach((v, i) => {
      const x = 60 + i * ((canvas.width - 100) / (s.values.length - 1));
      const y = canvas.height - (v / max) * (canvas.height - 60) - 30;
      if (i === 0) ctx.moveTo(x, y);
      else ctx.lineTo(x, y);
    });

    ctx.stroke();
    ctx.fillText(s.name, 70, 30 + idx * 18);
  });
}
