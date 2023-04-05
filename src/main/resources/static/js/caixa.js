function statusCaixa(){
    $.ajax({
        url: '/caixa/status',
        type: 'GET',
        dataType: 'json',
        success: function(response){
            console.log(response)
            if(response.status === '1'){
                $("#abrir-caixa").prop("disabled", true);
                $("#fechar-caixa").prop("disabled", false);

                $("#status").html('ABERTO')
                $("#valor-atual").html(response.saldo)
                $("#data-abertura").html(response.dataAbertura)

            }else if(response.status === '0'){

                $("#status").html('FECHADO')
                $("#valor-atual").html('')
                $("#data-abertura").html('')
                $("#abrir-caixa").prop("disabled", false);
                $("#fechar-caixa").prop("disabled", true);

            }
        },
        error: function(response){
            console.log(response)
        }
    })
}

$(document).ready(function(){
    statusCaixa();
});

function printDOM(data){
    $.each(data, function(index, value){
        if(parseInt(value.status, 10) == 1) return;

        console.log(value)
        
        $('#list-caixas').append('<li id="caixa' + value.id + '">' +
                                    '<h1>Caixa #' + String(value.id).padStart(3, "0") +'</h1>' +
                                    '<h1>Saldo: R$ ' + value.saldo + '</h1>' +
                                    '<h1>Data Abertura: ' + value.dataAbertura + '</h1>' +
                                    '<h1>Data Fechamento: ' + value.dataFechamento + '</h1>' +
                                 '</li>'
                                );
    });
}

$(document).ready(function(){
    $.ajax({
        url: '/caixa/historico',
        type: 'GET',
        dataType: 'json',
        success: function(response){
            console.log(response)
            printDOM(response)
        },
        error: function(response){
            console.log(response)
        }
    })
});

$(document).on('click', "#fechar-caixa", function(event){
    $.ajax({
        url: '/caixa/fechar',
        type: 'POST',
        dataType: 'text',
        success: function(response){
            console.log(response)
            console.log('Caixa fechado!!');

            statusCaixa();
        },
        error: function(response){
            console.log("ERROR FECHAR")
            console.log(response)
        }
    })
})

$(document).on('click', "#abrir-caixa", function(event){
    $.ajax({
        url: '/caixa/abrir',
        type: 'POST',
        dataType: 'text',
        success: function(response){
            console.log(response)
            console.log('Caixa aberto!!');
            
            statusCaixa();
        },
        error: function(response){
            console.log("ERROR ABRIR")
            console.log(response)
        }
    })
})