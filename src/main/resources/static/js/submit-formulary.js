$('#submit-button').click(function(event){
    var id = $(this).closest('form').attr('id');
    console.log(id);

    event.preventDefault();

    const form = $(`#${id}`);
    const formData = form.serializeArray();
    var comorbidades = [];
    var servicos = [];
    const requestData = {};

    $(formData).each(function(index, obj) {
        requestData[obj.name] = obj.value;
    });

    $('.campo-comorbidade input[type=checkbox]').each(function() {
        if($(this).is(':checked')){
            var data = {
                            id: $(this).attr('id'),
                            value: 'true'
                        }
        }else{
            var data = {
                            id: $(this).attr('id'),
                            value: 'false'
                        }
        }

        comorbidades.push(data);
    });

    requestData['comorbidades'] = comorbidades;
    
    if(id === 'medico'){
        console.log("TESTE servicos")
        $('.checkbox-list input[type=checkbox]').each(function() {
            if ($(this).is(':checked')) {
                servicos.push($(this).attr('id'));
            }
        });

        requestData['servicos'] = servicos;
    }
  
    $.ajax({
        type: 'POST',
        url: id + '/cadastrar',
        data: JSON.stringify(requestData),
        contentType:'application/json',
        success: function(response){
            console.log('Cadastro realizado!!');
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.text('Cadastro realizado!!');
            elementoParagrafo.css({
                                    'color': 'rgb(19, 163, 0)',
                                 })
            elementoParagrafo.show();

            setTimeout(function() {
                window.location.href = '/' + id + '/' + response;
            }, 2000);
        },
        error: function(error){
            var elementoParagrafo = $('#result-cadastro');

            response = error.responseJSON;

            elementoParagrafo.html(response.message);
            elementoParagrafo.css({
                                    'color': 'rgb(255, 0, 0)',
                                 })
            elementoParagrafo.show();
        }
    })
});