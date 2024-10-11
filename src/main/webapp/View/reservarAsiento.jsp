<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reservar Asiento</title>
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
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Reservar Asiento</h1>
    <form action="${pageContext.request.contextPath}/ReservarAsientoServlet?action=guardarReserva" method="post">
        <input type="hidden" name="busId" value="${bus.codigo}"/>

        <label for="idEstudiante">Código del Estudiante:</label>
        <input type="text" id="idEstudiante" name="idEstudiante" required/><br>

        <h2>Selecciona los días de la reserva:</h2>
        <label><input type="checkbox" name="diasReservados" value="Lunes"> Lunes</label><br>
        <label><input type="checkbox" name="diasReservados" value="Martes"> Martes</label><br>
        <label><input type="checkbox" name="diasReservados" value="Miércoles"> Miércoles</label><br>
        <label><input type="checkbox" name="diasReservados" value="Jueves"> Jueves</label><br>
        <label><input type="checkbox" name="diasReservados" value="Viernes"> Viernes</label><br>

        <button type="submit">Realizar Reserva</button>
    </form>
</div>
</body>
</html>
