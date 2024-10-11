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
            width: 60%;
            margin: auto;
            padding: 20px;
            background: #1c1c1c;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
        }
        h1, h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-size: 18px;
        }
        input[type="text"], input[type="checkbox"] {
            margin: 5px 0 15px;
            padding: 10px;
            width: calc(100% - 22px);
            border: none;
            border-radius: 5px;
            background: #2e2e2e;
            color: #dcdcdc;
        }
        .checkbox-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }
        .checkbox-group label {
            font-size: 16px;
            margin-left: 5px;
        }
        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #48578e;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
        }
        button:hover {
            background-color: #71a8df;
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
        .back-link a {
            color: #71a8df;
            text-decoration: none;
        }
        .back-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Reservar Asiento</h1>
    <form action="${pageContext.request.contextPath}/ReservarAsientoServlet?action=guardarReserva" method="post">
        <input type="hidden" name="busId" value="${bus.codigo}"/>

        <label for="idEstudiante">Código del Estudiante:</label>
        <input type="text" id="idEstudiante" name="idEstudiante" placeholder="Ingresa tu código de estudiante" required/>

        <h2>Selecciona los días de la reserva:</h2>
        <div class="checkbox-group">
            <label><input type="checkbox" name="diasReservados" value="Lunes"> Lunes</label>
            <label><input type="checkbox" name="diasReservados" value="Martes"> Martes</label>
            <label><input type="checkbox" name="diasReservados" value="Miércoles"> Miércoles</label>
            <label><input type="checkbox" name="diasReservados" value="Jueves"> Jueves</label>
            <label><input type="checkbox" name="diasReservados" value="Viernes"> Viernes</label>
        </div>

        <button type="submit">Realizar Reserva</button>
    </form>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/BusServlet?ruta=seleccionarJornada&jornada">Volver a la lista de buses</a>
    </div>
</div>
</body>
</html>
