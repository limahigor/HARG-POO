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
        if ($(this).is(':checked')) {
            comorbidades.push($(this).attr('id'));
        }

        requestData['comorbidades'] = comorbidades;
    });
    
    if(id === 'medico'){
        console.log("TESTE servicos")
        $('.checkbox-list input[type=checkbox]').each(function() {
            if ($(this).is(':checked')) {
                servicos.push($(this).attr('id'));
            }
        });

        requestData['servicos'] = servicos;
    }   
    console.log(requestData);
  
    $.ajax({
        type: 'POST',
        url: id + '/cadastrar',
        data: JSON.stringify(requestData),
        contentType:'application/json',
        success: function(response){
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.text(response);
            elementoParagrafo.css({
                                    'color': 'rgb(19, 163, 0)',
                                 })
            elementoParagrafo.show();

            console.log(response);
        },
        error: function(error){
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.text(error);
            elementoParagrafo.css({
                                    'color': 'rgb(255, 0, 0)',
                                 })
            elementoParagrafo.show();

            console.log(error)
        }
    })
});