function desativarTudo(){
    $(".select2").prop("disabled", true);
    
    $("#submit-button").prop("disabled", true)
    
    $.each($(".information-paciente"), function(){
        $(this).hide();
    })

    $.each($(".information-service"), function(){
        $(this).hide();
    })

    $.each($(".information-medico"), function(){
        $(this).hide();
    })
    
    $.each($(".choose"), function(){
        $(this).css("visibility", "hidden");
    })

    $(".resumo-content").css("visibility", "hidden");

    $('#input-data').val('');
    $('#input-hora').val('0');

}

function showMedicos(profissionais){
    console.log(profissionais)
    $.each(profissionais, function(index, value){
        var data = {
            id: value.id,
            text: value.nome
        }
        if(!($('#selectMedicos').find("option[value='" + data.id + "']").length)){
            var newOption = new Option(data.text, data.id, false, false);
            $('#selectMedicos').append(newOption).trigger('change');
        }
    })

    $('#selectMedicos').prop('disabled', false);
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

                if(!($('#selectServices').find("option[value='" + data.id + "']").length)){
                    console.log("TESTANDO SERVICES INPUT")
                    var newOption = new Option(data.text, data.id, false, false);
                    $('#selectServices').append(newOption).trigger('change');
                }
            })

            $('.services-content').css("visibility", "visible");
            $('#selectServices').prop('disabled', false);
        },
        error: function(response){
            console.log(response)
        }
    });
}

function verifyDataHora(){
    if($('#input-data').val() !== '' && $('#input-hora')[0].selectedIndex > 0){
        $('#submit-button').prop('disabled', false);
        
        var nomePaciente = $('#nome-paciente').text()

        var selectData = $('#selectServices').select2('data');
        var nomeProcedimento = selectData[0].text
        
        selectData = $('#selectMedicos').select2('data');
        var nomeMedico = selectData[0].text

        var data = $('#input-data').val()
        var hora = $('#input-hora option:selected').text();

        console.log(nomePaciente + ' ' + nomeProcedimento + ' ' + nomeMedico + ' ' + data + ' ' + hora + ' teste')

        let resumoInformation = [$('#nome-paciente-resumo'), $('#nome-medico-resumo'), $('#nome-procedimento-resumo'),
                                 $('#data-resumo'), $('#hora-resumo')]

        resumoInformation[0].html(nomePaciente)
        resumoInformation[1].html(nomeMedico)
        resumoInformation[2].html(nomeProcedimento)
        resumoInformation[3].html(data)
        resumoInformation[4].html(hora)

        $('.resumo-content').css("visibility", "visible");

        var posicao = $("#submit-button").offset().top;
        $(".content-forms").animate({
            scrollTop: posicao
        }, 1000);
    }else{
        $('#submit-button').prop('disabled', true);
        $('.resumo-content').css("visibility", "hidden");
    }
}

$('#submit-button').click(function(event){
    event.preventDefault();
    console.log("TESTE SUBMIT")

    const form = $('#form-consulta');
    const formData = form.serializeArray();
    const requestData = {};

    $(formData).each(function(index, obj) {
        requestData[obj.name] = obj.value;
    });

    var hora = $('#input-hora option:selected').text();
    

    var selectData = $('#selectServices').select2('data');
    var idProcedimento = selectData[0].id
        
    selectData = $('#selectMedicos').select2('data');
    var idMedico = selectData[0].id

    requestData['hora'] = hora;
    requestData['procedimento'] = idProcedimento;
    requestData['medico'] = idMedico;

    console.log(JSON.stringify(requestData));
    $.ajax({
        url: '/appointments/agendar',
        method: 'POST',
        data: JSON.stringify(requestData),
        contentType: 'application/json',
        dataType: 'json',
        success: function(response){
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.text('Consulta agendada!!');
            elementoParagrafo.css({
                                    'color': 'rgb(19, 163, 0)',
                                 })
            elementoParagrafo.show();
        },
        error: function(response){
            var elementoParagrafo = $('#result-cadastro');

            elementoParagrafo.html('Erro ao agendar! Verifique os dados!');
            elementoParagrafo.css({
                                    'color': 'rgb(255, 0, 0)',
                                 })
            elementoParagrafo.show();
        }
    })
})

$('#input-hora').change(function(e){
    verifyDataHora()
})

$('#input-data').on('change', function(e){
    verifyDataHora()
})

$('#selectMedicos').on('select2:select', function(e){
    let medicoInformation = [$('#nome-medico'), $('#crn-medico')]
    var selectedOption = e.params.data
    console.log(selectedOption);

    var idMedico = selectedOption.id;

    $.ajax({
        url: '/medico/buscar/' + idMedico,
        method: 'GET',
        dataType: 'json',
        success: function(response){
            console.log(response)
            medicoInformation[0].html(response.nome)
            medicoInformation[1].html(response.crm)
            $.each($(".information-medico"), function(){
                $(this).show();
            })
            
            var today = new Date();

            var year = today.getFullYear();
            var month = today.getMonth() + 1;

            var minDate = year + '-' + (month < 10 ? '0' : '') + month + '-01';
            var maxDate = year + '-' + (month < 10 ? '0' : '') + month + '-31';

            $('#input-data').attr('min', minDate);
            $('#input-data').attr('max', maxDate);
            
            $('.data-hora-content').css("visibility", "visible")
            verifyDataHora()
        },
        error: function(response){
            console.log(response)
        }
    })
});

$('#selectServices').on('select2:select', function(e){
    let procedimentoInformation = [$('#nome-procedimento'), $('#preco-procedimento')]

    var selectedOption = e.params.data
    console.log(selectedOption);

    var idService = selectedOption.id;

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
            showMedicos(response.profissionais);
            verifyDataHora()
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

            desativarTudo();
        }
    })
});

$('document').ready(function(){
    desativarTudo()
});

$('.select2').select2({
    placeholder: 'Selecione uma opção',
    width : 'resolve',
    height: 'resolve'
});