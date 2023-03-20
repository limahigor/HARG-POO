(function() {
    let d = document, 
        [inputs, knapp] = [
            d.querySelectorAll('[type="text"]'),
            d.querySelector('#submit-button')
        ];
    knapp.disabled = true;

    let radios = d.querySelectorAll('[type="radio"]');
    let dateInput = d.querySelector('#date');
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

            knapp.disabled = values.includes('');
            
            for(c = 0; c < radios.length; c++){
                console.log(!$(radios[c]).is(":checked"));
                knapp.disabled = !$(radios[c]).is(":checked");
            }
        })
    }

    $('input[type="radio"]').click(function(){
        knapp.disabled = !($('input[type="text"]').get().every((el) => el.value !== '') && $('input[type="radio"]').is(':checked') && $(dateInput).val() !== '');
    });

    dateInput.addEventListener('input', () => {
        knapp.disabled = !dateInput.checkValidity() || inputs[0].value === '' || inputs[1].value === '' || radios[0].checked === false && radios[1].checked === false;
    });

})();