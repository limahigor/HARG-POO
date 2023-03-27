function desativarTudo(occultDivs, button){
    $(".select2").prop("disabled", true);

    button.disabled = true;

    $.each($(".information-paciente"), function(){
        $(this).hide();
    })

    $.each($(".choose"), function(index, div){
        $(this).hide();
    })


    $.each($(".select2"), function(index, div){
        $(this).empty();
    })

    $('.select2').select2({
        placeholder: 'Selecione uma opção',
        width : 'resolve',
        height: 'resolve'
    });
}

$('#selectServices').on('select2:select', function(e){
    let procedimentoInformation = [$('#nome-procedimento'), $('#preco-procedimento')]

    var selectedOption = e.params.data
    console.log(selectedOption);

    var idService = selectedOption.id;
    console.log(idService);

    $("#medicos-content").show();

    $.ajax({
        url: '/services/' + idService,
        method: 'GET',
        dataType: 'json',
        success: function(response){
            procedimentoInformation[0].html(response.nome)
            procedimentoInformation[1].html(response.valor)
            $.each($(".information-service"), function(){
                $(this).show();
            })
            console.log('teste id service')
            console.log(response)
        },
        error: function(response){
            console.log(response);
        }
    })
});

function showServices(){
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
                var newOption = new Option(data.text, data.id, false, false);
                $('#selectServices').append(newOption).trigger('change');
            })
            $('.services-content').show();
            $('#selectServices').prop('disabled', false);
        },
        error: function(response){
            console.log(response)
        }
    });
}

$('#cpf').on("keyup", function(event){
    var cpfPaciente, nomePaciente, idade
    let pacienteInformation = [$('#nome-paciente'), $('#idade-paciente'), $('#sexo-paciente')];

    cpfPaciente = event.target.value;

    console.log(cpfPaciente + ': ' + cpfPaciente.length)
    
    if(cpfPaciente.length != 11){
        desativarTudo(occultDivs, button)
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

            showServices();
        },
        error: function(response){
            var paragrafo = $('#nome-paciente');
            
            pacienteInformation[0].html(' não encontrado')
            pacienteInformation[0].css({
                'color': 'rgb(255, 0, 0)',
            })

            $.each($(".information-paciente"), function(index, div){
                div.style.disabled = "none";
            })

            $.each(occultDivs, function(index, div){
                div.style.disabled = "none";
            })
        }
    })
});

$('document').ready(function(){
    let medicoInformation = [$('#nome-medico'), $('#crm-medico')]
    
    let d = document,
                    [occultDivs, button] = [
                        d.querySelectorAll('.choose'),
                        d.querySelector('#submit-button')
                    ];
    
    $(".select2").prop("disabled", true);
    button.disabled = true;
});

$('.select2').select2({
    placeholder: 'Selecione uma opção',
    width : 'resolve',
    height: 'resolve'
});