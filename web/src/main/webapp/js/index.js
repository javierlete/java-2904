'use strict';

const URL = 'http://localhost:8080/web/api/v1/personas/';

window.addEventListener('DOMContentLoaded', async () => {
	const respuesta = await fetch(URL);
	const personas = await respuesta.json();
	
	console.log(personas);
	
	const ul = document.querySelector('ul');
	
	for(const persona of personas) {
		console.log(persona);
		
		const li = document.createElement('li');
		
		li.innerHTML = `${persona.id}: ${persona.nombre}, ${persona.fechaNacimiento}`;
		
		ul.appendChild(li);
	}
});