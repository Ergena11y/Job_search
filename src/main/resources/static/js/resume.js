

const ResumeForm = (() => {



    function esc(str) {
        return String(str)
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;');
    }

    function today() {
        return (typeof RESUME_TODAY !== 'undefined')
            ? RESUME_TODAY
            : new Date().toISOString().slice(0, 10);
    }

    function msg(key) {
        return (typeof RESUME_MSG !== 'undefined' && RESUME_MSG[key]) ? esc(RESUME_MSG[key]) : key;
    }



    function workBlockHtml() {
        return `
<div class="card resume-block p-3 mb-2">
  <button type="button" class="btn-close position-absolute top-0 end-0 m-2"
          onclick="this.closest('.resume-block').remove()"></button>
  <div class="row g-2">
    <div class="col-md-4">
      <label class="form-label small">${msg('workYears')}</label>
      <input type="number" name="work_years[]" class="form-control form-control-sm"
             placeholder="${msg('workYearsPlaceholder')}" min="0">
    </div>
    <div class="col-md-8">
      <label class="form-label small">${msg('workCompany')}</label>
      <input type="text" name="work_company[]" class="form-control form-control-sm"
             placeholder="${msg('workCompany')}">
    </div>
    <div class="col-12">
      <label class="form-label small">${msg('workPosition')}</label>
      <input type="text" name="work_position[]" class="form-control form-control-sm"
             placeholder="${msg('workPosition')}">
    </div>
    <div class="col-12">
      <label class="form-label small">${msg('workResp')}</label>
      <textarea name="work_responsibilities[]" class="form-control form-control-sm"
                rows="2" placeholder="${msg('workRespPlaceholder')}"></textarea>
    </div>
  </div>
</div>`;
    }



    function eduBlockHtml() {
        const t = today();
        return `
<div class="card resume-block p-3 mb-2">
  <button type="button" class="btn-close position-absolute top-0 end-0 m-2"
          onclick="this.closest('.resume-block').remove()"></button>
  <div class="row g-2">
    <div class="col-md-6">
      <label class="form-label small">${msg('eduInstitution')}</label>
      <input type="text" name="edu_institution[]" class="form-control form-control-sm"
             placeholder="${msg('eduInstitution')}">
    </div>
    <div class="col-md-6">
      <label class="form-label small">${msg('eduProgram')}</label>
      <input type="text" name="edu_program[]" class="form-control form-control-sm"
             placeholder="${msg('eduProgram')}">
    </div>
    <div class="col-md-4">
      <label class="form-label small">${msg('eduStart')}</label>
      <input type="date" name="edu_start[]" class="form-control form-control-sm" max="${t}">
    </div>
    <div class="col-md-4">
      <label class="form-label small">${msg('eduEnd')}</label>
      <input type="date" name="edu_end[]" class="form-control form-control-sm" max="${t}">
    </div>
    <div class="col-md-4">
      <label class="form-label small">${msg('eduDegree')}</label>
      <select name="edu_degree[]" class="form-select form-select-sm">
        <option value="">${msg('eduDegreeSelect')}</option>
        <option value="Bachelor">${msg('eduBachelor')}</option>
        <option value="Master">${msg('eduMaster')}</option>
        <option value="Specialist">${msg('eduSpecialist')}</option>
        <option value="Doctor">${msg('eduDoctor')}</option>
        <option value="Secondary">${msg('eduSecondary')}</option>
      </select>
    </div>
  </div>
</div>`;
    }



    function validateDates() {
        const forms = document.querySelectorAll('#resumeForm, #editResumeForm');
        forms.forEach(form => {
            form.addEventListener('submit', function (e) {
                const blocks = form.querySelectorAll('.resume-block');
                let valid = true;

                blocks.forEach(block => {
                    const startInput = block.querySelector('[name="edu_start[]"]');
                    const endInput   = block.querySelector('[name="edu_end[]"]');
                    if (!startInput || !endInput) return;

                    const start = startInput.value;
                    const end   = endInput.value;

                    if (start && end && start > end) {
                        endInput.classList.add('is-invalid');
                        if (!endInput.nextElementSibling?.classList.contains('invalid-feedback')) {
                            const msg = document.createElement('div');
                            msg.className = 'invalid-feedback';
                            msg.textContent = 'Дата окончания не может быть раньше даты начала';
                            endInput.after(msg);
                        }
                        valid = false;
                    } else {
                        endInput.classList.remove('is-invalid');
                    }
                });

                if (!valid) e.preventDefault();
            });
        });
    }



    function addWork() {
        document.getElementById('workContainer').insertAdjacentHTML('beforeend', workBlockHtml());
    }

    function addEdu() {
        document.getElementById('eduContainer').insertAdjacentHTML('beforeend', eduBlockHtml());
    }

    function init(options = {}) {
        const { autoAddFirst = false } = options;
        if (autoAddFirst) {
            addWork();
            addEdu();
        }
        validateDates();
    }

    return { init, addWork, addEdu };
})();