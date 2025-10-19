<%@ page contentType="text/html; charset=UTF-8" %>
<%

    String docente = request.getParameter("docente");
    if (docente == null || docente.isBlank()) docente = "Joseph Cooper"; // valor por defecto

    String grupo = request.getParameter("grupo");
    if (grupo == null || grupo.isBlank()) grupo = "ATMGrup4";

    java.util.List<String> integrantes = new java.util.ArrayList<>();

    String integrantesParam = request.getParameter("integrantes");
    if (integrantesParam != null && !integrantesParam.isBlank()) {
        for (String s : integrantesParam.split("\\s*;\\s*")) {
            if (!s.isBlank()) integrantes.add(s);
        }
    }

    for (int i = 1; i <= 20; i++) {
        String a = request.getParameter("alumno" + i);
        if (a != null && !a.isBlank()) integrantes.add(a);
    }

    // 3) Si no vino nada, usa tu lista por defecto (la que pegaste)
    if (integrantes.isEmpty()) {
        integrantes.add("1#. Jeferson Mauricio Martinez Ramos - 202310010648");
        integrantes.add("2#. Edwin Rene Torres Hernandez - 991051087");
        integrantes.add("3#. Axel David Rubio Vega - 202210050019");
        integrantes.add("4#. Bruno Alexander Cruz Perdomo - 202330010342");
        integrantes.add("5#. Ledin Noe Mendez Reyes - 202310060827");
        integrantes.add("6#. Kensy Rosio Rodriguez - 202120120079");
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cajero • Ayuda</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/atm.css">
    <style>.encabezado{display:none}</style>
</head>
<body>
<main class="contenedor">
    <section class="atm-bisel">
        <div class="atm-panel">
            <!-- SOFT KEYS IZQUIERDA (acciones) -->
            <aside class="softcol">
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp"><span class="flecha">›</span>Menú</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/deposit.jsp"><span class="flecha">›</span>Depósito</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/retirar.jsp"><span class="flecha">›</span>Retiro</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/help.jsp"><span class="flecha">›</span>Ayuda</button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp"><span class="flecha">›</span>Salir</button>
            </aside>

            <!-- PANTALLA CENTRAL -->
            <section class="pantalla">
                <h2 class="lcd-titulo">AYUDA Y CONTACTO</h2>
                <p class="lcd-sub">Información del proyecto y soporte técnico.</p>

                <div class="tarjeta">
                    <p><b>Proyecto:</b> Cajero Automático Simulado</p>
                    <p><b>Asignatura:</b> Programación Web 2</p>
                    <p><b>Universidad:</b> Universidad Tecnológica de Honduras (UTH)</p>
                    <p><b>Docente:</b> <%= docente %></p>
                    <p><b>Grupo:</b> <%= grupo %></p>

                    <p style="margin:12px 0 4px;"><b>Integrantes:</b></p>
                    <ul style="margin:6px 0 0 18px;">
                        <% for (String it : integrantes) { %>
                        <li><%= it %></li>
                        <% } %>
                    </ul>
                </div>

                <div class="tarjeta mt-4">
                    <h4 style="margin:0 0 8px;">Centro de Soporte</h4>
                    <p>Si experimenta errores o desea sugerir mejoras, puede contactar al equipo técnico UTH a través de:</p>
                    <p><b>Email:</b> soporte@uth.hn</p>
                    <p><b>Teléfono:</b> +504 0000-0000</p>
                    <p><b>Horario:</b> Lunes a Viernes, 8:00 a.m. – 5:00 p.m.</p>
                </div>
            </section>

            <!-- SOFT KEYS DERECHA (navegación) -->
            <aside class="softcol">
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/deposit.jsp">Depósito<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/retirar.jsp">Retiro<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp">Menú<span class="flecha">‹</span></button>
                <button type="button" class="softkey" data-href="${pageContext.request.contextPath}/index.jsp">Salir<span class="flecha">‹</span></button>
            </aside>
        </div>

        <div class="ranura"></div>
    </section>
</main>

<script defer src="${pageContext.request.contextPath}/estilos/atm.js"></script>
<script>

    // Conecta soft keys genéricamente
    document.querySelectorAll('.softkey').forEach(btn=>{
        const href = btn.dataset.href;
        if (href) btn.addEventListener('click', ()=> window.location.href = href);
    });
</script>
</body>
</html>
