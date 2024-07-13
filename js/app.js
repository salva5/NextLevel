document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("itemForm");
  const itemsTableBody = document.getElementById("itemsTableBody");

  form.addEventListener('submit',function(event){
    event.preventDefault();
    const formData = new FormData(form)
    const itemId = formData.get("id")
    const data = {
      id: formData.get("id"),
      titulo: formData.get("titulo"),
      genero: formData.get("genero"),
      descripcion: formData.get("descripcion"),
      imagen: formData.get("imagen")

    }
    console.log(data);
    if(itemId) {

    } else{
      createItem(data)
    }
  })
  function createItem(data) {
    fetch("http://localhost:8080/NextLevelBackend/juegos",{
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(result => {
      console.log("Exito...", result);
      loadItems()
      form.reset();
    })
    .catch(error => {
      console.log("Hubo algun error", error);
      alert("Hubo algun error al ingresar el juego")
    })
  }
  // Función para cargar los elementos desde la API
  function loadItems() {
    fetch("http://localhost:8080/NextLevelBackend/juegos")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        itemsTableBody.innerHTML = "";
        if (data) {
          
          data.forEach((juego) => {
            const row = document.createElement("tr");
            row.innerHTML = `
              <td>${juego.id}</td>
              <td>${juego.titulo}</td>                   
              <td>${juego.genero}</td>
              <td>${juego.descripcion}</td>
                              
              <td>
                <button class="btn btn-danger" onclick="deleteItem(${juego.id})" >Eliminar</button>
              </td>
              <td>
                <button class="btn btn-success" onclick="editItem
                (${juego.id},
                '${juego.titulo}',                   
                '${juego.genero}',
                '${juego.descripcion}')" >Editar</button>
              </td>`;
            itemsTableBody.appendChild(row);
          });
        } else {
          console.error("No se encontraron películas");
        }
      })
      .catch((error) => console.error("Error:", error));
  }

  window.editItem = function (
    id,
    titulo,
    genero,
    descripcion,
  ) {
    document.getElementById("id").value = id;
    document.getElementById("titulo").value = titulo;
    document.getElementById("genero").value = genero;
    document.getElementById("descripcion").value = descripcion;
  };

  function deleteItem(id){
    fetch(`http://localhost:8080/NextLevelBackend/juegos/${id}`,{
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      }
    });
    loadItems();
  }
  window.deleteItem = deleteItem

  loadItems();
});
