(() => {
    const $ = (s, c=document) => c.querySelector(s);
    const $$ = (s, c=document) => Array.from(c.querySelectorAll(s));

    // Estado demo para ambas páginas (solo front)
    const KEY = "atm_demo_estado_sep";
    const state = load() || {
        cuentas: {
            "1001": { pin:"1234", saldo:1500.00 },
            "1002": { pin:"2222", saldo:300.25 },
            "1003": { pin:"9999", saldo:7800.00 }
        },
        ultimaOp: null
    };
    function load(){ try { return JSON.parse(localStorage.getItem(KEY)); } catch(e){ return null; } }
    function save(){ localStorage.setItem(KEY, JSON.stringify(state)); }

    // Helpers
    const toMonto = (str) => Number(String(str || "").replace(/[^\d.]/g, ""));
    const fmt = (n) => isFinite(n) ? new Intl.NumberFormat("es-HN",{minimumFractionDigits:2,maximumFractionDigits:2}).format(n) : "0.00";
    function setMsg(el, type, text){ if(!el) return; el.className = `alerta ${type}`; el.textContent = text; }

    // Inputs monto: sanitizar + formatear
    $$('input[data-amount]').forEach(inp=>{
        inp.addEventListener('input', ()=>{
            const pos = inp.selectionStart;
            const clean = inp.value.replace(/[^0-9.]/g,'');
            const parts = clean.split('.');
            inp.value = parts.length>2 ? parts.shift()+'.'+parts.join('') : clean;
            inp.setSelectionRange(pos,pos);
        });
        inp.addEventListener('change', ()=>{
            const n = toMonto(inp.value);
            if(isFinite(n)) inp.value = fmt(n);
        });
    });

    // Toggle ver PIN
    $$('.toggle-pin').forEach(btn=>{
        const target = btn.getAttribute('data-target');
        const inp = target ? $(target) : null;
        if(!inp) return;
        btn.addEventListener('click', ()=>{
            if(inp.type === 'password'){ inp.type='text'; btn.textContent='Ocultar PIN'; }
            else { inp.type='password'; btn.textContent='Ver PIN'; }
            inp.focus();
        });
    });

    // Teclados numéricos
    $$('.teclado').forEach(kp=>{
        const target = kp.getAttribute('data-target');
        const input = target ? $(target) : null;
        if(!input) return;
        kp.addEventListener('click', e=>{
            const key = e.target.closest('.key'); if(!key) return;
            const v = key.dataset.val || key.textContent.trim();
            if(v==='C') input.value = '';
            else if(v==='DEL') input.value = input.value.slice(0,-1);
            else if(v==='↵') input.dispatchEvent(new Event('change',{bubbles:true}));
            else input.value += v;
            input.focus();
        });
    });

    // Limitar teclado físico a números/punto si data-keyboard="numeric"
    $$('input[data-keyboard="numeric"]').forEach(inp=>{
        inp.addEventListener('keydown', e=>{
            const ok = ['Backspace','Tab','ArrowLeft','ArrowRight','Delete','Enter','.'];
            if(ok.includes(e.key)) return;
            if(!/^\d$/.test(e.key)) e.preventDefault();
        });
    });

    // --------- DEPÓSITO (deposit.jsp) ----------
    const depForm = $('#form-deposito');
    if(depForm){
        const depMsg = $('#dep-msg');
        depForm.addEventListener('submit', (e)=>{
            e.preventDefault();
            const cuenta = $('#dep-cuenta').value.trim();
            const pin = $('#dep-pin').value.trim();
            const monto = toMonto($('#dep-monto').value);
            if(!cuenta || !pin || !isFinite(monto) || monto<=0) return setMsg(depMsg,'peligro','Datos inválidos.');
            const acc = state.cuentas[cuenta]; if(!acc) return setMsg(depMsg,'peligro','La cuenta no existe.');
            if(acc.pin!==pin) return setMsg(depMsg,'peligro','PIN incorrecto.');
            acc.saldo = +(acc.saldo + monto).toFixed(2);
            state.ultimaOp = {tipo:'Depósito', cuenta, monto, saldo:acc.saldo, fecha:new Date().toISOString()};
            save();
            setMsg(depMsg,'ok',`Depósito exitoso. Saldo: L ${fmt(acc.saldo)}`);
            fillReceipt(state.ultimaOp);
            openModal();
            depForm.reset();
            renderTablaDemo(); // si hay tabla en index
        });
    }

    // --------- RETIRO (retirar.jsp) ----------
    const retForm = $('#form-retiro');
    if(retForm){
        const retMsg = $('#ret-msg');
        retForm.addEventListener('submit', (e)=>{
            e.preventDefault();
            const cuenta = $('#ret-cuenta').value.trim();
            const pin = $('#ret-pin').value.trim();
            const monto = toMonto($('#ret-monto').value);
            if(!cuenta || !pin || !isFinite(monto) || monto<=0) return setMsg(retMsg,'peligro','Datos inválidos.');
            const acc = state.cuentas[cuenta]; if(!acc) return setMsg(retMsg,'peligro','La cuenta no existe.');
            if(acc.pin!==pin) return setMsg(retMsg,'peligro','PIN incorrecto.');
            if(acc.saldo < monto) return setMsg(retMsg,'peligro','Saldo insuficiente.');
            acc.saldo = +(acc.saldo - monto).toFixed(2);
            state.ultimaOp = {tipo:'Retiro', cuenta, monto, saldo:acc.saldo, fecha:new Date().toISOString()};
            save();
            setMsg(retMsg,'ok',`Retiro exitoso. Saldo: L ${fmt(acc.saldo)}`);
            fillReceipt(state.ultimaOp);
            openModal();
            retForm.reset();
            renderTablaDemo();
        });
    }

    // --------- RECIBO (modal) ----------
    const modal = $('.modal');
    const openModal = ()=> modal?.classList.add('abierto');
    const closeModal = ()=> modal?.classList.remove('abierto');
    $('.modal .acciones .cerrar')?.addEventListener('click', closeModal);
    modal?.addEventListener('click', e=>{ if(e.target.classList.contains('modal')) closeModal(); });

    function fillReceipt(op){
        if(!op) return;
        const f = new Date(op.fecha);
        $('#rec-tipo')?.replaceChildren(document.createTextNode(op.tipo));
        $('#rec-cuenta')?.replaceChildren(document.createTextNode(op.cuenta));
        $('#rec-monto')?.replaceChildren(document.createTextNode(`L ${fmt(op.monto)}`));
        $('#rec-saldo')?.replaceChildren(document.createTextNode(`L ${fmt(op.saldo)}`));
        $('#rec-fecha')?.replaceChildren(document.createTextNode(f.toLocaleString('es-HN')));
    }

    // --------- Tabla demo (index opcional) ----------
    function renderTablaDemo(){
        const tb = $('#tabla-cuentas'); if(!tb) return;
        tb.innerHTML = Object.entries(state.cuentas)
            .map(([n,a])=>`<tr><td>${n}</td><td>L ${fmt(a.saldo)}</td></tr>`).join('');
    }
    renderTablaDemo(); // por si estamos en index.jsp
})();
