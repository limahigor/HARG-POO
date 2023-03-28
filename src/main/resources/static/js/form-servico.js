function verifyInputs(inputs){
    let values = []
    inputs.forEach(v => values.push(v.value))

    let checkInputs = values.includes('')

    return checkInputs || $('#input-tipo')[0].selectedIndex <= 0;
}


(function(){
    let d = document, 
        [inputs, knapp] = [
            d.querySelectorAll('[type="text"]'),
            d.querySelector('#submit-button'),
        ];

    knapp.disabled = true;

    for (i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('input',() => {
            let values = []

            inputs.forEach(v => values.push(v.value))
            
            knapp.disabled = verifyInputs(inputs)
        })
    }

    $('#input-tipo').change(function(e){
        if($('#input-tipo')[0].selectedIndex <= 0)
            knapp.disabled = false
        else
        knapp.disabled = verifyInputs(inputs)
    })

})();