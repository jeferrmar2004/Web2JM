<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>ATM • Inicio de Sesión</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/atm.css">
    <style>
        .encabezado { display: none; }
    </style>
</head>
<body>
<main class="contenedor">
    <section class="atm-bisel">
        <div class="atm-panel">
            <!-- Soft keys izquierda (AGREGADO: data-href) -->
            <aside class="softcol">
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp">
                    <span class="flecha">›</span>Ayuda
                </button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp">
                    <span class="flecha">›</span>Soporte
                </button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp">
                    <span class="flecha">›</span>Salir
                </button>
            </aside>

            <!-- Pantalla principal -->
            <section class="pantalla">
                <h2 class="lcd-titulo">BIENVENIDO</h2>
                <p class="lcd-sub">Por favor ingrese su número de cuenta y su PIN.</p>

                <form id="form-login" class="formulario">
                    <div class="campo">
                        <label for="login-cuenta">Número de cuenta</label>
                        <input id="login-cuenta" class="entrada" type="password" maxlength="6" data-keyboard="numeric">
                    </div>

                    <div class="campo">
                        <label for="login-pin">PIN</label>
                        <input id="login-pin" class="entrada" type="password" maxlength="6" data-keyboard="numeric">
                        <span class="ayuda">Por seguridad, los valores no se mostrarán.</span>
                    </div>

                    <div class="fila-botones">
                        <button id="btn-login" class="boton" type="button">Ingresar</button>
                        <button class="boton claro" type="reset">Cancelar</button>
                    </div>

                    <div id="login-msg" class="alerta info mt-3">
                        Ingrese sus datos para acceder al sistema.
                    </div>
                </form>
            </section>

            <!-- Soft keys derecha-->
            <aside class="softcol">
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp">
                    <span class="flecha">›</span>Ayuda
                </button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp">
                    <span class="flecha">›</span>Soporte
                </button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp">
                    <span class="flecha">›</span>Salir
                </button>
            </aside>
        </div>

        <div class="ranura"></div>
    </section>
</main>

<script defer src="${pageContext.request.contextPath}/estilos/atm.js"></script>

<script>
    document.getElementById("btn-login").addEventListener("click", () => {
        const cuenta = document.getElementById("login-cuenta").value.trim();
        const pin = document.getElementById("login-pin").value.trim();
        const msg = document.getElementById("login-msg");

        if (!cuenta || !pin) {
            msg.className = "alerta peligro mt-3";
            msg.textContent = "Debe ingresar su número de cuenta y PIN.";
            return;
        }

        if (cuenta === "1001" && pin === "1234") {
            msg.className = "alerta ok mt-3";
            msg.textContent = "Acceso concedido. The redirigiendo...";
            setTimeout(() => {
                window.location.href = "${pageContext.request.contextPath}/deposit.jsp";
            }, 1000);
        } else {
            msg.className = "alerta peligro mt-3";
            msg.textContent = "Datos incorrectos. Intente nuevamente.";
        }
    });

</script>
</body>
</html>
