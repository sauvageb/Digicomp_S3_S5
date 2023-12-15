// const apiKey = 'RPf33GN5Kbw5AcfDAj5YF52YC24ck3Ova6KbdPJF';
// const apiUrl = `https://api.nasa.gov/planetary/apod?api_key=${apiKey}&count=10`;

const apiUrl = 'https://raw.githubusercontent.com/sauvageb/Digicomp_S3/main/BOOTSTRAP_final/data.json';
fetch(apiUrl)
  .then(response => {
    if (!response.ok) {
      throw new Error(`Erreur HTTP ! Status: ${response.status}`);
    }
    return response.json();
  })
  .then(apodList => {
    display(apodList);
  })
  .catch(error => {
    console.error('Erreur lors de la requête à l\'API:', error);
  });

function display(apodList) {
  let container = document.querySelector('.container .row');
  let html = '';
  apodList.forEach(element => {
    html += `
  <div class="col">
  <a href="#exampleModal" data-bs-toggle="modal" data-bs-target="#exampleModal">
      <div class="normal-text">
        <div class="card mx-auto">`;
  
  if (element.media_type === "image") {
    html += `<img src="${element.url}" class="card-img-top" alt="...">`;
  } else {
    html +=  `<iframe src="${element.url}" class="card-img-top"></iframe>`;
  }
  
  html += `<div class="card-body">
              <p class="card-text text-center">${element.title}</p>
            </div>
          </div>
        </div>
      </a>
      </div>
      `;
  });
  container.innerHTML = html;
}