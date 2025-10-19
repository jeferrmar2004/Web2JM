<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Depósito • Cajero UTH</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/atm.css">
    <style>.encabezado{display:none}</style>
</head>
<body>
<main class="contenedor">
    <section class="atm-bisel">
        <div class="atm-panel">

            <!-- Soft keys IZQUIERDA -->
            <aside class="softcol">
                <!-- Disparan acciones del formulario -->
                <button type="button" class="softkey" data-click="#btn-confirmar"><span class="flecha">›</span>Confirmar</button>
                <button type="button" class="softkey" data-reset="#form-deposito"><span class="flecha">›</span>Cancelar</button>

                <!-- Navegación -->
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp"><span class="flecha">›</span>Ayuda</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp"><span class="flecha">›</span>Menú</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp"><span class="flecha">›</span>Salir</button>
            </aside>

            <!-- Pantalla central -->
            <section class="pantalla">
                <h2 class="lcd-titulo">DEPÓSITO</h2>
                <p class="lcd-sub">Ingrese el monto que desea depositar.</p>

                <form id="form-deposito" class="formulario">
                    <div class="campo">
                        <label for="deposit-cuenta">Número de cuenta</label>
                        <input id="deposit-cuenta" class="entrada" type="text" placeholder="Ej.: 1001">
                    </div>

                    <div class="campo">
                        <label for="deposit-monto">Monto a depositar (L)</label>
                        <input id="deposit-monto" class="entrada" type="number" min="1" placeholder="0.00">
                    </div>

                    <div class="fila-botones">
                        <button id="btn-confirmar" class="boton" type="button">Confirmar</button>
                        <button class="boton claro" type="reset">Cancelar</button>
                    </div>

                    <div id="deposit-msg" class="alerta info mt-3">
                        Ingrese el número de cuenta y el monto para procesar el depósito.
                    </div>
                </form>
            </section>

            <!-- Soft keys DERECHA -->
            <aside class="softcol">
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/retirar.jsp">Retiro<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp">Consulta<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp">Ayuda<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp">Menú<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp">Salir<span class="flecha">‹</span></button>
            </aside>
        </div>

        <div class="ranura"></div>
    </section>
</main>

<script defer src="${pageContext.request.contextPath}/estilos/atm.js"></script>

<!-- Enlazador genérico de SOFT KEYS -->
<script>
    document.querySelectorAll('.softkey').forEach(btn=>{
        const href = btn.dataset.href;
        const clickSel = btn.dataset.click;
        const resetSel = btn.dataset.reset;

        if (href) {
            btn.addEventListener('click', ()=>{ window.location.href = href; });
        }
        if (clickSel) {
            btn.addEventListener('click', ()=>{
                const el = document.querySelector(clickSel);
                if (el) el.click();
            });
        }
        if (resetSel) {
            btn.addEventListener('click', ()=>{
                const form = document.querySelector(resetSel);
                if (form) form.reset();
            });
        }
    });
</script>
</body>
</html>
