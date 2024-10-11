<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Consultar Reservas</title>
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

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #dcdcdc;
      padding: 15px;
      text-align: center;
    }

    th {
      background-color: #48578e;
      color: white;
    }

    td {
      background-color: #1c1c1c;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Consultar Reservas</h1>

  <table>
    <thead>
    <tr>
      <th>Jornada</th>
      <th>Lunes</th>
      <th>Martes</th>
      <th>Miércoles</th>
      <th>Jueves</th>
      <th>Viernes</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Matutina</td>
      <td>
        <!-- Lunes -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Lunes' && reserva.bus.jornada == 'matutino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Martes -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Martes' && reserva.bus.jornada == 'matutino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Miércoles -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Miércoles' && reserva.bus.jornada == 'matutino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Jueves -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Jueves' && reserva.bus.jornada == 'matutino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Viernes -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Viernes' && reserva.bus.jornada == 'matutino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

    </tr>
    <tr>
      <td>Vespertina</td>
      <td>
        <!-- Lunes -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Lunes' && reserva.bus.jornada == 'vespertino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Martes -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Martes' && reserva.bus.jornada == 'vespertino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Miércoles -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Miércoles' && reserva.bus.jornada == 'vespertino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Jueves -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Jueves' && reserva.bus.jornada == 'vespertino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>

      <td>
        <!-- Viernes -->
        <c:forEach var="reserva" items="${reservas}">
          <c:forEach var="dia" items="${reserva.diasReservados}">
            <c:if test="${dia == 'Viernes' && reserva.bus.jornada == 'vespertino'}">
              <a href="${pageContext.request.contextPath}/ReservarAsientoServlet?action=detalleReserva&idReserva=${reserva.idReserva}&dia=${dia}" style="text-decoration:none; color:inherit;">
                <div style="cursor: pointer; padding: 10px; border: 1px solid #dcdcdc;">
                  Bus #${reserva.bus.codigo} <br>
                  Fecha de la reserva: ${reserva.fechaReserva} <br>
                  Horario: ${reserva.bus.horario} <br>
                  Ruta: Desde ${reserva.bus.ruta.origen} hasta ${reserva.bus.ruta.destino} <br>
                  Nombre del estudiante: ${reserva.estudiante.nombre} ${reserva.estudiante.apellido}
                </div>
              </a>
            </c:if>
          </c:forEach>
        </c:forEach>
      </td>
    </tr>
    </tbody>
  </table>
</div>
</body>
</html>

