// Espera a que el DOM esté cargado
document.addEventListener("DOMContentLoaded", () => {

    /* ---------- SOFT KEYS ---------- */
    document.querySelectorAll(".softkey").forEach(btn => {
        const href = btn.dataset.href;
        const clickSel = btn.dataset.click;
        const resetSel = btn.dataset.reset;

        // Redirigir si tiene data-href
        if (href) {
            btn.addEventListener("click", () => window.location.href = href);
        }

        // Ejecutar click si tiene data-click
        if (clickSel) {
            btn.addEventListener("click", () => {
                const target = document.querySelector(clickSel);
                if (target) target.click();
            });
        }

        // Resetear formulario si tiene data-reset
        if (resetSel) {
            btn.addEventListener("click", () => {
                const form = document.querySelector(resetSel);
                if (form) form.reset();
            });
        }
    });


    /* ---------- TECLADO NUMÉRICO ---------- */
    const teclados = document.querySelectorAll(".teclado");
    teclados.forEach(teclado => {
        const targetSelector = teclado.dataset.target;
        const target = document.querySelector(targetSelector);

        if (!target) return;

        teclado.querySelectorAll(".key").forEach(tecla => {
            tecla.addEventListener("click", () => {
                const val = tecla.dataset.val;

                // Animación de pulsación
                tecla.classList.add("presionada");
                setTimeout(() => tecla.classList.remove("presionada"), 120);

                // Lógica del teclado
                if (val === "C") {
                    target.value = "";
                } else if (val === "DEL") {
                    target.value = target.value.slice(0, -1);
                } else if (!isNaN(val)) {
                    target.value += val;
                }
            });
        });
    });


    /* ---------- EFECTO DE BOTÓN AL PASAR ---------- */
    document.querySelectorAll(".boton, .softkey").forEach(btn => {
        btn.addEventListener("mousedown", () => btn.style.transform = "scale(0.97)");
        btn.addEventListener("mouseup", () => btn.style.transform = "scale(1)");
        btn.addEventListener("mouseleave", () => btn.style.transform = "scale(1)");
    });


    /* ---------- MENSAJE AUTOMÁTICO DE SALIDA ---------- */
    const salirBtns = document.querySelectorAll(".softkey, .boton");
    salirBtns.forEach(b => {
        if (b.textContent.toLowerCase().includes("salir")) {
            b.addEventListener("click", () => {
                alert("Gracias por usar el Cajero UTH. ¡Hasta pronto!");
            });
        }
    });

});
