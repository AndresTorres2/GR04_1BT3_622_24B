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
        #map {
            height: 600px;
            width: 100%;
            margin: 20px 0;
        }
    </style>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.2.0/dist/leaflet.css" />
    <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.css" />
</head>
<body>
<div class="container">
    <h1>Detalles del Viaje</h1>

    <h2>Jornada: ${viaje.jornada}</h2>

    <h3>Origen: ${viaje.ruta.origen}</h3>
    <h3>Destino: ${viaje.ruta.destino}</h3>
    <h4>Ruta:</h4>
    <ul>
        <c:forEach var="calle" items="${viaje.ruta.calles}">
            <li>${calle.nombre}</li>
        </c:forEach>
    </ul>

    <p><strong>Horario:</strong> ${viaje.horaDeSalida}</p>

    <div id="map"></div>

    <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=formularioReserva&busId=${viaje.idViaje}">Realizar reserva de asiento</a>
</div>

<script src="https://unpkg.com/leaflet@1.2.0/dist/leaflet.js"></script>
<script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.js"></script>
<script>
    var map = L.map('map').setView([-0.210194, -78.489326], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
    }).addTo(map);

    var origen = [-0.210194, -78.489326]; // TODO: reemplazar según viaje seleccionado
    var destino = [-0.346586, -78.553517];

    L.marker(origen).addTo(map).bindPopup('${viaje.ruta.origen}').openPopup();
    L.marker(destino).addTo(map).bindPopup('${viaje.ruta.destino}').openPopup();

    var waypoints = [ //TODO: obtener coordenadas reales de las calles
        L.latLng(-0.220428, -78.508314), // Ladrón de Guevara
        L.latLng(-0.224660, -78.507166), // Av. Oriental (antigua)
        L.latLng(-0.215192, -78.525153), // Av. Napo-Camal
        L.latLng(-0.232999, -78.547424)  // El Recreo
    ];

    L.Routing.control({
        waypoints: [
            L.latLng(origen[0], origen[1]),
            ...waypoints,
            L.latLng(destino[0], destino[1])
        ],
        routeWhileDragging: true
    }).addTo(map);
</script>

</body>
</html>
