function moveDiv(ulDestino, liId){
    div = $('#' + liId).clone()
    $('#' + liId).remove()

    
    $('#' + ulDestino).append(div)

    $('#resultado-select').trigger('listaModificada');
}

function printDOMPrescricao(dados){
    $('#resultado-prescricoes').empty()
    
    $.each(dados, function(index, value){
        console.log(value)
        stringNome = '<a href="" class="titulo link-prescricao" id="' + value.id + '"> Prescrição#'+ value.id + '</a>' +
                     '<h1 class="nomeTitle">Medico: <span class="nomeData">' + value.medico.name + '</span></h1>'
        
        var html = '<li id="prescricao-'+ index +'">' +
                        '<div class="resul">' +
                            '<div class="info-gerais-prescricao">' +
                                stringNome +
                            '</div>' +
                        '<div class="medicamento-list">'

        var mid = ""
        
        $.each(value.medicamentos, function(index, med){
             mid += '<div class="medicamento">' +
                     '<h1 class="nome-medicamento">Nome: ' + med.nome + '</h1>' +
                     '<h1 class="intervalo-medicamento">Intervalo: ' + med.intervalo + ' horas</h1>' +
                     '<h1 class="valor-medicamento">Valor: R$ ' + parseFloat(med.valor).toFixed(2) + '</h1>' +
                     '</div>'
        })

        finalhtml =             '</div>' +
                            '</div>' +
                        '</li>'

        $('#resultado-prescricoes').append(html + mid + finalhtml)     
    })

}

function printDOM(dados){
    $('#resultado-consultas').empty()
    $.each(dados, function(index, value){
        stringNome = '<a href="" class="titulo link-exame" id="' + value.id + '">' + value.nomeProcedimento + '</a>' +
                     '<h1 class="nomeTitle">Medico: <span class="nomeData">' + value.nomeMedico + '</span></h1>'
        
        $('#resultado-consultas').append('<li id="orcamento-'+ index +'">' +
                                            '<div class="resul">' +
                                                '<div class="info-gerais">' +
                                                    stringNome +
                                                '</div>' +
                                                '<div class="data-hora">' +
                                                    '<h1 class="data">' + value.data + '</h1>' +
                                                    '<h1 class="hora">' + value.hora + '</h1>' +
                                                '</div>' +
                                            '</div>' +
                                        '</li>'
                                        );
    })
}

function ajaxRequisition(url, data, div){
    $('#resultado-consultas').empty();
    $('#resultado-prescricoes').empty();

    $.ajax({
        url: url,
        type: "GET",
        data: data,
        dataType: "json",
        success: function(response) {
            var dados = []
            var datas = {}

            if(div !== 'prescricao'){
                $.each(response, function (index, value) {
                    var dataResponse = {
                                    'id' : value.id,
                                    'nomeProcedimento' : value.servico.name,
                                    'nomePaciente' : value.paciente.name,
                                    'nomeMedico' : value.medico.name,
                                    'data' : value.data,
                                    'hora' : value.hora
                                }
                    
                    dados.push(dataResponse)
                });
            }

            if(div !== 'prescricao')
                printDOM(dados)
            else
                printDOMPrescricao(response)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}

function getUrl(metodo){
    var url;

    url = "/paciente/" + metodo

    return url;
}

function getData(){
    const data = {
        type: '',
        paciente: '',
        ids: []
    };

    data.paciente = $('.info-pacientes').attr('id');

    var elementosExame = $('#resultado-select').find('.link-exame');
    var elementosPrescicao = $('#resultado-select').find('.link-prescricao');

    if(elementosExame.length !== 0){
        $('#resultado-select .link-exame').each(function() {
            const id = $(this).attr('id');
            data.ids.push(id);
        });

        data.type = 'consultas'
    }else if(elementosPrescicao.length !== 0){
        $('#resultado-select .link-prescricao').each(function() {
            const id = $(this).attr('id');
            data.ids.push(id);
        });

        data.type = 'prescricao'
    }

    return data;
}

function verificarSelect(){
    var elementosSelect = $('#resultado-select').find('.link-exame');

    console.log("VERIFICANDO SELECT");
    console.log(elementosSelect.length);

    if(elementosSelect.length === 0){
        $('#submitButton').prop('disabled', true)
    }else{
        $('#submitButton').prop('disabled', false)
    }
}

$(document).on('click', '#submitButton', function(event){
    event.preventDefault()

    data = getData();

    $.ajax({
        url: '/orcamento/gerar',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType: 'text',
        success: function(response){
            alert('Orcamento gerado!!')
            window.location.href = '/'
        },
        error: function(jqXHR, textStatus, errorThrown){
            console.log("Status: ", textStatus);
            console.log("Erro lançado: ", errorThrown);
            console.log("Resposta do servidor: ", jqXHR.responseText);
        }
    })

});

$('#resultado-select').on('listaModificada', function(event) {
    if($('#resultado-select').children().length === 0){
        $('.canto-esquerdo').prop("disabled", false);
        $('.canto-direito').prop("disabled", false);
        $('#valor').html('00.00');
    }else{
        $('.canto-esquerdo').prop("disabled", true);
        $('.canto-direito').prop("disabled", true);

        data = getData();

        $.ajax({
            url: '/orcamento/valor-total',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json',
            dataType: 'json',
            success: function(response){
                const valorTotal = parseFloat(response).toFixed(2);
                $('#valor').html(valorTotal);
            },
            error: function(response){
                console.log(response)
            }
        })
    }
});

$(document).on('click', '.link-prescricao', function(event){
    event.preventDefault()

    const liId = $(this).closest('li').attr('id');
    const ulId = $(this).closest('ul').attr('id');

    if(ulId === "resultado-select"){
        moveDiv("resultado-prescricoes", liId)
        
    }else if(ulId === "resultado-prescricoes"){
        moveDiv("resultado-select", liId)
    }

    verificarSelect();
});

$(document).on('click', '.link-exame', function(event){
    event.preventDefault()

    const liId = $(this).closest('li').attr('id');
    const ulId = $(this).closest('ul').attr('id');
    
    if(ulId === "resultado-select"){
        moveDiv("resultado-consultas", liId)
        
    }else if(ulId === "resultado-consultas"){
        moveDiv("resultado-select", liId)
    }

    verificarSelect();
});

function openDiv(evt, tabAtual) {
    var i, tabcontent, tablinks;
  
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
  
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    if(tabAtual === 'prescricao')
        url = getUrl('prescricao')
    else{
        url = getUrl('appointments')
    }

    idPessoa = $('.info-pacientes').attr('id');

    data = {id : idPessoa, tipo : tabAtual}

    ajaxRequisition(url, data, tabAtual)

    document.getElementById(tabAtual).style.display = "block";
    evt.currentTarget.className += " active";
}

function displayNone(){
    var i, tabcontent;
  
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

}

$(document).ready(function() {
    console.log("PAGINA CARREGADA")
    verificarSelect();
});