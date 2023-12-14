async function fetchRandomUsers() {
    try {
        const reponse = await fetch("https://randomuser.me/api/?results=10", {
            method: "GET",
            headers: { "Accept": "application/json" }
        });
        const data = await reponse.json();
        const users = data.results;
        display(users);
    } catch (erreur) {
        console.error("Erreur :", erreur);
    }
}
fetchRandomUsers();

function display(userList) {
    const divContainer = document.querySelector('.container .row');
    let html = ``;
    for (let u of userList) {
        html += `
        <div class="col">
        <div class="card" style="width: 18rem;">
            <img src="${u.picture.large}" class="card-img-top" alt="">
            <div class="card-body">
              <h5 class="card-title">${u.name.first} ${u.name.last}</h5>
              <p class="card-text">${u.email}</p>
              <a href="tel:${u.cell}" class="btn btn-primary">Call</a>
            </div>
          </div>
     </div>`;
    }
    divContainer.innerHTML = html;
}

fetchRandomUsers();