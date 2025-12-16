(async () => {
  const history = await api("/simulation/history");
  list.innerHTML = history.map(h =>
    `<li>Simulation ${h.simulationId} at ${h.runAt}</li>`
  ).join("");
})();
