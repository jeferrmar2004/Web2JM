<%--
  Created by IntelliJ IDEA.
  User: J MARTINEZ
  Date: 18/10/2025
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cajero • Inicio</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/atm.css">
</head>
<body>
<header class="encabezado">
    <div class="nav">
        <div class="marca">Cajero • UTH</div>
        <nav class="menu">
            <a href="index.jsp">Inicio</a>
            <a href="deposit.jsp">Depósito</a>
            <a href="retirar.jsp">Retiro</a>
            <a href="help.jsp">Ayuda</a>
        </nav>
    </div>
</header>

<main class="contenedor">
    <section class="marco-atm">
        <div class="pantalla">
            <article class="tarjeta">
                <h2>Bienvenido</h2>
                <p class="sub">Elija una opción del menú para simular operaciones.</p>
            </article>

            <article class="tarjeta mt-4">
                <h3>Saldos demo</h3>
                <table class="tabla mt-2">
                    <thead><tr><th>Cuenta</th><th>Saldo</th></tr></thead>
                    <tbody id="tabla-cuentas"></tbody>
                </table>
            </article>
        </div>
        <div class="ranura"></div>
    </section>
</main>

<script defer src="${pageContext.request.contextPath}/estilos/atm.js"></script>
</body>
</html>
