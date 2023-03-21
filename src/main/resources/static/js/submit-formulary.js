function cadastrarFormulario(idFormulario){
    const form = $(`#${idFormulario}`);

    console.log(idFormulario);
  
    form.on('submit', function(event) {
        event.preventDefault(); 

        const formData = form.serializeArray();

        const requestData = {};
        $(formData).each(function(index, obj) {
            requestData[obj.name] = obj.value;
        });

        dataJSON = JSON.stringify(requestData)

        console.log(formData)
        console.log(requestData)
        console.log(dataJSON)

        if(idFormulario === "cadastrar-medico"){
            console.log("teste medico")
        }else{
            console.log("teste paciente")
        }

        $.ajax({
            type: 'POST',
            url: idFormulario + '-submit',
            data: JSON.stringify(requestData),
            contentType:'application/json',
            success: function(response){
                console.log(response);
            },
            error: function(error){
                console.log(error)
            }
        })

    });
}