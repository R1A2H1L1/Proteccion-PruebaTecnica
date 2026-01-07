// Simple frontend to consume /solicitudes endpoints
const apiBase = '/solicitudes';

function showMessage(text, isError) {
  const el = document.getElementById('message');
  el.textContent = text;
  el.className = isError ? 'message error' : 'message success';
  setTimeout(() => { el.textContent = ''; el.className = 'message'; }, 4000);
}

async function fetchList(url) {
  try {
    const res = await fetch(url);
    if (!res.ok) throw new Error(await res.text());
    const data = await res.json();
    renderTable(data);
  } catch (err) {
    showMessage('Error: ' + err.message, true);
  }
}

function renderTable(items) {
  const container = document.getElementById('tableContainer');
  if (!items || items.length === 0) {
    container.innerHTML = '<p>No hay solicitudes.</p>';
    return;
  }

  const table = document.createElement('table');
  const thead = document.createElement('thead');
  thead.innerHTML = '<tr><th>ID</th><th>Tipo</th><th>Prioridad manual</th><th>Fecha creación</th><th>Usuario</th></tr>';
  table.appendChild(thead);

  const tbody = document.createElement('tbody');
  items.forEach(s => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${s.id ?? ''}</td>
      <td>${s.tipoSolicitud ?? ''}</td>
      <td>${s.prioridadManual ?? ''}</td>
      <td>${s.fechaCreacion ?? ''}</td>
      <td>${s.usuario ?? ''}</td>
    `;
    tbody.appendChild(tr);
  });
  table.appendChild(tbody);

  container.innerHTML = '';
  container.appendChild(table);
}

async function createSolicitud(payload) {
  try {
    const res = await fetch(apiBase, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!res.ok) throw new Error(await res.text());
    const created = await res.json();
    showMessage('Solicitud creada (id: ' + (created.id ?? 'N/A') + ')', false);
    return created;
  } catch (err) {
    showMessage('Error al crear: ' + err.message, true);
    throw err;
  }
}

function init() {
  document.getElementById('btn-load-all').addEventListener('click', () => fetchList(apiBase));
  document.getElementById('btn-load-priorizadas').addEventListener('click', () => fetchList(apiBase + '/priorizadas'));

  document.getElementById('form-create').addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
      tipoSolicitud: document.getElementById('tipoSolicitud').value,
      prioridadManual: parseInt(document.getElementById('prioridadManual').value, 10) || 0,
      usuario: document.getElementById('usuario').value.trim()
    };
    try {
      await createSolicitud(payload);
      fetchList(apiBase);
      e.target.reset();
    } catch (ignored) {}
  });

  // Load existing on start
  fetchList(apiBase);
}

document.addEventListener('DOMContentLoaded', init);
