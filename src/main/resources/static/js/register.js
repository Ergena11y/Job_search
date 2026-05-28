'use strict';

(function () {
    var select       = document.getElementById('accountTypeSelect');
    var nameLabel    = document.getElementById('nameLabel');
    var surnameBlock = document.getElementById('surnameBlock');
    var ageBlock     = document.getElementById('ageBlock');

    if (!select || !nameLabel) return;

    function updateForm() {
        var isEmployer = select.value === 'EMPLOYER';


        nameLabel.textContent = select.dataset[isEmployer ? 'labelEmployer' : 'labelApplicant'];

        surnameBlock.style.display = isEmployer ? 'none' : '';
        ageBlock.style.display     = isEmployer ? 'none' : '';

        surnameBlock.querySelector('input').required = !isEmployer;
        ageBlock.querySelector('input').required     = !isEmployer;
    }

    select.addEventListener('change', updateForm);
    updateForm();
})();