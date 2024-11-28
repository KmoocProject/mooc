document.getElementById('search-button').addEventListener('click', function (e) {
    e.preventDefault();
    document.getElementById('search-modal').style.display = 'block';
});

document.getElementById('close-modal').addEventListener('click', function () {
    document.getElementById('search-modal').style.display = 'none';
});

window.addEventListener('click', function (e) {
    if (e.target.classList.contains('modal')) {
        document.getElementById('search-modal').style.display = 'none';
    }
});
