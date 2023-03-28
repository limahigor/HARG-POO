$('#submit-button').click(function(event){
    event.preventDefault();

    const form = $('#cadastrar-servico');
    const formData = form.serializeArray();
    const requestData = {};
    
    $(formData).each(function(index, obj) {
        requestData[obj.name] = obj.value;
    });
    
    var selectTipo = $('#input-tipo option:selected').val();
    requestData['tipo'] = selectTipo;

    $.ajax({
        url: '/services/cadastrar',
        method: 'POST',
        data: JSON.stringify(requestData),
        contentType: 'application/json',
        success: function(response){
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.text('Cadastro realizado!!');
            elementoParagrafo.css({
                                    'color': 'rgb(19, 163, 0)',
                                 })
            elementoParagrafo.show();

            setTimeout(function() {
                window.location.href = '/' + id + '/' + response;
            }, 2000);

            console.log("TESTE EXITO")
            console.log(response)
        },
        error: function(response){
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.html('Erro ao cadastrar! Verifique os dados!');
            elementoParagrafo.css({
                                    'color': 'rgb(255, 0, 0)',
                                 })
            elementoParagrafo.show();

            console.log(response)
        }
    })
})

function verifyInputs(inputs){
    let values = []
    inputs.forEach(v => values.push(v.value))

    let checkInputs = values.includes('')

    return checkInputs || $('#input-tipo')[0].selectedIndex <= 0;
}


(function(){
    let d = document, 
        [inputs, knapp] = [
            d.querySelectorAll('[type="text"]'),
            d.querySelector('#submit-button'),
        ];

    knapp.disabled = true;

    for (i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('input',() => {
            let values = []

            inputs.forEach(v => values.push(v.value))
            
            knapp.disabled = verifyInputs(inputs)
        })
    }

    $('#input-tipo').change(function(e){
        if($('#input-tipo')[0].selectedIndex <= 0)
            knapp.disabled = false
        else
        knapp.disabled = verifyInputs(inputs)
    })

})();