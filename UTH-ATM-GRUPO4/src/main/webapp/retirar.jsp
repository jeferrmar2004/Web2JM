<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Retiro • Cajero UTH</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/atm.css">
    <style>.encabezado{display:none}</style>
</head>
<body>
<main class="contenedor">
    <section class="atm-bisel">
        <div class="atm-panel">

            <!-- SOFT KEYS IZQUIERDA -->
            <aside class="softcol">
                <button type="button" class="softkey" data-click="#btn-retirar"><span class="flecha">›</span>Confirmar</button>
                <button type="button" class="softkey" data-reset="#form-retiro"><span class="flecha">›</span>Cancelar</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp"><span class="flecha">›</span>Ayuda</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp"><span class="flecha">›</span>Menú</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp"><span class="flecha">›</span>Salir</button>
            </aside>

            <!-- PANTALLA CENTRAL -->
            <section class="pantalla">
                <h2 class="lcd-titulo">RETIRO</h2>
                <p class="lcd-sub">Ingrese su número de cuenta y el monto a retirar.</p>

                <form id="form-retiro" class="formulario">
                    <div class="campo">
                        <label for="retiro-cuenta">Número de cuenta</label>
                        <input id="retiro-cuenta" class="entrada" type="text" placeholder="Ej.: 1001">
                    </div>

                    <div class="campo">
                        <label for="retiro-monto">Monto a retirar (L)</label>
                        <input id="retiro-monto" class="entrada" type="number" min="1" placeholder="0.00">
                    </div>

                    <div class="fila-botones">
                        <button id="btn-retirar" class="boton" type="button">Confirmar</button>
                        <button class="boton claro" type="reset">Cancelar</button>
                    </div>

                    <div id="retiro-msg" class="alerta info mt-3">
                        Ingrese los datos solicitados para procesar su retiro.
                    </div>
                </form>
            </section>

            <!-- SOFT KEYS DERECHA -->
            <aside class="softcol">
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/deposit.jsp">Depósito<span class="flecha">‹</span></button>
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

<!-- SCRIPT PARA SOFT KEYS Y SIMULACIÓN -->
<script>
    // --- Soft keys dinámicos ---
    document.querySelectorAll('.softkey').forEach(btn=>{
        const href = btn.dataset.href;
        const clickSel = btn.dataset.click;
        const resetSel = btn.dataset.reset;

        if (href) btn.addEventListener('click', ()=>window.location.href = href);
        if (clickSel) btn.addEventListener('click', ()=>{ const el=document.querySelector(clickSel); if(el) el.click(); });
        if (resetSel) btn.addEventListener('click', ()=>{ const form=document.querySelector(resetSel); if(form) form.reset(); });
    });

</script>
</body>
</html>
