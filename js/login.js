const formIniciarSesion = document.querySelector("#iniciar-sesión")
formIniciarSesion.addEventListener("submit", validacionIniciarSesion)

function validacionIniciarSesion (e) {
  e.preventDefault()
  const email = document.querySelector("#email")
  const password = document.querySelector("#password")

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
}





