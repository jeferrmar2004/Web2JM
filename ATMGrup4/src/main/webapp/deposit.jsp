<%--
  Created by IntelliJ IDEA.
  User: J MARTINEZ
  Date: 18/10/2025
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cajero • Depósito</title>
    <link rel="stylesheet" href="resources/css/atm.css">
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
            <div class="rejilla">
                <article class="tarjeta col-12 col-6">
                    <h2>Depósito</h2>
                    <p class="sub">Ingrese los datos y confirme el monto.</p>

                    <form id="form-deposito" class="formulario">
                        <div class="campo">
                            <label for="dep-cuenta">Número de cuenta</label>
                            <input id="dep-cuenta" class="entrada" placeholder="Ej.: 1001" data-keyboard="numeric">
                        </div>

                        <div class="campo">
                            <label for="dep-pin">PIN</label>
                            <div class="input-grupo">
                                <input id="dep-pin" class="entrada" type="password" placeholder="****" maxlength="6" data-keyboard="numeric">
                                <button type="button" class="toggle-pin" data-target="#dep-pin">Ver PIN</button>
                            </div>
                        </div>

                        <div class="campo">
                            <label for="dep-monto">Monto</label>
                            <input id="dep-monto" class="entrada" placeholder="Ej.: 150.00" data-amount data-keyboard="numeric">
                            <span class="ayuda">Formato: 0.00</span>
                        </div>

                        <div class="fila-botones">
                            <button class="boton ok" type="submit">Depositar</button>
                            <button class="boton claro" type="reset">Limpiar</button>
                        </div>

                        <div id="dep-msg" class="alerta info mt-2">Listo para depositar.</div>
                    </form>

                    <h3 class="mt-4">Teclado</h3>
                    <div class="teclado mt-2" data-target="#dep-monto">
                        <div class="key" data-val="1">1</div><div class="key" data-val="2">2</div><div class="key" data-val="3">3</div>
                        <div class="key" data-val="4">4</div><div class="key" data-val="5">5</div><div class="key" data-val="6">6</div>
                        <div class="key" data-val="7">7</div><div class="key" data-val="8">8</div><div class="key" data-val="9">9</div>
                        <div class="key fn" data-val="C">C</div><div class="key" data-val="0">0</div><div class="key fn" data-val="DEL">DEL</div>
                    </div>
                </article>

                <aside class="tarjeta col-12 col-6">
                    <h3>Notas</h3>
                    <p class="sub">Esta es una simulación visual; no hay backend.</p>
                </aside>
            </div>
        </div>
        <div class="ranura"></div>
    </section>
</main>

<!-- Modal Recibo (propio de esta página) -->
<div class="modal" aria-hidden="true" role="dialog">
    <div class="caja">
        <h3>Recibo de operación</h3>
        <div class="alerta" style="border-style:dashed">
            <p><b>Tipo:</b> <span id="rec-tipo">—</span></p>
            <p><b>Cuenta:</b> <span id="rec-cuenta">—</span></p>
            <p><b>Monto:</b> <span id="rec-monto">—</span></p>
            <p><b>Saldo:</b> <span id="rec-saldo">—</span></p>
            <p><b>Fecha:</b> <span id="rec-fecha">—</span></p>
        </div>
        <div class="acciones">
            <button class="boton claro cerrar" type="button">Cerrar</button>
        </div>
    </div>
</div>

<script src="resources/js/atm.js"></script>
</body>
</html>
