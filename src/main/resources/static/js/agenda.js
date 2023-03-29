function printDOM(){
    var data = '26/03/2022'
    var hora = '14:30'
    var nomePaciente = 'Higor de Lima'
    var nomeMedico = 'Manoel Gomes'
    var nomeProcedimento = 'Consulta Ginecologica'
    var stringNomeMedico
    var stringNomePaciente

    for (var i = 0; i < 15; i++) {
        var id = Math.random() * (100000 - 1) + 1;

        stringNomeMedico = '<h1 class="nomeTitle">Medico: <span class="nomeData">' + nomePaciente + '</span></h1>'
        stringNomePaciente = '<h1 class="nomeTitle">Paciente: <span class="nomeData">' + nomeMedico + '</span></h1>'

        $('#resultado').append('<li>' +
                                    '<div class="result">' +
                                        '<div class="info">' +
                                            '<h1 class="titulo">' + nomeProcedimento + '</h1>' +
                                            '<h1 class="nomeTitle">Medico: <span class="nomeData">' + nomePaciente + '</span></h1>' +
                                            '<h1 class="nomeTitle">Paciente: <span class="nomeData">' + nomeMedico + '</span></h1>' +
                                        '</div>' +
                                        '<div class="info-data-hora">' +
                                            '<h1 class="data">' + data + '</h1>' +
                                            '<h1 class="hora">' + hora + '</h1>' +
                                        '</div>' +
                                    '</div>' +
                                '</li>'
                                );
    }
}

$('document').ready(function(){
    $.ajax({
        url: '/services',
        method: 'GET',
        dataType: 'json',
        success: function(response){
            console.log(response)
            $.each(response, function(index, value){
                var data = {
                    id: value.id,
                    text: value.nome
                }

                if(!($('#selectServices').find("option[value='" + data.id + "']").length)){
                    var newOption = new Option(data.text, data.id, false, false);
                    $('#selectServices').append(newOption).trigger('change');
                }
            })
        },
        error: function(response){
            console.log(response)
        }
    })
})

$('document').ready(function(){
    $.ajax({
        url: '/services',
        method: 'GET',
        dataType: 'json',
        success: function(response){
            console.log(response)
            $.each(response, function(index, value){
                var data = {
                    id: value.id,
                    text: value.nome
                }

                if(!($('#selectServices').find("option[value='" + data.id + "']").length)){
                    var newOption = new Option(data.text, data.id, false, false);
                    $('#selectServices').append(newOption).trigger('change');
                }
            })
        },
        error: function(response){
            console.log(response)
        }
    })

    printDOM()
});

$('document').ready(function(){
    $.ajax({
        url: '/medico/buscar',
        data: {
            name: ''
        },
        method: 'GET',
        dataType: 'json',
        success: function(response){
            console.log(response)
            $.each(response, function(index, value){
                var data = {
                    id: value.id,
                    text: value.nome
                }

                if(!($('#selectMedicos').find("option[value='" + data.id + "']").length)){
                    var newOption = new Option(data.text, data.id, false, false);
                    $('#selectMedicos').append(newOption).trigger('change');
                }
            })
        },
        error: function(response){
            console.log(response)
        }
    })
});

$('#selectMedicos').select2({
    placeholder: 'Selecione um médico',
    width : 'resolve',
    height: 'resolve'
});

$('#selectServices').select2({
    placeholder: 'Selecione um médico',
    width : 'resolve',
    height: 'resolve'
});