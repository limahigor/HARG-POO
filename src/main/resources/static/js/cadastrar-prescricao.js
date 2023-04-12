function desativarTudo(){
    $(".select2").prop("disabled", true);
    
    $("#submit-button").prop("disabled", true)
    $("#more-button").prop("disabled", true)

    $.each($(".information-medico"), function(){
        $(this).hide();
    })

    $('.result').css("visibility", "hidden");
    
    $.each($(".choose"), function(){
         $(this).css("visibility", "hidden");
    })
}

function showMedicos(){
    $.ajax({
        url: '/medico/buscar',
        data: {name : ''},
        method: 'GET',
        dataType: 'json',
        success: function(response){
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

            $('.services-content').css("visibility", "visible");
            $('#selectMedicos').prop('disabled', false);
        },
        error: function(response){
            console.log(response)
        }
    });
}

$("#selectMedicos").change(function() {
    if($(selectMedicos).val() > 0){
        $('.choose').css("visibility", "visible");  
    }
 }); 

$('#cpf').on("keyup", function(event){
    var cpfPaciente, nomePaciente, idade
    let pacienteInformation = [$('#nome-paciente'), $('#idade-paciente'), $('#sexo-paciente')];

    cpfPaciente = event.target.value;

    console.log(cpfPaciente + ': ' + cpfPaciente.length)
    
    if(cpfPaciente.length != 11){
        pacienteInformation[0].html(' CPF inválido!')
        desativarTudo()
        return;
    }

    $.ajax({
        url: '/paciente/buscar',
        method: 'GET',
        data: {
            name: cpfPaciente
        },
        dataType: 'json',
        success: function(response){
            console.log(response)

            nomePaciente = response[0].nome
            sexo = response[0].sexo
            idade = response[0].idade

            $('.cpf-input').attr('id', response[0].id)

            console.log(nomePaciente + ' ' + sexo + ' ' + idade)
            
            pacienteInformation[0].html(nomePaciente)
            pacienteInformation[0].css({
                                        'color': 'rgb(0, 0, 0)',
                                       })

            pacienteInformation[1].html(idade);
            pacienteInformation[2].html(sexo);

            $.each($(".information-paciente"), function(){
                $(this).show();
            })

            $(".select2").prop("disabled", false);
            $(".select2").css("visibility", "visible");

            showMedicos();
        },
        error: function(response){
            pacienteInformation[0].html(' não encontrado')
            pacienteInformation[0].css({
                'color': 'rgb(255, 0, 0)',
            })

            desativarTudo();
        }
    })
});

$('document').ready(function(){
    desativarTudo()
});

$(document).on('input', '.receita', function(){
    inputs = document.querySelectorAll('.receita')
    let values = []
    inputs.forEach(v => values.push(v.value))

    if(!values.includes('')){
        $("#submit-button").prop("disabled", false)
        $("#more-button").prop("disabled", false)
    }else{
        $("#submit-button").prop("disabled", true)
        $("#more-button").prop("disabled", true)
    }
});

$('#more-button').click(function(event){
    event.preventDefault();

    divId = $('.consulta-content').attr('id');
    let partes = divId.split("-");
    number = parseInt(partes[1]);

    var novaDiv = $('.consulta-content').clone();
    var nome = 'medicamento-' + (number + 1).toString();

    novaDiv.attr('id', nome)
    
    $('#container').append('<div class="consulta-content" id="' + nome + '">' +
                                '<label for="remedio">REMEDIO</label>'+
                                '<input class="receita nome" type="text" name="remedio">'+
                                '<label for="intervalo">INTERVALO</label>'+
                                '<input class="receita intervalo" type="text" name="intervalo">'+
                                '<label for="valor">VALOR</label>'+
                                '<input class="receita valor" type="text" name="valor">'+
                            '</div>'
                          )

    var posicao = $("#submit-button").offset().top;
    $(".content-forms").animate({
        scrollTop: posicao
    }, 1000);
})

$('#submit-button').click(function(event){
    event.preventDefault();
    inputsNome = document.querySelectorAll('.nome')
    inputsIntervalo = document.querySelectorAll('.intervalo')
    inputsValor = document.querySelectorAll('.valor')

    dados = {}
    dadosMedicamentos = []

    dados['paciente'] = $('.cpf-input').attr('id')
    dados['medico'] = $('#selectMedicos').val()

    for(var c = 0; c < inputsNome.length; c++){
        data = {
                nome : inputsNome[c].value,
                intervalo : inputsIntervalo[c].value,
                valor : inputsValor[c].value
               }
        dadosMedicamentos.push(data)
    }

    dados['medicamentos'] = dadosMedicamentos
    
    console.log("TESTE")
    $.ajax({
        url: '/prescricao/cadastrar',
        method: 'POST',
        data: JSON.stringify(dados),
        contentType: 'application/json',
        dataType: 'json',
        success: function(response){
            console.log("TESTE SUCESS")
            console.log(response)
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.text("Prescrição gerada com sucesso!!");
            elementoParagrafo.css({
                                    'color': 'rgb(19, 163, 0)',
                                  })

            $('.result').css("visibility", "visible");
            $('#submit-button').prop("disabled", true)
            $('#more-button').prop("disabled", true)
        },
        error: function(response){
            console.log("TESTE ERROR")
            console.log(response)
            var elementoParagrafo = $('#result-cadastro');

            error = response.responseJSON;

            elementoParagrafo.html(error.message);
            elementoParagrafo.css({
                                    'color': 'rgb(255, 0, 0)',
                                 })
            $('.result').css("visibility", "visible");
        }
    });
})