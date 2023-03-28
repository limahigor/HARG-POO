function desativarTudo(){
    $(".select2").prop("disabled", true);
    
    $("#submit-button").disabled = true;
    
    $.each($(".information-paciente"), function(){
        $(this).hide();
    })
    
    $.each($(".choose"), function(){
        console.log('TESTE')
        $(this).css("visibility", "hidden");
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

function showMedicos(profissionais){
    console.log(profissionais)
    $.each(profissionais, function(index, value){
        var data = {
            id: value.id,
            text: value.nome
        }
        var newOption = new Option(data.text, data.id, false, false);
        $('#selectMedicos').append(newOption).trigger('change');
    })

    $('.medicos-content').css("visibility", "visible");
}

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

$('#selectServices').on('select2:select', function(e){
    let procedimentoInformation = [$('#nome-procedimento'), $('#preco-procedimento')]

    var selectedOption = e.params.data
    console.log(selectedOption);

    var idService = selectedOption.id;

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
            // console.log(response)
            showMedicos(response.profissionais);
        },
        error: function(response){
            console.log(response);
        }
    })
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
            pacienteInformation[0].html(' não encontrado')
            pacienteInformation[0].css({
                'color': 'rgb(255, 0, 0)',
            })

            // desativarTudo();
        }
    })
});

$('document').ready(function(){
    // let medicoInformation = [$('#nome-medico'), $('#crm-medico')]  
    desativarTudo()
    $(".select2").prop("disabled", true);
    $("#submit-button").disabled = true;
});

$('.select2').select2({
    placeholder: 'Selecione uma opção',
    width : 'resolve',
    height: 'resolve'
});