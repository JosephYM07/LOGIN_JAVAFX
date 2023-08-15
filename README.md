#  Aplicación de Gestión de Usuarios
## Integrantes:
- Alejandro Moreira
- Jared Valenzuela
- Joseph Yépez

## Descripción:
El proyecto implica el desarrollo de una aplicación de escritorio utilizando JavaFX para crear una interfaz gráfica de usuario y MySQL para el almacenamiento de datos. La aplicación permite a los usuarios iniciar sesión, ver su información personal, actualizar sus datos, eliminar su cuenta y cerrar sesión de manera segura.

1. Componentes y Funcionalidades:

- Interfaz de Inicio de Sesión (InicioSesion.fxml):

Pantalla inicial donde los usuarios pueden ingresar sus credenciales para iniciar sesión.
Campos para ingresar el nombre de usuario y la contraseña.
Botón "Iniciar Sesión" que verifica las credenciales ingresadas y muestra un mensaje de error si son incorrectas.
Enlace o botón para "Registrarse" en caso de que el usuario no tenga una cuenta.
- Interfaz de Información del Usuario (Informacion.fxml):

Pantalla que muestra la información personal del usuario después de iniciar sesión.
Muestra el nombre de usuario y da la bienvenida al usuario.
Muestra los datos actuales del usuario: nombre, correo electrónico y contraseña.
Opción para actualizar los datos del usuario.
- Funcionalidad de Actualización de Datos (InformacionControlador.java):

Permite al usuario modificar su nombre, correo electrónico y contraseña.
Los campos de texto muestran los datos actuales del usuario.
Botón "Actualizar" guarda los cambios en la base de datos y muestra una confirmación.
- Funcionalidad de Eliminación de Cuenta (InformacionControlador.java):

Botón "Eliminar cuenta" que elimina la cuenta del usuario de la base de datos de manera irreversible.
Se muestra una confirmación y luego se cierra la sesión.
- Funcionalidad de Cierre de Sesión (InformacionControlador.java):

Botón "Cerrar sesión" que cierra la sesión actual y regresa a la ventana de inicio de sesión.
No cierra la aplicación por completo, solo la sesión actual.
Base de Datos MySQL:

Almacena información de usuarios como nombres, correos electrónicos y contraseñas.
Se utiliza JDBC para establecer una conexión con la base de datos.
Se realizan consultas para verificar credenciales, actualizar datos y eliminar cuentas.
Controlador de Ventana (InformacionControlador.java):

Maneja la lógica detrás de las acciones del usuario en la ventana de información.
Establece conexiones con la base de datos para actualizar o eliminar datos.
Administra la apertura de la ventana de inicio de sesión después de cerrar sesión o eliminar cuenta.
- Flujo:

1. El usuario inicia la aplicación y llega a la ventana de inicio de sesión.
2. El usuario ingresa sus credenciales y hace clic en "Iniciar Sesión".
3. Si las credenciales son correctas, la ventana de información del usuario se abre, mostrando la información personal.
4. El usuario puede optar por actualizar sus datos, lo que reflejará los cambios en la base de datos.
5. Si el usuario decide eliminar su cuenta, se elimina de la base de datos y se muestra un mensaje de confirmación.
6. El usuario también puede cerrar sesión, lo que lo devuelve a la ventana de inicio de sesión sin cerrar la aplicación.