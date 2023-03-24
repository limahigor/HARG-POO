function verifyInputs(inputs, radios, dateInput){
    let values = []
    inputs.forEach(v => values.push(v.value))

    let checkInputs = values.includes('')
    let checkRadio = (radios[0].checked === false) && (radios[1].checked === false)
    let checkData = !($(dateInput).val() !== '')

    return checkInputs || checkRadio || checkData;
}


(function(){
    let d = document, 
        [inputs, knapp, radios, dateInput] = [
            d.querySelectorAll('[type="text"]'),
            d.querySelector('#submit-button'),
            d.querySelectorAll('[type="radio"]'),
            d.querySelector('#date')
        ];

    knapp.disabled = true;

    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth()+1; //Janeiro é 0
    let yyyy = today.getFullYear();

    if(dd<10) dd='0'+dd;
    if(mm<10) mm='0'+mm;

    today = yyyy+'-'+mm+'-'+dd;
    dateInput.max = today;

    for (i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener('input',() => {
            let values = []

            inputs.forEach(v => values.push(v.value))
            
            knapp.disabled = verifyInputs(inputs, radios, dateInput)
        })
    }

    $('input[type="radio"]').click(function(){
        knapp.disabled = verifyInputs(inputs, radios, dateInput)
    });

    dateInput.addEventListener('input', () => {
        knapp.disabled = verifyInputs(inputs, radios, dateInput)
    });

})();