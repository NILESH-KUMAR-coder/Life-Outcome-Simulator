async function start() {
  const years = +document.getElementById("years").value;
  const riskTolerance = +document.getElementById("risk").value;
  const income = +document.getElementById("income").value;
  const expenses = +document.getElementById("expenses").value;

  const sim = await api("/simulation/start", "POST", {
    years,
    riskTolerance,
    seed: Date.now(),
    income,
    expenses
  });

  localStorage.simulationId = sim.id;
  window.location = "results.html";
}
