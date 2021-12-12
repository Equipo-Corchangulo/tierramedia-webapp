<script type="text/javascript">
    var tipoPromoeElm = document.getElementById('tipoPromo');
    var procentaje = document.getElementById('porcentaje');
    var extra = document.getElementById('extra');
    var costoFinal = document.getElementById('costoFinal');


    function hideElement(elm) {
        elm.style.display = 'none';
    }
    function showElement(elm){
        elm.style.display = 'block';
    }

    function ocultarTodo(){
        hideElement(procentaje);
        hideElement(extra);
        hideElement(costoFinal);
    }

    function setPromoTipe (tipoPromo){
        ocultarTodo();
        switch(tipoPromo) {
            case"AXB":
            showElement(extra);
                break;
            case "PORCENTUAL":
            showElement(procentaje);
                break;
            case "ABSOLUTA":
                showElement(costoFinal);
                break;
        }
    }

    tipoPromoeElm.addEventListener('change', (event) => {
        setPromoTipe(event.target.value);
    });;



</script>

<script type="module">
    var atracciones = document.getElementById('lista');
    var salida = document.getElementById('hiddenlist');
    import Tags from "/tierramedia/assets/js/tags.js";
    let tagslist = Tags.init();
    function setAtraccionesValue(){
        tagslist.forEach(tag =>{
            if(tag.selectElement == atracciones){
                let values= tag.getSelectedValues();
                let valoresString = "";
                values.forEach((element, i)=> valoresString+= i>0?","+element:element);
                salida.value= valoresString;
                return;
            }
        })
    }
    atracciones.addEventListener('change',(event)=> {
        setAtraccionesValue()
    });

    window.onload = function() {
          setPromoTipe(tipoPromoeElm.value);
          setAtraccionesValue();
        };
</script>