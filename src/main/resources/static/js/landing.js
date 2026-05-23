'use strict';



function animateCounter(el, target, suffix, duration) {
    var steps    = 30;
    var interval = Math.floor(duration / steps);
    var step     = Math.ceil(target / steps);
    var current  = 0;

    var timerId = setInterval(function () {
        current += step;

        if (current >= target) {
            current = target;
            clearInterval(timerId);
        }

        el.textContent = current + suffix;
    }, interval);
}

window.addEventListener('load', function () {
    var counters = document.querySelectorAll('[data-counter]');

    for (var i = 0; i < counters.length; i++) {
        var el       = counters[i];
        var target   = parseInt(el.getAttribute('data-counter'));
        var suffix   = el.getAttribute('data-suffix')   || '';
        var duration = parseInt(el.getAttribute('data-duration') || '1200');

        animateCounter(el, target, suffix, duration);
    }
});