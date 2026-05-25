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

    function workBlockHtml(data = {}) {
        return `
<div class="card resume-block p-3 mb-2">
  <button type="button" class="btn-close position-absolute top-0 end-0 m-2"
          onclick="this.closest('.resume-block').remove()"></button>
  <div class="row g-2">
    <div class="col-md-4">
      <label class="form-label small">${msg('workYears')}</label>
      <input type="number" name="work_years[]" class="form-control form-control-sm"
             value="${esc(String(data.years || ''))}"
             placeholder="${msg('workYearsPlaceholder')}" min="0">
    </div>
    <div class="col-md-8">
      <label class="form-label small">${msg('workCompany')}</label>
      <input type="text" name="work_company[]" class="form-control form-control-sm"
             value="${esc(data.company || '')}"
             placeholder="${msg('workCompany')}">
    </div>
    <div class="col-12">
      <label class="form-label small">${msg('workPosition')}</label>
      <input type="text" name="work_position[]" class="form-control form-control-sm"
             value="${esc(data.position || '')}"
             placeholder="${msg('workPosition')}">
    </div>
    <div class="col-12">
      <label class="form-label small">${msg('workResp')}</label>
      <textarea name="work_responsibilities[]" class="form-control form-control-sm"
                rows="2" placeholder="${msg('workRespPlaceholder')}">${esc(data.responsibilities || '')}</textarea>
    </div>
  </div>
</div>`;
    }

    function eduBlockHtml(data = {}) {
        const t = today();
        const degrees = [
            ['Bachelor',   msg('eduBachelor')],
            ['Master',     msg('eduMaster')],
            ['Specialist', msg('eduSpecialist')],
            ['Doctor',     msg('eduDoctor')],
            ['Secondary',  msg('eduSecondary')],
        ];
        const options = degrees.map(([val, label]) =>
            `<option value="${val}" ${data.degree === val ? 'selected' : ''}>${label}</option>`
        ).join('');

        return `
<div class="card resume-block p-3 mb-2">
  <button type="button" class="btn-close position-absolute top-0 end-0 m-2"
          onclick="this.closest('.resume-block').remove()"></button>
  <div class="row g-2">
    <div class="col-md-6">
      <label class="form-label small">${msg('eduInstitution')}</label>
      <input type="text" name="edu_institution[]" class="form-control form-control-sm"
             value="${esc(data.institution || '')}"
             placeholder="${msg('eduInstitution')}">
    </div>
    <div class="col-md-6">
      <label class="form-label small">${msg('eduProgram')}</label>
      <input type="text" name="edu_program[]" class="form-control form-control-sm"
             value="${esc(data.program || '')}"
             placeholder="${msg('eduProgram')}">
    </div>
    <div class="col-md-4">
      <label class="form-label small">${msg('eduStart')}</label>
      <input type="date" name="edu_start[]" class="form-control form-control-sm"
             value="${data.startDate || ''}" max="${t}">
    </div>
    <div class="col-md-4">
      <label class="form-label small">${msg('eduEnd')}</label>
      <input type="date" name="edu_end[]" class="form-control form-control-sm"
             value="${data.endDate || ''}" max="${t}">
    </div>
    <div class="col-md-4">
      <label class="form-label small">${msg('eduDegree')}</label>
      <select name="edu_degree[]" class="form-select form-select-sm">
        <option value="">${msg('eduDegreeSelect')}</option>
        ${options}
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
                            const errDiv = document.createElement('div');
                            errDiv.className = 'invalid-feedback';
                            errDiv.textContent = 'Дата окончания не может быть раньше даты начала';
                            endInput.after(errDiv);
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
        const { autoAddFirst = false, existingWork = [], existingEdu = [] } = options;

        if (existingWork.length > 0) {
            existingWork.forEach(w => {
                document.getElementById('workContainer')
                    .insertAdjacentHTML('beforeend', workBlockHtml(w));
            });
        } else if (autoAddFirst) {
            addWork();
        }

        if (existingEdu.length > 0) {
            existingEdu.forEach(e => {
                document.getElementById('eduContainer')
                    .insertAdjacentHTML('beforeend', eduBlockHtml(e));
            });
        } else if (autoAddFirst) {
            addEdu();
        }
        validateDates();
    }

    return { init, addWork, addEdu };
})();