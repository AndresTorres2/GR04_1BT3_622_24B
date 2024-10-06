<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
  <title>Ingresar Nuevo Cliente</title>
  <style>
    body {
      background: #100f0f;
      color: #dcdcdc;
      font-family: Arial, sans-serif;
    }

    .container {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      flex-direction: column;
    }

    form {
      background-color: #2e2e2e;
      padding: 20px;
      border-radius: 8px;

    }

    input[type="text"], input[type="tel"] {
      padding: 10px;
      margin-bottom: 10px;
      width: 90%;
      border-radius: 5px;
      border: 1px solid #ccc;
    }

    input[type="submit"] {
      padding: 10px 20px;
      background-color: #48578e;
      color: white;
      border: none;
      border-radius: 5px;
    }

    input[type="submit"]:hover {
      background-color: #71a8df;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Ingresar Nuevo Cliente</h1>
  <form action="${pageContext.request.contextPath}/ClienteServlet?ruta=guardarCliente" method="POST">
    <label for="cedula">Cédula:</label><br/>
    <input type="text" id="cedula" name="cedula" required /><br/>

    <label for="nombre">Nombre:</label><br/>
    <input type="text" id="nombre" name="nombre" required /><br/>

    <label for="apellido">Apellido:</label><br/>
    <input type="text" id="apellido" name="apellido" required /><br/>

    <label for="direccion">Dirección:</label><br/>
    <input type="text" id="direccion" name="direccion" required /><br/>

    <label for="numeroTelefono">Número de Teléfono:</label><br/>
    <input type="tel" id="numeroTelefono" name="numeroTelefono" required /><br/>

    <input type="submit" value="Guardar Cliente" />
  </form>
</div>
</body>
</html>
