'use strict';

const URL = 'http://localhost:8080/web/api/v1/personas/';

let form;

window.addEventListener('DOMContentLoaded', async () => {
    await refrescarLista();

    form = document.querySelector('form');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const persona = { nombre: form.nombre.value, fechaNacimiento: form.fecha.value };

        const respuesta = await fetch(URL, {
			method: 'POST',
			body: JSON.stringify(persona),
			headers: {
				'Content-type': 'application/json'
			}
		});
		
		respuesta.ok && await refrescarLista();
    });
});

async function refrescarLista() {
    const respuesta = await fetch(URL);
    const personas = await respuesta.json();

    console.log(personas);

    const ul = document.querySelector('ul');

    ul.innerHTML = '';

    for (const persona of personas) {
        console.log(persona);

        const li = document.createElement('li');

        li.innerHTML = `${persona.id}: ${persona.nombre}, ${persona.fechaNacimiento}
			<a href="javascript:borrar(${persona.id})">[Borrar]</a>
		`;

        ul.appendChild(li);
    }
}

async function borrar(id) {
    console.log('Borrar', id);

    const respuesta = await fetch(URL + id, { method: 'DELETE' });

    console.log(respuesta);

    respuesta.ok && await refrescarLista();
}