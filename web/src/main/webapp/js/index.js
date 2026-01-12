'use strict';

const URL = 'http://localhost:8080/web/api/v1/personas/';

let form;

window.addEventListener('DOMContentLoaded', async () => {
    await refrescarLista();

    form = document.querySelector('form');

    form.addEventListener('submit', guardar);
});

async function guardar(e) {
    e.preventDefault();

    const id = form['id'].value;

	let url = URL;
	
	let metodo = 'POST';
	
    const persona = { nombre: form.nombre.value, fechaNacimiento: form.fecha.value };

    if (id) {
		persona.id = id;
		
		url += id;
		
		metodo = 'PUT';
    }

    const respuesta = await fetch(url, {
        method: metodo,
        body: JSON.stringify(persona),
        headers: {
            'Content-type': 'application/json'
        }
    });

	form.reset();
	
    respuesta.ok && await refrescarLista();
}

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
			<a href="javascript:editar(${persona.id})">[Editar]</a>
			<a href="javascript:borrar(${persona.id})">[Borrar]</a>
		`;

        ul.appendChild(li);
    }
}

async function editar(id) {
    console.log('Editar', id);

    const respuesta = await fetch(URL + id);
    const persona = await respuesta.json();

    form['id'].value = persona.id;
    form.nombre.value = persona.nombre;
    form.fecha.value = persona.fechaNacimiento;
}

async function borrar(id) {
    console.log('Borrar', id);

    const respuesta = await fetch(URL + id, { method: 'DELETE' });

    console.log(respuesta);

    respuesta.ok && await refrescarLista();
}