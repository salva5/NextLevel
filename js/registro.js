const formRegistro = document.querySelector("#registro")
formRegistro.addEventListener("submit", validacionRegistro)

function validacionRegistro (e) {
  e.preventDefault()
  const nombre = document.querySelector("#nombre")
  const apellido = document.querySelector("#apellido")
  const fecha = document.querySelector("#fecha")
  const pais = document.querySelector("#pais")
  const terminos = document.querySelector("#terminos")
  const email = document.querySelector("#email")
  const password = document.querySelector("#password")

  let errorNombre = document.querySelector("#error-nombre")
  let errorApellido = document.querySelector("#error-apellido")
  let errorFecha = document.querySelector("#error-fecha")
  let errorPais = document.querySelector("#error-pais")
  let errorTerminos = document.querySelector("#error-terminos")  
  let errorEmail = document.querySelector("#error-email")
  let errorPassword = document.querySelector("#error-password")

  if(!email.value) {
    errorEmail.textContent = "Ingresa un email"
  } else{
    if(!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value)){
      errorEmail.textContent = "Email no valido" 
    }else {
      errorEmail.textContent = ""
    }
  }

  if(!password.value) {
    errorPassword.textContent = "Ingresa una contraseña"

  } else{
    if(!/^(?=.*\d).{4,15}$/.test(password.value)){
      errorPassword.textContent = "Debe tener al menos un número"

    } else {
      errorPassword.textContent = ""
    }
  }
  if(!nombre.value) {
    errorNombre.textContent = "Ingresa un nombre"
  } else {
    errorNombre.textContent = ""
  }
  if(!apellido.value){
    errorApellido.textContent = "Ingrese un apellido"
  } else{
    errorApellido.textContent = ""
  }
  if(!fecha.value){
    errorFecha.textContent = "Ingrese una fecha"
  } else{
    errorFecha.textContent = ""
  }
  if(!pais.value){
    errorPais.textContent = "Ingrese un pais"
  } else{
    errorPais.textContent = ""
  }
  if(!terminos.checked){
    errorTerminos.textContent = "Debes aceptar los terminos y condiciones"
  } else{
    errorTerminos.textContent = ""
  }
}