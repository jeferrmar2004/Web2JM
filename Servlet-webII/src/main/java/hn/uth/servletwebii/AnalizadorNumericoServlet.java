package hn.uth.servletwebii;

import java.io.*;
import java.util.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/numeros")
public class AnalizadorNumericoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, NumberFormatException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();

        //JM - Bariables
        String s1 = req.getParameter("numero1");
        String s2 = req.getParameter("numero2");
        String s3 = req.getParameter("numero3");
        String lista = req.getParameter("lista"); // opción 3: N números para la moda

        //JM - Valores Repetido
        if (!esVacio(lista)) {
            try {
                List<Integer> numeros = aListaEnteros(lista); // lanza IllegalArgumentException si hay tokens inválidos
                if (numeros.isEmpty()) {
                    mostrarFormulario(out, req, "Escribe al menos un número en «Valor más repetido».", true);
                    return;
                }
                ResultadoModa r = calcularModa(numeros);

                inicioDocumento(out, "Analizador numérico — Moda");
                encabezado(out);
                out.println("<div class='contenido'><div class='grid'>");

                //JM - Respuesta repetidos
                out.println("<div class='panel'>");
                out.println("<h3>Resultado: Valor más repetido</h3>");
                out.println("<table>");
                out.println("<tr><th>Cantidad (N)</th><th>Entrada</th><th>Moda(s)</th><th>Frecuencia</th></tr>");
                out.println("<tr>");
                out.println("<td>" + numeros.size() + "</td>");
                out.println("<td>" + unir(numeros) + "</td>");
                out.println("<td>" + unir(r.modas) + "</td>");
                out.println("<td>" + r.vecesMax + "</td>");
                out.println("</tr>");
                out.println("</table>");

                // Chips (SIN rango, como pediste)
                out.println("<div class='chips'>");
                out.println("<span class='chip'>Valores únicos: " + r.frecuencias.size() + "</span>");
                out.println("</div>");

                out.println("<div class='acciones'>");
                out.println("<a class='btn fantasma' href='" + req.getContextPath() + "/numeros'>Nuevo cálculo</a>");
                out.println("<a class='btn' href='" + req.getContextPath() + "/menu'>Volver al menú</a>");
                out.println("</div>");
                out.println("</div>");

                // (Eliminada la tabla de frecuencias detallada)
                out.println("</div></div>");
                finDocumento(out);
                return;

            } catch (IllegalArgumentException e) {
                mostrarFormulario(out, req, e.getMessage(), true);
                return;
            }
        }

        //JM - validar mayor y menor que
        if (!esVacio(s1) && !esVacio(s2) && !esVacio(s3)) {
            try {
                int n1 = Integer.parseInt(s1.trim());
                int n2 = Integer.parseInt(s2.trim());
                int n3 = Integer.parseInt(s3.trim());

                int mayor = Math.max(n1, Math.max(n2, n3));
                int menor = Math.min(n1, Math.min(n2, n3));

                inicioDocumento(out, "Analizador numérico — Mayor y menor");
                encabezado(out);
                out.println("<div class='contenido'><div class='grid'>");

                out.println("<div class='panel'>");
                out.println("<h3>Resultado</h3>");
                out.println("<table>");
                out.println("<tr><th>Entrada</th><th>Mayor</th><th>Menor</th></tr>");
                out.println("<tr><td>" + n1 + " , " + n2 + " , " + n3 + "</td><td>" + mayor + "</td><td>" + menor + "</td></tr>");
                out.println("</table>");
                out.println("<div class='chips'>");
                out.println("<span class='chip'>Orden: " + vistaOrdenada(n1, n2, n3) + "</span>");
                out.println("<span class='chip'>Rango: " + (mayor - menor) + "</span>");
                out.println("</div>");
                out.println("<div class='acciones'>");
                out.println("<a class='btn fantasma' href='" + req.getContextPath() + "/numeros'>Nuevo cálculo</a>");
                out.println("<a class='btn' href='" + req.getContextPath() + "/menu'>Volver al menú</a>");
                out.println("</div>");
                out.println("</div>");

                out.println("<div class='panel'>");
                out.println("<h3>Descripción</h3>");
                out.println("<p>Se reciben tres números enteros y se obtiene el <b>mayor</b> y el <b>menor</b>.</p>");
                out.println("</div>");

                out.println("</div></div>");
                finDocumento(out);
                return;

            } catch (NumberFormatException e) {
                mostrarFormulario(out, req, "Ingresa solo números enteros válidos en «Mayor y menor».", false);
                return;
            }
        }

        //JM - Por si queda alguna carilla varcia
        mostrarFormulario(out, req, null, false);
    }

    // JM - imprimir pantalla

    private void inicioDocumento(PrintWriter out, String titulo) {
        out.println("<!DOCTYPE html><html lang='es'><head><meta charset='UTF-8'/>"
                + "<meta name='viewport' content='width=device-width, initial-scale=1'/>"
                + "<title>"+titulo+"</title>"
                + "<style>"
                + "*,*::before,*::after{box-sizing:border-box}"
                + "body{margin:0;font-family:Inter,Segoe UI,Arial,sans-serif;min-height:100vh;"
                + "display:flex;align-items:center;justify-content:center;"
                + "background:radial-gradient(1200px 600px at 10% 10%,#22d3ee22,transparent),"
                + "radial-gradient(1200px 600px at 90% 90%,#a78bfa22,transparent),"
                + "linear-gradient(135deg,#0ea5e9,#7c3aed) fixed}"
                + ".tarjeta{width:min(980px,95vw);background:#0b1020cc;backdrop-filter:blur(6px);"
                + "border-radius:20px;box-shadow:0 25px 60px rgba(0,0,0,.35);overflow:hidden;color:#e5e7eb}"
                + ".cabecera{padding:28px 32px;background:linear-gradient(90deg,#22d3ee,#3b82f6,#8b5cf6);color:white}"
                + ".titulo{margin:0;font-weight:800;letter-spacing:.3px;font-size:clamp(20px,3.2vw,28px)}"
                + ".sub{margin:6px 0 0;font-weight:600;opacity:.95}"
                + ".contenido{padding:26px 32px}"
                + ".grid{display:grid;grid-template-columns:1.15fr .85fr;gap:22px}"
                + ".panel{background:#0f172a;border:1px solid #334155;border-radius:16px;padding:22px}"
                + ".panel h3{margin:0 0 12px;color:#93c5fd;font-weight:700}"
                + "table{width:100%;border-collapse:collapse;border:1px solid #334155;overflow:hidden;border-radius:10px}"
                + "th,td{padding:12px 14px;border-bottom:1px solid #334155;text-align:center}"
                + "th{background:#1e293b;color:#f8fafc;font-weight:700}"
                + ".chips{display:flex;gap:10px;flex-wrap:wrap;margin-top:12px}"
                + ".chip{background:#111827;border:1px dashed #374151;color:#cbd5e1;padding:6px 10px;border-radius:999px;font-size:12px}"
                + ".acciones{display:flex;gap:12px;margin-top:18px;flex-wrap:wrap}"
                + ".btn{appearance:none;border:0;border-radius:12px;padding:12px 18px;background:#3b82f6;color:#fff;font-weight:800;cursor:pointer;transition:.2s;text-decoration:none;display:inline-block}"
                + ".btn:hover{transform:translateY(-2px);box-shadow:0 10px 20px rgba(59,130,246,.35)}"
                + ".btn.fantasma{background:transparent;border:1px solid #64748b;color:#e5e7eb}"
                + "label{display:block;margin:12px 0 6px;color:#cbd5e1;font-weight:600}"
                + "input,textarea{width:100%;padding:12px 14px;border-radius:12px;border:1px solid #475569;"
                + "background:#0b1220;color:#e5e7eb;outline:none} "
                + "input:focus,textarea:focus{border-color:#22d3ee;box-shadow:0 0 0 4px #22d3ee22}"
                + ".error{background:#7f1d1d;border:1px solid #fecaca;color:#fee2e2;padding:10px 12px;border-radius:12px;margin-bottom:10px}"
                + ".pista{color:#94a3b8;font-size:12px;margin-top:6px}"
                + ".links{display:flex;gap:12px;margin-top:12px;flex-wrap:wrap}"
                + ".link{display:inline-block;padding:10px 14px;border-radius:10px;border:1px solid #64748b;color:#e5e7eb;text-decoration:none}"
                + "</style>"
                + "<script>"
                + "function msjRequerido(e,msj){e.target.setCustomValidity('');if(!e.target.validity.valid){e.target.setCustomValidity(msj);} }"
                + "function limpiarMensaje(e){e.target.setCustomValidity('');}"
                + "// Validación cliente para la lista (moda)"
                + "function validarListaModa(){"
                + "  var t = document.getElementById('lista');"
                + "  if(!t){return true;}"
                + "  var raw = (t.value||'').trim();"
                + "  if(raw===''){ t.setCustomValidity('Escribe uno o más números enteros.'); t.reportValidity(); return false; }"
                + "  var tokens = raw.split(/[,\\s]+/).filter(Boolean);"
                + "  var malos=[];"
                + "  for(var i=0;i<tokens.length;i++){ if(!/^[-]?\\d+$/.test(tokens[i])) malos.push(tokens[i]); }"
                + "  if(malos.length>0){ t.setCustomValidity('No válido: ' + malos.join(', ')); t.reportValidity(); return false; }"
                + "  t.setCustomValidity(''); return true;"
                + "}"
                + "</script>"
                + "</head><body><div class='tarjeta'>");
    }

    private void encabezado(PrintWriter out) {
        out.println("<div class='cabecera'>");
        out.println("<h1 class='titulo'>Analizador numérico <span style='font-size:14px;font-weight:700;opacity:.95'>&mdash; Mayor/Menor y Moda</span></h1>");
        out.println("<p class='sub'>Jeferson Mauricio Martinez Ramos — Cuenta: 202310010648</p>");
        out.println("</div>");
    }

    private void finDocumento(PrintWriter out) {
        out.println("</div></body></html>");
    }

    //JM -  Formulario completo
    private void mostrarFormulario(PrintWriter out, HttpServletRequest req, String mensajeError, boolean enfocarModa) {
        inicioDocumento(out, "Analizador numérico — Formulario");
        encabezado(out);
        out.println("<div class='contenido'><div class='grid'>");

        //JM -  Mayor/Menor
        out.println("<div class='panel'>");
        out.println("<h3>Mayor y menor de 3 enteros</h3>");
        if (mensajeError != null && !enfocarModa) out.println("<div class='error'>" + mensajeError + "</div>");
        out.println("<form method='get' action='" + req.getContextPath() + "/numeros'>");
        out.println("<label for='numero1'>Primer número</label>");
        out.println("<input id='numero1' name='numero1' type='number' required placeholder='Ej. 1' "
                + "oninvalid=\"msjRequerido(event,'Este campo es obligatorio.')\" oninput='limpiarMensaje(event)'>");
        out.println("<label for='numero2'>Segundo número</label>");
        out.println("<input id='numero2' name='numero2' type='number' required placeholder='Ej. 2' "
                + "oninvalid=\"msjRequerido(event,'Este campo es obligatorio.')\" oninput='limpiarMensaje(event)'>");
        out.println("<label for='numero3'>Tercer número</label>");
        out.println("<input id='numero3' name='numero3' type='number' required placeholder='Ej. 3' "
                + "oninvalid=\"msjRequerido(event,'Este campo es obligatorio.')\" oninput='limpiarMensaje(event)'>");
        out.println("<button class='btn' type='submit'>Calcular mayor y menor</button>");
        out.println("</form>");
        out.println("</div>");

        //JM - repetidos
        out.println("<div class='panel'>");
        out.println("<h3>Valor más repetido (N enteros)</h3>");
        if (mensajeError != null && enfocarModa) out.println("<div class='error'>" + mensajeError + "</div>");
        out.println("<form method='get' action='" + req.getContextPath() + "/numeros' onsubmit='return validarListaModa()'>");
        out.println("<label for='lista'>Escribe N enteros separados por comas, espacios o saltos de línea</label>");
        out.println("<textarea id='lista' name='lista' rows='6' required "
                + "placeholder='Ej.: 1, 2, 3, 4, 5, 6, 7' "
                + "oninvalid=\"msjRequerido(event,'Escribe uno o más números enteros.')\" "
                + "oninput='limpiarMensaje(event)'></textarea>");
        out.println("<div class='pista'>Ejemplos válidos: <code>1, 2, 3, 4, 5, 6, 7</code> o <code>10 10 1 2 2 2 3</code></div>");
        out.println("<button class='btn' type='submit'>Calcular valor más repetido</button>");
        out.println("</form>");
        out.println("<div class='links'><a class='link' href='" + req.getContextPath() + "/menu'>Volver al menú</a></div>");
        out.println("</div>");

        out.println("</div></div>");
        finDocumento(out);
    }

    //JM - calculos
    private static boolean esVacio(String s) { return s == null || s.trim().isEmpty(); }

    private String vistaOrdenada(int a, int b, int c) {
        int[] v = {a,b,c};
        if (v[0] > v[1]) { int t=v[0]; v[0]=v[1]; v[1]=t; }
        if (v[1] > v[2]) { int t=v[1]; v[1]=v[2]; v[2]=t; }
        if (v[0] > v[1]) { int t=v[0]; v[0]=v[1]; v[1]=t; }
        return v[0] + " ≤ " + v[1] + " ≤ " + v[2];
    }

    private List<Integer> aListaEnteros(String texto) throws NumberFormatException {
        String[] partes = texto.trim().split("[,\\s]+"); // comas, espacios o saltos de línea
        List<Integer> lista = new ArrayList<>();
        List<String> invalidos = new ArrayList<>();

        for (String p : partes) {
            if (p.isEmpty()) continue;
            if (p.matches("-?\\d+")) {
                // es entero (permite negativos)
                lista.add(Integer.parseInt(p));
            } else {
                invalidos.add(p);
            }
        }

        if (!invalidos.isEmpty()) {
            // mensaje detallado para el usuario
            throw new IllegalArgumentException("Los siguientes valores no son enteros válidos: " + String.join(", ", invalidos));
        }
        return lista;
    }

    private ResultadoModa calcularModa(List<Integer> numeros) {
        LinkedHashMap<Integer,Integer> conteo = new LinkedHashMap<>();
        int max = 0;
        for (int n : numeros) {
            int c = conteo.getOrDefault(n, 0) + 1;
            conteo.put(n, c);
            if (c > max) max = c;
        }
        List<Integer> modas = new ArrayList<>();
        for (Map.Entry<Integer,Integer> e : conteo.entrySet()) {
            if (e.getValue() == max) modas.add(e.getKey());
        }
        return new ResultadoModa(max, modas, conteo);
    }

    private String unir(Collection<Integer> valores) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = valores.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }
        return sb.toString();
    }

    //JM - Respuestas repetidos
    private static class ResultadoModa {
        final int vecesMax;
        final List<Integer> modas;
        final LinkedHashMap<Integer,Integer> frecuencias;
        ResultadoModa(int vecesMax, List<Integer> modas, LinkedHashMap<Integer,Integer> frecuencias) {
            this.vecesMax = vecesMax;
            this.modas = modas;
            this.frecuencias = frecuencias;
        }
    }
}
