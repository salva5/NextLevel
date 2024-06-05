
fetch("https://api.rawg.io/api/games?key=a45ecf58b9de4ce7bba64fbd84376e8a&page_size=9")
.then((res) =>res.json() )
.then((res) => {
  const juegosPopulares = document.querySelector(".juegos-populares")
  
  juegosPopulares.innerHTML = res.results.map( juego => {   
    return (
      `<div class="col-6 col-md-4 col-lg-3 card">
        <div class=" card-populares ">
          <a href="./paginas/detalle.html">
            <img src=${juego.background_image} class="card-img-top img-fluid" alt=${juego.name}/>
            <div class="card-body">
              <h5 class="card-title">${juego.name}</h5>
            </div>
          </a>
        </div>
      </div>`
    )
  }).join("")

})