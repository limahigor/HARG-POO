// SOLICITAÇÃO AJAX
function printDOM(bodyId, div){
    var data = '26/03/2022'
    var hora = '14:30'
    var nome = 'Higor de Lima'
    var nomeProcedimento
    var stringNome

    if(div === 'list-exames')
        nomeProcedimento = 'Hemograma'
    else if(div === 'list-consultas')
        nomeProcedimento = 'Consulta Ginecologista'
    else if(div === 'list-procedimentos')
        nomeProcedimento = 'Botox Labial'
    else if(div === 'list-prescricoes')
        nomeProcedimento = 'Prescrição Paracetamol'

    for (var i = 0; i < 9; i++) {
        var id = Math.random() * (100000 - 1) + 1;


        if(bodyId === "pagina-paciente"){
            stringNome = '<h1 class="nomeTitle">Medico: <span class="nomeData">' + nome + '</span></h1>'
        }else if(bodyId === "pagina-medico"){
            stringNome = '<h1 class="nomeTitle">Paciente: <span class="nomeData">' + nome + '</span></h1>'
        }

        $('#' + div).append('<li>' +
                                    '<div class="resul">' +
                                        '<div class="info-gerais">' +
                                            '<a href="" class="titulo link-exame" id="' + id + '">' + nomeProcedimento + '</a>' +
                                            stringNome +
                                        '</div>' +
                                        '<div class="data-hora">' +
                                            '<h1 class="data">' + data + '</h1>' +
                                            '<h1 class="hora">' + hora + '</h1>' +
                                        '</div>' +
                                    '</div>' +
                                '</li>'
                                );
    }
}

//     $.each(list, function(index, procedimento) {

//         if(bodyId === "pagina-paciente"){
//             stringNome = '<h1 class="nomeTitle">Medico: <span class="nomeData">' + nome + '</span></h1>'
//         }else if(bodyId === "pagina-medico"){
//             stringNome = '<h1 class="nomeTitle">Paciente: <span class="nomeData">' + nome + '</span></h1>'
//         }

//         $(div).append('<li>' +
//                                     '<div class="resul">' +
//                                         '<div class="info-gerais">' +
//                                             '<a href="" class="titulo link-exame">' + nomeProcedimento + '</a>' +
//                                             stringNome +
//                                         '</div>' +
//                                         '<div class="data-hora">' +
//                                             '<h1 class="data">' + data + '</h1>' +
//                                             '<h1 class="hora">' + hora + '</h1>' +
//                                         '</div>' +
//                                     '</div>' +
//                                 '</li>'
//                                 );

//     });

// function ajaxRequisition(url, searchValue){
//     $('ul').empty();
//     var bodyId = $('body').attr('id');

//     $.ajax({
//         url: url,
//         type: "GET",
//         data: {
//             name: searchValue
//         },
//         dataType: "json",
//         success: function (data) {
//             printDOM(data, bodyId);
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             console.error(textStatus, errorThrown);
//         }
//     });
// }

// function getUrl(){
//     var bodyId = $('body').attr('id');
//     var url;

//     if(bodyId === "buscar-medico"){
//         url = "/medico/buscar"
//     }else if(bodyId === "buscar-paciente"){
//         url = "/paciente/buscar"
//     }

//     return url;
// }

// $(document).ready(function () {
//     var bodyId = $('body').attr('id');
//     var url = getUrl();

//     ajaxRequisition(url, "")
// });


/// TAB
function openDiv(evt, cityName) {
    var bodyId = $('body').attr('id');

    var i, tabcontent, tablinks;
  
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
  
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    printDOM(bodyId, cityName);

    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

function displayNone(){
    var i, tabcontent;
  
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

}