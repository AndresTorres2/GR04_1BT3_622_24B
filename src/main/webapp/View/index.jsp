<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Clientes</title>
    <style>
        body{
            background: #100f0f;
            color: #dcdcdc;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        table {
            margin-bottom: 20px;
            border-collapse: collapse;
        }

        .button-group {
            display: flex;
            gap: 10px;
        }

        a {
            text-decoration: none;
            padding: 10px 20px;
            background-color: #48578e;
            color: white;
            border-radius: 5px;
        }

        a:hover {
            background-color: #71a8df;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Registro de Clientes - Tienda Maruja</h1>
    <div>
        <table border="1" cellpadding="20">
            <thead>
            <tr>
                <th>Cédula</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Dirección</th>
                <th>Número de Teléfono</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="button-group">
        <a href="hello-servlet">Ingresar nuevo cliente</a>
        <a href="#">Eliminar registro</a>
    </div>
</div>
</body>
</html>
