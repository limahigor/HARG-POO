
function printDOMPrescricao(bodyId, dados, div){
    $('#' + div).empty()
    
    $.each(dados, function(index, value){
        if(bodyId === "pagina-paciente"){
            stringNome =  '<a href="" class="titulo link-prescricao" id="' + value.id + '"> Prescrição#'+ value.id + '</a>' +
                          '<h1 class="nomeTitle">Medico: <span class="nomeData">' + value.medico.nome + '</span></h1>'
        }
        
        var html = '<ul id="resultado-'+ div +'">' + 
                        '<li>' +
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
                     '</div>'
        })

        finalhtml =             '</div>' +
                            '</div>' +
                        '</li>'+
                    '</ul>'

        $('#' + div).append(html + mid + finalhtml)     
    })

}

function printDOM(bodyId, dados, div){
    $('#' + div).empty()
    $.each(dados, function(index, value){
        if(bodyId === "pagina-paciente"){
            stringNome = '<a href="" class="titulo link-exame" id="' + value.id + '">' + value.nomeProcedimento + '</a>' +
            '<h1 class="nomeTitle">Medico: <span class="nomeData">' + value.nomeMedico + '</span></h1>'
            
        }
        
            $('#' + div).append('<ul id="resultado-'+ div +'">' + 
                                    '<li>' +
                                        '<div class="resul">' +
                                            '<div class="info-gerais">' +
                                                    stringNome+
                                            '</div>' +
                                            '<div class="data-hora">' +
                                                '<h1 class="data">' + value.data + '</h1>' +
                                                '<h1 class="hora">' + value.hora + '</h1>' +
                                            '</div>' +
                                        '</div>' +
                                    '</li>' +
                                '</ul>'
                                );
    })
    

}

function ajaxRequisition(url, data, div){
    $('ul').empty();
    var bodyId = $('body').attr('id');

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
                printDOM(bodyId, dados, div)
            else
                printDOMPrescricao(bodyId, response, div)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}

function getUrl(metodo){
    var bodyId = $('body').attr('id');
    var url;

    url = "/paciente/" + metodo

    return url;
}

$(document).on('click', '.link-prescricao', function(event){
    event.preventDefault()

    $('.canto-esquerdo').prop("disabled", true);

    
        
});

$(document).on('click', '.link-exame', function(event){
    event.preventDefault()

    $('.canto-direito').prop("disabled", true);

    const el = event.target || event.srcElement;
    id = el.id;

    console.log(id)

    if ($('.select2').find("option[value='" + id + "']").length) {
        $('.select2').val(id).trigger('change');
    } else { 
        var newOption = new Option("Appointment#" + id.toString(), id, false, false);
        $('.select2').append(newOption).trigger('change');
    } 

    $(".select2 > option").prop("selected","selected");
    $(".select2").trigger("change");

});

// $('.select2').select2({
//     closeOnSelect: false
// });

/// TAB
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

$(document).ready(function(){
    $(".select2").select2({
        placeholder: "Click em uma opcao",
        allowClear: false
    });
})