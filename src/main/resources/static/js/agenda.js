function printDOM(dados) {
    $('#resultado').empty()
    $.each(dados, function(index, data){
        $('#resultado').append('<li>' +
                                    '<div class="result">' +
                                        '<div class="info">' +
                                            '<h1 class="titulo">' + data.nomeProcedimento + '</h1>' +
                                            '<h1 class="nomeTitle">Medico: <span class="nomeData">' + data.nomeMedico + '</span></h1>' +
                                            '<h1 class="nomeTitle">Paciente: <span class="nomeData">' + data.nomePaciente + '</span></h1>' +
                                        '</div>' +
                                        '<div class="info-data-hora">' +
                                            '<h1 class="data">' + data.data + '</h1>' +
                                            '<h1 class="hora">' + data.hora + '</h1>' +
                                        '</div>' +
                                    '</div>' +
                                '</li>'
                                );
    })
}

function ajaxReq(url, dataGet){
    $.ajax({
        url: url,
        method: 'GET',
        data: dataGet,
        dataType: 'json',
        success: function (response) {
            var dados = []

            $.each(response, function (index, value) {
                var dataResponse = {
                                'nomeProcedimento' : value.servico.name,
                                'nomePaciente' : value.paciente.name,
                                'nomeMedico' : value.medico.name,
                                'data' : value.data,
                                'hora' : value.hora
                            }
                
                dados.push(dataResponse)
            })

            printDOM(dados)
        },
        error: function (response) {
            console.log(response)
        }
    })
}

$('document').ready(function(){
    var today = new Date();

    var year = today.getFullYear();
    var month = today.getMonth() + 1;

    var minDate = year + '-' + (month < 10 ? '0' : '') + month + '-01';
    var maxDate = year + '-' + (month < 10 ? '0' : '') + month + '-31';

    $('#input-data').attr('min', minDate);
    $('#input-data').attr('max', maxDate);

    ajaxReq('/agenda/geral', null)
})

$('document').ready(function () {
    $.ajax({
        url: '/services',
        method: 'GET',
        dataType: 'json',
        success: function (response) {
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
        error: function (response) {
            console.log(response)
        }
    })
});

$('document').ready(function () {
    var data = {
                 name: ''
               }

    $.ajax({
        url: '/medico/buscar',
        method: 'GET',
        data: data,
        dataType: 'json',
        success: function (response) {
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
        error: function (response) {
            console.log(response)
        }
    })
});

$('#selectMedicos').select2({
    placeholder: 'Selecione um médico',
    width: 'resolve',
    height: 'resolve'
});

$('#selectServices').select2({
    placeholder: 'Selecione um médico',
    width: 'resolve',
    height: 'resolve'
});

$('#selectServices').on('select2:select', function(e){
    $("#selectMedicos").prop("disabled", true);
    var selectedOption = e.params.data

    var idServico = selectedOption.id;
    console.log(idServico)

    var url;
    var data = {};

    if($('#input-data').val() === ''){
        console.log('data input not');
        url = '/agenda/servico'
        data = {
            id : idServico
        }
    }else{
        dataInput = $('#input-data').val();
        let partes = dataInput.split("-");

        diaInput = partes[2];

        url = '/agenda/servico/data'
        data = {
            id : idServico,
            dia : diaInput
        }
    }

    ajaxReq(url, data)
});

$('#selectMedicos').on('select2:select', function(e){
    $("#selectServices").prop("disabled", true);
    var selectedOption = e.params.data

    var idMedico = selectedOption.id;
    var url;
    var data = {};

    if($('#input-data').val() === ''){
        url = '/agenda/medico'
        data = {
            id : idMedico
        }
    }else{
        dataInput = $('#input-data').val();
        let partes = dataInput.split("-");

        diaInput = partes[2];

        url = '/agenda/medico/data'
        data = {
            id : idMedico,
            dia : diaInput
        }
    }

    ajaxReq(url, data)
});

$("#input-data").on('change', function(){
    valorData = $("#input-data").val();

    selectMedicos = $('#selectMedicos').select2('data');
    selectServices = $('#selectServices').select2('data');

    serviceId = selectServices[0].id
    medicoId = selectMedicos[0].id

    console.log('sid: ' + serviceId)
    console.log('mid: ' + medicoId)

    if(valorData === ''){
        if((medicoId === '0') && (serviceId === '0')){
            ajaxReq('/agenda/geral', null)
        }else{
            if(medicoId === 0 && serviceId !== 0){
                url = '/agenda/services'
                data = {id : serviceId};
            }else{
                url = '/agenda/medico'
                data = {id : medicoId};
            }
            
            ajaxReq(url, data);
        }
    }else{
        console.log('teste data não vazia1')

        dataInput = $('#input-data').val();
        let partes = dataInput.split("-");
        diaInput = partes[2];

        if((medicoId === '0') && (serviceId === '0')){
            console.log('teste data não vazia2')

            ajaxReq('/agenda/data', { dia : diaInput })
        }else{
            console.log('teste data não vazia3')

            if((medicoId === '0') && (serviceId !== '0')){
                url = '/agenda/servico/data'
                data = { id : serviceId, dia : diaInput };
            }else{
                url = '/agenda/medico/data'
                data = { id : medicoId, dia : diaInput };
            }
            
            ajaxReq(url, data);
        }
    }
});

$("#buttonReset").click(function(){
    $("#selectMedicos").prop("disabled", false);
    $("#selectServices").prop("disabled", false);

    var selectMedicos = $('#selectMedicos').select2()
    var selectServices = $('#selectServices').select2()
    var inputData = $('#input-data')

    selectMedicos.val('0').trigger('change')
    selectServices.val('0').trigger('change')
    inputData.val('0').trigger('change')
})