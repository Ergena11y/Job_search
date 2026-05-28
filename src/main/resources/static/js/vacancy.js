'use strict';

const FILTER_KEY = 'vacancyFilter';

function saveFilter(filter) {
    try{localStorage.setItem(FILTER_KEY, JSON.stringify(filter));}catch (e){}
}

function restoreFilter() {
    try{ const raw = localStorage.getItem(FILTER_KEY);
        return raw ? JSON.parse(raw) : null;
    }catch (e){return null;}

}

function clearFilter() {
    try {
        localStorage.removeItem(FILTER_KEY);
    }catch (e){}
}

function debounce(fn, delay) {
    let timer;
    return function (...args) {
        clearTimeout(timer);
        timer = setTimeout(() => fn.apply(this, args), delay);
    };
}

function getFilterValues() {
    return {
        search: document.querySelector('[name="search"]').value.trim(),
        salaryMin: document.querySelector('[name="salaryMin"]').value,
        expFrom: document.querySelector('[name="expFrom"]').value,
        sortBy: document.querySelector('[name="sortBy"]').value,
    };
}

function buildVacancyCard(v) {
    const desc = v.description && v.description.length > 120
        ? v.description.substring(0, 120) + '...'
        : (v.description || '');
    const salary = v.salary != null ? Number(v.salary).toFixed(0) : '';
    const expFrom = v.expFrom != null ? v.expFrom : 0;
    const expTo = v.expTo != null ? v.expTo : 0;
    const date = v.formattedCreatedDate || '';

    return `
        <div class="card shadow-sm mb-3 w-100">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-start">
                    <div class="flex-grow-1">
                        <h5 class="card-title mb-1">
                            <a href="/vacancies/${v.id}" class="text-decoration-none text-dark fw-bold">
                                ${v.name || '-'}
                            </a>
                        </h5>
                        <p class="text-muted small mb-2">${desc}</p>
                        <p class="mb-0 small">
                            <span class="badge bg-light text-dark border me-2">
                                <i class="bi bi-briefcase me-1"></i>Опыт: ${expFrom} – ${expTo} лет
                            </span>
                            <span class="text-muted">
                                <i class="bi bi-calendar3 me-1"></i>${date}
                            </span>
                        </p>
                    </div>
                    <div class="text-end ms-3 text-nowrap">
                        <span class="text-success fw-bold fs-5">${salary} ⃀</span>
                    </div>
                </div>
                <div class="d-flex gap-2 mt-3">
                    <a href="/vacancies/${v.id}" class="btn btn-sm btn-outline-primary">
                        <i class="bi bi-eye me-1"></i>Подробнее
                    </a>
                </div>
            </div>
        </div>`;
}

function buildPagination(currentPage, totalPages) {
    if (totalPages <= 1) return '';
    let html = '<nav class="mt-4"><ul class="pagination justify-content-center">';

    html += currentPage === 0
        ? `<li class="page-item disabled"><a class="page-link" href="#">«</a></li>`
        : `<li class="page-item"><a class="page-link pagination-link" href="#" data-page="${currentPage - 1}">«</a></li>`;

    for (let p = 0; p < totalPages; p++) {
        html += `<li class="page-item ${p === currentPage ? 'active' : ''}">
            <a class="page-link pagination-link" href="#" data-page="${p}">${p + 1}</a>
        </li>`;
    }

    html += currentPage === totalPages - 1
        ? `<li class="page-item disabled"><a class="page-link" href="#">»</a></li>`
        : `<li class="page-item"><a class="page-link pagination-link" href="#" data-page="${currentPage + 1}">»</a></li>`;

    html += '</ul></nav>';
    return html;
}

async function fetchVacancies(page = 0) {
    const filter = getFilterValues();
    saveFilter(filter);

    const params = new URLSearchParams();
    if (filter.search)    params.set('search', filter.search);
    if (filter.salaryMin) params.set('salaryMin', filter.salaryMin);
    if (filter.expFrom)   params.set('expFrom', filter.expFrom);
    params.set('sortBy', filter.sortBy || 'date');
    params.set('page', page);
    params.set('size', 20);

    try {
        const res = await fetch('/vacancies/search?' + params.toString());
        const data = await res.json();
        const list = document.getElementById('vacancy-list');

        if (!data.vacancies || data.vacancies.length === 0) {
            list.innerHTML = '<div class="alert alert-info">Вакансии не найдены</div>';
            return;
        }

        let html = data.vacancies.map(buildVacancyCard).join('');
        html += buildPagination(data.currentPage, data.totalPages);
        list.innerHTML = html;

        list.querySelectorAll('.pagination-link').forEach(link => {
            link.addEventListener('click', function (e) {
                e.preventDefault();
                fetchVacancies(parseInt(this.dataset.page));
            });
        });

    } catch (err) {
        console.error('Ошибка поиска:', err);
    }
}

const liveSearch = debounce(() => fetchVacancies(0), 400);

document.querySelector('[name="search"]').addEventListener('input', liveSearch);
document.querySelector('[name="salaryMin"]').addEventListener('input', liveSearch);
document.querySelector('[name="expFrom"]').addEventListener('input', liveSearch);
document.querySelector('[name="sortBy"]').addEventListener('change', () => fetchVacancies(0));

document.getElementById('filter-form').addEventListener('submit', function (e) {
    e.preventDefault();
    fetchVacancies(0);
});

document.getElementById('reset-filter').addEventListener('click', function (e) {
    e.preventDefault();
    clearFilter();
    document.querySelector('[name="search"]').value = '';
    document.querySelector('[name="salaryMin"]').value = '';
    document.querySelector('[name="expFrom"]').value = '';
    document.querySelector('[name="sortBy"]').value = 'date';
    fetchVacancies(0);
});

(function () {
    const params = new URLSearchParams(window.location.search);
    const hasFilterInUrl = params.has('search') || params.has('salaryMin') || params.has('expFrom');

    if (!hasFilterInUrl) {
        const saved = restoreFilter();
        if (saved && (saved.search || saved.salaryMin || saved.expFrom)) {
            if (saved.search)    document.querySelector('[name="search"]').value = saved.search;
            if (saved.salaryMin) document.querySelector('[name="salaryMin"]').value = saved.salaryMin;
            if (saved.expFrom)   document.querySelector('[name="expFrom"]').value = saved.expFrom;
            if (saved.sortBy)    document.querySelector('[name="sortBy"]').value = saved.sortBy;
            fetchVacancies(0);
        }
    }
})();