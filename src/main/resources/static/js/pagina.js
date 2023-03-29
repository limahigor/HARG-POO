function printDOM(bodyId, dados, div){
    $.each(dados, function(index, value){
        if(bodyId === "pagina-paciente"){
            stringNome = '<h1 class="nomeTitle">Medico: <span class="nomeData">' + value.nomeMedico + '</span></h1>'
        }else if(bodyId === "pagina-medico"){
            stringNome = '<h1 class="nomeTitle">Paciente: <span class="nomeData">' + value.nomePaciente + '</span></h1>'
        }
    
        $('#' + div).append('<li>' +
                                '<div class="resul">' +
                                    '<div class="info-gerais">' +
                                        '<a href="" class="titulo link-exame" id="' + value.id + '">' + value.nomeProcedimento + '</a>' +
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
    $('ul').empty();
    var bodyId = $('body').attr('id');

    $.ajax({
        url: url,
        type: "GET",
        data: data,
        dataType: "json",
        success: function(response) {
            var dados = []

            console.log(response)

            $.each(response, function (index, value) {
                var dataResponse = {
                                'id' : value.id,
                                'nomeProcedimento' : value.servico.name,
                                'nomePaciente' : value.paciente.name,
                                'nomeMedico' : value.medico.name,
                                'data' : value.data,
                                'hora' : value.hora
                            }
                
                console.log(dataResponse)
                dados.push(dataResponse)
            })

            printDOM(bodyId, dados, div)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}

function getUrl(){
    var bodyId = $('body').attr('id');
    var url;

    if(bodyId === "pagina-medico"){
        url = "/appointments/medico"
    }else if(bodyId === "pagina-paciente"){
        url = "/appointments/paciente"
    }

    return url;
}


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
    
    url = getUrl()

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