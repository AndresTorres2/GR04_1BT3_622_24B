<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Detalles del Bus</title>
    <style>
        body {
            background: #100f0f;
            color: #dcdcdc;
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
            background: #1c1c1c;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
        }
        h1, h2 {
            text-align: center;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin: 5px 0;
        }
        a {
            display: block;
            text-align: center;
            text-decoration: none;
            padding: 10px;
            background-color: #48578e;
            color: white;
            border-radius: 5px;
            margin: 20px 0;
        }
        a:hover {
            background-color: #71a8df;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Detalles del Bus</h1>

    <h2>Jornada: ${bus.jornada}</h2>

    <h3>Origen: ${bus.ruta.origen}</h3>
    <h3>Destino: ${bus.ruta.destino}</h3>
    <h4>Ruta:</h4>
    <ul>
        <c:forEach var="calle" items="${bus.ruta.calles}">
            <li>${calle.nombreCalle}</li> <!-- Mostrar el nombre de cada calle de la ruta -->
        </c:forEach>
    </ul>

    <p><strong>Horario:</strong> ${bus.horario}</p>
    <p><strong>Fecha:</strong> ${bus.fecha}</p>
    <p><strong>Asientos:</strong> ${bus.asientosOcupados}/${bus.capacidad}</p>

    <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?busId=${bus.codigo}">Realizar reserva de asiento</a>
</div>
</body>
</html>
