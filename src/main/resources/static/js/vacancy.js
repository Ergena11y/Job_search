'use strict';

const FILTER_KEY = 'vacancyFilter';

function saveFilter(filter) {
    localStorage.setItem(FILTER_KEY, JSON.stringify(filter));
}

function restoreFilter() {
    const raw = localStorage.getItem(FILTER_KEY);
    return raw ? JSON.parse(raw) : null;
}

function clearFilter() {
    localStorage.removeItem(FILTER_KEY);
}

document.getElementById('filter-form').addEventListener('submit', function (e) {
    const form = e.target;
    const formData = new FormData(form);
    const filter = Object.fromEntries(formData);
    saveFilter(filter);
});

document.getElementById('reset-filter').addEventListener('click', function () {
    clearFilter();
});


(function () {
    const params = new URLSearchParams(window.location.search);
    const hasFilterInUrl = params.has('search') || params.has('salaryMin') || params.has('expFrom');

    if (!hasFilterInUrl) {
        const saved = restoreFilter();
        if (saved && (saved.search || saved.salaryMin || saved.expFrom)) {
            const newParams = new URLSearchParams(saved);
            if (!newParams.has('sortBy') && params.has('sortBy')) {
                newParams.set('sortBy', params.get('sortBy'));
            }
            window.location.replace('/vacancies?' + newParams.toString());
        }
    }
})();