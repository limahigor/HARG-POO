function formatarCPF(cpf) {
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d)/, "$1.$2");
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    return cpf;
}
  

function printDOM(list, bodyId){
    if(bodyId === "buscar-medico"){
        $.each(list, function(index, medico) {
            console.log("Nome do médico: " + medico.nome);
            console.log("Especialidade: " + medico.especializacao);
            console.log("CRM: " + medico.crm);
            $('#resultado').append('<li>' +
                                        '<div class="resul">' + 
                                                '<a id=' + '"' + medico.id + '" class="link-medico" ' + 'href="#"><h1 class="nome-medico">' + medico.nome + '</h1></a>' +
                                                '<p class="divisor"> - </p>' +
                                                '<h1 class="especialidade">' + medico.especializacao + '</h1>' +
                                                '<h1 class="crm">' + medico.crm + '</h1>' +
                                        '</div>' +
                                    '</li>'
                                    );
        });
    }else if(bodyId === "buscar-paciente"){
        $.each(list, function(index, paciente) {
            console.log("Nome do paciente: " + paciente.nome);
            console.log("CPF: " + paciente.cpf);
            $('#resultado').append('<li>' +
                                        '<div class="resul">' + 
                                                '<a id=' + '"' + paciente.id + '" class="link-paciente" ' + 'href="#"><h1 class="nome-paciente">' + paciente.nome + '</h1></a>' +
                                                '<h1 class="cpf">' + formatarCPF(paciente.cpf) + '</h1>' +
                                        '</div>' +
                                    '</li>'
                                    );
        });
    }
}

function ajaxRequisition(url, searchValue){
    $('ul').empty();
    var bodyId = $('body').attr('id');

    $.ajax({
        url: url,
        type: "GET",
        data: {
            name: searchValue
        },
        dataType: "json",
        success: function (data) {
            printDOM(data, bodyId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}

function getUrl(){
    var bodyId = $('body').attr('id');
    var url;

    if(bodyId === "buscar-medico"){
        url = "/medico/buscar"
    }else if(bodyId === "buscar-paciente"){
        url = "/paciente/buscar"
    }

    return url;
}

$('#inputBusca').on('input',() => {
    var valorInput = $('#inputBusca').val();
    console.log(valorInput);

    url = getUrl();

    if(valorInput === ""){
        ajaxRequisition(url, "")
    }else{
        ajaxRequisition(url, valorInput)
    }
});

$(document).on('click', '.link-paciente', function(event) {
    event.preventDefault(); // Impede que o link seja seguido

    var userId = $(this).attr('id');

    console.log("TESTE MEDICO: " + userId)
    url = '/paciente/' + userId;
    window.location.href = '/paciente/' + userId;

});

// $(document).on('click', '.link-paciente', function(event) {
//     event.preventDefault(); // Impede que o link seja seguido

//     var userId = $(this).attr('id');

//     $.ajax({
//         url: '/paciente/' + userId, // Endpoint para buscar as informações do usuário
//         type: 'GET',
//         dataType: 'json',
//         success: function(data) {
//             console.log(data)
//         },
//         error: function(xhr, status, error) {
//             console.error(error); // Registra o erro no console
//         }
//     });
// });

$(document).ready(function () {
    var bodyId = $('body').attr('id');
    var url = getUrl();

    ajaxRequisition(url, "")
});