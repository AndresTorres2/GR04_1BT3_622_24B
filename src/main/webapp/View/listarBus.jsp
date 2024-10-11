<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <title>PoliBuses</title>
    <style>
        body {
            background: #100f0f;
            color: #dcdcdc;
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .header {
            margin: 20px 0;
        }

        .button-group {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .card {
            background-color: #1c1c1c;
            border-radius: 10px;
            padding: 20px;
            width: 250px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
        }

        .card h3 {
            margin: 0 0 10px 0;
        }

        .card p {
            margin: 5px 0;
        }

        a {
            text-decoration: none;
            padding: 10px 20px;
            background-color: #48578e;
            color: white;
            border-radius: 5px;
            display: inline-block;
            margin-top: 10px;
        }

        a:hover {
            background-color: #71a8df;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="header">Registro de Buses - Tienda Maruja</h1>

    <div>
        <h2>Seleccione la jornada</h2>
        <div class="button-group">
            <a href="${pageContext.request.contextPath}/BusServlet?ruta=seleccionarJornada&jornada=matutino">Matutina</a>
            <a href="${pageContext.request.contextPath}/BusServlet?ruta=seleccionarJornada&jornada=vespertino">Vespertina</a>
        </div>
    </div>

    <div class="card-container">
        <c:forEach var="bus" items="${buses}">
            <div class="card">
                <h3>Horario: ${bus[0]}</h3> <!-- Horario -->
                <p>Capacidad: ${bus[1]}</p> <!-- Capacidad -->
                <p>Origen: ${bus[2]}</p> <!-- Origen -->
                <p>Destino: ${bus[3]}</p> <!-- Destino -->
                <a href="${pageContext.request.contextPath}/BusServlet?ruta=verDetalles&id=${bus[4]}">Ver detalles</a>
            </div>
        </c:forEach>
    </div>

</div>
<a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=consultarReservas">Consultar Reservas</a>

</body>
</html>
